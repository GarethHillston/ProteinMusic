/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proteinmusic;

import java.util.ArrayList;

/**
 *
 * @author Gareth
 */
public class Mapping {
    
    // Containing all nucelotide sequences denoting the amino acid of that index
    private ArrayList<Codon> nucSeqs;
    // Mapping of codons to amino acids
    private int[] map;
    // First value at each index is the polarity, second is the class
    private int[][] values;
    // Amino acid names
    private String[] names;
    
    public Mapping() {
        
        nucSeqs = new ArrayList<Codon>();
        
        char[] nucs = {'a','c','g','t'};
        
        for (int i = 0; i < 4; i ++) {
            for (int j = 0; j < 4; j ++) {
                for ( int k = 0; k < 4; k ++) {
                    nucSeqs.add(new Codon(nucs[i],nucs[j],nucs[k]));
                }
            }
        }
        
        map = new int[] {0,1,0,1, 2,2,2,2, 3,4,3,4, 5,5,6,5, 7,8,7,8, 9,9,9,9,
                            3,3,3,3, 10,10,10,10, 11,12,11,12, 13,13,13,13,
                            14,14,14,14, 15,15,15,15, 16,17,18,17, 4,4,4,4,
                            19,20,21,20, 10,22,10,22};
        
        values = new int[][]{{2,4},{1,5},{1,1},{2,4},{1,1},{0,0},{0,1},{1,5},
            {2,4},{0,2},{0,0},{3,5},{3,5},{0,0},{0,0},{0,0},{4,6},{1,3},{4,6},
            {4,6},{1,1},{0,3},{0,3}};
        
        
        names = new String[]{"Lysine","Asparagine","Threonine","Arginine",
            "Serine","Isoleucine","Methionine","Glutamine","Histidine",
            "Proline","Leucine","Glutamate","Aspartate","Alanine","Glycine",
            "Valine","ochre","Tyrosine","amber","opal","Cysteine","Tryptophan",
            "Phenylalanine"};
    } // Mapping
    
    public int getIndex(Codon c) {
        return map[nucSeqs.indexOf(c)];
    } // getIndex
    
    public String getName(int index) {
        return names[index];
    }
    
    public int getPolarity(int index) {
        return values[index][0];
    }
    
    public int getAAClass(int index) {
        return values[index][1];
    }

} // Class - Mapping