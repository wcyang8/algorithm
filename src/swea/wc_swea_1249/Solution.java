package swea.wc_swea_1249;

/**
 * Step 1. 미방문정점 중 dist비용 가장 최소 정점 찾기
 * Step 2. 찾은 정점 경유지로 해서 미방문 인접정점으로의 최소비용 갱신
 * 
 * pq를 쓰면, 간선이 많아질수록 불리함.
 * 	= 완전 그래프일수록 불리.
 * 	-> 행렬 그래프는 정점 당 간선이 4개인 그래프이므로 희소 그래프에 가까움
 * 	= 따라서 pq 쓰는게 유리.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class node implements Comparable<node> {
	int dist;
	int i;
	int j;

	public node(int dist, int i, int j) {
		super();
		this.dist = dist;
		this.i = i;
		this.j = j;
	}

	@Override
	public int compareTo(node o) {
		return Integer.compare(this.dist, o.dist);
	}
}

public class Solution {

	static int[][] d = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		// 입력 단자?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String s;
		// 변수 및 그래프 생성
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 1 <= N <= 100
			int[][] map = new int[N][N]; // 지도
			int[][] dist = new int[N][N]; // (0,0)에서 그 위치까지 거리
			for (int[] d : dist) {
				Arrays.fill(d, Integer.MAX_VALUE);
			}
			// 변수 및 그래프 입력
			for (int i = 0; i < N; i++) {
				s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			// pq 이용 다익스트라 : E log(V)
			PriorityQueue<node> pq = new PriorityQueue<node>();

			pq.add(new node(0, 0, 0)); // 0,0에서 시작, 거리 0
			dist[0][0] = 0;
			while (!pq.isEmpty()) {
				node cur = pq.poll();
				if (cur.i == N - 1 && cur.j == N - 1)
					break; // N-1,N-1 도착하면 종료

				for (int k = 0; k < 4; k++) { // 4방 탐색
					int ni = cur.i + d[k][0]; // 다음 i
					int nj = cur.j + d[k][1]; // 다음 j

					if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
						if (dist[ni][nj] > dist[cur.i][cur.j] + map[ni][nj]) {
							dist[ni][nj] = dist[cur.i][cur.j] + map[ni][nj];
							pq.add(new node(dist[ni][nj], ni, nj));
						}
					}
				}
			}
			// System.out.println(Arrays.deepToString(dist));
			sb.append('#').append(tc).append(' ').append(dist[N - 1][N - 1]).append('\n');
		}
		System.out.println(sb);
	}

}
