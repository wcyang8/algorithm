package baekjoon.wc_bj_3190_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		// 보드
		boolean[][] board = new boolean[N][N]; // 사과 위치
		int[][] snake = new int[N][N];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;

			board[i][j] = true; // 사과 입력
		}

		int L = Integer.parseInt(br.readLine());

		// 뱀 이동 시작
		int dir = 0;
		int ci = 0;
		int cj = 0;
		int time = 0;
		int l = 1;
		int tail_i = 0;
		int tail_j = 0;
		snake[0][0] = 1;
		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken());
		char C = st.nextToken().charAt(0);
		while (true) {
			++time;

			int ni = ci + d[dir][0];
			int nj = cj + d[dir][1];
			// 다음 위치가 벽 or 몸이면 게임 끝
			if (ni < 0 || ni >= N || nj < 0 || nj >= N || snake[ni][nj] != 0) {
				System.out.println(time);
				return;
			}

			// 아니라면 현재 초 진행
			snake[ni][nj] = dir + 1;

			if(board[ni][nj]) {		// 사과가 있다면
				board[ni][nj] = false;
			}
			else{	// 사과가 없다면
				int t = snake[tail_i][tail_j] - 1;
				snake[tail_i][tail_j] = 0;
				tail_i += d[t][0];
				tail_j += d[t][1];
			}
			ci = ni;
			cj = nj;

			// 정해진 초에 도달했다면
			if (time == X) {
				// 방향 전환
				if (C == 'D') {
					dir++;
					dir %= 4;
				} else if (C == 'L') {
					dir += 3;
					dir %= 4;
				}
				snake[ci][cj] = dir + 1;

				// 다음 입력 받기
				if (l < L) {
					st = new StringTokenizer(br.readLine());
					X = Integer.parseInt(st.nextToken());
					C = st.nextToken().charAt(0);
					++l;
				} else
					X = 10001;
			}
		}
	}
}