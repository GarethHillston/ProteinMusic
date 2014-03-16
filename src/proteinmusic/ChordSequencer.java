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
public class ChordSequencer {
    
    ArrayList<Chord> chords, chordProg;
    
    public ChordSequencer () {
        chords = new ArrayList();
        chordProg = new ArrayList();
    }
    
    public ArrayList<Chord> run() {
        return chords;
    }
    
    public ArrayList<Chord> run(ArrayList input) {
        return chords;
    }
    
}
