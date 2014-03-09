/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proteinmusic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Gareth
 */
public class MarkovTranslator extends Translator {
    
    MarkovTranslator(StatCollector stats) {
        super(stats);
    }
    
    public void translate(ArrayList nuc, ArrayList bass) {
        chordSequencer = new BasicMarkovChordSequencer(mapping);
        noteSequencer = new BasicMarkovNoteSequencer(mapping);
        
        chords = chordSequencer.run(bass);
        notes = noteSequencer.run(chords, bass);  
    }
    
}
