package baekjoon.wc_bj_18352;

/**
 * 요약
 * 1. 1번부터 N번까지 M개의 단방향 도로
 * 2. 모든 도로 거리는 1
 * 3. 최단 거리가 정확히 K인 모든 도시 번호 출력.
 * 
 * 풀이
 * 1. BFS로 탐색하면서
 * 2. 거리 K일 때의 도시 번호 sb에 넣어서 출력
 * 
 * 유의
 * 1. 연결 그래프? 상관 X
 * 2. 출력은 오름차순으로
 * 3. 단방향 도로.
 * 
 * @author SSAFY
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
		boolean[] visited = new boolean[N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		PriorityQueue<Integer> result = new PriorityQueue<>();
		q.add(X);
		q.add(0);
		visited[X] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			int dist = q.poll();
			if(dist == K) {
				result.add(cur);
				continue;
			}
			
			for(int next: adjList[cur]) {
				if(!visited[next]) {
					q.add(next);
					q.add(dist+1);
					visited[next] = true;
				}
			}
		}
		if(result.isEmpty()) {
			System.out.println(-1);
			return;
		}
		StringBuilder sb = new StringBuilder();
		while(!result.isEmpty()) {
			sb.append(result.poll()).append('\n');
		}
		System.out.println(sb);
	}

}
