package baekjoon.wc_bj_10026;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 요약
 * 1. NxN map
 * 2. R G B 중 하나로 칠해짐
 * 3. 구역은 같은 색으로 이루어져 있음.
 * 4. 적록색약인 사람과 아닌 사람이 봤을 때 구역 수 구하기
 * 
 * 풀이
 * 1. 2번 탐색
 * 2. 전부 따로 한번, RG 합쳐서 한번
 * 
 * @author SSAFY
 *
 */

public class Main {

	static boolean[][] visited;
	static int normal, RG;
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static String[] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		map = new String[N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}
		
		// 일반
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					DFS(i,j,map[i].charAt(j));
					++normal;
				}
			}
		}
		
		// 적록색약
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j]) {
					RGDFS(i,j,map[i].charAt(j));
					++RG;
				}
			}
		}
		System.out.println(normal + " " + RG);
	}
	private static void DFS(int i, int j, char c) {
		visited[i][j] = true;
		for(int[] d : dir) {
			int ni = i + d[0];
			int nj = j + d[1];
			if(ni >= 0 && ni < visited.length && nj >= 0 && nj < visited.length) {
				if(!visited[ni][nj] && map[ni].charAt(nj) == c) {
					DFS(ni,nj,c);
				}
			}
		}
	}
	private static void RGDFS(int i, int j, char c) {
		visited[i][j] = false;
		for(int[] d : dir) {
			int ni = i + d[0];
			int nj = j + d[1];
			if(ni >= 0 && ni < visited.length && nj >= 0 && nj < visited.length && visited[ni][nj]) {
				if(map[ni].charAt(nj) == c
						|| (c == 'R' && map[ni].charAt(nj) == 'G')
						|| (c=='G' && map[ni].charAt(nj) == 'R')) {
					RGDFS(ni,nj,c);
				}
			}
		}
	}
}
