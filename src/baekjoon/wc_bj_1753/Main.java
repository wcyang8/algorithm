package baekjoon.wc_bj_1753;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class edge implements Comparable<edge>{
	int src;
	int dest;
	int cost;
	public edge(int src, int dest, int cost) {
		super();
		this.src = src;
		this.dest = dest;
		this.cost = cost;
	}
	@Override
	public int compareTo(edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}

class pair implements Comparable<pair>{
	int v;
	int totalDist;
	public pair(int v, int totalDist) {
		super();
		this.v = v;
		this.totalDist = totalDist;
	}
	@Override
	public int compareTo(pair o) {
		return Integer.compare(this.totalDist, o.totalDist);
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine())-1;
		
		int[] dist = new int[V];		// K부터 i까지의 최단거리.
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		List<edge>[] adjList = new LinkedList[V];
		boolean[] visited = new boolean[V];
		
		for(int i = 0; i < V; i++) {
			adjList[i] = new LinkedList<edge>();
		}
		
		for(int i = 0; i < E; i++) {	// 간선 입력 받기. 인접리스트
			st= new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new edge(from,to,cost));
		}
		
		PriorityQueue<pair> pq = new PriorityQueue<>();
		
		pq.add(new pair(K,0));
		dist[K] = 0;
		while(!pq.isEmpty()) {
			pair cur = pq.poll();
			if(visited[cur.v]) continue;
			visited[cur.v] = true;								//빠른 경로가 항상 top에 있게 된다.
			for(edge e : adjList[cur.v]) {
				if(dist[e.dest] > dist[cur.v] + e.cost) {		//dist : Integer.MAX가 아니면 visit. 
					dist[e.dest] = dist[cur.v] + e.cost;
					pq.add(new pair(e.dest,dist[e.dest]));
				}
			}
		}
		for(int i : dist) sb.append(i == Integer.MAX_VALUE?"INF":i).append('\n');
		System.out.print(sb);
	}

}
