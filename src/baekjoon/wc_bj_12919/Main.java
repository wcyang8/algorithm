package baekjoon.wc_bj_12919;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        System.out.println(isPossible(S,T)?1:0);
    }

    private static boolean isPossible(String s, String t) {
        if(t.length() == s.length()){
            return s.equals(t);
        }
        boolean res = false;
        if(t.length() == 1 || t.length() < s.length()) return res;
        if(t.charAt(t.length()-1) == 'A') res = isPossible(s, t.substring(0, t.length() - 1));
        if(t.charAt(0) == 'B') res = res || isPossible(s, reverse(t.substring(1)));
        return res;
    }

    private static String reverse(String substring) {
        StringBuilder sb = new StringBuilder(substring);

        return sb.reverse().toString();
    }
}
