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
public class BasicMarkovNoteSequencer extends NoteSequencer{
    
    private MusicMapping mapping;
    private ArrayList<AminoAcid> bass;
    private ArrayList<Integer> notes;
    
    public BasicMarkovNoteSequencer(MusicMapping mappingIn) {
        super(mappingIn);
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
            
            tempChord = chords.get(bar);
            
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
    
}
