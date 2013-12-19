package proteinmusic;

import java.util.*;
import java.io.*;

// Class to read in and partially translate nucleotide sequences from given files
public class Input {

    private String file;
    private ArrayList<Character> nuc;
    private ArrayList<AminoAcid> bass;
    private BufferedReader reader;
    private int ch;
    private char[] nucs;
    private Mapping mapping;

    public Input(String fileName) {
        mapping = new Mapping();
        nucs = new char[3];
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
                
                nucs[i-1] = c;

                if (i >= 2) {
                    int s = nuc.size();
                    bass.add(new AminoAcid((Character)nucs[0],
                            (Character)nucs[1], (Character)nucs[2], mapping));
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