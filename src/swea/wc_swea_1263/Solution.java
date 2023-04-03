package swea.wc_swea_1263;

/**
 * 요약
 * 1. CC(i) = dist(i,j) 의 합 (dist(i,j) = i부터 j까지의 최단거리
 * 2. 각 사람마다 최단거리를 전부 더해 그 중 최소값을 구해보자.
 * 
 * 풀이
 * 1. 거리를 1000으로 초기화
 * 2. 서로 연결되어 있으면 거리 1로 갱신.
 * 3. 플로이드 워셜 알고리즘으로 각 사람간 최단거리 구하기.
 * 4. for문으로 사람간 최단거리 합을 구해서 합의 최소값 구하기.
 * 
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[N][N];
			
			for(int i = 0; i < N; i++) Arrays.fill(dp[i], 1000);
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					
					if(temp == 1) dp[i][j] = temp;
				}
			}
			
			for(int p = 0; p < N; p++) {
				for(int i = 0; i < N; i++) {
					if(p == i) continue;
					for(int j = 0; j < N; j++) {
						if(i == j || j == p) continue;
						dp[i][j] = Math.min(dp[i][j], dp[i][p]+dp[p][j]);
					}
				}
			}
			
			int min = 1000000;
			for(int i = 0; i < N; i++) {
				int sum = 0;
				for(int j = 0; j < N; j++) {
					if(dp[i][j] != 1000) sum += dp[i][j];
				}
				min = Math.min(min, sum);
			}
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		System.out.print(sb);
	}
}