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
    
    int n1, n2, n3;
    
    public Chord(int note1, int note2, int note3) {
        n1 = note1;
        n2 = note2;
        n3 = note3;
    }
    
    public int getN1() {
        return n1;
    }
    
    public int getN2() {
        return n2;
    }
    
    public int getN3() {
        return n3;
    }
    
}
