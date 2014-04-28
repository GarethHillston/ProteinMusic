/*
 * Basic sequencer using a variant of the markov model to select each
 * subsequent note based on the chord structure
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class BasicMarkovNoteSequencer extends NoteSequencer{
    
    private MusicMapping mapping;
    private ArrayList<AminoAcid> bass;
    private ArrayList<Integer> notes;
    
    public BasicMarkovNoteSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
        notes = new ArrayList();
    } // Constructor - ArpeggiatingNoteSequencer
    
    public ArrayList<Integer> run(ArrayList<Chord> chords,
            ArrayList<AminoAcid> bassIn) {
        
        bass = bassIn;
        int randomAA; 
        int bar = 0;
        int noteInChord = 1;
        Chord tempChord;
        
        notes.add(60);
        
        while (bar < chords.size()) {
            // For each note in the chord array;
            tempChord = chords.get(bar);
            
            // There are four notes to each chord, for each a random amino
            // acid is selected. The distribution of the classes of amino acids
            // is already known and so they can be used as a rough random number
            // generator. For example 42% of amino acids are Hydroxyl, so the
            // note I want to appear roughly 42% of the time is mapped to
            // Hydroxyl. The possible notes are those of the chord, or none (a
            // pause in the melody).
            while (noteInChord < 4) {
                randomAA = (int)(Math.random()*bass.size());
                switch (((AminoAcid)(bass.get(randomAA))).getAAClass()) {
                    case 0 : notes.add(-1);
                             break;
                    case 3 : case 5 : notes.add(tempChord.get1st() + 12);
                             break;
                    case 1 : case 2 : notes.add(tempChord.get2nd() + 12);
                             break;
                    case 4 : case 6 : notes.add(tempChord.get3rd() + 12);
                             break;
                    default : notes.add(tempChord.get1st() + 12);
                              break;
                } // switch
            
                noteInChord ++;
                
            } // while
            
            noteInChord = 0;            
            bar ++;
        } // while
        
        return notes;
    } // Method - run
    
} // Class - MasicMarkovNoteSequencer
