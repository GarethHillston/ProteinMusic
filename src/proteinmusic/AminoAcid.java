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
    private int num, polarity, aaClass;
    private ArrayList<Character> nucSeq;

    public AminoAcid(Character nuc1, Character nuc2, Character nuc3) {
        nucSeq = new ArrayList<Character>();
        nucSeq.add(nuc1);
        nucSeq.add(nuc2);
        nucSeq.add(nuc3);

        name = "Thyamine";
        num = 0;
        polarity = 0;
        if (nuc2 == 'A') {
            aaClass = 0;
        } else if(nuc2 == 'G') {
            aaClass = 1;
        } else if (nuc2 == 'C') {
            aaClass = 2;
        } else if (nuc2 == 'T') {
            aaClass = 3;
        }
    } // Method - AminoAcid

    public String getName() {
        return name;
    } // Method - getName

    public int getNum() {
        return num;
    } // Method - getNum

    public int getPolarity() {
        return polarity;
    } // Method - getPolarity

    public int getAAClass() {
        return aaClass;
    } // Method - getAAClass

    public ArrayList<Character> getNuc() {
        return nucSeq;
    } // Method - getNuc
} // Class - AminoAcid
