/*
 * Tranlsator called by main class, selects appropriate sequencers based on 
 * user input and returns note and chord sequences
 */

package proteinmusic;

import java.util.*;

/**
 *
 * @author Gareth
 */
public class Translator {
    
    ArrayList<Integer> notes;
    ArrayList<Chord> chords;
    MusicMapping mapping;
    ChordSequencer chordSequencer;
    NoteSequencer noteSequencer;
    
    public Translator(StatCollector stats) {
        mapping = new MusicMapping(stats);
    } // Constructor - Translate
    
    public void translate(ArrayList nuc, ArrayList bass, int chordSeq, int noteSeq) {  
        
        // Initialise appropriate chord sequencer
        switch (chordSeq) {
            case 0 : chordSequencer = new RandomChordSequencer(mapping);
                     chords = chordSequencer.run();
                     break;
            case 1 : chordSequencer = new DirectClassChordSequencer(mapping);
                     chords = chordSequencer.run(bass);
                     break;
            case 2 : chordSequencer = new BasicMarkovChordSequencer(mapping);
                     chords = chordSequencer.run(bass);
                     break;
            case 3 : chordSequencer = new NoteDerivedChordSequencer(mapping);
                     break;
        }
        
        // Initialise appropriate note sequencer
        switch (noteSeq) {
            case 0 : noteSequencer = new ArpeggiatingNoteSequencer(mapping);
                     notes = noteSequencer.run(chords, bass);
                     break;
            case 1 : noteSequencer = new BasicMarkovNoteSequencer(mapping);
                     notes = noteSequencer.run(chords, bass);
                     break;
            case 2 : noteSequencer = new ExtractionNoteSequencer(mapping);
                     notes = noteSequencer.run(bass);
                     chords = chordSequencer.run(notes);
                     break;
        }
        
    } // Method - tranlsate
    
    public ArrayList getNotes() { 
        return notes;
    } // Method - getNotes
    
    public ArrayList getChords() { 
        return chords;
    } // Method - getChords
    
} // Class - Translator
