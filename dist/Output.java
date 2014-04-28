/*
 * Class translates the note and chord arrays into MIDI counds
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
        // Initialise sound generation components
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            mc = synth.getChannels();
            instr = synth.getDefaultSoundbank().getInstruments();
            mc[5].programChange(0, 11);
            mc[6].programChange(0, 11);
        } // try
        catch (Exception e) {
            System.err.println("Output constructor failed, exception: " + e.getMessage());
            System.exit(0);
        } // catch

        // Set up note and chord arrays/iterators
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
        
        // For each chord, if the last has gone on long enough play the next one
        // Notes are played every 1/4 chord in between
        while (iterChord.hasNext()) {
            
            if (System.currentTimeMillis() >= bassStart + 1200) {
                mc[6].allNotesOff();
                tempChord = (Chord)iterChord.next();
                mc[6].noteOn(tempChord.get1st(), 85);
                mc[6].noteOn(tempChord.get2nd(), 85);
                mc[6].noteOn(tempChord.get3rd(), 85);
                bassStart = System.currentTimeMillis();
            } // if
            
            if (iterNote.hasNext()) {
                while (notes < 4) {
                    if (System.currentTimeMillis() >= noteStart + 300) {
                        thisNote = (int)iterNote.next();
                        if (thisNote != -1) {
                            mc[5].allNotesOff();
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
