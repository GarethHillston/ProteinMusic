========================
 How to use the program
========================

The program takes plain text files of DNA sequences of the from acgt. The ones used
for this project were from the National Biological Institute's public database;
http://www.ncbi.nlm.nih.gov/bioproject/browse/ if you want to get your own data.
The sequences that the project at present is calibrated for (and the provided files)
are of Salmonella DNA.

Run as a normal .jar file, the song will play after a slight pause;
$java -jar ProteinMusic.jar X Y Z

X = file name (sal1 - sal19)

Y = chord sequencer type;
0 = Random chords
1 = Chords translated directly from the amino acid classes
2 = Chords generated using a markov model and statistics gathered on the DNA sequence
3 = Chords generated from the melody (only works with Extraction note sequencer - 2)

Z = note sequencer type;
0 = Arpeggiating note sequencer, just goes up and down
1 = Note generated using a markov model similar to the chord model
2 = Generates an 8-bar riff starting on each amino acid by directly translating
the amino acid polarities into notes. Selects the best riffs based on a scoring system
and patches them together according to a given structure to create a melody. (only
works with Note Derived chord sequencer - 3)

If the extraction/note derived combination produces a very repetitive song try another
input file, some don't provide a lot for the program to work with.

Thanks, hope it doesn't offend your musical sensibilities.