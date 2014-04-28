/*
 * Class representing a chord, holds the three composite notes and a method
 * to change it's composition to closer match a previous chord
 */

package proteinmusic;

/**
 *
 * @author Gareth
 */
public class Chord {
    
    // The original base note and current three notes
    int base, n1, n2, n3;
    
    public Chord(int note1, int note2, int note3) {
        n1 = note1;
        n2 = note2;
        n3 = note3;
        base = n1;
    } // Constructor - Chord
    
    public int getBase() {
        return base;
    } // Method - getBase
    
    public int get1st() {
        return n1;
    } // Method - get1st
    
    public int get2nd() {
        return n2;
    } // Method - get2nd
    
    public int get3rd() {
        return n3;
    } // Method - get3rd
    
    // Check that the top note of thise chord isn't too far from the bottom note
    // of the given chord, if it is decrease it by an octave. Do the same if the
    // bottom is too far from the top of the previous. Repeat until they are in
    // roughly the same range
    public void chordCheck(Chord other) {
        boolean done = false;
        int temp;
                
        while (!done) {
            if (n3 > other.get1st() + 10) {
                temp = n3;
                n3 = n2;
                n2 = n1;
                n1 = temp - 12;
            } else {
                if (n1 < other.get3rd() - 10) {
                    temp = n1;
                    n1 = n2;
                    n2 = n3;
                    n3 = temp + 12;
                } else {
                    done = true;
                } // else
            } // else
        } // while
    } // Method - chordCheck
    
} // Class - Chord
