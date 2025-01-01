package liu;

public class NumbersAndPuctuations {
    private String qnguhas =".,;\"0123456789"; // +*:?!“”=…/-()[]{}'
    private String hnomhas ="。，；\"0123456789"; // ＋＊：？！「」=…/—（）｛｝'    the origin is in front of the cross character (/)
    
    public String getQnguHas() {
        return qnguhas;
    }

    public String getHnomHas() {
        return hnomhas;
    }

    public boolean isQngu(char c) {
        return qnguhas.contains(c + "");
    }

    public boolean isHnom(char c) {
        return hnomhas.contains(c + "");
    }

    public char convert(char c) {
        int i = qnguhas.indexOf(c);
        if (i >= 0) {
            return hnomhas.charAt(i);
        } else {
            i = hnomhas.indexOf(c);
            if (i >= 0) {
                return qnguhas.charAt(i);
            }
        }
        return c;
    }
}
