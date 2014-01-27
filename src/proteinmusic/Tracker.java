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
        
        aaTotal = new int[23];  classTotal = new int[7];
        polTotal = new int[5];  codonTotal = new int[64];
        
        nucRun = new ArrayList();
        classRun = new ArrayList();
        polRun = new ArrayList();
        aaRun = new ArrayList();
        codonRun = new ArrayList();
        forRun = new ArrayList();         
        backRun = new ArrayList();
        
        codonTracker = 0;
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
            
            // -- Codon data --
            if (codonTracker >= 2) {
                // Codon data
                //currentCodon = new Codon(codonNucs[0], codonNucs[1],
                //                           codonNucs[2]);
                if (iterBass.hasNext()) {
                    currentCodon = ((AminoAcid)iterBass.next()).getCodon();
                
                    // CodonTotal
                    codonTotal[currentCodon.getIndex()] ++;
                
                    checkForRepeatRun(currentCodon, codonRun, codonRunNo);
            
                    // == Amino Acid data ==
                    currentAA = (AminoAcid)iterBass.next();
                    
                    // Amino acid type, class and polarity totals
                    aaTotal[currentAA.getIndex()] ++;
                    classTotal[currentAA.getAAClass()] ++;
                    polTotal[currentAA.getPolarity()] ++;
                
                
                    // Amino Acid run
                    checkForRepeatRun(currentAA, aaRun, aaRunNo);
                
                    // Class run
                    checkForRepeatRun(currentAA.getAAClass(), classRun, classRunNo);
                
                    // Polarity run
                    checkForRepeatRun(currentAA.getPolarity(), polRun, polRunNo);
                
                    codonTracker = 0;
                    
                    //System.out.println(currentCodon.getN1()+""+currentCodon.getN2()+""+currentCodon.getN3());
                    //System.out.println(currentAA.getName());
                    //System.out.println(currentAA.getAAClass());
                } // if
            } // if
            
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
        //  -- Totals --
        System.out.println("  == Totals for individual elements ==");
        System.out.println();

        // Codon totals
        System.out.println("  --  Total numbers of each codon composition --");
        System.out.println("| - | -AA | -AC | -AG | -AT | -CA | -CC | -CG | -CT | -GA | -GC | -GG | -GT | -TA | -TC | -TG | -TT |");
        System.out.print("| A |");
        for (int i = 0; i < 16; i ++) {
            System.out.print(String.format(" %3d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| C |");
        for (int i = 16; i < 32; i ++) {
            System.out.print(String.format(" %3d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| G |");
        for (int i = 32; i < 48; i ++) {
            System.out.print(String.format(" %3d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| T |");
        for (int i = 48; i < 64; i ++) {
            System.out.print(String.format(" %3d |", codonTotal[i])); }
        System.out.println();
        System.out.println();
        
        // Amino Acid Totals
        System.out.println("  --  Total numbers of each amino acid --");
        System.out.print("| Lys | Asp | Thr | Arg | Ser | Iso | Met | Glu |\n|");
        for (int i = 0; i < 8; i ++) {
            System.out.print(String.format(" %3d |", aaTotal[i])); }
        System.out.println();
        System.out.print("| His | Pro | Leu |G'ate|A'ate| Ala | Gly | Val |\n|");
        for (int i = 8; i < 16; i ++) {
            System.out.print(String.format(" %3d |", aaTotal[i])); }
        System.out.println();

        System.out.print("| och | Tyr | amb | opa | Cys | Try | Phe |\n|");
        for (int i = 16; i < 23; i ++) {
            System.out.print(String.format(" %3d |", aaTotal[i])); }
        System.out.println();
        System.out.println();
        
        // Class Totals
        System.out.println("  --  Total numbers of each Class --");
        System.out.println("| Aliphatic | Hydroxyl | Cyclic | Aromatic | Basic | Acidic | stop codon |");
        System.out.print(String.format("| %9d |", classTotal[0]));
        System.out.print(String.format(" %8d |", classTotal[1]));
        System.out.print(String.format(" %6d |", classTotal[2]));
        System.out.print(String.format(" %9d |", classTotal[3]));
        System.out.print(String.format(" %5d |", classTotal[4]));
        System.out.print(String.format(" %6d |", + classTotal[5]));
        System.out.println(String.format(" %10d |", + classTotal[6]));
        System.out.println();
        System.out.println();
        
        // Polarity Totals
        System.out.println("  --  Total numbers of each Polarity --");
        System.out.println("| Non-polar | Polar | Basic | Acidic | stop codon |");
        System.out.print(String.format("| %9d |", polTotal[0]));
        System.out.print(String.format(" %5d |", polTotal[1]));
        System.out.print(String.format(" %5d |", polTotal[2]));
        System.out.print(String.format(" %6d |", polTotal[3]));
        System.out.println(String.format(" %10d |", polTotal[4]));
        System.out.println();
        System.out.println();
        
        // -- Repeats/Runs --
        System.out.println("  == Runs of elements ==");
        System.out.println();
        
        // Nucleotide repeat run
        System.out.println(" -- Runs of repeat nucelotides --");
        runsPrint(nucRunNo);
        
        // Nucleotide forward run
        System.out.println(" -- Runs of increasing value nucelotides --");
        runsPrint(forRunNo);
        
        // Nucleotide reverse run
        System.out.println(" -- Runs of reversing value nucelotides --");
        runsPrint(backRunNo);
        
        // Codon run
        System.out.println(" -- Runs of repeat codons --");
        runsPrint(codonRunNo);
        
        // Amino acid run
        System.out.println(" -- Runs of repeat amino acids --");
        runsPrint(aaRunNo);
        
        // Class run
        System.out.println(" -- Runs of repeat classes --");
        runsPrint(classRunNo);
        
        // Polarity run
        System.out.println(" -- Runs of repeat polarities --");
        runsPrint(polRunNo);
        
    } // Method - printResults
    
    private void runsPrint(int[] array) {
        System.out.print("|   2 |   3 |   4 |   5 |   6 |  7+ |\n|");
        for (int i = 1; i < 7; i ++) {
            System.out.print(String.format(" %3d |", array[i])); }
        System.out.println();
        System.out.println();
    }
    
} // Class - Tracker
