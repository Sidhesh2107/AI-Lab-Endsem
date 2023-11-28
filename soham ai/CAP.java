import java.util.*;

public class CAP {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String s1 = "KANSAS";
        String s2 = "OHIO";
        String s3 = "OREGON";
        Map<Character, Integer> map = new HashMap<>();

        for(int i=0; i<s1.length(); i++){
            if(!map.containsKey(s1.charAt(i)))
                map.put(s1.charAt(i), -1);
        }

        for(int i=0; i<s2.length(); i++){
            if(!map.containsKey(s2.charAt(i)))
                map.put(s2.charAt(i), -1);
        }

        for(int i=0; i<s3.length(); i++){
            if(!map.containsKey(s3.charAt(i)))
                map.put(s3.charAt(i), -1);
        }

        char[] unique = new char[map.size()];
        int k=0;
        for (char c: map.keySet()){
            unique[k++] = c;
        }

        boolean[] visited = new boolean[10];

        solution(0, map, unique, visited, s1, s2, s3);
    }

    public static void solution(int ind, Map<Character, Integer> map, char[] unique, boolean[] visited, String s1, String s2, String s3){

        if(ind >= unique.length){
            int n1 = convert(map, s1);
            int n2 = convert(map, s2);
            int n3 = convert(map, s3);

            if(n1 + n2 == n3){
                for(char c:map.keySet()){
                    System.out.print(c + ": " + map.get(c) + ", ");
                }
                System.out.println();
            }
            return;
        }


        for(int i=0; i<=9; i++){
            if(visited[i] == false) {
                map.put(unique[ind], i);
                visited[i] = true;
                solution(ind+1, map, unique, visited, s1, s2, s3);
                visited[i] = false;
                map.put(unique[ind], -1);
            }
        }
    }

    public static int convert(Map<Character, Integer> map, String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 10 + map.get(s.charAt(i));  // Updated numeric conversion
        }
        return num;
    }

}