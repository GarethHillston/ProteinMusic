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
            mc[5].programChange(0, 11);
        } // try
        catch (Exception e) {
            System.err.println("Output constructor failed, exception: " + e.getMessage());
            System.exit(0);
        } // catch

        notes = notesIn;
        chords = ChordsIn;
        iterNote = notes.iterator();
        iterChord = chords.iterator();
        
        noteStart = System.currentTimeMillis() - 300;
    } // Method - Output

    public void play() throws InterruptedException {
        Chord tempChord;
        int notes = 0;
        int thisNote;
        
        while (iterChord.hasNext()) {
            
            if (System.currentTimeMillis() >= bassStart + 1200) {
                mc[5].allNotesOff();
                tempChord = (Chord)iterChord.next();
                mc[5].noteOn(tempChord.get1st(), 85);
                mc[5].noteOn(tempChord.get2nd(), 85);
                mc[5].noteOn(tempChord.get3rd(), 85);
                bassStart = System.currentTimeMillis();
            } // if
            
            if (iterNote.hasNext()) {
                while (notes < 4) {
                    if (System.currentTimeMillis() >= noteStart + 300) {
                        thisNote = (int)iterNote.next();
                        if (thisNote != -1) {
                            mc[5].noteOn(thisNote, 600);
                        }
                        noteStart = System.currentTimeMillis();
                        notes ++;
                    } // if 
                } // while
            } // if
            
            notes = 0;
            
        } // while
        
        Thread.sleep(5000);
        
    } // Method - play
} // Class - Output
