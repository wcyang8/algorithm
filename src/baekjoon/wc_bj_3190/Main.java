package baekjoon.wc_bj_3190;

/**
 * 요약
 * 1. 뱀이 사과를 먹으면 길이가 1씩 증가한다.
 * 2. 사과 위치와 이동 경로가 주어질 때 이 게임이 끝나는 시간 계산.
 * 3. 몸이나 벽에 부딪히면 게임 끝.
 * 
 * 풀이
 * 1. 단순 시뮬레이션
 * 
 * 풀기 전 유의 사항
 * 1. int 초과 없음
 * 2. 처음에 오른쪽 방향
 * 
 * @author SSAFY
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int[][] d = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		// 보드
		boolean[][] board = new boolean[N][N];		// 사과 위치
		boolean[][] snake = new boolean[N][N];
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			
			board[i][j] = true;		// 사과 입력
		}

		int L = Integer.parseInt(br.readLine());
		
		// 뱀 이동 시작
		int dir = 0;
		int ci = 0;
		int cj = 0;
		int time = 0;
		snake[0][0] = true;
		for(int l = 0; l < L; l++) {
			st = new StringTokenizer(br.readLine());
			
			int X = Integer.parseInt(st.nextToken());
			
			for(int x = 0; x < X; x++) {
				time++;
				int ni = ci + d[dir][0];
				int nj = cj + d[dir][1];
				
				if(ni < 0 || ni >= N || nj < 0 || nj >= N || snake[ni][nj]) {
					System.out.println(time);
					return;
				}
				
				if(board[ni][nj])snake[ni][nj] = true;
			}
		}
	}

}
