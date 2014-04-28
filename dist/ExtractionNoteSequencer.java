/*
 * Gets notes directly from the source DNA sequence by generating short riffs 
 * and selecting the best ones according to a ruleset
 */

package proteinmusic;

import java.util.*;

/**
 *
 * @author Gareth
 */
public class ExtractionNoteSequencer extends NoteSequencer {
    
    private MusicMapping mapping;
    ArrayList<int[]> riffs;
    ArrayList<Integer> scores;
    
    public ExtractionNoteSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
        riffs = new ArrayList();
        scores = new ArrayList();
    } // Constructor - ExtractionNoteSequencer
    
    @Override
    public ArrayList<Integer> run(ArrayList<AminoAcid> bass) {
        
        Iterator iterator = bass.iterator();
        int score = 10;
        int mainPosition = 0;
        int subPosition;
        boolean done = false;
        int note = 1;
        int noteValue;
        double numberOf1s = 0;
        double numberOf7s = 0;
        int[] riff = new int[8];
        for(int i = 0; i < riff.length; i ++) {
            riff[i] = -2;
        }
        
        // For every amino acid in the sequence up until the 8th last, convert
        // it and the next eight into notes directly by mapping individual 
        // amino acids to note values. They are given scores according to 
        // various qualities of the melody, if they get better than 5 (down from
        // 10 and finish well, they are added to an ArrayList of possible riffs
        while (mainPosition < bass.size() - 8) {
            
            noteValue = mapping.getNote(bass.get(mainPosition));
            riff[0] = noteValue;
            subPosition = mainPosition + 1;
            
                while (bass.get(subPosition) != null && !done && note < 8) {
                    noteValue = mapping.getNote(bass.get(subPosition));
                    riff[note] = noteValue;
                    
                    if (riff[note] != -1) {
                        if (note > 2) {
                            if (riff[note-1] == -1) {
                                if (riff[note] > riff[note-2] + 5) {
                                    score -= 2;
                                } else {
                                    if (riff[note] < riff[note-2] - 5) {
                                        score -= 2;
                                    }
                                }
                            }
                        }
                        // Is it too far a jump from the previous note?
                        if (riff[note] > riff[note-1] + 5) {
                            score -= 3;
                        } else {
                            if (riff[note] < riff[note-1] - 5) {
                                score -= 3;
                            }
                        }
                    }
                        
                        // Not the first three notes
                        if (note > 2) {
                            // Is this the same as the last 2/3+ notes?
                            if (riff[note] == riff[note-1] &&
                                    riff[note] == riff[note-2] ) {
                                score -= 3;
                                if (riff[note] == riff[note-3] ) {
                                    score -= 2;
                                }
                            }
                            // Have there been 3+ increments of 1?
                            if (Math.abs(riff[note] - riff[note-1]) == 1 &&
                                    Math.abs(riff[note-1] - riff[note-2]) == 1&&
                                    Math.abs(riff[note-2] - riff[note-3]) == 1){
                                score -= 2;
                            }
                        }
                    
                    // End of every bar
                    if ((note+1)%4 == 0) {
                        // Ending on a 1/6/7 note?
                        if (riff[note] == 45 || riff[note] == 47
                                || riff[note] == 48 || riff[note] == 57
                                || riff[note] == 59 || riff[note] == 60
                                || riff[note] == 69 || riff[note] == 71
                                || riff[note] == 72) {
                            score += 0.5;
                        }
                        // Count the number of 7's and 1's so far
                        for (int i = 0; i < riff.length; i ++) {
                            if (riff[i] == 47 || riff[i] == 59
                                    || riff[i] == 71) {
                                numberOf7s ++;
                            }
                            if (riff[i] == 48 || riff[i] == 60
                                    || riff[i] == 72) {
                                numberOf1s ++;
                            }
                        }
                        // If number of 1's per base is too high or too low
                        if (numberOf1s/((note+1)%4) < 0.5) {
                            score -= 0.5;
                        } else {
                            if (numberOf1s/((note+1)%4) > 1.5) {
                                score -= 0.5;
                            }
                        }
                        // If number of 7's per base is too high
                        if (numberOf1s/((note+1)%4) > 0.75) {
                                score -= 0.5;
                        }
                    }
                        
                    if (note > 5) {
                        // At end of the bar, determine if riff is done
                        // If score is greater than 5
                        if (score > 5) {
                            // If it is note 7
                           if (note == 6) {
                               // If the last note is a 7, add a blank and end
                               if (riff[note] == 47 || riff[note] == 59
                                        || riff[note] == 71) {
                                    riff[7] = -1;
                                    riffs.add(new int[riff.length]);
                                    System.arraycopy( riff, 0,
                                        riffs.get(riffs.size()-1),
                                        0, riff.length );
                                    scores.add(score);
                                    done = true;
                                   } 
                          } else {
                              // Last note and it is a 1 or 7, end
                              if (riff[note] == 47 || riff[note] == 48
                                     || riff[note] == 59 || riff[note] == 60
                                     || riff[note] == 71 || riff[note] == 72) {
                                  riffs.add(new int[riff.length]);
                                  System.arraycopy( riff, 0,
                                     riffs.get(riffs.size()-1),
                                     0, riff.length );
                                  scores.add(score);
                                  done = true;
                              }
                          }
                       } else {
                           done = true;
                       }
                  }
                
                  note ++;
                  subPosition ++;
                }
            
            for (int i = 0; i < riff.length; i ++) {
                riff[i] = -2;
            }
            score = 10;
            note = 1;
            done = false;
            
            mainPosition ++;
        }
        
        if (riffs.isEmpty()) {
            System.out.println("NOT GOOD ENOUGH");
        }
        
        return generateMelody();
    }
    
    
    //Select riffs based on location in the song and previous riffs used and
    // stitich together into 'notes'
    public ArrayList<Integer> generateMelody() {
        
        ArrayList<int[]> startRiffs = new ArrayList();
        ArrayList<int[]> endRiffs = new ArrayList();
        ArrayList<int[]> nextRiffs = new ArrayList();
        int randArray;
        notes.clear();
        
        // Search for any riffs starting on the base note, to start phrases
        for (int i = 0; i < riffs.size(); i ++) {
            if (riffs.get(i)[0] == 48 || riffs.get(i)[0] == 60
                    || riffs.get(i)[0] == 72) {
                startRiffs.add(riffs.get(i));
            }
        } // If there aren't any, make some
        if (startRiffs.isEmpty()) {
            for (int i = 0; i < riffs.size(); i ++) {
                if (riffs.get(i)[1] > 48 && riffs.get(i)[1] < 72) {
                    startRiffs.add(riffs.get(i));
                    startRiffs.get(startRiffs.size()-1)[0] = 60;
                }
            }
        } 
        startRiffs.add(new int[0]);
        // Do the same for riffs ending on the base note, to end the song
        for (int i = 0; i < riffs.size(); i ++) {
            if (riffs.get(i)[7] == -1) {
                if (riffs.get(i)[6] == 48 || riffs.get(i)[6] == 60
                        || riffs.get(i)[6] == 72) {
                    endRiffs.add(riffs.get(i));
                }
            } else {
                if (riffs.get(i)[7] == 48 || riffs.get(i)[7] == 60
                        || riffs.get(i)[7] == 72) {
                    endRiffs.add(riffs.get(i));
                }
            } 
        } 
        // If there aren't any, make some
        if (endRiffs.isEmpty()) {
            for (int i = 0; i < riffs.size(); i ++) {
                if (riffs.get(i)[6] > 48 && riffs.get(i)[6] < 72) {
                    endRiffs.add(riffs.get(i));
                    endRiffs.get(endRiffs.size()-1)[7] = 60;
                }
            }
        } 
        endRiffs.add(new int[0]);
        // Stitch together riffs into a 16 bar melody
        for (int i = 0; i < 16; i ++) {
            switch (i) {
                // First and middle riffs the same, from startRiffs
                case 0:
                    randArray = (int)((Math.random()*(startRiffs.size()-1))-0.001);
                    for (int j = 0; j < 8; j ++) {
                        notes.add(startRiffs.get(randArray)[j]);
                    }
                    break;
                case 8:
                    for (int j = 0; j < 8; j ++) {
                        notes.add(notes.get(j));
                    }
                    break;
                // 1/4 and 3/4 riffs the same, from startRiffs
                case 4:
                    randArray = (int)((Math.random()*(startRiffs.size()-1))-0.001);
                    for (int j = 0; j < 8; j ++) {
                        notes.add(startRiffs.get(randArray)[j]);
                    }
                    break;
                case 12:
                    for (int j = 33; j < 41; j ++) {
                        notes.add(notes.get(j));
                    }
                    break;
                case 15 :
                    randArray = (int)((Math.random()*(endRiffs.size()-1))-0.001);
                    for (int j = 0; j < 8; j ++) {
                        notes.add(endRiffs.get(randArray)[j]);
                    }
                    break;
                // Rest of the notes randomly selected
                case 1: case 2: case 3: case 5-7:
                    // Randomly select a riff whose start note is similar to
                    // the last riff's end note
                    for (int j = 0; j < riffs.size(); j ++) {
                        if (notes.get(notes.size()-1) == -1) {
                            if (riffs.get(j)[0] < notes.get(notes.size()-2) + 8
                                && riffs.get(j)[0] > notes.get(notes.size()-2) - 8) {
                            nextRiffs.add(riffs.get(j));
                            }
                        } else {
                            if (riffs.get(j)[0] < notes.get(notes.size()-1) + 8
                                    && riffs.get(j)[0] > notes.get(notes.size()-1) - 8) {
                                nextRiffs.add(riffs.get(j));
                            }
                        }
                    }
                    nextRiffs.add(new int[0]);
                    randArray = (int)((Math.random()*(nextRiffs.size()-1))-0.001);
                    for (int j = 0; j < 8; j ++) {
                        notes.add(nextRiffs.get(randArray)[j]);
                    }
                    nextRiffs.clear();
                    break;
                // Repeat this for the second half, with some riffs
                // having a chance to change and some repeating
                case 9:
                    for (int j = 9; j < 17; j ++) {
                        notes.add(notes.get(j));
                    }
                    break;
                case 13:
                    for (int j = 41; j < 49; j ++) {
                        notes.add(notes.get(j));
                    }
                    break;
                case 10: case 11: case 14:
                    // Flip a coin for random riff selection
                    if (Math.random()*1 < mapping.getProb(0.5)) {
                        for (int j = 0; j < riffs.size(); j ++) {
                            if (notes.get(notes.size()-1) == -1) {
                                if (riffs.get(j)[0] < notes.get(notes.size()-2) + 8
                                    && riffs.get(j)[0] > notes.get(notes.size()-2) - 8) {
                                nextRiffs.add(riffs.get(j));
                                }
                            } else {
                                if (riffs.get(j)[0] < notes.get(notes.size()-1) + 8
                                        && riffs.get(j)[0] > notes.get(notes.size()-1) - 8) {
                                    nextRiffs.add(riffs.get(j));
                                }
                            }
                        }
                        nextRiffs.add(new int[0]);
                        randArray = (int)((Math.random()*(nextRiffs.size()-1))-0.001);
                        for (int j = 0; j < 8; j ++) {
                            notes.add(nextRiffs.get(randArray)[j]);
                        }
                        nextRiffs.clear();
                        break;
                    } else {
                        // Otherwise repeat the mirrored section in the first half
                        for (int j = i*8; j < (i*8)+8; j ++) {
                            notes.add(notes.get(j));
                        }
                        break;
                    }
                // Default goes for a 'good' ending, as worst case is not ending well
                default :
                    randArray = (int)((Math.random()*(endRiffs.size()-1))-0.001);
                    for (int j = 0; j < 8; j ++) {
                        notes.add(endRiffs.get(randArray)[j]);
                    }
                    break;
            }
            
        }
        
        return notes;
    } // generateMelody
    
} // Class - ExtractionNoteSequencer
