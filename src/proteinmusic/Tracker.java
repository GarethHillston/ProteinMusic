/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proteinmusic;

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
    
    double totalAAs, totalNucRuns, totalClassRuns, totalCodonRuns,
            totalPolarityRuns, totalAARuns, totalForRuns, totalBackRuns;
    private char c;
    double total;
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
        
        totalAAs = 0; totalNucRuns = 0; totalClassRuns = 0;
        totalCodonRuns = 0; totalPolarityRuns = 0; totalAARuns = 0;
        totalForRuns = 0; totalBackRuns = 0;       
        
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
                if (iterBass.hasNext()) {
                    
                    // Get the next amino acid/its codon
                    currentAA = (AminoAcid)iterBass.next();
                    currentCodon = (currentAA.getCodon());
                
                    // CodonTotal
                    codonTotal[currentCodon.getIndex()] ++;
                
                    checkForRepeatRun(currentCodon, codonRun, codonRunNo);
            
                    // == Amino Acid data ==
                     
                    // Amino acid type, class and polarity totals
                    aaTotal[currentAA.getIndex()] ++;
                    classTotal[currentAA.getAAClass()] ++;
                    polTotal[currentAA.getPolarity()] ++;
                    
                    totalAAs ++;
                
                
                    // Amino Acid run
                    checkForRepeatRun(currentAA, aaRun, aaRunNo);
                
                    // Class run
                    checkForRepeatRun(currentAA.getAAClass(), classRun, classRunNo);
                
                    // Polarity run
                    checkForRepeatRun(currentAA.getPolarity(), polRun, polRunNo);
                
                    codonTracker = -1;
                    
                    //System.out.println(currentCodon.getN1()+""+currentCodon.getN2()+""+currentCodon.getN3());
                    //System.out.println(currentAA.getName());
                    //System.out.println(currentAA.getAAClass());
                } // if
            } // if
            codonTracker ++;
        } // while
        
        totalClassRuns = calculateTotal(classRunNo);
        totalCodonRuns = calculateTotal(codonRunNo);
        totalPolarityRuns = calculateTotal(polRunNo);
        totalAARuns = calculateTotal(aaRunNo);
        totalForRuns = calculateTotal(forRunNo);
        totalBackRuns = calculateTotal(backRunNo);

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
                array.add(n);
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
    
    public double calculateTotal(int[] array) {
        for (int i = 1; i < array.length; i ++) {
            total +=array[i];
        }
        return total;
    } // Method - calculateTotal
    
    public void printResults() {
        //  -- Totals --
        System.out.println("  == Percentages for individual elements ==");
        System.out.println();

        // Codon totals
        System.out.println("  --  Percentages for each codon composition --");
        System.out.println("| - |  -AA  |  -AC  |  -AG  |  -AT  |  -CA  |  -CC  |  -CG  |  -CT  |  -GA  |  -GC  |  -GG  |  -GT  |  -TA  |  -TC  |  -TG  |  -TT  |");
        System.out.print("| A |");
        for (int i = 0; i < 16; i ++) {
            System.out.print(String.format(" %3.3f |", codonTotal[i]/totalAAs)); }
        System.out.println();
        System.out.print("| C |");
        for (int i = 16; i < 32; i ++) {
            System.out.print(String.format(" %3.3f |", codonTotal[i]/totalAAs)); }
        System.out.println();
        System.out.print("| G |");
        for (int i = 32; i < 48; i ++) {
            System.out.print(String.format(" %3.3f |", codonTotal[i]/totalAAs)); }
        System.out.println();
        System.out.print("| T |");
        for (int i = 48; i < 64; i ++) {
            System.out.print(String.format(" %3.3f |", codonTotal[i]/totalAAs)); }
        System.out.println();
        System.out.println();
        
        // Amino Acid Totals
        System.out.println("  --  Percentages for each amino acid --");
        System.out.print("|  Lys  |  Asp  |  Thr  |  Arg  |  Ser  |  Iso  |  Met  |  Glu  |\n|");
        for (int i = 0; i < 8; i ++) {
            System.out.print(String.format(" %3.3f |", aaTotal[i]/totalAAs)); }
        System.out.println();
        System.out.print("|  His  |  Pro  |  Leu  | G'ate | A'ate |  Ala  |  Gly  |  Val  |\n|");
        for (int i = 8; i < 16; i ++) {
            System.out.print(String.format(" %3.3f |", aaTotal[i]/totalAAs)); }
        System.out.println();

        System.out.print("|  och  |  Tyr  |  amb  |  opa  |  Cys  |  Try  |  Phe  |\n|");
        for (int i = 16; i < 23; i ++) {
            System.out.print(String.format(" %3.3f |", aaTotal[i]/totalAAs)); }
        System.out.println();
        System.out.println();
        
        // Class Totals
        System.out.println("  --  Percentages for each Class --");
        System.out.println("| Aliphatic |  Hydroxyl |  Cyclic  | Aromatic |  Basic  |  Acidic  | stop codon |");
        System.out.print(String.format("|   %3.3f   |", classTotal[0]/totalAAs));
        System.out.print(String.format("   %3.3f   |", classTotal[1]/totalAAs));
        System.out.print(String.format("   %3.3f  |", classTotal[2]/totalAAs));
        System.out.print(String.format("   %3.3f  |", classTotal[3]/totalAAs));
        System.out.print(String.format("  %3.3f  |", classTotal[4]/totalAAs));
        System.out.print(String.format("   %3.3f  |", + classTotal[5]/totalAAs));
        System.out.println(String.format("   %3.3f    |", + classTotal[6]/totalAAs));
        System.out.println();
        System.out.println();
        
        // Polarity Totals
        System.out.println("  --  Percentages for each Polarity --");
        System.out.println("| Non-polar |  Polar  |  Basic  |  Acidic  | stop codon |");
        System.out.print(String.format("|   %3.3f   |", polTotal[0]/totalAAs));
        System.out.print(String.format("  %3.3f  |", polTotal[1]/totalAAs));
        System.out.print(String.format("  %3.3f  |", polTotal[2]/totalAAs));
        System.out.print(String.format("   %3.3f  |", polTotal[3]/totalAAs));
        System.out.println(String.format("   %3.3f    |", polTotal[4]/totalAAs));
        System.out.println();
        System.out.println();
        
        // -- Repeats/Runs --
        System.out.println("  == Percentage runs of elements ==");
        System.out.println();
        
        // Nucleotide repeat run
        System.out.println(" -- Percentage runs of repeat nucelotides --");
        runsPrint(nucRunNo, totalNucRuns);
        
        // Nucleotide forward run
        System.out.println(" -- Percentage runs of increasing value nucelotides --");
        runsPrint(forRunNo, totalForRuns);
        
        // Nucleotide reverse run
        System.out.println(" -- Percentage runs of reversing value nucelotides --");
        runsPrint(backRunNo, totalBackRuns);
        
        // Codon run
        System.out.println(" -- Percentage runs of repeat codons --");
        runsPrint(codonRunNo, totalCodonRuns);
        
        // Amino acid run
        System.out.println(" -- Percentage runs of repeat amino acids --");
        runsPrint(aaRunNo, totalAARuns);
        
        // Class run
        System.out.println(" -- Percentage runs of repeat classes --");
        runsPrint(classRunNo, totalClassRuns);
        
        // Polarity run
        System.out.println(" -- Percentage runs of repeat polarities --");
        runsPrint(polRunNo, totalPolarityRuns);
        
        System.out.println();
        System.out.println();
        System.out.println();
        
            //  -- Totals --
        System.out.println("  == Totals for individual elements ==");
        System.out.println();

        // Codon totals
        System.out.println("  --  Total numbers of each codon composition --");
        System.out.println("| - |  -AA  |  -AC  |  -AG  |  -AT  |  -CA  |  -CC  |  -CG  |  -CT  |  -GA  |  -GC  |  -GG  |  -GT  |  -TA  |  -TC  |  -TG  |  -TT  |");
        System.out.print("| A |");
        for (int i = 0; i < 16; i ++) {
            System.out.print(String.format(" %5d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| C |");
        for (int i = 16; i < 32; i ++) {
            System.out.print(String.format(" %5d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| G |");
        for (int i = 32; i < 48; i ++) {
            System.out.print(String.format(" %5d |", codonTotal[i])); }
        System.out.println();
        System.out.print("| T |");
        for (int i = 48; i < 64; i ++) {
            System.out.print(String.format(" %5d |", codonTotal[i])); }
        System.out.println();
        System.out.println();
        
        // Amino Acid Totals
        System.out.println("  --  Total numbers of each amino acid --");
        System.out.print("|  Lys  |  Asp  |  Thr  |  Arg  |  Ser  |  Iso  |  Met  |  Glu  |\n|");
        for (int i = 0; i < 8; i ++) {
            System.out.print(String.format(" %5d |", aaTotal[i])); }
        System.out.println();
        System.out.print("|  His  |  Pro  |  Leu  | G'ate | A'ate |  Ala  |  Gly  |  Val  |\n|");
        for (int i = 8; i < 16; i ++) {
            System.out.print(String.format(" %5d |", aaTotal[i])); }
        System.out.println();

        System.out.print("|  och  |  Tyr  |  amb  |  opa  |  Cys  |  Try  |  Phe  |\n|");
        for (int i = 16; i < 23; i ++) {
            System.out.print(String.format(" %5d |", aaTotal[i])); }
        System.out.println();
        System.out.println();
        
        // Class Totals
        System.out.println("  --  Total numbers of each Class --");
        System.out.println("| Aliphatic |  Hydroxyl |  Cyclic  | Aromatic |  Basic  |  Acidic  | stop codon |");
        System.out.print(String.format("| %9d |", classTotal[0]));
        System.out.print(String.format(" %9d |", classTotal[1]));
        System.out.print(String.format(" %8d |", classTotal[2]));
        System.out.print(String.format(" %8d |", classTotal[3]));
        System.out.print(String.format(" %7d |", classTotal[4]));
        System.out.print(String.format(" %8d |", + classTotal[5]));
        System.out.println(String.format(" %10d |", + classTotal[6]));
        System.out.println();
        System.out.println();
        
        // Polarity Totals
        System.out.println("  --  Total numbers of each Polarity --");
        System.out.println("| Non-polar |  Polar  |  Basic  |  Acidic  | stop codon |");
        System.out.print(String.format("| %9d |", polTotal[0]));
        System.out.print(String.format(" %7d |", polTotal[1]));
        System.out.print(String.format(" %7d |", polTotal[2]));
        System.out.print(String.format(" %8d |", polTotal[3]));
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
        System.out.print("|   2   |   3   |   4   |   5   |   6   |   7+  |\n|");
        for (int i = 1; i < 7; i ++) {
            System.out.print(String.format(" %5d |", array[i])); }
        System.out.println();
        System.out.println();
        
    } // Method - printResults
    
    private void runsPrint(int[] array, double arrayTotal) {
        System.out.print("|    2    |    3    |    4    |    5    |    6    |    7+   |\n|");
        for (int i = 1; i < 7; i ++) {
            System.out.print(String.format(" %3.5f |", array[i]/arrayTotal)); }
        System.out.println();
        System.out.println();
        
    } // Method - printResults
    
} // Class - Tracker
