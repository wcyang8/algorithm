package baekjoon.wc_bj_14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약 1. 연구소 크기 N x M 2. 0은 빈 칸, 1은 벽, 2는 바이러스 3. 바이러스는 인전 상하좌우 확산 4. 벽을 3개 무조건
 * 세워야함. 5. 바이러스가 퍼지지 않는 안전영역의 최대 크기를 구해보자.
 * 
 * 풀이 1. 조합으로 벽의 위치를 정해주고 BFS로 확산한 후, 안전영역의 크기를 구한다.
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] map, sim, dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		sim = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// sim에 map 복사
		comb(0,0);
		System.out.println(max);
	}

	static void copyMap() {
		for (int i = 0; i < N; i++)
			System.arraycopy(map[i], 0, sim[i], 0, M);
	}

	static void comb(int cur, int start) {
		if (cur == 3) {
			copyMap();
			BFS();
			max = Math.max(max, count());
			return;
		}

		for (int i = start; i < N * M; i++) {
			int r = i / M;
			int c = i % M;
			if (map[r][c] != 0)
				continue;
			map[r][c] = 1;
			comb(cur + 1, i+1);
			map[r][c] = 0;
		}
	}

	private static void BFS() {
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sim[i][j] == 2) {
					q.add(i);
					q.add(j);
				}
			}
		}

		while (!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();

			for (int[] d : dir) {
				int ni = ci + d[0];
				int nj = cj + d[1];
				if (ni >= 0 && ni < N && nj >= 0 && nj < M && sim[ni][nj] == 0) {
					sim[ni][nj] = 2;
					q.add(ni);
					q.add(nj);
				}
			}
		}
	}

	private static int count() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sim[i][j] == 0)
					++ret;
			}
		}
		return ret;
	}
}
