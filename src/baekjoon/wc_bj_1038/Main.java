package baekjoon.wc_bj_1038;

/**
 * 한 자리 수 중에 감소하는 수 9개
 * 
 * 두 자리 수 중에 감소하는 수 8 7 6 5 4 3 2 1 0
 * 
 * 세 자리 수 중에 감소하는 수 
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] dp = new int[20][10];
		
		for(int i = 1; i < 10; i++) dp[1][i] = 1;
		
		for(int i = 2; i < 20; i++) {
			for(int j = 1; j < 10; j++) {
				for(int k = 1; k < j; k++) {
					dp[i][j] += dp[i-1][k];
				}
			}
		}
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

}
