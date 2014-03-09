/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proteinmusic;

/**
 *
 * @author Gareth
 */
public class MusicMapping {
    
    StatCollector stats;
    private int[][] scales;
    private int[][][] scaleChords;
    private double[][] probMapping;
    int chordScale;
    
    public MusicMapping(StatCollector statsIn) {
        stats = statsIn;
        scaleChords = new int[][][] {{{48,52,55},{50,53,57},{52,55,59},{53,57,60},
                            {55,59,62},{57,60,64},{59,62,65}}};
        scales = new int[][] {{48,50,52,53,55,57,59,60,62,64,65,67,69,71,72,74,
                                76,77,79,81,83}};
        probMapping = new double[][] {
            {stats.getPerClasses()[6], stats.getPerAAs()[14]}, // 0.05
            {stats.getPerAAs()[11], stats.getPerAARuns()[1]}, // 0.1
            {stats.getPerClasses()[4], stats.getPerForRuns()[1]}, // 0.15
            {stats.getPerClasses()[1], stats.getPerNucRuns()[1]}, // 0.2
            {stats.getPerPolarities()[1], 1 - stats.getPerBackRuns()[0]}, // 0.25
            {stats.getPerClasses()[0],
                1 - stats.getPerPolarities()[0] - stats.getPerPolarities()[1]}, // 0.3
            {1 - stats.getPerNucRuns()[0],
                1 - stats.getPerClasses()[0] - stats.getPerClasses()[1] 
                    - stats.getPerClasses()[4]}, // 0.35
            {1 - stats.getPerPolarityRuns()[0],
                stats.getPerPolarities()[1] + stats.getPerPolarities()[2]}, // 0.4
            {stats.getPerClasses()[0] + stats.getPerClasses()[5],
                stats.getPerPolarities()[0]}, // 0.45
            {stats.getPerClasses()[0] + stats.getPerClasses()[1],
                stats.getPerPolarities()[0] + stats.getPerPolarities()[3]}, // 0.5
            {stats.getPerClasses()[0] + stats.getPerClasses()[1]
                + stats.getPerClasses()[6], 
                1 - stats.getPerPolarities()[0]}, // 0.55
            {stats.getPerPolarityRuns()[0],
                stats.getPerPolarities()[0] + stats.getPerPolarities()[2]}, // 0.6
            {stats.getPerNucRuns()[0], stats.getPerPolarityRuns()[0]}, // 0.65
            {1 - stats.getPerClasses()[0],
                stats.getPerPolarities()[0] + stats.getPerPolarities()[1]}, // 0.7
            {1 - stats.getPerPolarities()[1], stats.getPerClassRuns()[0]}, // 0.75
            {stats.getPerForRuns()[0], 1 - stats.getPerClassRuns()[1]}, // 0.8
            {stats.getPerNucRuns()[0] + stats.getPerNucRuns()[1],
                stats.getPerPolarityRuns()[0] + stats.getPerPolarityRuns()[1]}, // 0.85
            {stats.getPerAARuns()[0], 1 - stats.getPerClasses()[3]}, // 0.9
            {stats.getPerCodonRuns()[0], 1 - stats.getPerClasses()[2]} // 0.95 
        };
    }
    
    public Chord newChord(int chord, int scaleIndex) {
        Chord tempChord = new Chord(scaleChords[scaleIndex][chord-1][0],
                scaleChords[scaleIndex][chord-1][1],
                scaleChords[scaleIndex][chord-1][2]);
        return tempChord;
    }
    
    public double getProb(int probability) {
        int index = (probability * 20) - 1;
        double choice = Math.random() * 1;
        
        if (choice < 0.5) {
            return probMapping[index][0];
        } else {
            return probMapping[index][1];
        }
        
    }
    
    public double[] getPerAAs() {
        return stats.getPerAAs();
    }
    
    public double[] getPerCodons() {
        return stats.getPerCodons();
    }
    
    public double[] getPerClasses() {
        return stats.getPerClasses();
    }
    
    public double[] getPerPolarities() {
        return stats.getPerPolarities();
    }
    
    public double[] getPerNucRuns() {
        return stats.getPerNucRuns();
    }
    
    public double[] getPerForRuns() {
        return stats.getPerForRuns();
    }
    
    public double[] getPerBackRuns() {
        return stats.getPerBackRuns();
    }
    
    public double[] getPerCodonRuns() {
        return stats.getPerCodonRuns();
    }
    
    public double[] getPerAARuns() {
        return stats.getPerAARuns();
    }
    
    public double[] getPerClassRuns() {
        return stats.getPerClassRuns();
    }
    
    public double[] getPerPolarityRuns() {
        return stats.getPerPolarityRuns();
    }
    
}
