package baekjoon.wc_bj_1707;

/**
 * 요약
 * 1. 정점들을 두 집합으로 나눈다.
 * 2. 인접한 정점들은 서로 다른 집합이어야 한다.
 * 
 * 풀이
 * 1. bfs 쓰면서 인접한 정점들을 반대 집합에 분배
 * 2. 탐색하면서 반대 집합은 ok, 비어있으면 반대 집합 분배, 같은 집합이면 NO 출력.
 *
 * 유의
 * 1. 그래프가 서로 연결이 안되어 있는 경우
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			boolean res = true;
			st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[] vNs = new int[V]; // visited & set : 방문 여부, 집합 저장. index 0: !visited, 1: set 1, 2: set 2
			List<Integer>[] adjList = new ArrayList[V]; // 인접리스트 배열 생성

			for (int v = 0; v < V; v++) { // 인접리스트 생성
				adjList[v] = new ArrayList<Integer>();
			}

			for (int e = 0; e < E; e++) { // 인접리스트 입력
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;

				adjList[from].add(to);
				adjList[to].add(from);
			}

			// BFS
			Queue<Integer> q = new ArrayDeque<>();
			for (int v = 0; v < V; v++) {
				if(vNs[v] != 0) continue;
				q.add(v);
				vNs[v] = 1;
				while (!q.isEmpty()) {
					int cur = q.poll();
					for (int next : adjList[cur]) {
						if (vNs[next] == 0) {
							vNs[next] = 3 - vNs[cur];
							q.add(next);
							continue;
						}
						if (vNs[cur] == vNs[next]) {
							res = false;
							break;
						}
					}
					if (!res)
						break;
				}
			}
			sb.append(res ? "YES" : "NO").append('\n');
		}
		System.out.println(sb);
	}
}
