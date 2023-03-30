package baekjoon.wc_bj_1162_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 1. 도로를 i개 지웠을 때 최소 거리 vs 도로를 i+1개 지웠을 때 최소 거리
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[][] dist = new long[N][K+1]; // 1부터 i까지 최단거리를 저장할 배열 (K개에 따라)

		for(int i = 0; i < N; i++) Arrays.fill(dist[i], Long.MAX_VALUE); // int 최대값으로 초기화

		List<int[]>[] adjList = new ArrayList[N]; // 인접 리스트 배열 초기화
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>(); // 인접 리스트 생성
		}

		for (int i = 0; i < M; i++) { // 간선 받기
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { to, cost });
			adjList[to].add(new int[] { from, cost });
		}
		
		
		PriorityQueue<long[]> pq = new PriorityQueue<>((o1,o2)-> Long.compare(o1[2], o2[2]));
		
		long ans = -1;
		pq.add(new long[] {0,0,0});		// [정점][지운 수][거리]
		dist[0][0] = 0;
		while(!pq.isEmpty()) {
			long[] cur = pq.poll();
			
			if(dist[(int)cur[0]][(int)cur[1]] < cur[2]) continue;
			
			if(cur[0] == N-1) {
				ans = cur[2];
				break;
			}
			
			for(int[] next : adjList[(int)cur[0]]) {
				if(dist[next[0]][(int)cur[1]] > cur[2] + next[1]) {
					dist[next[0]][(int)cur[1]] = cur[2] + next[1];
					pq.add(new long[] {next[0], cur[1], cur[2]+next[1]});
				}
				if(cur[1] < K && dist[next[0]][(int)cur[1]+1] > cur[2]) {
					dist[next[0]][(int)cur[1]+1] = cur[2];
					pq.add(new long[] {next[0], cur[1]+1, cur[2]});
				}
			}
		}
		System.out.println(ans);
	}

}