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
    
    // First value at each index is the polarity, second is the class
    private int[][] mappings;
    // Containing all nucelotide sequences denoting the amino acid of that index
    private char[][][] nucSeqs;
    // Amino acid names
    private String[] names;
    
    public Mapping() {
        mappings = new int[][]{{0,3},{0,0},{0,0},{0,1},{0,0},
            {1,1},{0,2},{1,1},{0,0}, 
            {1,3},{4,6},{4,6},{2,4},{1,5},{1,5},{2,4},{4,5},{4,5},
            {1,1},{4,6},{0,3},{2,4},{1,1},{2,4},{0,0}};
        
        nucSeqs = new char[][][]{
            {{'t','t','t'},{'t','t','c'}}, //Phe
            {{'t','t','a'},{'t','t','g'},{'c','t','t'},{'c','t','c'},
                {'c','t','a'},{'c','t','g'}}, // Leu
            {{'a','t','t'},{'a','t','c'},{'a','t','a'}}, // Ile
            {{'a','t','g'}}, // Met
            {{'g','t','t'},{'g','t','c'},{'g','t','a'},{'g','t','g'}}, // Val
            {{'t','c','t'},{'t','c','c'},{'t','c','a'},{'t','c','g'}, 
                {'a','g','t'},{'a','g','c'}}, // Ser
            {{'c','c','t'},{'c','c','c'},{'c','c','a'},{'c','c','g'}}, // Pro
            {{'a','c','t'},{'a','c','c'},{'a','c','a'},{'a','c','g'}}, // Thr
            {{'g','c','t'},{'g','c','d'},{'g','c','a'},{'g','c','g'}}, // Ala
            {{'t','a','t'},{'t','a','c'}}, // Tyr
            {{'c','a','t'},{'c','a','c'}}, // His
            {{'c','a','a'},{'c','a','g'}}, // Gln
            {{'a','a','t'},{'a','a','c'}}, // Asn
            {{'a','a','a'},{'a','a','g'}}, // Lys
            {{'g','a','t'},{'g','a','c'}}, // Asp
            {{'g','a','a'},{'g','a','g'}}, // Glu
            {{'t','g','t'},{'t','g','c'}}, // Cys
            {{'t','g','g'}}, //Trp
            {{'c','g','t'},{'c','g','c'},{'c','g','a'},{'c','g','g'},
                {'a','g','a'},{'a','g','g'}}, // Arg
            {{'g','g','t'},{'g','g','c'},{'g','g','a'},{'g''g','g'}} // Gly
        };
            
        names = new String[]{"Phenylalanine","Leucine","Isoleucine",
            "Methionine","Valine","Serine","Proline","Threonine","Alanine",
            "Tyrosine","Histidine","Glutamine","Asparagine","Lysine",
            "Aspartate","Gulatmate","Cysteine","Tryptophan","Arginine",
            "Glycine","ochre","amber","opal"};
    } // Mapping
} // Class - Mapping