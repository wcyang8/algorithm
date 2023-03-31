package baekjoon.wc_bj_4485;

/**
 * 요약
 * 1. 0,0번 칸에서 N-1,N-1칸까지 이동.
 * 2. 도둑 루피를 먹으면 금액을 잃는다.
 * 3. 도둑 루피 크기만큼 금액을 잃음.
 * 4. 잃을 수 밖에 없는 최소 금액은?
 * 
 * 입력
 * 1. N 최소 2 최대 125
 * 2. N=0이면 입력 종료.
 * 3. 도둑 루피의 크기는 0~9
 * 전부 int로 입력 받자.
 * 
 * 풀이
 * 1. 다익스트라
 * 
 * 유의
 * 1. dist[0][0]이 0으로 시작하지 않음을 유의하자.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class pair{
	int first;
	int second;
}

public class Main {

	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());

			if (N == 0) {
				System.out.println(sb);
				return;
			}

			int[][] cave = new int[N][N]; // 동굴 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dist = new int[N][N];
			for(int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
				return Integer.compare(o1[2],o2[2]);
			});

			pq.add(new int[] { 0, 0, cave[0][0] });
			dist[0][0] = cave[0][0];
			while (!pq.isEmpty()) {
				int[] cur = pq.poll(); // cur : 현재 상태, index 0,1 : 현재 i,j 위치, 2 : 현재까지 거리

				if(cur[0] == N-1 && cur[1] == N-1) {
					sb.append("Problem").append(' ').append(T).append(": ").append(dist[N-1][N-1]).append('\n');
					T++;
					break;
				}
				for (int k = 0; k < 4; k++) {
					int ni = cur[0] + d[k][0];
					int nj = cur[1] + d[k][1];

					if (ni >= 0 && ni < N && nj >= 0 && nj < N
						&& dist[ni][nj] > dist[cur[0]][cur[1]] + cave[ni][nj]) {
						dist[ni][nj] = dist[cur[0]][cur[1]] + cave[ni][nj];
						pq.add(new int[] { ni, nj, dist[ni][nj] });
					}
				}
			}
		}
	}

}
