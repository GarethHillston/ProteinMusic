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
public class DirectClassChordSequencer extends ChordSequencer {
    
    private MusicMapping mapping;
    private ArrayList<AminoAcid> bass;
    
    public DirectClassChordSequencer(MusicMapping mappingIn) {
        super();
        mapping = mappingIn;
    }
    
    public ArrayList<Chord> run(ArrayList bassIn) {
        
        if (!(bassIn.get(0) instanceof AminoAcid)) {
            System.out.println("That's no amino acid list!");
            System.exit(0);
        }
        bass = bassIn;
        
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
            
            if (bar > 0) {
                chordProg.get(chordProg.size()-1).chordCheck(chordProg.get(chordProg.size()-2));
            }
            
            bar ++;
        }
        
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        chords.addAll(chordProg);
        
        return chords;
    }
    
}
