package baekjoon.wc_bj_15988;

/**
 * 풀이
 * 1. DP
 * 2. 점화식 F(n) = F(n-1) + F(n-2) + F(n-3)
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int R = 1_000_000_009;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		long[][] mat = { { 1, 1, 1 }, { 1, 0, 0 }, { 0, 1, 0 } };

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			if (N <= 3) {
				switch (N) {
				case 1:
					sb.append(1).append("\n");
					break;
				case 2:
					sb.append(2).append("\n");
					break;
				case 3:
					sb.append(4).append("\n");
					break;
				}
				continue;
			}

			long[][] temp = pow(mat, N-3);
			sb.append((temp[0][0] * 4 + temp[0][1] * 2 + temp[0][2] * 1)%R).append("\n");
		}
		System.out.println(sb);
	}

	private static long[][] pow(long[][] mat, int n) {
		if (n == 1)
			return mat;
		long[][] temp = pow(mat, n/2);
		
		temp = mul(temp, temp);
		
		if(n % 2 == 1) {
			temp = mul(temp, mat);
		}
		
		return temp;
	}

	private static long[][] mul(long[][] A, long[][] B) {
		long[][] res = new long[A.length][A.length];
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < A.length; j++) {
				for(int k = 0; k < A.length; k++) {
					res[i][j] += A[i][k] * B[k][j];
					res[i][j] %= R;
				}
			}
		}
		return res;
	}

}
