package baekjoon.wc_bj_17135;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = { { 0, -1 }, { -1, 0 }, { 0, 1 } };
	static int[][] map, simul;
	static int N, M, D, castle;
	static boolean[] archer;
	static int[] target = new int[3];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		simul = new int[N][M];
		castle = N;
		archer = new boolean[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(comb(0, 0));
	}

	static int comb(int cnt, int cur) {
		if (cnt == 3)
			return simulation();
		int max = 0;
		for (int i = cur; i < M; i++) {
			archer[i] = true;
			max = Math.max(max, comb(cnt + 1, i + 1));
			archer[i] = false;
		}
		return max;
	}

	static int simulation() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				simul[i][j] = map[i][j];
			}
		}
		int result = 0;
		for (castle = N; castle > 0; castle--) {
			for (int i = 0; i < M; i++) {
				if (archer[i])
					targeting(i);
			}
			result += shot();
		}
		return result;
	}

	static void targeting(int pos) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(castle + d[1][0]);
		q.add(pos + d[1][1]);
		q.add(1);
		while (!q.isEmpty()) { // 비어있을 때 까지
			int ti = q.poll();
			int tj = q.poll();
			int tk = q.poll();
			if (simul[ti][tj] > 0) {
				if (simul[ti][tj] == 1) {
					simul[ti][tj] = 2;
				}
				return;
			}

			for (int k = 0; k < 3; k++) {
				if(tk == 0 && k == 2) continue;
				if(tk == 2 && k == 0) continue;
				if (ti + d[k][0] >= 0 && tj + d[k][1] >= 0 && tj + d[k][1] < M) {
					if (Math.abs(ti + d[k][0] - castle) + Math.abs(tj + d[k][1] - pos) <= D) {		// while 앞에서 판단 시 시간초과
						q.add(ti + d[k][0]);
						q.add(tj + d[k][1]);
						q.add(k);
					}
				}
			}
		}
	}

	static int shot() {
		int result = 0;
		for (int i = 0; i < castle; i++) {
			for (int j = 0; j < M; j++) {
				if (simul[i][j] == 2) {
					result++;
					simul[i][j] = 0;
				}
			}
		}
		return result;
	}
}
