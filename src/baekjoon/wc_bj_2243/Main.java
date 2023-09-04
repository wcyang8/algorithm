package baekjoon.wc_bj_2243;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] candy;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        candy = new int[N+1];
        sgt = new int[N * 4 + 1];

        build();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            if(a == 1){
                query();
                update();
            }else if(a == 2){
                update();
            }
        }
    }
}
