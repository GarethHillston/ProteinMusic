/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proteinmusic;

import javax.sound.midi.*;
import java.util.*;

/**
 *
 * @author Gareth
 */
public class Tracker {
    
    private ArrayList<Character> nuc;
    private ArrayList<AminoAcid> bass;
    private Iterator iterNuc, iterBass;
    private Mapping mapping;
    
    private int[] nucRunNo, classRunNo, polRunNo, aaRunNo, codonRunNo;
    private int[] forRunNo, backRunNo;
    private int[] aaTotal, classTotal, polTotal, codonTotal;
    
    private ArrayList<Character> nucRun;
    private ArrayList<Integer> classRun;
    private ArrayList<Integer> polRun;
    private ArrayList<Integer> aaRun;
    private ArrayList<Integer> codonRun;
    private ArrayList<Character> forRun;         
    private ArrayList<Character> backRun;
    
    private char c;
    private int codonTracker;
    private char[] codonNucs;
    private Codon currentCodon;
    private AminoAcid currentAA;

    public Tracker(ArrayList nucIn, ArrayList bassIn) {

        nuc = nucIn;
        bass = bassIn;
        iterNuc = nuc.iterator();
        iterBass = bass.iterator();
        mapping = new Mapping();

        nucRunNo = new int[7]; classRunNo = new int[7]; codonRunNo = new int[7];
        polRunNo = new int[7];  aaRunNo = new int[7];  forRunNo = new int[7];
        backRunNo = new int[7];
        
        aaTotal = new int[22];  classTotal = new int[7];
        polTotal = new int[4];  codonTotal = new int[64];
        
        nucRun = new ArrayList();
        classRun = new ArrayList();
        polRun = new ArrayList();
        aaRun = new ArrayList();
        codonRun = new ArrayList();
        forRun = new ArrayList();         
        backRun = new ArrayList();
        
        codonTracker = 0;  codonNucs = new char[3];
    } // Method - Tracker

    public void run() throws InterruptedException {
        while (iterNuc.hasNext()) {
            c = (char)iterNuc.next();
            
            // repeat nuc
            checkForRepeatRun(c, nucRun, nucRunNo);
            
            // alph nuc
            checkForForwardRun(c, forRun, forRunNo);
            
            // reverse nuc
            checkForBackwardRun(c, backRun, backRunNo);
            
            
            codonNucs[codonTracker] = c;
            
            // -- Codon data --
            if (codonTracker >= 2) {
                // Codon data
                //currentCodon = new Codon(codonNucs[0], codonNucs[1],
                //                           codonNucs[2]);
                currentCodon = ((AminoAcid)iterBass.next()).getCodon();
                
                // CodonTotal
                codonTotal[currentCodon.getIndex()] ++;
                
                checkForRepeatRun(currentCodon, nucRun, nucRunNo);
            
                // == Amino Acid data ==
                currentAA = (AminoAcid)iterBass.next();
                    
                // Amino acid type, class and polarity totals
                aaTotal[currentAA.getIndex()] ++;
                classTotal[currentAA.getAAClass()] ++;
                polTotal[currentAA.getPolarity()] ++;
                
                
                // Amino Acid run
                checkForRepeatRun(currentAA, nucRun, nucRunNo);
                
                // Class run
                checkForRepeatRun(currentAA.getAAClass(), nucRun, nucRunNo);
                
                // Polarity run
                checkForRepeatRun(currentAA.getPolarity(), nucRun, nucRunNo);
                
                codonTracker = 0;
            }  
            
            codonTracker ++;
        } // while
        
        printResults();
        
    } // Method - run
    
    
    private void checkForRepeatRun(Object n, ArrayList array, int[] arrayTotal){    
        if (array.isEmpty()) {
            array.add(n);
        } else {
            if (array.contains(n)) {
                array.add(n);
                if (array.size() >= 7) {
                    array.clear();
                    arrayTotal[6]++;
                } // if
            } // if
            else {
                if (array.size() >= 2) {
                    arrayTotal[array.size()-1]++;
                } // if
                array.clear();
            } // else
        } // if else
    }
    
    private void checkForBackwardRun(Object n, ArrayList array, int[] arrayTotal){    
        if (array.isEmpty()) {
            array.add(n);
        } else {
            if (c2I((char)array.get(array.size()-1)) == c2I((char)n)+1
              || ((char)n == 'T' && c2I((char)array.get(array.size()-1)) == 0)){
                array.add(n);
                if (array.size() >= 7) {
                    array.clear();
                    arrayTotal[6]++;
                } // if
            } // if
            else {
                if (array.size() >= 2) {
                    arrayTotal[array.size()-1]++;
                } // if
                array.clear();
            } // else
        } // if else
    }
    
    private void checkForForwardRun(Object n, ArrayList array, int[] arrayTotal){    
        if (array.isEmpty()) {
            array.add(n);
        } else {
            if (c2I((char)array.get(array.size()-1)) == c2I((char)n)-1
              || ((char)n == 'A' && c2I((char)array.get(array.size()-1)) == 3)){
                array.add(n);
                if (array.size() >= 7) {
                    array.clear();
                    arrayTotal[6]++;
                } // if
            } // if
            else {
                if (array.size() >= 2) {
                    arrayTotal[array.size()-1]++;
                } // if
                array.clear();
            } // else
        } // if else
    }
    
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
    
    public void printResults() {
        // Codon totals
        System.out.println("| - | -AA | -AC | -AG | -AT | -CA | -CC | -CG | -CT | -GA | -GC | -GG | -GT | -TA | -TC | -TG | -TT |");
        System.out.print("| A |");
        for (int i = 0; i < 16; i ++) {
            System.out.print(String.format(" %+3d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| C |");
        for (int i = 16; i < 32; i ++) {
            System.out.print(String.format(" %+3d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| G |");
        for (int i = 32; i < 48; i ++) {
            System.out.print(String.format(" %+3d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| T |");
        for (int i = 48; i < 64; i ++) {
            System.out.print(String.format(" %+3d |", codonTotal[i])); }
        System.out.println();
        
        // Reapeats/Runs
        
    } // Method - printResults
    
} // Class - Tracker
