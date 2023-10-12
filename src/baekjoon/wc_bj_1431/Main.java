package baekjoon.wc_bj_1431;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] sn = new String[N];

        for(int i = 0; i < N; i++) {
            sn[i] = br.readLine();
        }

        Arrays.sort(sn, (o1,o2)->((o1.length() != o2.length())?(o1.length() - o2.length())
                :(sum(o1) != sum(o2))?sum(o1) - sum(o2):o1.compareTo(o2)));

        for(int i = 0; i < N; i++) {
            System.out.println(sn[i]);
        }
    }
    static int sum(String s) {
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                res += s.charAt(i) - '0';
            }
        }
        return res;
    }
}
