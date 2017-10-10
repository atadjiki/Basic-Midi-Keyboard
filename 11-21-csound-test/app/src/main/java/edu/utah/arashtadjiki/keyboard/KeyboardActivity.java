package edu.utah.arashtadjiki.keyboard;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import org.billthefarmer.mididriver.MidiDriver;

/**
 * Created by AlleyCat on 11/22/2016.
 *
 * Based on the MIDI Driver for Sonivox EAS Library
 * https://github.com/billthefarmer/mididriver
 */
public class KeyboardActivity extends Activity implements View.OnKeyListener, View.OnTouchListener, MidiDriver.OnMidiStartListener, SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private MidiDriver midiDriver;
    private int[] config;
    private int octave = MidiDefinitions.OCTAVE_DEFAULT;
    private boolean sustain = false;
    private boolean portamento = false;

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        midiDriver = new MidiDriver();
        midiDriver.setOnMidiStartListener(this);
        setUI();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.keyboard_horizontal);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.keyboard_vertical);
        }
    }

    private void setUI(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.keyboard_horizontal);
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.keyboard_vertical);
        initialize();
    }

    private void initialize() {

        findViewById(R.id.noteOne).setOnTouchListener(this);
        findViewById(R.id.noteTwo).setOnTouchListener(this);
        findViewById(R.id.noteThree).setOnTouchListener(this);
        findViewById(R.id.noteFour).setOnTouchListener(this);
        findViewById(R.id.noteFive).setOnTouchListener(this);
        findViewById(R.id.noteSix).setOnTouchListener(this);
        findViewById(R.id.noteSeven).setOnTouchListener(this);
        findViewById(R.id.noteEight).setOnTouchListener(this);
        findViewById(R.id.noteNine).setOnTouchListener(this);
        findViewById(R.id.noteTen).setOnTouchListener(this);
        findViewById(R.id.noteEleven).setOnTouchListener(this);
        findViewById(R.id.noteTwelve).setOnTouchListener(this);

        SeekBar octave = (SeekBar) findViewById(R.id.octave_bar);
        octave.setMax(MidiDefinitions.OCTAVE_MAX);
        octave.setOnSeekBarChangeListener(this);
        octave.setProgress(6);

        SeekBar instrument = (SeekBar) findViewById(R.id.instrument_bar);
        instrument.setOnSeekBarChangeListener(this);
        instrument.setMax(MidiDefinitions.INSTRUMENT_MAX);
        instrument.setProgress(MidiDefinitions.INSTRUMENT_DEFAULT);

        SeekBar modulation = (SeekBar) findViewById(R.id.modulation_bar);
        modulation.setOnSeekBarChangeListener(this);
        modulation.setMax(MidiDefinitions.MODULATION_MAX);
        modulation.setProgress(MidiDefinitions.MODULATION_DEFAULT);

        SeekBar pitch = (SeekBar) findViewById(R.id.pitch_bar);
        pitch.setOnSeekBarChangeListener(this);
        pitch.setMax(MidiDefinitions.PITCH_MAX);
        pitch.setProgress(MidiDefinitions.PITCH_DEFAULT);

        Switch sustainSwitch = (Switch)findViewById(R.id.sustain_switch);
        sustainSwitch.setOnCheckedChangeListener(this);

        findViewById(R.id.reset_button).setOnTouchListener(this);
        findViewById(R.id.reset_pitch).setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        midiDriver.start();

        // Get the configuration.
        config = midiDriver.config();

        // Print out the details.
        Log.d(this.getClass().getName(), "maxVoices: " + config[0]);
        Log.d(this.getClass().getName(), "numChannels: " + config[1]);
        Log.d(this.getClass().getName(), "sampleRate: " + config[2]);
        Log.d(this.getClass().getName(), "mixBufferSize: " + config[3]);
    }

    @Override
    protected void onPause() { super.onPause(); midiDriver.stop();}

    @Override
    public void onMidiStart() {
        Log.d(this.getClass().getName(), "onMidiStart()");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v.getId() == R.id.noteOne) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_ONE, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_ONE, octave));
            }
        } else if (v.getId() == R.id.noteTwo) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_TWO, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_TWO, octave));
            }
        } else if (v.getId() == R.id.noteThree) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_THREE, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_THREE, octave));
            }
        } else if (v.getId() == R.id.noteFour) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_FOUR, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_FOUR, octave));
            }
        } else if (v.getId() == R.id.noteFive) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_FIVE, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_FIVE, octave));
            }
        } else if (v.getId() == R.id.noteSix) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_SIX, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_SIX, octave));
            }
        } else if (v.getId() == R.id.noteSeven) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_SEVEN, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_SEVEN, octave));
            }
        } else if (v.getId() == R.id.noteEight) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_EIGHT, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_EIGHT, octave));
            }
        } else if (v.getId() == R.id.noteNine) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_NINE, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_NINE, octave));
            }
        } else if (v.getId() == R.id.noteTen) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_TEN, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_TEN, octave));
            }
        } else if (v.getId() == R.id.noteEleven) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_ELEVEN, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_ELEVEN, octave));
            }
        } else if (v.getId() == R.id.noteTwelve) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_TWELVE, octave));
            } else if (sustain == false && event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_TWELVE, octave));
            }
        } else if(v.getId() == R.id.reset_button){
            Log.i("Debug", "Reset");
            midiDriver.write(MidiCodes.stopAllNotes());
        } else if(v.getId() == R.id.reset_pitch){
            Log.i("Debug", "Pitch Reset");
            midiDriver.write(MidiCodes.resetPitch());
            resetPitchBar();
        }

        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if(seekBar.getId() == R.id.octave_bar){

            int input = seekBar.getProgress();
            Log.i("Debug", "Octave Input: " + input);
            octave = input*10;

        }else if(seekBar.getId() == R.id.instrument_bar){

            int input = seekBar.getProgress();
            Log.i("Debug", "Instrument: " + input);
            midiDriver.write(MidiCodes.selectInstrument(input));
        } else  if(seekBar.getId() == R.id.pitch_bar){
            int input = seekBar.getProgress();
            Log.i("Debug", "Pitch: " + input);
            midiDriver.write(MidiCodes.setPitch(input));
        } else if(seekBar.getId() == R.id.modulation_bar){
            int input = seekBar.getProgress();
            Log.i("Debug", "Modulation: " + input);
            midiDriver.write(MidiCodes.setModulation(input));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void resetPitchBar(){
        //60 = 0x40 --
        int mid = 60;
        SeekBar pitchBar = (SeekBar) findViewById(R.id.pitch_bar);
        pitchBar.setProgress(60);
    }

    public void resetModulationBar(){
        int _default = 0;
        SeekBar modulation = (SeekBar) findViewById(R.id.modulation_bar);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView.getId() == R.id.sustain_switch){
            Log.i("Debug", "Sustain Toggle");
            if(sustain) sustain = false;
            else sustain = true;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        Log.i("Debug", "Key pressed");

        if (keyCode == KeyEvent.KEYCODE_A) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_ONE, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_ONE, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_W) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_TWO, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_TWO, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_S) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_THREE, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_THREE, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_E) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_FOUR, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_FOUR, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_D) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_FIVE, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_FIVE, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_R) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_SIX, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_SIX, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_F) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_SEVEN, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_SEVEN, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_T) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_EIGHT, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_EIGHT, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_G) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_NINE, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_NINE, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_Y) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_TEN, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_TEN, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_H) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_ELEVEN, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_ELEVEN, octave));
            }
        } else if (keyCode == KeyEvent.KEYCODE_U) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_DOWN");
                midiDriver.write(MidiCodes.playNote(MidiDefinitions.NOTEMODIFY_TWELVE, octave));
            } else if (sustain == false && event.getAction() == KeyEvent.ACTION_UP) {
                Log.d(this.getClass().getName(), "MotionEvent.ACTION_UP");
                midiDriver.write(MidiCodes.stopNote(MidiDefinitions.NOTEMODIFY_TWELVE, octave));
            }
        }

        return false;
    }
}
