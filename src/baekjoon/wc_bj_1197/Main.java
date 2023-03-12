package baekjoon.wc_bj_1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] djset;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// 서로소 집합 생성
		djset = new int[V];
		
		for(int i = 0; i < V; i++) {
			djset[i] = i;
		}
		
		// 간선 입력 받기
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return Integer.compare(o1[2], o2[2]);
		});
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {from, to, cost});
		}
		
		// 크루스칼 알고리즘
		int sum = 0;
		for(int i = 0; i < V-1; i++) {
			// 싸이클이 생성되지 않는 최소 비용 간선 선택 & 동시에 연결
			while(!pq.isEmpty() && !union(pq.peek()[0],pq.peek()[1])) pq.poll();
			int[] cur = pq.poll();
			sum += cur[2];
		}
		System.out.println(sum);
	}
	static int findSet(int v) {
		if(djset[v] != v) return djset[v] = findSet(djset[v]);
		return v;
	}
	static boolean union(int from, int to) {
		int a = findSet(from);
		int b = findSet(to);
		if(a == b) return false;
		djset[b] = a;
		return true;
	}
}