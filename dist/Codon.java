/*
 * Holds information for each codon (group of three nucleotides).
 */

package proteinmusic;

/**
 *
 * @author Gareth
 */
public class Codon implements Comparable {
    
    // Three composite nucleotides and index
    private char n1, n2, n3;
    private int index;
    
    public Codon(char nuc1, char nuc2, char nuc3) {
        n1 = nuc1;
        n2 = nuc2;
        n3 = nuc3;
        
        // Assigns index from 1 to 64 based on nucleotide composition; AAA = 0,
        // CAA = 16, GGG = 64 etc.
        index = ((c2I(nuc1)*16)+(c2I(nuc2)*4)+c2I(nuc3));
    } // Constructor - Codon
    
    public char getN1() {
        return n1;
    } // Method - getN1
    
    public char getN2() {
        return n2;
    }// Method - getN2
    
    public char getN3() {
        return n3;
    }// Method - getN3
    
    public int getIndex() {
        return index;
    }// Method - getIndex
    
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
    
    // Assigns an integer value to the four possible nucleotide values in order;
    // A,C,G,T
    private int c2I(char c) {
        int x = 0;
        switch (c) {
            case 'A': x = 0;
                      break;
            case 'C': x = 1;
                      break;
            case 'G': x = 2;
                      break;
            case 'T': x = 3;
                      break;
            default: x = 0;
                     System.out.println("Couldn't convert char "+c+" to index");
                     break;
        }
        return x;
    } // Method - charToIndex
} // Class - Codon
