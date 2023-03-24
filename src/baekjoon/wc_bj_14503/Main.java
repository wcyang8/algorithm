package baekjoon.wc_bj_14503;

/**
 * 요약
 * 1. N x M 방 청소
 * 2. 청소기엔 방향이 있다.
 * 3. 로봇청소기는
 * 	현재 칸이 청소되지 않으면 청소
 * 	주변 4칸이 전부 청소되어 있으면?
 * 		바라보는 방향으로 한칸 후진
 * 		후진 못하면 작동 끝
 * 	청소되지 않은 칸이 있으면
 * 		반시계 방향으로 돌면서
 * 		앞쪽 칸이 청소되지 않았으면 한칸 전진
 * 
 * 유의
 * 1. 방의 크기가 주어진 후
 * 2. 청소기 좌표와 방향 입력
 * 3. 0이 청소되지 않은 빈 칸, 1은 벽
 * 
 * 풀이
 * 1. DFS 사용 시 뒤로 돌아오는 구현이 편리
 * 
 * 풀고난 후
 * 1. 처음 방향부터 탐색하지 않는다는 점을 파악하지 못했다.
 * 2. 문제의 진행 순서를 정확하게 파악하자.
 * 
 * @author SSAFY
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] room;
	static int N, M, sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		room = new int[N + 2][M + 2]; // false : 1 , true : 0

		for (int[] r : room) {
			Arrays.fill(r, 1);
		}

		// 시작 상태
		st = new StringTokenizer(br.readLine());
		int ci = Integer.parseInt(st.nextToken()) + 1;
		int cj = Integer.parseInt(st.nextToken()) + 1;
		int dir = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(ci, cj, dir);
		System.out.println(sum);
//		for(int[] r: room) {
//			for(int i: r) {
//				System.out.print(i+ " ");
//			}
//			System.out.println();
//		}
	}

	private static void dfs(int ci, int cj, int dir) {
		// 청소
		if (room[ci][cj] == 0) {
			room[ci][cj] = 2;
			++sum;
		}
		// 4방 탐색
		for (int k = 1; k <= 4; k++) {
			int nd = (dir + 3 * k) % 4;
			int ni = ci + d[nd][0];
			int nj = cj + d[nd][1];

			if (room[ni][nj] == 0) {
				dfs(ni, nj, nd);
				return;
			}
		}
		// 뒤로 가기
		int bi = ci - d[dir][0];
		int bj = cj - d[dir][1];
		if (room[bi][bj] == 1)
			return;
		dfs(bi, bj, dir);
	}
}
