package baekjoon.wc_bj_6198;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<int[]> st = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        long sum = 0;
        for(int i = 0; i < N; i++){
            int cur = Integer.parseInt(br.readLine());
            // 스택 비어있거나 더 작으면 add
            while(!st.isEmpty() && st.peek()[1] <= cur){
                sum += i - st.pop()[0] - 1;
            }
            st.add(new int[] {i, cur});
        }

        while(!st.isEmpty()){
            sum += N - st.pop()[0] - 1;
        }

        System.out.println(sum);
    }
}
