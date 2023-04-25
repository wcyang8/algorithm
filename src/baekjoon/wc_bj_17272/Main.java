package baekjoon.wc_bj_17272;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약 1. 1초 짜리 A 스킬과, M초 짜리 B 스킬 2. 싸움시간 N과 B의 스킬시전시간 M이 주어질때 3. 가능한 조합의 수를
 * 1_000_000_007으로 나눈 나머지 값을 출력하자.
 * 
 * 풀이 1. 점화식을 세운다. 2. f(n) = f(n-1) + f(n-k) 3. 행렬 거듭 제곱을 이용하여 n을 구한다.
 * 
 * 배운 것
 * 1. memoization을 쓰면 시간 더 절약 가능?
 * 
 * @author SSAFY
 *
 */

public class Main {

	static final int F = 1_000_000_007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[][] mat = new long[M][M];

		// N < M이면 1 출력 후 종료
		if(N < M) {
			System.out.println(1);
			return;
		}
		
		// 행렬 만들기
		makeMat(mat, M);
		// 행렬 거듭제곱하기
		mat = pow(mat, N - M + 2);
		// 행렬의 0열 x f(1~M)
		int ans = 0;
		for (int i = 0; i < M - 1; i++) {
			ans += mat[0][i];
			ans %= F;
		}

		System.out.println(ans);
	}

	private static void printM(long[][] mat) {
		System.out.println("========");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("========");
	}

	private static long[][] pow(long[][] mat, long i) {
		if (i == 1)
			return mat;
		// 거듭제곱 횟수의 절반을 제곱
		long[][] temp = pow(mat, i / 2);

		// 행렬 곱셈
		long[][] ans = mul(temp, temp);
		if (i % 2 == 1)
			ans = mul(ans, mat);

		return ans;
	}

	private static long[][] mul(long[][] A, long[][] B) {
		long[][] res = new long[A.length][A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				for (int k = 0; k < A.length; k++) {
					res[i][j] += A[i][k] * B[k][j];
					res[i][j] %= F;
				}
			}
		}
		return res;
	}

	public static void makeMat(long[][] mat, int M) {
		// f(n) = f(n-1) + f(n-k)
		mat[0][0] = 1;
		mat[0][M - 1] = 1;

		// f(n-j) = f(n-j)
		for (int i = 1; i < M; i++) {
			mat[i][i - 1] = 1;
		}
	}
}
