/*
 * Parent class to all note sequencers
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class NoteSequencer {
    // All children must output an array of notes
    ArrayList<Integer> notes;
    
    public NoteSequencer () {
        notes = new ArrayList();
    } // Constructor - NoteSequencer
    
    public ArrayList<Integer> run() {
        return notes;
    } // Method - run
    
    public ArrayList<Integer> run(ArrayList<AminoAcid> bassIn) {
        return notes;
    } // Method - run(alt)
    
    public ArrayList<Integer> run(ArrayList<Chord> chords,
            ArrayList<AminoAcid> bassIn) {
        return notes;
    } // Method - run(alt)
    
} // Class - NoteSequencer