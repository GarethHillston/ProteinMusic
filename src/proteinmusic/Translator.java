/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    }
    
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
        
    }
    
    public ArrayList getNotes() { 
        return notes;
    }
    
    public ArrayList getChords() { 
        return chords;
    }
    
}
