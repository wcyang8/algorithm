package baekjoon.wc_bj_14427;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A[], N, segT[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N + 1];
        segT = new int[4*N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // st 구현
        build(1, 1, N);

        // build, query, update 3가지 구현

        // 쿼리 받기
        int M = Integer.parseInt(br.readLine());

        // 쿼리 처리
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd){
                case 1:
                    int i = Integer.parseInt(st.nextToken());
                    int v = Integer.parseInt(st.nextToken());
                    update(1,1,N,i,v);
                    break;
                case 2:

                    break;
                default:
                    break;
            }
        }
    }

    public static void build(int node, int start, int end){
        // 리프노드면 트리에 원소값 저장
        if(start == end){
            segT[node] = A[start];
            return;
        }

        int mid = (start + end)/2;
        // 왼쪽 자식으로 재귀
        build(node * 2, start, mid);
        // 오른쪽 자식으로 재귀
        build(node * 2 + 1, mid + 1, end);
        // 내부 노드면 자식 노드의 합 저장
        segT[node] = segT[node*2] + segT[node*2 + 1];
    }
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
    public static int query(int node, int start, int end, int left, int right){
        if(left <= start && end <= right){
            return segT[node];
        }

        int mid = (start + end)/2;
        int leftChild = query(node * 2, start,mid, left, right);
        int rightChild = query(node * 2 + 1, mid + 1, end, left, right);
        return leftChild + rightChild;
    }
}
