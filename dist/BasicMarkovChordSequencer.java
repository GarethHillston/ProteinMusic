/*
 * More complicated chord sequencer using a markov model influenced by the
 * unique traits of the specified DNA sequence.
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class BasicMarkovChordSequencer extends ChordSequencer{
    
    private MusicMapping mapping;
    private ArrayList<AminoAcid> bass;
    
    public BasicMarkovChordSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
    } // Constructor - BasicMarkocChordSequencer
    
    public ArrayList<Chord> run(ArrayList bassIn) {
        
        // Make sure bass is an array of amino acids
        if (!(bassIn.get(0) instanceof AminoAcid)) {
            System.out.println("That's no amino acid list!");
            System.exit(0);
        }
        
        // Amino acid array
        bass = bassIn;
        
        int randomAA;
        int bar = 0;
        
        // Establishes a repeated chord sequence of eight chords. At each point
        // the switch statement randomly chooses an amino acid from the array,
        // the next chord is chosen according to which amino acid is chosen. 
        // This is in effect a markov model as the distributions of AA polarities
        // proportions is know, within reasonable variance which will allow for
        // some randomness, i.e. character in the music.
        // E.g. If non-polar occurs 33% of the time, I will map the chord I want
        // to happen roughly 1/3 of the time to that chord.
        while (bar < 8) {
            if (bar % 4 == 0) {
                if (bar == 0) {
                    chordProg.add(mapping.newChord(1,0));
                } else {
                    randomAA = (int)(Math.random()*bass.size());
                    switch (((AminoAcid)(bass.get(randomAA))).getPolarity()) {
                        // 0.5
                        case 0 : chordProg.add(mapping.newChord(5,0));
                                 break;
                        // 0.25
                        case 1 : chordProg.add(mapping.newChord(1,0));
                                 break;
                        // 0.25
                        case 2 : case 3 : case 4 :
                                 chordProg.add(mapping.newChord(3,0));
                                 break;
                        default : chordProg.add(mapping.newChord(1,0));
                                  break;
                    }
                }
            } else {
                if (bar == 7) {
                    // 
                    randomAA = (int)(Math.random()*bass.size());
                    switch (((AminoAcid)(bass.get(randomAA))).getIndex()) {
                        // 0.05
                        case 8 : case 16 :chordProg.add(mapping.newChord(1,0));
                                 break;
                        // 0.1
                        case 14 : case 15 : chordProg.add(mapping.newChord(2,0));
                                 break;
                        // 0.15
                        case 0 : case 1 : case 9 : 
                                 chordProg.add(mapping.newChord(3,0));
                                 break;
                        // 0.2
                        case 3 : case 2 : case 5 : case 19 : 
                                 chordProg.add(mapping.newChord(4,0));
                                 break;
                        // 0.25
                        case 4 : case 10 : case 13 : 
                                 chordProg.add(mapping.newChord(5,0));
                                 break;
                        // 0.2
                        case 6 : case 7 : case 17 : case 18 : case 20 :
                        case 21 : case 22 : chordProg.add(mapping.newChord(6,0));
                                 break;
                        // 0.05
                        case 11 : case 12 : chordProg.add(mapping.newChord(7,0));
                                 break;
                        default : chordProg.add(mapping.newChord(1,0));
                                  break;
                    }
                } else {
                    // 
                    randomAA = (int)(Math.random()*bass.size());
                    switch (((AminoAcid)(bass.get(randomAA))).getIndex()) {
                        // 0.05
                        case 8 : case 16 : chordProg.add(mapping.newChord(1,0));
                                 break;
                        // 0.1
                        case 14 : case 15 : chordProg.add(mapping.newChord(2,0));
                                 break;
                        // 0.1
                        case 2 : case 11 : case 12 : 
                                 chordProg.add(mapping.newChord(3,0));
                                 break;
                        // 0.15
                        case 3 : case 5 : case 19 : 
                                 chordProg.add(mapping.newChord(4,0));
                                 break;
                        // 0.15
                        case 0 : case 1 : case 9 : 
                                 chordProg.add(mapping.newChord(5,0));
                                 break;
                        // 0.25
                        case 4 : case 10 : case 13 :
                                 chordProg.add(mapping.newChord(6,0));
                                 break;
                        // 0.2
                        case 6 : case 7 : case 17 : case 18 : case 20 :
                        case 21 : case 22 : chordProg.add(mapping.newChord(7,0));
                                 break;
                        default : chordProg.add(mapping.newChord(1,0));
                                  break;
                    }
                }
            }
            
            // For all but the first, modify each chord's note composition such
            // that the notes contained aren't too disparate from those of the 
            // previous chord.
            if (bar > 0) {
                chordProg.get(chordProg.size()-1).chordCheck(chordProg.get(chordProg.size()-2));
            }
            
            bar ++;
        }
        
        // This chord progression is repeated four times, and this forms the
        // output of the method.
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        
        return chords;
    } // Method - run
    
} // Class - BasicMarkovChordSequencer
