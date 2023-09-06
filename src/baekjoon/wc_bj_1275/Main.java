package baekjoon.wc_bj_1275;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] A, sgt;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        A = new int[N+1];
        sgt = new int[4*N+1];

        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        build(1,1,N);
        for(int q = 0; q < Q; q++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(query(1,1,N,x,y));
            update(1,1,N,a,b);
        }
    }

    private static void update(int i, int i1, int n, int a, int b) {
    }

    private static boolean query(int i, int i1, int n, int x, int y) {
        return false;
    }

    private static void build(int node, int start, int end) {
        if(start == end){
            sgt[start] = A[node];
            return;
        }
        int mid = (start + end) / 2;
    }
}
