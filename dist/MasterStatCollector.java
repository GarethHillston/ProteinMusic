/*
 * Gathers data on the number of times and percentages proportions various
 * attributes, amino acids or nucleotide patterns appear. Does this over
 * multiple files.
 * Collected is;
 * Amino acid frequency, codon frequency, amino acid class & polarity 
 * frequencies, runs of repeated instances of these values, repeated
 * nucleotides and runs of increasing value and decreasing value nucleotides.
 */

package proteinmusic;

import java.util.*;

/**
 *
 * @author Gareth
 */
public class MasterStatCollector {
    
    private double[] percentageAAs, percentageCodons, percentageClasses,
            percentagePolarities, percentageNucRuns, percentageClassRuns,
            percentageCodonRuns, percentagePolarityRuns, percentageAARuns,
            percentageForRuns, percentageBackRuns;
    
    int totalSequences;
    
    StatCollector statCollector;
    
    public MasterStatCollector() {
        
        totalSequences = 0;  
        
        percentageAAs = new double[23]; percentageCodons = new double[64];
        percentageClasses = new double[7]; percentagePolarities = new double[5];
        percentageNucRuns = new double[7]; percentageClassRuns = new double[7];
        percentageCodonRuns = new double[7]; percentagePolarityRuns = new double[7];
        percentageAARuns = new double[7]; percentageForRuns = new double[7];
        percentageBackRuns = new double[7];
        
        statCollector = new StatCollector();
    }
    
