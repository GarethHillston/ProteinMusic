/*
 * Parent class for all chord sequencers
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class ChordSequencer {
    
    // All children should produce a chords array, probably usign chordProg
    ArrayList<Chord> chords, chordProg;
    
    public ChordSequencer () {
        chords = new ArrayList();
        chordProg = new ArrayList();
    } // Constructor - ChordSequencer
    
    public ArrayList<Chord> run() {
        return chords;
    } // Method - run
    
    public ArrayList<Chord> run(ArrayList input) {
        return chords;
    } // Method - run(alt)
    
} // Class - ChordSequencer
