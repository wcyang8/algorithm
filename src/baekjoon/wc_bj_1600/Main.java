package baekjoon.wc_bj_1600;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약 1. 말은 체스의 나이트와 같이 움직이며 장애물을 뛰어넘을 수 있다. 2. 원숭이는 K번만 말처럼 움직일 수 있으며, 그 이외엔 4방
 * 이동 3. 맨 왼쪽 위에서 출발하여 원숭이가 최소한의 동작으로 시작지점에서 맨오른쪽아래까지 가도록 해보자. 4. W x H 크기, 1 =
 * 장애물, K는 0이상 30이하
 * 
 * 풀이 1. BFS 사용
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] horse = { { 1, 2 }, { 2, 1 }, { -1, 2 }, { 2, -1 }, { 1, -2 }, { -2, 1 }, { -1, -2 }, { -2, -1 } };
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		boolean[][][] map = new boolean[H][W][K+1];
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < W; w++) {
				Arrays.fill(map[h][w] ,st.nextToken().equals("1"));
			}
		}

		Queue<int[]> q = new ArrayDeque<>();

		int min = -1;
		map[0][0][0] = true;
		q.add(new int[] { 0, 0, 0, 0 });
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == H - 1 && cur[1] == W - 1) {
				min = cur[2];
				break;
			}

			if (cur[3] < K) {
				for (int[] h : horse) {
					int ni = cur[0] + h[0];
					int nj = cur[1] + h[1];
					if (ni >= 0 && ni < H && nj >= 0 && nj < W && !map[ni][nj][cur[3]+1]) {
						map[ni][nj][cur[3]+1] = true;
						q.add(new int[] { ni, nj, cur[2] + 1, cur[3] + 1 });
					}
				}
			}
			for (int[] d : dir) {
				int ni = cur[0] + d[0];
				int nj = cur[1] + d[1];
				if (ni >= 0 && ni < H && nj >= 0 && nj < W && !map[ni][nj][cur[3]]) {
					map[ni][nj][cur[3]] = true;
					q.add(new int[] { ni, nj, cur[2] + 1, cur[3]});
				}
			}
		}
		System.out.println(min);
	}

}
