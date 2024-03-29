package baekjoon.wc_bj_1162_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 클래스 사용
 * 
 * 
 * 
 * @author SSAFY
 *
 */

class move implements Comparable<move>{
	int vertex;
	int num;
	long dist;
	public move(int vertex, int num, long dist) {
		super();
		this.vertex = vertex;
		this.num = num;
		this.dist = dist;
	}
	@Override
	public int compareTo(move o) {
		return Long.compare(this.dist, o.dist);
	}
}

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
		
		
		PriorityQueue<move> pq = new PriorityQueue<>();
		
		long ans = -1;
		pq.add(new move(0,0,0));		// [정점][지운 수][거리]
		dist[0][0] = 0;
		while(!pq.isEmpty()) {
			move cur = pq.poll();
			
			if(dist[cur.vertex][cur.num] < cur.dist) continue;		// 이 한 줄이 시간초과 없애줌
																// 일반 다익과 차이 : 최단거리로 방문하고 또 방문하는 경우가 생김.
			if(cur.vertex == N-1) {
				ans = cur.dist;
				break;
			}
			
			for(int[] next : adjList[cur.vertex]) {
				if(dist[next[0]][cur.num] > cur.dist + next[1]) {
					dist[next[0]][cur.num] = cur.dist + next[1];
					pq.add(new move(next[0], cur.num, cur.dist+next[1]));
				}
				if(cur.num < K && dist[next[0]][cur.num+1] > cur.dist) {
					dist[next[0]][cur.num+1] = cur.dist;
					pq.add(new move(next[0], cur.num+1, cur.dist));
				}
			}
		}
		System.out.println(ans);
	}

}
