package baekjoon.wc_bj_2098;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] dist = new int[N][(1<<N)];		// 도시 방문 상태별 dist
		int[][] W = new int[N][N];				// 각 도시 간 방문 비용
		
		for(int i = 0; i < N; i++) Arrays.fill(dist[i], 20_000_000);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1[2], o2[2]));
		
		pq.add(new int[]{0,(1<<0),0});
		
		int min = 20_000_000;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(cur[1] == (1<<N) - 1 && W[cur[0]][0] != 0) {
				min = Math.min(min, cur[2] + W[cur[0]][0]);
				continue;
			}
			
			if(dist[cur[0]][cur[1]] < cur[2]) continue;
			
			for(int next = 0; next < N; next++) {
				if(W[cur[0]][next] != 0 && dist[next][cur[1]] > cur[2] + W[cur[0]][next] && (cur[1] & (1<<next)) == 0) {
					dist[next][cur[1]] = cur[2] + W[cur[0]][next];
					pq.add(new int[] {next, cur[1] | (1<<next), dist[next][cur[1]]});
				}
			}
		}
		System.out.println(min);
	}
}
