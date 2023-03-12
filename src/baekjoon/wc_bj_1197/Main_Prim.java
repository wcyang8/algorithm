package baekjoon.wc_bj_1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Prim {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// visited 배열 및 인접리스트 생성
		boolean[] visited = new boolean[V];
		List<int[]>[] adjList = new ArrayList[V];
		
		for(int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
		
		// 인접 리스트 받기
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new int[] {to, cost});
			adjList[to].add(new int[] {from, cost});
		}
		
		// 프림 알고리즘 시작
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return Integer.compare(o1[1], o2[1]);
		});	// 간선 저장
		pq.add(new int[] {0, 0});	// 0부터 시작
		
		int sum = 0;
		for(int i = 0; i < V; i++) {	// 정점을 V개 고를 때 까지
			int[] cur = pq.poll();
            if(visited[cur[0]]) {
				i--;
				continue;
			}
			visited[cur[0]] = true;		// pq 맨 위에 있는 정점 선택
			sum += cur[1];
			
			for(int[] next: adjList[cur[0]]) {		// 고른 정점과 연결된 간선 중에
				if(!visited[next[0]]) {			// 방문하지 않은 정점을 목적지로 하는 간선을 전부 pq에 add
					pq.add(next);
				}
			}
		}
		System.out.println(sum);
	}

}
