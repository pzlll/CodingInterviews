package tree;

import java.util.*;

public class ThroneInheritance {
    private Map<String, List<String>> map;
    private Set<String> dead;
    private String root;
    public ThroneInheritance(String kingName) {
        map = new HashMap<>();
        dead = new HashSet<>();
        root = kingName;
        map.put(kingName, null);
    }

    public void birth(String parentName, String childName) {
        if(map.containsKey(parentName)) {
            List<String> list = map.get(parentName);
            if(list == null) {
                list = new ArrayList<>();
            }
            list.add(childName);
            map.put(parentName, list);
            map.put(childName, null);

        }
    }



    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> list = new ArrayList<>();
        preOrder(list, root);
        return list;
    }

    private void preOrder(List<String> list, String p) {
        if(!dead.contains(p))
            list.add(p);

        List<String> children = map.get(p);
        if(children != null) {
            for (String child :
                    children) {
                preOrder(list, child);
            }
        }
    }


}

