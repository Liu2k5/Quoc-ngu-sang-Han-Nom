package liu;

import java.io.Serializable;

public class WordPair implements Serializable, Comparable<WordPair> {
    private int firstWordID, secondWordID;
    private int frequency;
    
    public WordPair(int firstWordID, int secondWordID) {
        this.firstWordID = firstWordID;
        this.secondWordID = secondWordID;
        this.frequency = 1;
    }

    public int getFirstWordID() {
        return firstWordID;
    }

    public int getSecondWordID() {
        return secondWordID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incFrequency() {
        frequency++;
    }
    
    public void decFrequency() {
        frequency--;
    }
    
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(WordPair o) {
        if (this.getFrequency() < o.getFrequency()) {
            return 1;
        } else if (this.getFrequency() > o.getFrequency()) {
            return -1;
        } else {
            return 0;
        }
    }
}
