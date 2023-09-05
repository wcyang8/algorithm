package baekjoon.wc_bj_2003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];

        int left = 0;
        int right = 0;
        int cur = A[0];
        int res = 0;
        while(right < N){
            if(cur <= M){
                if(cur == M) res++;
                if(++right >= N) break;
                cur += A[right];
            }else {
                cur -= A[left++];
            }
        }
        System.out.println(res);
    }
}
