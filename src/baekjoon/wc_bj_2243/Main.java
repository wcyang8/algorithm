package baekjoon.wc_bj_2243;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] candy, sgt;
    static int N, R = 1_000_000;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        candy = new int[R+1];
        sgt = new int[R * 4 + 1];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 1){
                int x = find(b);
                sb.append(x).append("\n");
                update(1,1,R,x,-1);
            }else if(a == 2){
                int c = Integer.parseInt(st.nextToken());
                update(1,1,R,b,c);
            }
        }
        System.out.print(sb);
    }

    private static int find(int b) {
        int left = 1;
        int right = R;

        int res = 0;
        while(left <= right){
            int mid = (left + right) / 2;

            int cur = query(1,1,R,1,mid);

            if(cur >= b){
                res = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return res;
    }

    private static void update(int node, int start, int end, int idx, int gap) {
        if(start == end){
            sgt[node] += gap;
            return;
        }
        int mid = (start + end) / 2;

        if(idx <= mid){
            update(node*2, start, mid, idx, gap);
        }else{
            update(node*2+1, mid+1, end, idx, gap);
        }
        sgt[node] = sgt[node*2] + sgt[node*2+1];
    }

    public static int query(int node, int start, int end, int left, int right){
        if(right < start || end < left){
            return 0;
        }

        if(left <= start && end <= right){
            return sgt[node];
        }

        int mid = (start + end)/2;
        int leftChild = query(node * 2, start,mid, left, right);
        int rightChild = query(node * 2 + 1, mid + 1, end, left, right);
        return leftChild + rightChild;
    }
}
