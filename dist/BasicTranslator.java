/*
 * This is my first iteration of the translation algorithm, initially only
 * mimicking the original Protein Music program.
 */

package proteinmusic;

import java.util.*;

/**
 *
 * @author Gareth
 */
public class BasicTranslator extends Translator {
    
    public BasicTranslator(StatCollector stats) {
        super(stats);
    } // Constructor - BasicTranslate
    
    public void translate(ArrayList nuc, ArrayList bass) {
        // Get in the raw nucleotide and amino acid sequences
        Iterator iterNuc = nuc.iterator();
        Iterator iterBass = bass.iterator();
        
        // Nucleotides are directly mapped to notes
        while (iterNuc.hasNext()) {
            switch((char)(iterNuc.next())) {
                case 'A': notes.add(60);
                          break;
                case 'C': notes.add(63);
                          break;
                case 'G': notes.add(65);
                          break;
                case 'T': notes.add(67);
                          break;
            } // switch
        } // while
        // Amino acids are mapped to chords by class
        while (iterBass.hasNext()) {
            switch(((AminoAcid)iterBass.next()).getAAClass()) {
                case 0: chords.add(new Chord(60,64,67));
                          break;
                case 1: chords.add(new Chord(64,67,70));
                          break;
                case 2: chords.add(new Chord(65,69,72));
                          break;
                case 3: chords.add(new Chord(67,72,64));
                          break;
                case 4: chords.add(new Chord(69,74,65));
                          break;
                case 5: chords.add(new Chord(60,64,67));
                          break;
                case 6: chords.add(new Chord(65,69,72));
                          break;
                default: chords.add(new Chord(60,64,67));
                         break;
            } // switch
        } // while
    } // translate
    
} // Class - BasicTranslator
