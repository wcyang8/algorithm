package baekjoon.wc_bj_12865;

/**
 * 요약
 * 1. 냅색 문제
 * 
 * 풀이
 * 1. 1차원 배열 사용
 * 2. 저장할 때 오른쪽부터 dp
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] product = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			product[i][0] = Integer.parseInt(st.nextToken());
			product[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[K+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = K; j >= 0; j--) {
				if(j >= product[i][0])
					dp[j] = Math.max(dp[j], dp[j-product[i][0]] + product[i][1]);
			}
		}
		System.out.println(dp[K]);
	}

}
