package baekjoon.wc_bj_2110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] house = new int[N];

        int[] select = new int[C];

        for(int i = 0; i < N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        select[0] = house[0];

        select[C-1] = house[N-1];

        int t = (house[N-1] - house[0]) / (C-1);

        int mem = 0;
        for(int i = 1; i <= C - 2; i++){
            mem = bs(house, mem,select[0] + i * t);
            select[i] = house[mem++];
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < C; i++){
            min = Math.min(min,select[i] - select[i-1]);
        }

        for(int i = 0; i < C; i++){
            System.out.print(select[i] + " ");
        }
        System.out.println();

        System.out.println(min);
    }

    private static int bs(int[] house,int start, int t) {
        System.out.println("bs : "+start+" "+t);
        int left = start;
        int right = house.length - 1;
        int li = -1;
        int ri = -1;
        while(left < right){
            int mid = (left + right) / 2;
            if(house[mid] <= t){
                li = mid;
                left = mid + 1;
            }else{
                ri = mid;
                right = mid;
            }
        }
        System.out.println(li + " " + ri);
        if(li == -1) return ri;
        if(ri == -1) return li;
        return (t - house[li] > house[ri] - t)?ri:li;
    }
}
