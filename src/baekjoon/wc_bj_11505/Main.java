package baekjoon.wc_bj_11505;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A[], N;
    static long segT[];
    static final int R = 1_000_000_007;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        segT = new long[4*N+1];

        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        // seg tree 초기화
        build(1,1,N);

//        System.out.println(Arrays.toString(segT));
        // update & query
        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch (a){
                case 1:
                    update(1,1,N,b,c);
//                    System.out.println(Arrays.toString(segT));
                    break;
                case 2:
                    if(b > c){
                        int temp = b;
                        b = c;
                        c = temp;
                    }
                    sb.append(query(1,1,N,b,c)).append("\n");
                    break;
                default:
                    break;
            }
        }
        System.out.print(sb);
    }
    private static void build(int node, int start, int end){
        if(start == end){
            segT[node] = A[start];
            return;
        }
        int mid = (start + end) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        segT[node] = (segT[node * 2] * segT[node * 2 + 1]) % R;
    }
    private static void update(int node, int start, int end, int b, int c){
        if(start == end){
            segT[node] = c;
            return;
        }
        int mid = (start + end) / 2;

        if(b <= mid){
            update(node * 2, start, mid, b, c);
        }else{
            update(node * 2 + 1, mid + 1, end, b, c);
        }
        segT[node] = (segT[node * 2] * segT[node * 2 + 1]) % R;
    }
    private static long query(int node, int start, int end, int b, int c){
        if(c < start || b > end){
            return 1;
        }
        if(b <= start && c >= end){
            return segT[node];
        }

        int mid = (start + end) / 2;
        long lc = query(node * 2, start, mid, b, c);
        long rc = query(node*2 + 1,mid + 1, end, b, c);

        return (lc * rc) % R;
    }
}
