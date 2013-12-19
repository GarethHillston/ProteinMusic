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
public class Codon implements Comparable {
    
    private char n1, n2, n3;
    
    public Codon(char nuc1, char nuc2, char nuc3) {
        n1 = nuc1;
        n2 = nuc2;
        n3 = nuc3;
    }
    
    public char getN1() {
        return n1;
    }
    
    public char getN2() {
        return n2;
    }
    
    public char getN3() {
        return n3;
    }
    
    @Override
    public int compareTo(Object o) {
        Codon c = (Codon)o;
        
        if (c.getN1() == n1) {
            if (c.getN2() == n2) {
                if (c.getN3() == n3) {
                    return 0;
                } else if (c.getN3() < n3) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (c.getN2() < n2) {
                return 1;
            } else {
                return -1;
            }
        } else if (c.getN1() < n1) {
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Codon && ((Codon)obj).getN1() == n1
                && ((Codon)obj).getN2() == n2 && ((Codon)obj).getN3() == n3) {
            return true;
        } else {
            return false;
        }
    }
}
