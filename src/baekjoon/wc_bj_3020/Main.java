package baekjoon.wc_bj_3020;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] floor = new int[N/2];
        int[] ceil = new int[N/2];

        for(int i = 0; i < N; i++){
            if(i % 2 == 0){
                floor[i/2] = Integer.parseInt(br.readLine());
            }else{
                ceil[i/2] = Integer.parseInt(br.readLine());
            }
        }

        Arrays.sort(floor);
        Arrays.sort(ceil);

        // 이분탐색
        int min = Integer.MAX_VALUE;
        int num = 0;
//        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= H; i++){
            int temp = BS(floor, i) + BS(ceil, H-i+1);
//            System.out.println(BS(floor,i) + " " + BS(ceil,H-i+1));
            // 최소값 갱신
            if(temp < min) {
                min = temp;
                num = 1;
//                list.clear();
//                list.add(i);
            }else if(temp == min){
                ++num;
//                list.add(i);
            }
        }
//        for(int k : list){
//            System.out.print(k + " ");
//        }
//        System.out.println();
        System.out.println(min + " " + num);
    }

    // lower bound left bs
    private static int BS(int[] arr, int i) {
        int left = 0;
        int right = arr.length - 1;

        int idx = arr.length;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] < i){
                left = mid + 1;
            }else{
                right = mid - 1;
                idx = mid;
            }
        }
//        if(idx == -1) idx = left;
        return arr.length - idx;
    }
}
