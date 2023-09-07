package baekjoon.wc_bj_1275;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] A, sgt;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        A = new long[N+1];
        sgt = new long[4*N+1];

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

            if(x > y){
                int temp = x;
                x = y;
                y = temp;
            }

            sb.append(query(1,1,N,x,y)).append("\n");
            update(1,1,N,a,b);

        }
        System.out.print(sb);
    }

    private static void update(int node, int start, int end, int a, int b) {
        if(start == end){
            sgt[node] = b;
            return;
        }

        int mid = (start + end) / 2;
        if(start <= a && mid >= a) update(node*2,start,mid, a, b);
        else if(end >= a && mid < a) update(node*2 + 1, mid+1,end, a, b);
        sgt[node] = sgt[node*2] + sgt[node*2+1];
    }

    private static long query(int node, int start, int end, int x, int y) {
        // 범위 밖으로 넘어가면 out
        if(x > end || y < start){
            return 0;
        }

        // [x,y] 안에 [start,end] 가 있다
        if(x <= start && y >= end){
            return sgt[node];
        }

        int mid = (start + end) / 2;
        long leftC = query(node * 2, start, mid, x, y);
        long rightC = query(node * 2 + 1, mid + 1, end, x, y);

        return leftC + rightC;
    }

    private static void build(int node, int start, int end) {
        if(start == end){
            sgt[node] = A[start];
            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid+1, end);
        sgt[node] = sgt[node * 2] + sgt[node * 2 + 1];
    }
}
