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
        notes = new ArrayList();
        chords = new ArrayList();
        mapping = new MusicMapping(stats);
    }
    
    public void translate(ArrayList nuc, ArrayList bass) {  
        
    }
    
    public ArrayList getNotes() { 
        return notes;
    }
    
    public ArrayList getChords() { 
        return chords;
    }
    
}
