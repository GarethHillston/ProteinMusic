package proteinmusic;

import java.util.*;
import java.io.*;

// Class to read in and partially translate nucleotide sequences from given files
public class Input {

    private String file;
    private ArrayList nuc;
    private ArrayList<AminoAcid> bass;
    private BufferedReader reader;
    private int ch;

    public Input(String fileName) {
        file = fileName;
        nuc = new ArrayList();
        bass = new ArrayList<AminoAcid>();
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            System.exit(0);
        }

        try {
            int i = 0;
            while ((ch = reader.read()) != -1) {
                char c = (char) ch;
                nuc.add(c);

                if (i >= 2) {
                    int s = nuc.size();
                    bass.add(new AminoAcid((Character) nuc.get(s - 1),
                            (Character) nuc.get(s - 2), (Character) nuc.get(s - 3)));
                    i = 0;
                } else {
                    i++;
                } // if else
            } // while
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
            System.exit(0);
        }

        //Convert AA's

    } // Method - Input

    public ArrayList getNuc() {
        return nuc;
    } // Method - getNuc

    public ArrayList<AminoAcid> getBass() {
        return bass;
    } // Method - getBass
} // Class - Input