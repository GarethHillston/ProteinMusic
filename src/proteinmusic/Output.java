/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proteinmusic;

import javax.sound.midi.*;
import java.util.*;

/**
 *
 * @author Gareth
 */
public class Output {

    private MidiChannel[] mc = null;
    private Synthesizer synth;
    private Instrument[] instr;
    private ArrayList<Integer> notes;
    private ArrayList<Chord> chords;
    private Iterator iterNote, iterChord;
    private long noteStart, bassStart;

    public Output(ArrayList<Integer> notesIn, ArrayList<Chord> ChordsIn) {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            mc = synth.getChannels();
            instr = synth.getDefaultSoundbank().getInstruments();
            synth.loadInstrument(instr[90]);
        } // try
        catch (Exception e) {
            System.err.println("Output constructor failed, exception: " + e.getMessage());
            System.exit(0);
        } // catch

        notes = notesIn;
        chords = ChordsIn;

        iterNote = notes.iterator();
        iterChord = chords.iterator();
    } // Method - Output

    public void play() throws InterruptedException {
        while (iterNote.hasNext()) {
            if (System.currentTimeMillis() >= noteStart + 300) {
                mc[5].noteOn((int)iterNote.next(), 600);
                noteStart = System.currentTimeMillis();
            } // if

            if (System.currentTimeMillis() >= bassStart + 900) {
                mc[5].allNotesOff();
                mc[5].noteOn(((Chord)iterChord.next()).getN1(), 600);
                mc[5].noteOn(((Chord)iterChord.next()).getN2(), 600);
                mc[5].noteOn(((Chord)iterChord.next()).getN3(), 600);
                bassStart = System.currentTimeMillis();
            } // if
        } // while
    } // Method - play
} // Class - Output
