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
        
        // Catch improper inputs
        if (args.length < 3 || Integer.parseInt(args[1]) > 3
                || Integer.parseInt(args[1]) < 0 || Integer.parseInt(args[2]) > 2
                || Integer.parseInt(args[1]) < 0) {
            System.out.println("Bad inputs, need (string,int,int). args[1]<3 . args[2]<2.");
            System.exit(0);
        }
        if (Integer.parseInt(args[1]) == 3 && Integer.parseInt(args[2]) != 2
            ||Integer.parseInt(args[2]) == 2 && Integer.parseInt(args[1]) != 3){
            System.out.println("Bad inputs, chord sequencer 3 and note sequencer 2 must go together.");
            System.exit(0);
        }
        
        Input input = new Input();         
        input.parseInput(args[0]);
            
        StatCollector stats = new StatCollector();
        try {
            stats.run(input.getNuc(), input.getBass());
        } catch(Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
            System.exit(0);
        }
            
        Translator translator = new Translator(stats);
        translator.translate(input.getNuc(), input.getBass(), 
                Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            
        Output output = new Output(translator.getNotes(), translator.getChords());
        try {
            output.play();
        }
        catch (InterruptedException e) {
            System.err.println("Caught InterruptedException: " + e.getMessage());
            System.exit(0);
        }
         
        /* MasterStatTracker for gathering DNA data
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

        */
        
    } // Main
    
} // Class - Protein Music