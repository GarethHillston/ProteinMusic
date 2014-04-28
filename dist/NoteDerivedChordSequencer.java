/*
 * As opposed to all other methods this one generates the chords from the notes
 * and not the other way around
 */

package proteinmusic;

import java.util.*;

/**
 *
 * @author Gareth
 */
public class NoteDerivedChordSequencer extends ChordSequencer{
    
    private MusicMapping mapping;
    private ArrayList<Integer> notes;
    
    public NoteDerivedChordSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
    } // Constructor - NoteDerivedChordSequencer
    
    @Override
    public ArrayList<Chord> run(ArrayList notesIn) {
        
        // Makes sure the input is fo the right format
        if (!(notesIn.get(0) instanceof Integer)) {
            System.out.println("That's no integer list!");
            System.exit(0);
        }
        
        notes = notesIn;
        Iterator iterNote = notes.iterator();
        int[] nextNotes = new int[] {-2,-2,-2};
        int newNote;
        int size2, size1;
        // Row for each chord, 1 colum for how many unique matching notes, 1
        // for if the base note is matched
        int[][] matchingChords = new int[7][2];
        int[][] numMatches = new int[3][7];
        int[] baseMatches = new int[2];
        
        // For each 4 note group, identify each unique note matching a composite
        // note in each chord, and is the note in question matches that chord's
        // base note
        while (iterNote.hasNext()) {
            for (int i = 0; i < 7; i ++) {
                matchingChords[i][0] = 0;
                matchingChords[i][1] = 0;
            }
            for (int i = 0; i < 3; i ++) {
                nextNotes[i] = -2;
            }
            
            for (int i = 0; i < 4; i ++) {
                newNote = (int)iterNote.next();
                if (newNote != -1 && newNote != nextNotes[0]
                        && newNote != nextNotes[1]
                        && newNote != nextNotes[2]) {
                    nextNotes[nextNotes.length-1] = newNote;
                }
            }
            
            // For each chord, check each note in nextNotes against the notes
            // in that chord
            for (int i = 0; i < 7; i ++) {
                for (int j = 0; j < 3; j ++) {
                    for (int k = 0; k < 3; k++) {
                        if (nextNotes[j] == mapping.getChord(i+1, 0)[k]
                              || nextNotes[j] == mapping.getChord(i+1, 0)[k] + 12
                              || nextNotes[j] == mapping.getChord(i+1, 0)[k] - 12) {
                            matchingChords[i][0] += 1;
                            if (k == 0) {
                                matchingChords[i][1] = 1;
                            }
                        }
                    }
                }
            }
            
            size2 = 0;
            size1 = 0;
            for (int i = 0; i < 3; i ++) {
                for (int j = 0; j < 7; j ++) {
                    numMatches[i][j] = 0;
                }
            }
           baseMatches[0] = -1;
           baseMatches[1] = -1;
            
            // Find the chord the provides the best match to the notes by
            // most matching unique notes and matching base note
            for (int i = 0; i < 7; i ++) {
                if (matchingChords[i][0] == 3) {
                    numMatches[2][0] = i;
                } else {
                    if (matchingChords[i][0] == 2) {
                        numMatches[1][size2] = i;
                        if (matchingChords[i][1] == 1) {
                            baseMatches[1] = i;
                        }
                        size2 ++;
                    } else {
                        if (matchingChords[i][0] == 1) {
                            numMatches[0][size1] = i;
                            if (matchingChords[i][1] == 1) {
                                baseMatches[0] = i;
                            }
                            size1 ++;
                        }
                    }
                }
            }
            
            //3 unique notes is a perfect match
            if (numMatches[2][0] != 0) {
                chords.add(mapping.newChord(numMatches[2][0]+1, 0));
            } else {
                // If 2 matches, if there's only one, it's the best solution,
                // If multiple, preference to that with base note, otherwise
                // doesn't matter
                if (numMatches[1][0] != 0) {
                    if (size2 == 1) {
                        chords.add(mapping.newChord(numMatches[1][0]+1, 0));
                    } else {
                        if (baseMatches[1] != -1) {
                            chords.add(mapping.newChord(baseMatches[1]+1, 0));
                        } else {
                            chords.add(mapping.newChord(numMatches[1][(int)(Math.random()*size2)]+1, 0));
                        }
                    }
                } else {
                    // Same again for 1 match
                    if (size1 == 1) {
                        chords.add(mapping.newChord(numMatches[0][0]+11, 0));
                    } else {
                        if (baseMatches[1] != -1) {
                            chords.add(mapping.newChord(baseMatches[1]+1, 0));
                        } else {
                            chords.add(mapping.newChord(numMatches[0][(int)(Math.random()*size1)]+1, 0));
                        }
                    }
                }
            }
            
            if (chords.size() < 0) {
                chords.get(chords.size()-1).chordCheck(chords.get(chords.size()-2));
            }
            
        }
        
        return chords;
        
    } // Method - run
    
} // Class - NoteDerivedChordSequencer
