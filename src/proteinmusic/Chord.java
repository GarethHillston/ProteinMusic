/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proteinmusic;

/**
 *
 * @author Gareth
 */
public class Chord {
    
    int base, n1, n2, n3;
    
    public Chord(int note1, int note2, int note3) {
        n1 = note1;
        n2 = note2;
        n3 = note3;
        base = n1;
    }
    
    public int getBase() {
        return base;
    }
    
    public int get1st() {
        return n1;
    }
    
    public int get2nd() {
        return n2;
    }
    
    public int get3rd() {
        return n3;
    }
    
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
                }
            }
        }
    }
    
}
