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
public class RandomTranslator extends Translator{
    
    private int i;
    private double x;
    
    RandomTranslator(StatCollector stats) {
        super(stats);
        i = 0;
    }
    
    public void translate(ArrayList nuc, ArrayList bass) {
        while (i < 20) {
            x = Math.random() * 1;
            if (x < 0.5) {
                if (x < 0.3) {
                    if (x < 0.1) {
                        chords.add(mapping.newChord(6,0));
                    } else {
                        chords.add(mapping.newChord(5,0));
                    }
                } else {
                    if (x < 0.4) {
                        chords.add(mapping.newChord(4,0));
                    } else {
                        chords.add(mapping.newChord(3,0));
                    }
                }
            } else {
                if (x < 0.75) {
                    if (x < 0.7) {
                        chords.add(mapping.newChord(2,0));
                    } else {
                        chords.add(mapping.newChord(1,0));
                    }
                } else {
                    chords.add(mapping.newChord(0,0));
                }
            }
            
            notes.add(chords.get(chords.size()-1).get1st());
            notes.add(chords.get(chords.size()-1).get2nd());
            notes.add(chords.get(chords.size()-1).get3rd());
            
            //System.out.println(notes.get(notes.size()-3)+" "+notes.get(notes.size()-2)+" "+notes.get(notes.size()-1));
            
            if (i > 0) {
                chords.get(chords.size()-1).chordCheck(chords.get(chords.size()-2));
            }
            
            i ++;
            
        }
    }
    
}
