package edu.utah.arashtadjiki.keyboard;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.util.MidiProcessor;

import csnd6.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout _rootLayout;
    private EditText pitch;
    private EditText duration;
    static final int pitchLevel = 10;
    static final int velocityLevel = 100;
    static final int durationLevel = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent midiIntent = new Intent(this, KeyboardActivity.class);
        startActivity(midiIntent);


    }

    public void doLeff(){
        _rootLayout = new LinearLayout(this);
        _rootLayout.setBackgroundColor(Color.BLACK);

        Button playButton = new Button(this);
        playButton.setBackgroundColor(Color.BLACK);
        playButton.setTextSize(30);
        playButton.setTextColor(Color.WHITE);
        playButton.setText("Play");
        playButton.setOnClickListener(this);


        _rootLayout.setOrientation(LinearLayout.VERTICAL);
        _rootLayout.addView(playButton, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        setContentView(_rootLayout);
    }


    @Override
    public void onClick(View v) {

        csoundTest();
    }

    public void csoundTest(){

        Csound csound = new Csound();
        CsoundMidiInputStream inputStream = new CsoundMidiInputStream(csound);
        inputStream.SendNoteOn(3,6,127);
        CsoundMidiOutputStream outputStream = new CsoundMidiOutputStream(csound);
        CsoundArgVList args = new CsoundArgVList();
        args.argv();
       // outputStream.EnableMidiOutput();

    }

    public MIDI_Note createNote(int channel, int pitch, int velocity){

        byte[] buffer = new byte[32];
        int numBytes = 0;
        buffer[numBytes++] = (byte)(0x90 + (channel - 1)); // note on
        buffer[numBytes++] = (byte)pitch; // pitch is middle C
        buffer[numBytes++] = (byte)velocity; // max velocity
        int offset = 0;

        MIDI_Note note = new MIDI_Note();
        note.buffer = buffer;
        note.offset = offset;
        note.numBytes = numBytes;
        return note;

    }

    public class MIDI_Note{
        public byte[] buffer;
        public int offset;
        public int numBytes;
    }

    public MidiProcessor midiTest(){

        MidiTrack midiTrack = new MidiTrack();
        midiTrack.insertNote(1, pitchLevel, 1000, 1000, durationLevel);
        MidiFile midiFile = new MidiFile();
        midiFile.addTrack(new MidiTrack());
        return new MidiProcessor(midiFile);


    }


}

