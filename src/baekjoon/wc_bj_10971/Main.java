package baekjoon.wc_bj_10971;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] cost;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			visited[i] = true;
			min = Math.min(min, lowestCost(0,0,i,i));
			visited[i] = false;
		}
		System.out.println(min);
	}

	static int lowestCost(int sum, int cnt, int cur, int end) {
		if(cnt == N - 1) {
			if(cost[cur][end] != 0) return sum + cost[cur][end];
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			if(!visited[i] && cost[cur][i] != 0) {
				visited[i] = true;
				min = Math.min(min,lowestCost(sum + cost[cur][i], cnt+1, i, end));
				visited[i] = false;
			}
		}
		return min;
	}
}
