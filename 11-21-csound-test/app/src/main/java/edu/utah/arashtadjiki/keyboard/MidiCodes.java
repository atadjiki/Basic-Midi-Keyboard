package edu.utah.arashtadjiki.keyboard;

/**
 * Created by AlleyCat on 12/1/2016.
 */
public class MidiCodes {

    public static byte[] playNote(int pitchModify, int octave) {

        // Construct a note ON message for the specified pitch at maximum velocity on channel 1:
        byte[] event = new byte[3];
        event[0] = (byte) (0x90 | 0x00);  // 0x90 = note On, 0x00 = channel 1
        event[1] = (byte) (octave + pitchModify);
        event[2] = (byte) 0x7F;  // 0x7F = max velocity

        // Internally this just calls write() and can be considered obsoleted:
        //midiDriver.queueEvent(event);

        // Send the MIDI event to the synthesizer.
        return event;
    }

    public static byte[] stopNote(int pitchModify, int octave) {

        // Construct a note OFF message for the specified pitch at minimum velocity on channel 1:
        byte[] event = new byte[3];
        event[0] = (byte) (0x80 | 0x00);  // 0x80 = note Off, 0x00 = channel 1
        event[1] = (byte) (octave + pitchModify);
        event[2] = (byte) 0x00;  // 0x00 = min velocity (0)

        // Send the MIDI event to the synthesizer.
        return event;
    }

    public static byte[] stopAllNotes(){

        byte[] event = new byte[3];
        event[0] = (byte) (0xB0); //status byte
        event[1] = (byte) (0x7B); //123 - turn off all notes on channel
        event[2] = (byte) (0x00);

        return event;
    }

    public static byte[] togglePortamento(boolean on) {

        byte[] event = new byte[3];
        event[0] = (byte) (0xB0 | 0x00); //status code
        event[1] = (byte) (0x41|0x00); //65 == toggle portamento, channel 1

        if(on) event[2] = (byte) (0x7F); //127 = ON
        else event[2] = (byte) (0x00); //0 = OFF

        return event;
    }

    public static byte[] setPortamentoAmount(int amount){

        byte[] event = new byte[3];
        event[0] = (byte) (0xB0 | 0x00); //status code, channel 1
        event[1] = (byte) (0x54); //84 == portamento amount
        event[2] = (byte) amount; //amount

        return event;
    }

    public static byte[] setPortamentoTime(int time){

        byte[] event = new byte[3];
        event[0] = (byte) (0xB0 | 0x00); //status code, channel 1
        event[1] = (byte) (0x05); //5 = portamento time
        event[2] = (byte) time; //time

        return event;

    }

    public static byte[] selectInstrument(int instrument) {

        // Construct a program change to select the instrument on channel 1:
        byte[] event = new byte[2];
        event[0] = (byte)(0xC0 | 0x00); // 0xC0 = program change, 0x00 = channel 1
        event[1] = (byte)instrument;

        // Send the MIDI event to the synthesizer.
        return event;

    }

    public static byte[] setPitch(int pitch){

        byte[] event = new byte[3];
        event[0] = (byte) (0xE0 | 0x00);
        event[1] = (byte) (0x00);
        event[2] = (byte) pitch;

        return event;
    }

    public static byte[] resetPitch(){
        byte[] event = new byte[3];
        event[0] = (byte) (0xE0 | 0x00);
        event[1] = (byte) (0x00);
        event[2] = (byte) (0x40);

        return event;
    }

    public static byte[] setModulation(int amount){
        byte[] event = new byte[3];
        event[0] = (byte) (0xB0 | 0x00);
        event[1] = (byte) (0x01);
        event[2] = (byte) amount;

        return event;
    }


}
