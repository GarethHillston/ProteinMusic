/*
 * Directly translates amino acid classes into chords
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class DirectClassChordSequencer extends ChordSequencer {
    
    private MusicMapping mapping;
    private ArrayList<AminoAcid> bass;
    
    public DirectClassChordSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
    } // Constructor - DirectClassChordSequencer
    
    public ArrayList<Chord> run(ArrayList bassIn) {
        
        // Make sure the input is an array of amino acids
        if (!(bassIn.get(0) instanceof AminoAcid)) {
            System.out.println("That's no amino acid list!");
            System.exit(0);
        }
        bass = bassIn;
        
        // For each bar up to 32, directly translate the amino acid class at the
        // index (bar) to the corresponding chord
        int bar = 0;
        while (bar < 32) {
            switch (bass.get(bar).getAAClass()) {
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
        
        chords = chordProg;
        return chords;
    } // Method - run
    
} // Class - DirectClassChordSequencer
