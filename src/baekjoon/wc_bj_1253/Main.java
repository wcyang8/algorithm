package baekjoon.wc_bj_1253;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int A[], N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[N];

        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int ans = 0;
        // checkGood -> N개 탐색
        for(int i = 0; i < N; i++){
            // i번째 수가 good인가?
            if(checkGood(i)) ++ans;
        }
        System.out.println(ans);
    }

    private static boolean checkGood(int i) {
        // 0~N-1 선택 -> j
        for(int j = 0; j < N; j++){
            if(j == i) continue;
            // j+1~N-1(not i) 선택 -> k
            // 이분탐색으로 구현해야함
            int value = A[i] - A[j];
            int left = j+1;
            int right = N-1;
//            System.out.println("value = " + value);
            while(left <= right){
                int mid = (left + right)/2;
                if(A[mid] < value){
                    left = mid + 1;
                }else if(A[mid] > value){
                    right = mid - 1;
                }else{
                    if(mid == i){
                        // 찾은 수의 왼쪽도 같은 값이라면 true
                        if(i > 0 && j != i - 1 && A[i-1] == value) {
//                            System.out.println(i + " " + j + " " + mid);
//                            System.out.println(A[i] + " = " + A[j] + " + " + A[mid]);
                            return true;
                        }
                        // 찾은 수의 오른쪽도 같은 값이라면 true
                        if(i < N - 1 && j != i + 1 && A[i+1] == value) {
//                            System.out.println(i + " " + j + " " + mid);
//                            System.out.println(A[i] + " = " + A[j] + " + " + A[mid]);
                            return true;
                        }
                        break;
                    }
//                    System.out.println(i + " " + j + " " + mid);
//                    System.out.println(A[i] + " = " + A[j] + " + " + A[mid]);
                    return true;
                }
//                System.out.println("mid = " + mid);
            }
        }
        return false;
    }
}
