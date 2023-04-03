package baekjoon.wc_bj_1956;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N][N];
		
		for(int i = 0; i < N; i++) Arrays.fill(dp[i], 10_000_000);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			dp[from][to] = Math.min(dp[from][to], cost);	// A에서 B로 가는 간선이 여러개일 수 있음.
		}
		
		for(int p = 0; p < N; p++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dp[i][j] = Math.min(dp[i][p]+dp[p][j], dp[i][j]);		// p를 경유하는 경우 vs 그냥 바로 j로 가는 경우
				}
			}
		}
		
		int min = 10_000_000;
		for(int i = 0; i < N; i++) min = Math.min(min, dp[i][i]);
		System.out.println(min == 10_000_000? -1:min);
	}

}
