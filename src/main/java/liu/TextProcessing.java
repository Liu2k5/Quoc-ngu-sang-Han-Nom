package liu;

public class TextProcessing {
    private String uppercasechars = "AĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXYÁÀẢÃẠẮẰẲẴẶẤẦẨẪẬÉÈẺẼẸẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌỐỒỔỖỘỚỜỞỠỢÚÙỦŨỤỨỪỬỮỰÝỲỶỸỴÐ"; // Ð is capital form of ð
    private String lowercasechars = "aăâbcdđeêghiklmnoôơpqrstuưvxyáàảãạắằẳẵặấầẩẫậéèẻẽẹếềểễệíìỉĩịóòỏõọốồổỗộớờởỡợúùủũụứừửữựýỳỷỹỵđ";

    private String anotherwritingways[] =
    {"ỳ", "óa", "òa", "ỏa", "õa", "ọa", "óe", "òe", "ỏe", "õe",
    "ọe", "úy", "ùy", "ủy", "ũy", "ụy", "hy", "hý", "hỳ", "hỷ",
    "ly", "lý", "lỳ", "lỵ", "my", "mỳ", "mỹ", "mỵ", "ny", "sỹ",
    "ty", "tý", "tỳ", "tỷ", "tỵ", "vy", "vỹ", "thy", "qui", "quí",
    "quì", "quỉ", "quĩ", "quị" , "“", "”"}; // , "“", "”"   “”
    private String newwritingways[] = 
    {"ì", "oá", "oà", "oả", "oã", "oạ", "oè", "oẻ", "oé", "oẽ",
    "oẹ", "uý", "uỳ", "uỷ", "uỹ", "uỵ", "hi", "hí", "hì", "hỉ",
    "li", "lí", "lì", "lị", "mi", "mì", "mĩ", "mị", "ni", "sĩ",
    "ti", "tí", "tì", "tỉ", "tị", "vi", "vĩ", "thi", "quy", "quý",
    "quỳ", "quỷ", "quỹ", "quỵ" , "\"", "\""}; // , "\"", "\""

    public String toLowerCase(String string) {
        String temp = "";
        int tempPos;
        try {
            for (int i = 0; i < string.length(); i++) {
                tempPos = uppercasechars.indexOf(string.charAt(i));
                if (tempPos >= 0) {
                    temp += lowercasechars.charAt(tempPos);
                } else {
                    temp += string.charAt(i);
                }
            }
            return temp;
        } catch (Exception e) {
            System.out.println("Error in converting string to lower case form");
            return "ERROR";
        }
    }

    public String toNewWritingWay(String string) {
        String temp = string;
        // int tempPos;
        for (int i = 0; i < anotherwritingways.length; i++) {
            // tempPos = 0;
            // while (true) {
            //     tempPos = temp.indexOf(anotherwritingways[i], tempPos);
            //     if (tempPos == -1) {
            //         break;
            //     } else {
            //         temp = temp.substring(0, tempPos) + newwritingways[i] + temp.substring(newwritingways[i].length());
            //     }
            // }
            temp = temp.replace(anotherwritingways[i], newwritingways[i]);
        }
        return temp;
    }
}
