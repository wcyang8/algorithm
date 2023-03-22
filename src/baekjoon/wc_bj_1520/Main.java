package baekjoon.wc_bj_1520;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M][N];
		int[][] dp = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o2,o1)
				->Integer.compare(map[o1[0]][o1[1]], map[o2[0]][o2[1]]));

		dp[0][0] = 1;
		pq.add(new int[] {0,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			for(int k = 0; k < 4; k++) {
				int[] next = {cur[0]+d[k][0],cur[1] + d[k][1]};
				if(next[0] >= 0 && next[0] < M && next[1] >= 0 && next[1] < N) {
					if(map[cur[0]][cur[1]] > map[next[0]][next[1]]) {
						if(dp[next[0]][next[1]] == 0) pq.add(next);
						dp[next[0]][next[1]] += dp[cur[0]][cur[1]];
					}
				}
			}
		}
		System.out.println(dp[M-1][N-1]);
	}

}
