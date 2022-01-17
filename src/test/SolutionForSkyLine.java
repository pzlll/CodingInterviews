package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//class Node{
//    public Edge next;
//    public int[] property;
//
//    public int[] dist;
//
//    public Node(int n) {
//        this.property = new int[n];
//    }
//}
//class Edge{
//    public int vertex;
//    public Edge next;
//}
class Match{
    private int index;
    private int property;

    public Match() {
    }

    public Match(int index, int property) {
        this.index = index;
        this.property = property;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return index == match.index && property == match.property;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, property);
    }
}
public class SolutionForSkyLine {



    public static void main(String[] args) {


        long startTime=System.currentTimeMillis();

        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        Map<Integer, List<Integer>> reverseEdgeMap = new HashMap<>();
        Map<Integer, List<Integer>> propertyMap = new HashMap<>();
        int n = 0;
//        int m = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\课程\\算法导论\\实验二\\Yago\\Yago\\edge.txt"));
            String str;
            while ((str = br.readLine()) != null) {
                char[] chars = str.toCharArray();
                int i = 0;
                int index = 0;
                while(i < chars.length && chars[i] != ':') {
                    index = index * 10 + chars[i] - '0';
                    i++;

                }

                i++;
                i++;
                int vertex = 0;
                List<Integer> nextList = edgeMap.getOrDefault(index, new ArrayList<>());
                while (i < chars.length) {
                    while (i < chars.length && chars[i] != ',') {
                        vertex = vertex * 10 + chars[i] - '0';
                        i++;
                    }
                    List<Integer> reverseNextList = reverseEdgeMap.getOrDefault(vertex, new ArrayList<>());
                    n = Math.max(n, vertex);
                    nextList.add(vertex);
                    reverseNextList.add(index);
                    reverseEdgeMap.put(vertex, reverseNextList);
                    vertex = 0;
                    i++;
                }

                edgeMap.put(index, nextList);

            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\课程\\算法导论\\实验二\\Yago\\Yago\\node_keywords.txt"));
            String str;
            while ((str = br.readLine()) != null) {
                char[] chars = str.toCharArray();
//                String[] split = str.split(": ");
                int i = 0;
                int index = 0;
                while(i < chars.length && chars[i] != ':') {
                    index = index * 10 + chars[i] - '0';
                    i++;

                }

                i++;
                i++;


                int property = 0;
                List<Integer> list = propertyMap.getOrDefault(index, new ArrayList<>());
                while (i < chars.length) {
                    while (i < chars.length && chars[i] != ',') {
                        property = property * 10 + chars[i] - '0';
                        i++;

                    }

                    list.add(property);


                    property = 0;
                    i++;
                }


                propertyMap.put(index, list);

            }
        }catch (IOException e) {
            e.printStackTrace();
        }


        n = n + 1;
//        m = m + 1;


        Set<Integer> keyWords = new HashSet<>();

        keyWords.add(9971703);
        keyWords.add(9480506);
        keyWords.add(8950840);
        keyWords.add(6957909);
        keyWords.add(6269094);

        keyWords.add(5497009);
        keyWords.add(8056680);
        keyWords.add(5753616);
        keyWords.add(4471252);
        keyWords.add(7647509);

        keyWords.add(7669691);
        keyWords.add(1731569);
        keyWords.add(4861613);
        keyWords.add(7521569);
        keyWords.add(4835617);
        keyWords.add(3201791);
        keyWords.add(6169103);
        keyWords.add(945591);
        keyWords.add(6748680);
        keyWords.add(6520647);




        System.out.println(n);
//        System.out.println(m);
        Map<Match, Integer> dist = new HashMap<>();

        Map<Integer, List<Integer>> keywordMap = new HashMap<>();

        for (Integer i :
                edgeMap.keySet()) {

            if(propertyMap.get(i) == null) {
                continue;
            }
            for (Integer property :
                    propertyMap.get(i)) {
                if(keyWords.contains(property)) {
                    List<Integer>  vertex = keywordMap.getOrDefault(property, new ArrayList<>());
                    vertex.add(i);
                    keywordMap.put(property, vertex);
                }
            }
        }



        for (Map.Entry<Integer, List<Integer>> entry :
                keywordMap.entrySet()) {

            int key = entry.getKey();
            List<Integer> list = entry.getValue();

            for (int vertex :
                    list) {
                Queue<int[]> queue = new LinkedList<>();

                queue.add(new int[]{vertex, vertex});
                Match match = new Match(vertex, key);
                dist.put(match, 0);


                boolean[] visited = new boolean[n];
                while (!queue.isEmpty()) {
                    int[] poll = queue.poll();
                    int now = poll[0];
                    int pre = poll[1];
                    visited[now] = true;
                    Match nowMatch = new Match(now, key);
                    Integer nowDist = dist.getOrDefault(nowMatch, Integer.MAX_VALUE);
                    Match preMatch =  new Match(pre, key);
                    Integer preDist = dist.get(preMatch);
                    if(preDist >= nowDist) {
                        continue;
                    }
                    nowDist = Math.min(nowDist, preDist + 1);
                    dist.put(nowMatch, nowDist);

                    for (Integer next :
                            reverseEdgeMap.get(now)) {
                        if (!visited[next]) {
                            queue.offer(new int[]{next, now});
                        }
                    }

                }
            }
        }

        Set<Integer> sps = new HashSet<>();

        for (Integer vertex :
                edgeMap.keySet()) {
            boolean flag = false;
            if(!isAddr(dist, vertex, keyWords)) {
                continue;
            }
            if(sps.isEmpty()) {
                sps.add(vertex);
            }else {
                for (Integer sp : sps) {
                    if(isLess(dist, vertex, sp, keyWords)) {

                        sps.remove(sps);
                    }
                    if(isLess(dist, sp, vertex, keyWords)) {
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    sps.add(vertex);
                }
            }

        }


        for (Integer sp :
                sps) {
            System.out.println(sp);
        }

        System.out.println(sps.size());


        long endTime=System.currentTimeMillis();

        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");

    }

    private static boolean isAddr(Map<Match, Integer> dist, Integer vertex, Set<Integer> keywords) {
        for (Integer keyword :
                keywords) {
            Match match = new Match(vertex, keyword);
            if(dist.get(match) == null) {
                return false;
            }

        }
        return true;
    }

    private static boolean isLess(Map<Match, Integer> dist, Integer i, int j, Set<Integer> keywords) {
        boolean flag = false;

        for (Integer keyword :
                keywords) {
            Match iMatch = new Match(i, keyword);
            Match jMatch = new Match(j, keyword);
            Integer iDist = dist.get(iMatch);
            Integer jDist = dist.get(jMatch);
            if(iDist > jDist) {
                return false;
            }else if(iDist < jDist) {
                flag = true;
            }
        }

        return flag;
    }



}
