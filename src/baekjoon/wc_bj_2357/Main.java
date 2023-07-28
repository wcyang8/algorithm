package baekjoon.wc_bj_2357;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] A, minSt, maxSt;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        A = new int[N + 1];

        // original 배열 입력
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        // 최소값 segTree
        minSt = new int[4*N + 1];

        maxSt = new int[4*N + 1];

        // build
        buildMin(1,1,N);
        buildMax(1,1,N);

        // query
        for(int i = 0;i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(queryMin(1,1,N,a,b))
                    .append(" ")
                    .append(queryMax(1,1,N,a,b))
                    .append("\n");
        }

        // 정답 출력
        System.out.print(sb);
    }

    // segment tree build
    static void buildMin(int node, int start, int end){
        // 루트 노드인 경우
        if(start == end){
            minSt[node] = A[start];
            return;
        }

        int mid = (start + end) / 2;
        // 왼쪽 자식으로
        buildMin(node*2, start, mid);
        // 오른쪽 자식으로
        buildMin(node*2+1, mid+1, end);
        // 자식들 중 작은 값 저장
        minSt[node] = Math.min(minSt[node * 2],minSt[node*2+1]);
    }
    static void buildMax(int node, int start, int end){
        // 루트 노드인 경우
        if(start == end){
            maxSt[node] = A[start];
            return;
        }

        int mid = (start + end) / 2;
        // 왼쪽 자식으로
        buildMax(node*2, start, mid);
        // 오른쪽 자식으로
        buildMax(node*2+1, mid+1, end);
        // 자식들 중 작은 값 저장
        maxSt[node] = Math.max(maxSt[node * 2],maxSt[node*2+1]);
    }

    // query
    static int queryMin(int node, int start, int end, int a, int b){
        if(a > end || b < start){
            return Integer.MAX_VALUE;
        }
        if(a <= start && b >= end){
            return minSt[node];
        }

        int mid = (start + end) / 2;
        int lc = queryMin(node*2, start, mid, a, b);
        int rc = queryMin(node*2+1,mid+1,end,a,b);

        return Math.min(lc,rc);
    }

    static int queryMax(int node, int start, int end, int a, int b){
        if(a > end || b < start){
            return 0;
        }
        if(a <= start && b >= end){
            return maxSt[node];
        }

        int mid = (start + end) / 2;
        int lc = queryMax(node*2, start, mid, a, b);
        int rc = queryMax(node*2+1,mid+1,end,a,b);

        return Math.max(lc,rc);
    }
}
