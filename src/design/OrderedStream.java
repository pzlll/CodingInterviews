package design;

import java.util.ArrayList;
import java.util.List;

public class OrderedStream {
    private String[] str;
    private int ptr;
    public OrderedStream(int n) {
        str = new String[n];
        ptr = 0;
    }

    public List<String> insert(int idKey, String value) {
        str[idKey - 1] = value;
        List<String> res = new ArrayList<>();
        if(ptr == idKey - 1) {
            while (ptr < str.length && str[ptr] != null) {
                res.add(str[ptr]);
                ptr++;
            }
        }

        return res;
    }

}
