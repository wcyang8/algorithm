package baekjoon.wc_bj_2146;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int res, ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬마다 번호 매기기 & 섬 주변 추가
		boolean check = false;
		while (true) {
			boolean[][] visited = new boolean[N][N]; // 매번 새로운 visit 생성
			int num = 2; // 섬은 2번부터 시작
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((map[i][j] == 1 || map[i][j] == num) && !visited[i][j]) { // map[i][j] == 1인 경우는 맨 처음만
						check = BFS(map, visited, i, j, num++);
					}
				}
			}
			if(check) {
				System.out.println(ans);
				return;
			}
			res += 2;
		}

	}

	private static boolean BFS(int[][] map, boolean[][] visited, int i, int j, int k) {
		boolean ret = false;
		
		Queue<Integer> q = new ArrayDeque<>();

		int N = map.length;

		q.add(i);
		q.add(j);
		map[i][j] = k;
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();

			if (map[ci][cj] < 0) { // 테두리가 -k면 k로 변환
				map[ci][cj] *= -1;
				continue;
			}

			for (int[] d : dir) {
				int ni = ci + d[0];
				int nj = cj + d[1];

				if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
					if (!visited[ni][nj]) {
						if (map[ni][nj] == 1) { // 1이면 k로 바꾼다
							map[ni][nj] = k;
						} else if (map[ni][nj] == 0) { // 0이면 테두리 -> -k
							map[ni][nj] = -k;
						}
						visited[ni][nj] = true;
						q.add(ni);
						q.add(nj);
					}
					if (Math.abs(map[ni][nj]) > k) { // 작은 번호에서 큰 번호를 만나면 : 그대로
						ans = Math.min(ans, res);
						ret = true;
					} else if (Math.abs(map[ni][nj]) < k && Math.abs(map[ni][nj]) > 1) { // 큰 번호에서 작은 번호를 만나면 : res + 1
						ans = Math.min(ans, res + 1);
						ret = true;
					}
				}
			}
		}
		
		return ret;
	}
}
