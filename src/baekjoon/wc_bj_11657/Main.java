package baekjoon.wc_bj_11657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class route {
	int src;
	int dest;
	int cost;

	public route(int src, int dest, int cost) {
		super();
		this.src = src;
		this.dest = dest;
		this.cost = cost;
	}
}

public class Main {

	static List<route>[] bus;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[] dist = new long[N]; // 1번 도시로부터 각 도시로 가는 최단 시간을 저장하는 배열
		Arrays.fill(dist, Integer.MAX_VALUE);
		bus = new LinkedList[N]; // 각 버스 노선을 넣어줄 리스트

		for (int i = 0; i < N; i++) { // 리스트 생성
			bus[i] = new LinkedList<route>();
		}

		int A, B, C;
		for (int i = 0; i < M; i++) { // 노선 받기
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken()) - 1;
			B = Integer.parseInt(st.nextToken()) - 1;
			C = Integer.parseInt(st.nextToken());
			bus[A].add(new route(A, B, C));
		}

		// 벨만 포드
		dist[0] = 0;
		for (int i = 0; i < N; i++) {
			for (List<route> l : bus) {
				for (route r : l) {
					if (dist[r.src] + r.cost < dist[r.dest]) {
						dist[r.dest] = dist[r.src] + (long) r.cost;
					}
				}
			}
		}
		
		visited = new boolean[N];
		dfs(0);
		
		for (List<route> l : bus) {
			for (route r : l) {
				if (!visited[r.src]) break;
				if (dist[r.src] + r.cost < dist[r.dest]) {
					System.out.println(-1);
					return;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			if(!visited[i])dist[i] = Integer.MAX_VALUE;
		}
		for (int i = 1; i < N; i++)
			sb.append(dist[i] == Integer.MAX_VALUE ? -1 : dist[i]).append('\n');
		System.out.print(sb);
	}

//	static int findSet(int[] djset, int v) {
//		if (djset[v] != v)
//			return djset[v] = findSet(djset, djset[v]);
//		return v;
//	}
	static void dfs(int cur) {
		visited[cur] = true;
		for(route r : bus[cur]) {
			if(!visited[r.dest]) dfs(r.dest);
		}
	}
}
