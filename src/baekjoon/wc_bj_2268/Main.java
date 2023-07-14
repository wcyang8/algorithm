package baekjoon.wc_bj_2268;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A[], N;
    static long segT[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        segT = new long[4*N + 1];

//        st = new StringTokenizer(br.readLine());
//        for(int i = 0; i < N; i++){
//            A[i] = Integer.parseInt(st.nextToken());
//        }

        // st 구현
//        build(1, 1, N);

        // build, query, update 3가지 구현

        // 쿼리 받기
        int M = Integer.parseInt(st.nextToken());

        // 쿼리 처리
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            switch (cmd){
                case 0:
                    if(i > j){
                        int temp = i;
                        i = j;
                        j = temp;
                    }
                    sb.append(query(1,1,N,i , j)).append("\n");
                    break;
                case 1:
                    update(1,1,N,i, j);
                    break;
                default:
                    break;
            }
        }
        System.out.print(sb);
    }

//    public static void build(int node, int start, int end){
//        // 리프노드면 트리에 원소값 저장
//        if(start == end){
//            segT[node] = A[start];
//            return;
//        }
//
//        int mid = (start + end)/2;
//        // 왼쪽 자식으로 재귀
//        build(node * 2, start, mid);
//        // 오른쪽 자식으로 재귀
//        build(node * 2 + 1, mid + 1, end);
//        // 내부 노드면 자식 노드의 합 저장
//        segT[node] = segT[node*2] + segT[node*2 + 1];
//    }
    public static void update(int node, int start, int end, int i, int v){
        if(start == end){
            segT[node] = v;
            return;
        }
        int mid = (start + end) / 2;

        if(i <= mid){
            update(node * 2, start, mid, i, v);
        }else{
            update(node * 2 + 1, mid + 1, end, i, v);
        }
        segT[node] = segT[node*2] + segT[node*2 + 1];
    }
    public static long query(int node, int start, int end, int left, int right){
        if(right < start || end < left){
            return 0;
        }

        if(left <= start && end <= right){
            return segT[node];
        }

        int mid = (start + end)/2;
        long leftChild = query(node * 2, start,mid, left, right);
        long rightChild = query(node * 2 + 1, mid + 1, end, left, right);
        return leftChild + rightChild;
    }
}
