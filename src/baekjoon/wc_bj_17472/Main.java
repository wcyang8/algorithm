package baekjoon.wc_bj_17472;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이 1. 섬의 번호를 저장한다. 2. 각 섬의 인접리스트(다리를 놓을 수 있을 때 다리의 최소 거리) 구하기. 3. 인접리스트를 기반으로
 * 최소 스패닝 트리를 구한다.
 * 
 * 
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] adjM, map, dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		List<int[]> island = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 번호 저장. (2부터 시작)
		int num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					num++;
					island.add(new int[] { num + 1, i, j });
					dfs(i, j, num + 1);
				}
			}
		}

		// 섬 인접배열 생성
		adjM = new int[num][num];
		for(int i = 0; i < num; i++) {
			for(int j = 0; j < num; j++) {
				adjM[i][j] = N+M;
			}
		}

		makeMatrix();

		System.out.println(prim());
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
//		for(int i = 0; i < num; i++) {
//			for(int j = 0; j < num; j++) {
//				System.out.print(adjM[i][j]+" ");
//			}
//			System.out.println();
//		}
	}

	private static int prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		boolean[] visited = new boolean[adjM.length];
		
		visited[0] = true;
		for(int i = 0; i < adjM.length; i++) {
			if(adjM[0][i] != N+M) {
				pq.add(new int[] {i,adjM[0][i]});
			}
		}
		
		int sum = 0;
		for(int i = 0; i < adjM.length-1; i++) {
			int[] cur = pq.poll();
			while(!pq.isEmpty() && visited[cur[0]]) {
				cur = pq.poll();
			}
			if(cur == null || visited[cur[0]]) {
				sum = -1;
				break;
			}
			sum += cur[1];
			visited[cur[0]] = true;
			
			for(int j = 0; j < adjM.length; j++) {
				if(!visited[j] && adjM[cur[0]][j] != N+M) {
					pq.add(new int[] {j,adjM[cur[0]][j]});
				}
			}
		}
		
		return sum;
	}

	private static void makeMatrix() {
//		if(k != -1 && map[i][j] == num) return;		// k = -1 이면 섬 내부에서 출발 전, 0 이상이면 출발
//		if(len <= 2 && map[i][j] != 0) return;		// len <= 2 내에 다른 섬을 만나면 안됨.
//		if(len > 2 && map[i][j] != num) {			// 2 넘을 때 
//			adjM[num].add(new int[]{map[i][j], len-1});
//			return;
//		}
		for (int i = 0; i < N; i++) {
			int from = 0;
			int to = 0;
			int count = 0;
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					if (from == 0 && from != map[i][j]) {
						from = map[i][j];
					} else if(to != map[i][j]){
						if(count < 2) {
							from = map[i][j];
							to = 0;
							count = 0;
							continue;
						}
						if (to != 0) {
							from = to;
						}
						to = map[i][j];
						adjM[from-2][to-2] = Math.min(adjM[from-2][to-2], count);
						adjM[to-2][from-2] = Math.min(adjM[to-2][from-2], count);
						count = 0;
					}
				} else {
					if (from != 0 && from != map[i][j])
						count++;
				}
			}
		}
		for (int j = 0; j < M; j++) {
			int from = 0;
			int to = 0;
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (map[i][j] != 0) {
					if (from == 0 && from != map[i][j]) {
						from = map[i][j];
					} else if(to != map[i][j]){
						if(count < 2) {
							from = map[i][j];
							to = 0;
							count = 0;
							continue;
						}
						if (to != 0) {
							from = to;
						}
						to = map[i][j];
						adjM[from-2][to-2] = Math.min(adjM[from-2][to-2], count);
						adjM[to-2][from-2] = Math.min(adjM[to-2][from-2], count);
						count = 0;
					}
				} else {
					if (from != 0)
						count++;
				}
			}
		}
	}

	static void dfs(int i, int j, int num) {
		map[i][j] = num;
		for (int[] d : dir) {
			int ni = i + d[0];
			int nj = j + d[1];

			if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == 1) {
				dfs(ni, nj, num);
			}
		}
	}

}
