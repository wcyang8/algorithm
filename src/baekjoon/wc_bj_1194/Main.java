package baekjoon.wc_bj_1194;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약 
 * 1. 미로 N x M 
 * 2. 열쇠를 들고있으면 대응하는 문을 지나갈 수 있다. 
 * 3. 0은 민식이의 현재 위치 
 * 4. 1에 도착하면 끝
 * 5. 이동 횟수의 최솟값을 구해보자.
 * 
 * 풀이 
 * 1. 열쇠 보유 상태를 비트마스크로 표현. 
 * 2. visit 배열을 [N][M][1<<key] 로 
 * 3. BFS
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[] map = new String[N];

		boolean[][][] visited = new boolean[N][M][(1 << 6)];

		int ci = 0;
		int cj = 0;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
			int temp;
			if ((temp = map[i].indexOf("0")) != -1) {
				ci = i;
				cj = temp;
			}
		}

		Queue<Integer> q = new ArrayDeque<>();

		q.add(ci);
		q.add(cj);
		q.add(0);
		q.add(0);
		visited[ci][cj][0] = true;
		while (!q.isEmpty()) {
			ci = q.poll();
			cj = q.poll();
			int dist = q.poll();
			int state = q.poll();
			for (int[] d : dir) {
				int ni = ci + d[0];
				int nj = cj + d[1];
				if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
					int next = map[ni].charAt(nj);
					if (next == '1') {
						System.out.println(dist + 1);
						return;
					}
					if (next != '#' && !visited[ni][nj][state]) {	// 벽 or visit
						if (next >= 'A' && next <= 'F') {			// 문
							if ((state & (1 << (next - 'A'))) != 0) {	// 상태에 열쇠가 있으면 이동 가능
								q.add(ni);
								q.add(nj);
								q.add(dist + 1);
								q.add(state);
								visited[ni][nj][state] = true;
							}
						} else {									// 평지 or 열쇠면 무조건 이동
							q.add(ni);
							q.add(nj);
							q.add(dist + 1);
							if (next >= 'a' && next <= 'f') {		// 열쇠면 state를 1로 바꾼 상태로 이동
								q.add(state | (1 << (next - 'a')));
								visited[ni][nj][state | (1 << (next - 'a'))] = true;
							} else {
								q.add(state);
								visited[ni][nj][state] = true;
							}
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

}
