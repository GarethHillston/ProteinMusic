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
    
    public AminoAcid(String n, int i, int p, int cl, Codon c) {
        name = n;
        index = i;
        polarity = p;
        aaClass = cl;
        codon = c;
    }
    
    public AminoAcid(Character nuc1, Character nuc2, Character nuc3, Mapping m){
        
        codon = new Codon(nuc1, nuc2, nuc3);
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
