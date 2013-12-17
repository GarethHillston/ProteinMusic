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
public class Output {

    private MidiChannel[] mc = null;
    private Synthesizer synth;
    private Instrument[] instr;
    private ArrayList<Character> nuc;
    private ArrayList<AminoAcid> bass;
    private Iterator iterNuc, iterBass;
    private long noteStart, bassStart;

    public Output(ArrayList nucIn, ArrayList<AminoAcid> bassIn) {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            mc = synth.getChannels();
            instr = synth.getDefaultSoundbank().getInstruments();
            synth.loadInstrument(instr[90]);
        } // try
        catch (Exception e) {
            System.err.println("Output constructor failed, exception: " + e.getMessage());
            System.exit(0);
        } // catch

        nuc = nucIn;
        bass = bassIn;

        iterNuc = nuc.iterator();
        iterBass = bass.iterator();
    } // Method - Output

    public void play() throws InterruptedException {
        while (iterNuc.hasNext()) {
            if (System.currentTimeMillis() >= noteStart + 300) {
                switch (((Character) iterNuc.next())) {
                    case 'A':
                        mc[5].noteOn(60, 600);
                        noteStart = System.currentTimeMillis();
                        break;
                    case 'C':
                        mc[5].noteOn(63, 600);
                        noteStart = System.currentTimeMillis();
                        break;
                    case 'T':
                        mc[5].noteOn(65, 600);
                        noteStart = System.currentTimeMillis();
                        break;
                    case 'G':
                        mc[5].noteOn(67, 600);
                        noteStart = System.currentTimeMillis();
                        break;
                } // switch
            } // if

            if (System.currentTimeMillis() >= bassStart + 900) {
                mc[5].allNotesOff();
                switch (((AminoAcid) iterBass.next()).getAAClass()) {
                    case 0:
                        mc[5].noteOn(60, 600);
                        bassStart = System.currentTimeMillis();
                        break;
                    case 1:
                        mc[5].noteOn(63, 600);
                        bassStart = System.currentTimeMillis();
                        break;
                    case 2:
                        mc[5].noteOn(65, 600);
                        bassStart = System.currentTimeMillis();
                        break;
                    case 3:
                        mc[5].noteOn(66, 600);
                        bassStart = System.currentTimeMillis();
                        break;
                    case 4:
                        mc[5].noteOn(67, 600);
                        bassStart = System.currentTimeMillis();
                        break;
                    case 5:
                        mc[5].noteOn(69, 600);
                        bassStart = System.currentTimeMillis();
                        break;
                } // switch
            } // if
        } // while
    } // Method - play
} // Class - Output
