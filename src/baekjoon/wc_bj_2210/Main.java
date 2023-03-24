package baekjoon.wc_bj_2210;

/**
 * 요약
 * 1. 5x5 숫자판
 * 2. 0~9로 채워져 있음.
 * 3. 임의의 위치에서 시작해, 인접한 4방으로 5번 이동
 * 4. 각 칸의 숫자를 차레로 붙이면 6자리 수가 된다.
 * 5. 서로 다른 6자리 수 개수 구해보자.
 * 
 * 유의
 * 1. 한번 거친 칸을 다시 거쳐도 됨.
 * 2. 0으로 시작해도 됨.
 * 
 * 풀이
 * 1. 4^5 x 5 x 5
 * 2. set에 넣기
 * 
 * @author SSAFY
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] board;
	static Set<Integer> s = new HashSet<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		board = new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 탐색
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				dfs(i,j,new StringBuilder());
			}
		}
	}
	private static Set<> dfs(int i, int j, int k) {
		StringBuilder sb = new StringBuilder();
		sb.append(board[i][j]);
		
	}

}
