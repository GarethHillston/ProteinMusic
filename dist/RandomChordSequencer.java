/*
 * Just generates random chords, mostly for testing
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
    } // Constructor - RandomChordSequencer
    
    @Override
    public ArrayList<Chord> run() {
        int bar = 0;
        
        // For each bar up to 8 generate a random chord
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
            
            // For all but the first, modify each chord's note composition such
            // that the notes contained aren't too disparate from those of the 
            // previous chord.
            if (bar > 0) {
                chordProg.get(chordProg.size()-1).chordCheck(chordProg.get(chordProg.size()-2));
            }
            
            bar ++;
        }
        
        // Use that as a chord progression, repeated 4 times to form 'chords' 
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        
        return chords;
    } // Method - run
    
} // Class - RandomChordSequencer
