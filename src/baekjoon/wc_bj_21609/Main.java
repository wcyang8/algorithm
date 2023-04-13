package baekjoon.wc_bj_21609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. N x N 맵
 * 2. 블록의 종류 : 검은색 -1, 무지개 0, 일반 1~M 가지 색
 * 3. 인접 = 4방
 * 4. 블록 그룹 = 일반 블록이 적어도 1개 이상. 일반 블록의 색은 모두 같아야 함.
 * 	4.1. 검은색 블록은 있으면 안되고, 무지개 블록은 얼마나 들어있든 상관 X
 * 	4.2. 블록 개수는 2 이상
 * 	4.3. 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다.
 * 	4.4. 기준블록 = 일반 블록 중 행의 번호가 가장 작은 블록. 행의 번호가 같으면 열 번호가 가장 작은 블록
 * 5. 오토플레이
 * 	5.1. 크기가 가장 큰 블록 그룹을 찾음. 크기가 같으면 무지개 블록 수가 가장 많은 블록 그룹, 같으면 기준 블록 행이 가장 큰, 같으면 열이 가장 큰
 * 	5.2. 1의 블록 그룹의 모든 블록을 제거. 블록 그룹에 포함된 블록 수를 B라고 했을 때, B^2점 획득
 * 	5.3. 격자에 중력이 작용
 * 	5.4. 격자가 90도 반시계 방향으로 회전
 * 	5.5. 격자에 중력 작용.
 * 	5.6. 검은색 블록은 중력 X
 * 
 * 풀이
 * 1. findBG()
 * 	1.1. visit을 안했으면 BFS(i,j)
 * 	1.2. max를 갱신
 * 2. gravity()
 * 3. rotate()
 * 4. gravity()
 * 5. 1~4 반복
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] map, dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M, max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(findBG());
		
	}

	static int findBG() {
		boolean[][] visited = new boolean[N][N];
		
		int max = 0;
		int pos = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] > 0 && !visited[i][j]) {
					int temp = DFS(map[i][j], i,j, visited);
					visited[i][j] = true;
					if(max > temp) {
						max = temp;
						pos = i * N + j;
					}
				}
			}
		}
		return pos;
	}
	
	private static int DFS(int k, int i, int j, boolean[][] visited) {
		int sum = 1;
		visited[i][j] = true;
		for(int[] d: dir) {
			int ni = i + d[0];
			int nj = j + d[1];
			
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && !visited[ni][nj]) {
				if(map[ni][nj] == k || map[ni][nj] == 0) {
					sum += DFS(k, ni, nj, visited);
				}
			}
		}
		return sum;
	}

//	static int getPoint() {
//		
//	}
	
	static void rotate() {
		
	}
	
	static void gravity() {
		
	}
}
