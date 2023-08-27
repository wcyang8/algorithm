package baekjoon.wc_bj_14916;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        if(N == 1 || N == 3){
            System.out.println(-1);
            return;
        }

        int five = N / 5;

        int left = N % 5;

        if(left % 2 == 1){
            --five;
            left += 5;
        }

        left /= 2;

        System.out.println(five + left);
    }
}
