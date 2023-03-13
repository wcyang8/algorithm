package baekjoon.wc_bj_16932;

/**
 * 0인 부분에서 시작해 전부 BFS 돌렸음.
 * 이러면 시간이 엄청 오래 걸린다.
 * visited를 초기화 해주는 요령을 얻음.
 * BFS : Queue를 2개 사용해 Queue에서 빼낸거 add 해준다음 BFS 끝나고 전부 visited = false
 * DFS는 모르겠음.
 * 
 * 각 연결요소의 집합 번호를 정한 후, 연결요소의 크기를 저장하고,
 * 0인 부분에서 어느 연결요소와 연결되는지 탐색 후 연결요소 크기들을 더하면 훨씬 빠를 것 같다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean[][] visited = new boolean[N][M];;
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> remove = new ArrayDeque<>();

		int max = 0;
		for (int i = 0; i < N; i++) {			// 배열을 탐색한다.
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {			// 0이면 넣고 BFS를 시작한다.
					int sum = 1;
					// BFS
					q.add(new int[]{i,j});
					while(!q.isEmpty()) {
						int[] cur = q.poll();
						remove.add(cur);
						for(int k = 0; k < 4; k++) {
							int ni = cur[0] + d[k][0];
							int nj = cur[1] + d[k][1];
							if(ni >= 0 && ni < N && nj >= 0 && nj < M && !visited[ni][nj] && arr[ni][nj] == 1) {
								q.add(new int[] {ni,nj});
								visited[ni][nj] = true;
								sum++;
							}
						}
					}
					max = Math.max(max, sum);
					while(!remove.isEmpty()) {
						int[] cur = remove.poll();
						visited[cur[0]][cur[1]] = false;
					}
				}
			}
		}
		System.out.println(max);
	}

}
