package liu;

import java.io.Serializable;

public class WordGroup implements Serializable, Comparable<WordGroup> {
    private int group[];
    private int frequency;

    public WordGroup(int[] group) {
        this.group = group;
        frequency = 1;
    }

    public int[] getGroup() {
        return group;
    }

    public void incFrequency() {
        frequency++;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(WordGroup wordGroup) {
        if (this.group.length > wordGroup.getGroup().length) {
            return -1;
        } else if (this.group.length < wordGroup.getGroup().length) {
            return 1;
        } else {
            return 0;
        }
    }

    
}
