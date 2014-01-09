/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class AminoAcid {

    private String name;
    private int index, polarity, aaClass;
    private Codon codon;
    
    public AminoAcid(Codon codonIn, Mapping m){
        
        codon = codonIn;
        index = m.getIndex(codon);
        name = m.getName(index);
        polarity = m.getPolarity(index);
        aaClass = m.getAAClass(index);
        
    } // Method - AminoAcid

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
} // Class - AminoAcid
