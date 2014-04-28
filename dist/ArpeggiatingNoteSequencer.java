/*
 * Simple class to generate a rising or falling sequence of notes based on the
 * chords, mostly to ensure pleasant sounding 'melody' whilst listening to
 * the chord sequence.
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class ArpeggiatingNoteSequencer extends NoteSequencer {
    
    private MusicMapping mapping;
    private ArrayList<Integer> notes;
    
    public ArpeggiatingNoteSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
    } // Constructor - BasicMarkovNoteSequencer
    
    public ArrayList<Integer> run(ArrayList<Chord> chords,
                                    ArrayList<AminoAcid> bass) {
        
        int bar = 0;
        Chord tempChord;
        ArrayList n = new ArrayList<Integer>();
        
        // For each chord, play each note of the chord in succession either
        // rising or falling
        while (bar < chords.size()) {
            
            tempChord = chords.get(bar);
            
            if (Math.random()*1 < 0.5) {
                n.add((tempChord.get1st() + 12));
                n.add((tempChord.get2nd() + 12));
                n.add((tempChord.get3rd() + 12));
                n.add((tempChord.get3rd() + 12));
            } else {
                n.add((tempChord.get3rd() + 12));
                n.add((tempChord.get2nd() + 12));
                n.add((tempChord.get1st() + 12));
                n.add((tempChord.get1st() + 12));
            } // if else
                
            bar ++;
        } // while
        
        return n;
    } // Method - run
    
} // Class - BasicMarkovNoteSequencer
