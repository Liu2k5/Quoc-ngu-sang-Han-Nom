package liu;

import java.io.Serializable;

/**
 * Word is the smallest unit for processing texts.
 * @author Liu
 */
public class Word implements Serializable, Comparable<Word> {

    /**
     * qngu is for containing Quoc Ngu form of a word.
     * while hnom contains Han Nom form.
     */
    private String qngu;
    private String hnom;
    private int frequency;
    private int unicode;

    /**
     * when a word is added, it must has the initial frequency is one.
     * @param qngu
     * @param hnom
     */
    public Word(String qngu, String hnom) {
        this.qngu = qngu;
        this.hnom = hnom;
        this.frequency = 1;
        this.unicode = (int) hnom.charAt(0);
    }

    public String getQngu() {
        return qngu;
    }

    public String getHnom() {
        return hnom;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getUnicode() {
        return unicode;
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
    public int compareTo(Word o) {
        if (this.getFrequency() < o.getFrequency()) {
            return 1;
        } else if (this.getFrequency() > o.getFrequency()) {
            return -1;
        } else {
            return 0;
        }
    }
}
