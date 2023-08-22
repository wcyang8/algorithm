package baekjoon.wc_bj_3079;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 심사대 N개
 * 2. 심사 받는 사람 M명
 * 3. 심사대 마다 걸리는 시간이 있다
 * 심사 받는 최소 시간을 구하시오
 *
 * N 시간 이하로 걸린다고 하면,
 * 최대 몇명 심사 가능?
 * 으로 이분탐색
 */

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] T = new int[N];

        long limit = 0;
        for(int i = 0; i < N; i++){
            T[i] = Integer.parseInt(br.readLine());
            limit = Math.max(limit, T[i]);
        }
        // 가장 느린 심사대가 전부 처리하는게 time limit
        limit *= M;

        // 최소 시간 찾기
        long left = 1;
        long right = limit;
        long idx = -1;
        while(left <= right){
            long mid = (left + right) / 2;
            if(people(T,mid) < M){
                left = mid + 1;
            }else{
                right = mid - 1;
                idx = mid;
            }
        }

        System.out.println(idx);
    }

    // time 동안 몇명의 사람 처리 가능한지 return
    private static long people(int[] t, long time) {
        long ans = 0;
        for(int i : t){
            ans += time / i;
        }
        return ans;
    }
}
