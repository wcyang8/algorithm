package baekjoon.wc_bj_2638;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약 1. N x M 맵 2. 2변 이상이 접촉하면 1시간만에 녹아 없어진다. 3. 치즈 내부 공기는 ok 4. 치즈가 전부 녹아
 * 없어지는데 걸리는 시간 5. 단, 가장자리엔 치즈가 없다.
 * 
 * 풀이 1. 외부에서 BFS 2. visited에 방문한 횟수 적기 3. 치즈 녹이기 4. visited 초기화 5. time 갱신 6.
 * 1~5 반복
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	static boolean[][] cheeze;
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cheeze = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheeze[i][j] = st.nextToken().charAt(0) == '1';
			}
		}

		int time = 0;
		while (melt() > 0) {
			++time;
		}
		System.out.println(time);
	}

	private static int melt() {
		int[][] visited = new int[N][M];
		
		Queue<Integer> q = new ArrayDeque<>();

		q.add(0);
		q.add(0);
		visited[0][0] = -1; // 빈 공간을 visit 한 경우 = -1

		while (!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();

			for (int[] d : dir) {
				int ni = ci + d[0];
				int nj = cj + d[1];

				if (ni >= 0 && ni < N && nj >= 0 && nj < M && visited[ni][nj] != -1) {
					if (cheeze[ni][nj])			// 치즈 방문 시 visit 횟수 +1
						++visited[ni][nj];
					else {						// 공기 방문 시 visit = -1
						q.add(ni);
						q.add(nj);
						--visited[ni][nj];
					}
				}
			}
		}

		// 치즈 녹이기
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j] >= 2) {
					cheeze[i][j] = false;
					cnt++;
				}
			}
		}
		return cnt;			// 치즈 녹인 개수 return
	}

}