    public void analyseFiles(ArrayList[] nucleotides, ArrayList[] basses) {
        totalSequences = nucleotides.length;
        
        try {
            for (int i = 0; i < totalSequences; i ++) {
                statCollector.run(nucleotides[i],basses[i]);
                
                for (int j = 0; j < 23; j ++) {
                    percentageAAs[j] += statCollector.getPerAAs()[j];
                }
                for (int j = 0; j < 64; j ++) {
                    percentageCodons[j] += statCollector.getPerCodons()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageClasses[j] += statCollector.getPerClasses()[j];
                }
                for (int j = 0; j < 5; j ++) {
                    percentagePolarities[j] += statCollector.getPerPolarities()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageNucRuns[j] += statCollector.getPerNucRuns()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageClassRuns[j] += statCollector.getPerClassRuns()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageCodonRuns[j] += statCollector.getPerCodonRuns()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentagePolarityRuns[j] += statCollector.getPerPolarityRuns()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageAARuns[j] += statCollector.getPerAARuns()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageForRuns[j] += statCollector.getPerForRuns()[j];
                }
                for (int j = 0; j < 7; j ++) {
                    percentageBackRuns[j] += statCollector.getPerBackRuns()[j];
                }
            }
            
            for (int j = 0; j < 23; j ++) {
                percentageAAs[j] /= totalSequences;
            }                
            for (int j = 0; j < 64; j ++) {
                percentageCodons[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageClasses[j] /= totalSequences;
            }
            for (int j = 0; j < 5; j ++) {
                percentagePolarities[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageNucRuns[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageClassRuns[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageCodonRuns[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentagePolarityRuns[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageAARuns[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageForRuns[j] /= totalSequences;
            }
            for (int j = 0; j < 7; j ++) {
                percentageBackRuns[j] /= totalSequences;
            }
        }
        catch (InterruptedException e) {
            System.err.println("Caught InterruptedException: " + e.getMessage());
            System.exit(0);
        }
    } // Method - analyseFiles
    
    public void printPercentages() {
        //  -- Percentages --
        System.out.println("  == Percentages for individual elements ==");
        System.out.println();

        // Codon percentages
        System.out.println("  --  Percentages for each codon composition --");
        System.out.println("| - |  -AA  |  -AC  |  -AG  |  -AT  |  -CA  |  -CC  |  -CG  |  -CT  |  -GA  |  -GC  |  -GG  |  -GT  |  -TA  |  -TC  |  -TG  |  -TT  |");
        System.out.print("| A |");
        for (int i = 0; i < 16; i ++) {
            System.out.print(String.format(" %5.1f |", percentageCodons[i])); }
        System.out.println();
        System.out.print("| C |");
        for (int i = 16; i < 32; i ++) {
            System.out.print(String.format(" %5.1f |", percentageCodons[i])); }
        System.out.println();
        System.out.print("| G |");
        for (int i = 32; i < 48; i ++) {
            System.out.print(String.format(" %5.1f |", percentageCodons[i])); }
        System.out.println();
        System.out.print("| T |");
        for (int i = 48; i < 64; i ++) {
            System.out.print(String.format(" %5.1f |", percentageCodons[i])); }
        System.out.println();
        System.out.println();
        
        // Amino Acid Percentages
        System.out.println("  --  Percentages for each amino acid --");
        System.out.print("|  Lys  |  Asp  |  Thr  |  Arg  |  Ser  |  Iso  |  Met  |  Glu  |\n|");
        for (int i = 0; i < 8; i ++) {
            System.out.print(String.format(" %5.1f |", percentageAAs[i])); }
        System.out.println();
        System.out.print("|  His  |  Pro  |  Leu  | G'ate | A'ate |  Ala  |  Gly  |  Val  |\n|");
        for (int i = 8; i < 16; i ++) {
            System.out.print(String.format(" %5.1f |", percentageAAs[i])); }
        System.out.println();

        System.out.print("|  och  |  Tyr  |  amb  |  opa  |  Cys  |  Try  |  Phe  |\n|");
        for (int i = 16; i < 23; i ++) {
            System.out.print(String.format(" %5.1f |", percentageAAs[i])); }
        System.out.println();
        System.out.println();
        
        // Class Percentages
        System.out.println("  --  Percentages for each Class --");
        System.out.println("| Aliphatic |  Hydroxyl |  Cyclic  | Aromatic |  Basic  |  Acidic  | stop codon |");
        System.out.print(String.format("|   %5.1f   |", percentageClasses[0]));
        System.out.print(String.format("   %5.1f   |", percentageClasses[1]));
        System.out.print(String.format("   %5.1f  |", percentageClasses[2]));
        System.out.print(String.format("   %5.1f  |", percentageClasses[3]));
        System.out.print(String.format("  %5.1f  |", percentageClasses[4]));
        System.out.print(String.format("   %5.1f  |", + percentageClasses[5]));
        System.out.println(String.format("   %5.1f    |", + percentageClasses[6]));
        System.out.println();
        System.out.println();
        
        // Polarity Percentages
        System.out.println("  --  Percentages for each Polarity --");
        System.out.println("| Non-polar |  Polar  |  Basic  |  Acidic  | stop codon |");
        System.out.print(String.format("|   %5.1f   |", percentagePolarities[0]));
        System.out.print(String.format("  %5.1f  |", percentagePolarities[1]));
        System.out.print(String.format("  %5.1f  |", percentagePolarities[2]));
        System.out.print(String.format("   %5.1f  |", percentagePolarities[3]));
        System.out.println(String.format("   %5.1f    |", percentagePolarities[4]));
        System.out.println();
        System.out.println();
        
        // -- Repeats/Runs --
        System.out.println("  == Percentage runs of elements ==");
        System.out.println();
        
        // Nucleotide repeat run
        System.out.println(" -- Percentage runs of repeat nucelotides --");
        runsPrint(percentageNucRuns);
        
        // Nucleotide forward run
        System.out.println(" -- Percentage runs of increasing value nucelotides --");
        runsPrint(percentageForRuns);
        
        // Nucleotide reverse run
        System.out.println(" -- Percentage runs of reversing value nucelotides --");
        runsPrint(percentageBackRuns);
        
        // Codon run
        System.out.println(" -- Percentage runs of repeat codons --");
        runsPrint(percentageCodonRuns);
        
        // Amino acid run
        System.out.println(" -- Percentage runs of repeat amino acids --");
        runsPrint(percentageAARuns);
        
        // Class run
        System.out.println(" -- Percentage runs of repeat classes --");
        runsPrint(percentageClassRuns);
        
        // Polarity run
        System.out.println(" -- Percentage runs of repeat polarities --");
        runsPrint(percentagePolarityRuns);
        
        System.out.println();
        System.out.println();
        System.out.println();
        
    } // Method - PrintPercentages
    
    private void runsPrint(double[] array) {
        System.out.print("|   2   |   3   |   4   |   5   |   6   |   7+  |\n|");
        for (int i = 1; i < 7; i ++) {
            System.out.print(String.format(" %5.1f |", array[i])); }
        System.out.println();
        System.out.println();
        
    } // Method - runsPrint
    
}
