package baekjoon.wc_bj_1162;

/**
 * idea
 * 1. 다익스트라로 N까지 최단경로 구함.
 * 
 * 2. 각 dist 함수는 경로를 전부 저장하고, k개를 뺀 결과값을 저장한다.
 * 
 * 3. 1개씩 빼면서 최단거리를 갱신한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class edge {
	int src;
	int dest;
	int dist;

	public edge(int src, int dest, int dist) {
		this.src = src;
		this.dest = dest;
		this.dist = dist;
	}
}

class node implements Comparable<node>{
	int src;
	int dest;
	int totalDist;
	List<Integer> route = new LinkedList<Integer>();

	public node(edge e, int totalDist) {
		super();
		this.src = e.src;
		this.dest = e.dest;
		this.totalDist = totalDist;
		this.route.add(e.dist);
	}

	public node(edge e, List<Integer> l, int totalDist) {
		this.src = e.src;
		this.dest = e.dest;
		this.totalDist = totalDist;
		for (int i : l) {
			this.route.add(i);
		}
		this.route.add(e.dist);
	}

	public int res(int k) {
		Collections.sort(route);
		System.out.println(route);
		int result = totalDist;
		for(int i = route.size() - 1; i >= route.size() - k && i >= 0; i--) {
			result -= route.get(i);
		}
		return result;
	}
	@Override
	public int compareTo(node o) {
		return Integer.compare(this.totalDist, o.totalDist);
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<edge>[] adjList = new ArrayList[N];
		for(int i = 0; i < N; i++) adjList[i] = new ArrayList<>();
		
		int[] d = new int[N];
		Arrays.fill(d, Integer.MAX_VALUE);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int src = Integer.parseInt(st.nextToken()) - 1;
			int dest = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());
			adjList[src].add(new edge(src,dest,dist));
			adjList[dest].add(new edge(dest,src,dist));
		}
		
		PriorityQueue<node> pq = new PriorityQueue<node>();
		
		d[0] = 0;
		for(edge e :adjList[0]) {
			pq.add(new node(e,0));
		}
		int result = -1;
		while(!pq.isEmpty()) {
			node cur = pq.poll();
			if(cur.dest == N-1) {
				result = cur.res(K);
				break;
			}
			for(edge e : adjList[cur.dest]) {
				if(d[e.src] > d[e.dest] + e.dist) {
					d[e.src] = d[e.dest] + e.dist;
					pq.add(new node(e,cur.route,d[e.src]));
				}
			}
		}
		System.out.println(result);
	}

}
