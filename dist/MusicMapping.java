/*
 * Provides mappings for integer values of notes and chords of relavent keys, as
 * well as probability data for various attributes from the nucleotide data,
 * in this case gathered on Salmonella DNA
 */

package proteinmusic;

/**
 *
 * @author Gareth
 */
public class MusicMapping {
    
    StatCollector stats;
    // Note mapping for the Extraction class
    private int notesAA[];
    // Notes in each scale (currently on C major)
    private int[][] scales;
    // Chord compositions for each chord in keys (currently on C major)
    private int[][][] scaleChords;
    // Probabilities of various attributes that will roughly map to given
    // probabilities 0.5 - 0.95
    private double[][] probMapping;
    int chordScale;
    
    public MusicMapping(StatCollector statsIn) {
        stats = statsIn;
        scaleChords = new int[][][] {{{48,52,55},{50,53,57},{52,55,59},{53,57,60},
                            {55,59,62},{57,60,64},{59,62,65}}};
        scales = new int[][] {{48,50,52,53,55,57,59,60,62,64,65,67,69,71,72,74,
                                76,77,79,81,83}};
        notesAA = new int[] {-1,45,47,48,50,52,53,55,57,59,60,62,64,65,
                                67,69,71,72,74,76,77,79,-1};
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
    } // Method - MusicMapping
    
    public Chord newChord(int chord, int scaleIndex) {
        Chord tempChord = new Chord(scaleChords[scaleIndex][chord-1][0],
                scaleChords[scaleIndex][chord-1][1],
                scaleChords[scaleIndex][chord-1][2]);
        return tempChord;
    } // Method - newChord
    
    public int[] getChord(int chord, int scaleIndex) {
        return scaleChords[scaleIndex][chord-1];
    } // Method - getChord
    
    public int getNote(AminoAcid acid) {
        return notesAA[acid.getIndex()];
    } // Method - getNote
    
    public double getProb(double probability) {
        int index = (int)(probability * 20) - 1;
        double choice = Math.random() * 1;
        
        if (choice < 0.5) {
            return probMapping[index][0];
        } else {
            return probMapping[index][1];
        }
        
    } // Method - getProb
    
    public double[] getPerAAs() {
        return stats.getPerAAs();
    } // Method - getPerAAs
    
    public double[] getPerCodons() {
        return stats.getPerCodons();
    } // Method - getPerCodons
    
    public double[] getPerClasses() {
        return stats.getPerClasses();
    } // Method - getPerClasses
    
    public double[] getPerPolarities() {
        return stats.getPerPolarities();
    } // Method - getPerPolarities
    
    public double[] getPerNucRuns() {
        return stats.getPerNucRuns();
    } // Method - getPerNucRuns
    
    public double[] getPerForRuns() {
        return stats.getPerForRuns();
    } // Method - getPerForRuns
    
    public double[] getPerBackRuns() {
        return stats.getPerBackRuns();
    } // Method - getPerBackRuns
    
    public double[] getPerCodonRuns() {
        return stats.getPerCodonRuns();
    } // Method - getPerCodonRuns
    
    public double[] getPerAARuns() {
        return stats.getPerAARuns();
    } // Method - getPerAARuns
    
    public double[] getPerClassRuns() {
        return stats.getPerClassRuns();
    } // Method - getPerClassRuns
    
    public double[] getPerPolarityRuns() {
        return stats.getPerPolarityRuns();
    } // Method - getPerPolarityRuns
    
} // Class - MusicMapping
