package cn;

public class No5 {
    public String replaceSpace(String s) {
        if(s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder(s);

        StringBuilder resb = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if(c == ' ') {
                resb.append("%20");
            }else
            {
                resb.append(c);
            }
        }

        return resb.toString();
    }
}
