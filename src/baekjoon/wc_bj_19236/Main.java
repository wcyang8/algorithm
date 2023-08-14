package baekjoon.wc_bj_19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int[][][] state = new int[4][4][2];

        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 2; k++){
                    state[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }


    }
}
