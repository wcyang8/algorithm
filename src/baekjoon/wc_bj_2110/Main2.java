package baekjoon.wc_bj_2110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] house = new int[N];

        for(int i = 0; i < N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int range = house[N-1] - house[0];

        // 1부터 range 중에 ans 찾기 (bs)
        int left = 1;
        int right = range;
        int ans = 0;
        while(left <= right){
            int mid = (left + right) / 2;
            if(check(house, mid, C)){     // lower bound
                ans = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
    private static boolean check(int[] house, int t, int C){
        int cnt = 1;
        int start = house[0];
        for(int i = 1; i < house.length; i++){
            if(house[i] - start >= t){
                start = house[i];
                ++cnt;
            }
//            if(cnt >= C) break;
        }
        if(cnt >= C){
            return true;
        }
        return false;
    }
}
