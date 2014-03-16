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
public class ArpeggiatingNoteSequencer extends NoteSequencer{
    
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
        
        while (bar < chords.size()) {
            
            tempChord = chords.get(bar);
            
            if (Math.random()*1 < 0.5) {
                notes.add(tempChord.get1st() + 12);
                notes.add(tempChord.get2nd() + 12);
                notes.add(tempChord.get3rd() + 12);
            } else {
                notes.add(tempChord.get3rd() + 12);
                notes.add(tempChord.get2nd() + 12);
                notes.add(tempChord.get1st() + 12);
            } // if else
                
            bar ++;
        } // while
        
        return notes;
    } // Method - run
    
} // Class - BasicMarkovNoteSequencer
