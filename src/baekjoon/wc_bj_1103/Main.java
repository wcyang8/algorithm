package baekjoon.wc_bj_1103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약 1. 1~9까지 숫자가 있다. 2. 동전이 있는 곳에 쓰여있는 숫자 X를 본다. 3. 위, 아래, 왼쪽, 오른쪽 방향 중 한가지를
 * 고른다. 4. X칸 만큼 움직인다. 5. 최대 몇번 동전을 움직일 수 있는지 구하는 프로그램을 작성해보자.
 * 
 * 풀이 
 * 1. DFS 
 * 2. visit 한 위치를 또 방문하면? 
 * 	2.1 무한루프 : max = 1 & return 
 * 	2.2 다른 경로로 방문 : visited 갱신
 * 
 * 무한 루프와 다른 경로 방문을 어떻게 구분해야되나?
 * 
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] visited[], d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static String[] board;
	static int N, M, max = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new int[N][M][4];

		board = new String[N];

		for (int i = 0; i < N; i++) {
			board[i] = br.readLine();
		}

		for (int i = 0; i < 4; i++)
			visited[0][0][i] = 1;

		backT(0, 0, 0);

		System.out.println(max == Integer.MAX_VALUE ? -1 : max);
	}

	private static void backT(int i, int j, int k) {
		System.out.println(i + " " + j);
		// 4방 X만큼 탐색
		int X = board[i].charAt(j) - '0';

		for (int nk = 0; nk < 4; nk++) {
			int ni = i + X * d[nk][0];
			int nj = j + X * d[nk][1];

			if (ni >= 0 && ni < N && nj >= 0 && nj < M && board[ni].charAt(nj) != 'H') {
				if (visited[ni][nj][nk] > 0 && nk == k) {
					max = Integer.MAX_VALUE;
					return;
				} else {
					visited[ni][nj][nk] = Math.max(visited[ni][nj][nk], visited[i][j][k] + 1);
					max = Math.max(max, visited[ni][nj][nk]);
					backT(ni, nj, nk);
				}
			}
		}
	}

}
