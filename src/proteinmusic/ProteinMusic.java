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
public class ProteinMusic {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create modules
        
        Input input = new Input();     
        
        if (Integer.parseInt(args[0]) == 0) {
            input.parseInput(args[1]);
            
            StatCollector stats = new StatCollector();
            try {
                stats.run(input.getNuc(), input.getBass());
            } catch(Exception e) {
                System.err.println("Caught Exception: " + e.getMessage());
                System.exit(0);
            }
            
            Translator translator = new MarkovTranslator(stats);
            translator.translate(input.getNuc(), input.getBass());
            
            Output output = new Output(translator.getNotes(), translator.getChords());
            try {
                output.play();
            }
            catch (InterruptedException e) {
                System.err.println("Caught InterruptedException: " + e.getMessage());
                System.exit(0);
            }
        }
        else {
            
            String[] files = new String[args.length - 1];
            for (int i = 0; i < files.length; i ++) {
                files[i] = args[i+1];
            }
            
            ArrayList[] nucleotides = new ArrayList[files.length];
            ArrayList[] basses = new ArrayList[files.length];
            for (int i = 0; i < files.length; i ++) {
                input.parseInput(files[i]);
                nucleotides[i] = input.getNuc();
                basses[i] = input.getBass();
                
            }
            
            MasterStatCollector stats = new MasterStatCollector();
            stats.analyseFiles(nucleotides, basses);
            stats.printPercentages();

        }
        //Generator gen = new Generator();
    } // Main
    
} // Class - Protein Music