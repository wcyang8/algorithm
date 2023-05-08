package baekjoon.wc_bj_1938;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		boolean[][] map = new boolean[N][N];

		boolean[][][] visited = new boolean[N][N][2];	// 가로, 세로 상태로 방문 체크

		// 4방 및 8방 탐색
		int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		int[][] dir2 = { {1,0},{0,1},{-1,0},{0,-1},{ 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
		
		int res = 0;
		int cbi = -1, cbj = -1, cei = -1, cej = -1;		// center B, E 좌표
		boolean bs = false, es = false; // false면 세로 true면 가로
		boolean bcheck = false;
		boolean echeck = false;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();

			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if (c == '0') {
					map[i][j] = false;
				} else if (c == '1') {
					map[i][j] = true;
				} else if (c == 'B') {
					if (cbi == -1 && cbj == -1) {
						cbi = i;
						cbj = j;
					} else if (!bcheck) {
						if (cbj + 1 == j) {
							bs = true;
						}
						cbi = i;
						cbj = j;
						bcheck = true;
					}
				} else if (c == 'E') {
					if (cei == -1 && cej == -1) {
						cei = i;
						cej = j;
					} else if (!echeck) {
						if (cej + 1 == j) {
							es = true;
						}
						cei = i;
						cej = j;
						echeck = true;
					}
				}
			}
		}

		// BFS
		Queue<int[]> q = new ArrayDeque<>();

		if (bs) {
			visited[cbi][cbj][1] = true;
			q.add(new int[] { cbi, cbj, 1, 0 });
		} else {
			visited[cbi][cbj][0] = true;
			q.add(new int[] { cbi, cbj, 0, 0 });
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();		// cur[0] : i좌표, cur[1] : j좌표, cur[2] : 현재 상태(가로 세로), cur[3] : 동작 횟수
			if(cur[0] == cei && cur[1] == cej && cur[2] == (es?1:0)) {		// E 위치+상태와 같아지면
				res = cur[3];			// 동작 횟수 출력
				break;
			}

			// 4방 이동
			for (int[] d : dir) {
				int ni = cur[0] + d[0];
				int nj = cur[1] + d[1];

				if (ni >= 0 && ni < N && nj >= 0 && nj < N && !visited[ni][nj][cur[2]] && !map[ni][nj]) {
					// 현재 상태가 세로
					if (cur[2] == 0) {
						if(ni >= 1 && ni < N - 1			// 기둥 양쪽이 map 범위를 넘어가면 안됨
								&& !map[ni+1][nj] && !map[ni-1][nj]) {		// 기둥 양쪽이 1을 만나면 안됨
							visited[ni][nj][cur[2]] = true;					// 현재 상태(가로 세로)로 visit
							q.add(new int[] {ni,nj,cur[2], cur[3]+1});		// 동작 횟수 +1
						}
					}else {		// 현재 상태가 가로
						if(nj >= 1 && nj < N - 1
								&& !map[ni][nj+1] && !map[ni][nj-1]) {
							visited[ni][nj][cur[2]] = true;
							q.add(new int[] {ni,nj,cur[2], cur[3]+1});
						}
					}
				}
			}
			
			boolean rotOK = true;
			if(!visited[cur[0]][cur[1]][1-cur[2]]) {		// 현재 위치에서 90도 회전한 상태 visit 체크
				for(int[] d : dir2) {		// 인접 8칸 확인
					int ni = cur[0] + d[0];
					int nj = cur[1] + d[1];
					
					if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
						if(map[ni][nj]) {	// 1(기둥) 있으면 안됨.
							rotOK = false;
							break;
						}
					}else {					// map 범위 넘어가면 안됨
						rotOK = false;
						break;
					}
				}
				if(rotOK) {					// 회전 가능한 경우
					visited[cur[0]][cur[1]][1-cur[2]] = true;
					q.add(new int[] {cur[0],cur[1],1-cur[2], cur[3]+1});		// 회전 후 결과 add
				}
			}
		}
		
		System.out.println(res);
	}

}
