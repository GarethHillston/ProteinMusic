/*
 * Holds information on animo acid based on codon configuration
 */
package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class AminoAcid {

    // Properties of the amino acid
    private String name;
    private int index, polarity, aaClass;
    private Codon codon;
    
    public AminoAcid(Codon codonIn, Mapping m){
        // Set variables to inputs, or consult mapping
        codon = codonIn;
        index = m.getAAIndex(codon);
        name = m.getName(index);
        polarity = m.getPolarity(index);
        aaClass = m.getAAClass(index);
        
    } // Constructor - AminoAcid

    public String getName() {
        return name;
    } // Method - getName

    public int getIndex() {
        return index;
    } // Method - getNum

    public int getPolarity() {
        return polarity;
    } // Method - getPolarity

    public int getAAClass() {
        return aaClass;
    } // Method - getAAClass

    public Codon getCodon() {
        return codon;
    } // Method - getNuc
    
    public boolean equals(Object o) {
        if (((AminoAcid)o).getIndex() == this.index)
            { return true; }
        else
            {return false; }
    } // Method - equals
} // Class - AminoAcid
