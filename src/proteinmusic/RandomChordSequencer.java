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
public class RandomChordSequencer extends ChordSequencer {
    
    private MusicMapping mapping;
    
    public RandomChordSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
    }
    
    @Override
    public ArrayList<Chord> run() {
        int bar = 0;
        
        while (bar < 8) {
            switch ((int)(Math.random()*7)) {
                case 0 : chordProg.add(mapping.newChord(1, 0));
                         break;
                case 1 : chordProg.add(mapping.newChord(2, 0));
                         break;
                case 2 : chordProg.add(mapping.newChord(3, 0));
                         break;
                case 3 : chordProg.add(mapping.newChord(4, 0));
                         break;
                case 4 : chordProg.add(mapping.newChord(5, 0));
                         break;
                case 5 : chordProg.add(mapping.newChord(6, 0));
                         break;
                case 6 : chordProg.add(mapping.newChord(7, 0));
                         break;
                default : chordProg.add(mapping.newChord(1, 0));
                           break;
            }
            
            if (bar > 0) {
                chordProg.get(chordProg.size()-1).chordCheck(chordProg.get(chordProg.size()-2));
            }
            
            bar ++;
        }
        
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        
        return chords;
    }
    
}
