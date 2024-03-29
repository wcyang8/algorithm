package baekjoon.wc_bj_11404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. n개의 도시
 * 2. 한 도시에서 출발하여 다른 도시에 도착하는 m개의 버스
 * 3. 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 * 4. 모든 도시의 쌍(A,B)에 대해 A에서 B로 가는 비용의 최솟값을 구해보자.
 * 
 * 풀이
 * 1. 플로이드 워셜 알고리즘 사용.
 * 2. 초기화 값을 Integer.MAXVALUE로 하면 dp 덧셈 과정에서 음수가 된다.
 * 3. 따라서 100개의 도시를 100_000의 비용으로 거치는 최댓값인 10_000_000을 넣는다.
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
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
				if(i == p) continue;
				for(int j = 0; j < N; j++) {
					if(p == j || i == j) continue;
					dp[i][j] = Math.min(dp[i][p]+dp[p][j], dp[i][j]);		// p를 경유하는 경우 vs 그냥 바로 j로 가는 경우
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(dp[i][j] == 10_000_000?0:dp[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
