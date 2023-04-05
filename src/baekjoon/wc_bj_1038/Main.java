package baekjoon.wc_bj_1038;

import java.util.Arrays;

/**
 * 풀이
 * 
 * 1. 가장 앞자리가 j인 i자리 감소하는 수의 개수
 * 
 * 2. 
 * 
 * 
 */

import java.util.Scanner;

public class Main {

	static int num, N;
	static int[] selected = new int[10];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		int[][] dp = new int[20][10];

		for (int i = 1; i < 10; i++)
			dp[1][i] = 1;

//		int num = 0;
//		for(int i = 2; i < 20; i++) {
//			for(int j = 1; j < 10; j++) {
//				for(int k = 1; k < j; k++) {
//					dp[i][j] += dp[i-1][k];
//				}
//				num += dp[i][j];
//				if(num > N) {
//					num -= dp[i][j];
//					// 가장 앞자리가 j인 i자리 감소하는 수 중에 있다는 뜻
//					
//				}
//			}
//		}
//		for(int i = 0; i < 20; i++) {
//			for(int j = 0; j < 9; j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}

		for (int i = 1; num < N; i++) {
			comb(i, 0, 0);
		}
		System.out.println(Arrays.toString(selected));
	}

	private static void comb(int k, int len, int start) {
		if (num == N)
			return;
		if (k == len) {
			++num;
			System.out.println(num);
			System.out.println(Arrays.toString(selected));
			return;
		}
		for (int i = start; i < 10; i++) {
			selected[len] = i;
			comb(k, len + 1, i + 1);
		}
	}

}
