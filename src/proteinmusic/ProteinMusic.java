/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proteinmusic;

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
        Input input = new Input(args[0]);
        Output output = new Output(input.getNuc(), input.getBass());
        try {
            output.play();
        }
        catch (InterruptedException e) {
            System.err.println("Caught InterruptedException: " + e.getMessage());
            System.exit(0);
        }
        //Generator gen = new Generator();
    } // Main
    
} // Class - Protein Music