
import org.jfugue.Player;

/**
 *Author: Lisa Bai on 11/7/14.
 * Title: Music Cipher
 *
 *This program acts like a cipher, interpreting and playing each alphabetical letter into a music note.
 * The first letter of the input message determines the Key Signature, tempo, instrument, cadences, and more.
 */

public class musicCipher {
    public String instrument;
    public String keySignature;
    public String musicComponents;
    public int instrumentNumber;
    public int tempoMarking;
    public String tonic;
    public String perfect5th;
    public String perfect4th;
    public static Player player = new Player();
    private Character[] durationValues = {'h','q','i'};
    private String[] jFugueValues = {"A","B","C","D","E","F","G","R"};
    public char first;
    private final String[] majorKeySignatures = new String[] { "Cb", "Gb", "Db", "Ab", "Eb", "Bb", "F", "C", "G", "D", "A", "E", "B", "F#", "C#" };
    private final String[] minorKeySignatures = new String[] { "Ab", "Eb", "Bb", "F", "C", "G", "D", "A", "E", "B", "F#", "C#", "G#", "D#", "A#" };


    public int letterValue(char c){
        return Math.abs('A'-c)%jFugueValues.length;
    }

    public int keySignatureValue(char c){
        return Math.abs('A'-c)%minorKeySignatures.length;
    }

    public int getInstrumentNumber(char c){
        int i = first;
        return (i%127);
    }

    public String getNote(int idx){
        return jFugueValues[idx]+durationValues[idx%durationValues.length]+ " ";
    }



/*
Determines Major vs. Minor, Key Signature, tempo marking, tonic, perfect 4th, and perfect 5th

Special cases:
if tonicIndex is last element in minorKeySignaturesArray, perfect 5th is first element
if tonicIndex is first element in minorKeySignaturesArray, perfect 4th is last element
if tonicIndex is last element in majorKeySignaturesArray, perfect 5th is first element
if tonicIndex is first element in majorKeySignaturesArray, perfect 4th is last element

*/
public String getKeySignature(int index){
        StringBuilder keySignatureBuilder = new StringBuilder();
         tempoMarking = (int) first ;

        if(tempoMarking<90){
            tempoMarking=tempoMarking*2;

            keySignatureBuilder.append("T"+Integer.toString(tempoMarking)+" K");}
        else {
            keySignatureBuilder.append("T"+Integer.toString(tempoMarking)+" K");}

        if(index%2==0){
            tonic = minorKeySignatures[index];
            keySignatureBuilder.append(tonic);
            keySignatureBuilder.append("min");
            keySignature = tonic + " Minor";

            if(index==(minorKeySignatures.length-1)) {
                perfect5th = minorKeySignatures[3];
            }

            else {
                perfect5th = minorKeySignatures[((index + 1) % minorKeySignatures.length)];
            }

            if(index==0){
                perfect4th=minorKeySignatures[11];
            }
            else{
                perfect4th = minorKeySignatures[((index) % minorKeySignatures.length)-1];
            }
        }

        else{
            tonic = majorKeySignatures[index];
            keySignatureBuilder.append(tonic);
            keySignatureBuilder.append("maj");
            keySignature = tonic + " Major";

            if(index==(majorKeySignatures.length-1)){
                perfect5th=majorKeySignatures[3];
            }
            else{
                perfect5th = majorKeySignatures[(index + 1) % majorKeySignatures.length];
            }

            if(index==0){
                perfect4th=majorKeySignatures[11];
            }
            else {
                perfect4th = majorKeySignatures[((index) % majorKeySignatures.length)-1];
            }
        }
        return keySignatureBuilder.toString();
    }

    public void playMessage(String message){
        int keyAndInstrument = keySignatureValue(first);
        instrumentNumber = getInstrumentNumber(first);
        musicComponents = getKeySignature(keyAndInstrument);
        instrument = "I["+instrumentNumber+"] ";
        player.play(musicComponents + " " + instrument + message);

        System.out.println("Key Signature: " + keySignature);
        System.out.println("Tempo: "+tempoMarking+" BPM");
        System.out.println("Instrument Number:" + instrumentNumber);
        System.out.println("Music Notes: "+message);
    }


    public void runApp(String message) {
        String music = "";
        first = message.charAt(0);
        for (int i = 0; i < message.length(); i++) {
            char y = message.charAt(i);
            int loc = letterValue(y);
            music+=getNote(loc);
        }

        getKeySignature(Math.abs('A'-first)%minorKeySignatures.length);
        tonic = tonic +"4w ";
        perfect5th=perfect5th+"5h ";
        perfect4th = perfect4th+"5h ";
        music=tonic+perfect4th+music+"Rx "+perfect5th+tonic;
        playMessage(music);

    }

    public static void main(String[] args) {

    }
}
