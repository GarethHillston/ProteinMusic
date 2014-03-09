/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class NoteSequencer {
    private MusicMapping mapping;
    ArrayList<Integer> notes;
    
    public NoteSequencer (MusicMapping mappingIn) {
        mapping = mappingIn;
        notes = new ArrayList();
    }
    
    public ArrayList<Integer> run() {
        return notes;
    }
    
    public ArrayList<Integer> run(ArrayList<Chord> chords,
            ArrayList<AminoAcid> bassIn) {
        return notes;
    }
    
}
