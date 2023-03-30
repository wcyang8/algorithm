package baekjoon.wc_bj_2239;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 요약
 * 1. 스도쿠
 * 
 * 풀이
 * 1. 백트래킹
 * 2. 탐색하면서 0이면 되는걸로 다 채우고
 * 3. 다음 0으로 되는걸 다 채우고
 * 4. N-1,N-1 도착하면 종료
 * 
 * @author SSAFY
 *
 */

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int[][] sudoku = new int[9][9];
	static boolean complete = false;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			String s = br.readLine();
			for(int j = 0; j < 9; j++) {
				sudoku[i][j] = s.charAt(j) - '0';
			}
		}
		fill(0, 0);
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sb.append(sudoku[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	private static void fill(int i, int j) {
		if(complete) return;
		if(i == 9) {
			complete = true;
			return;	// 다 채웠으면 끝
		}
		if(sudoku[i][j] != 0) {
			fill(i + (j+1)/9, (j + 1)%9);	// 이미 채워져 있으면 다음거
			return;
		}
		
		for(int n = 1; n <= 9; n++) {
			boolean flag = false;
			for(int k = 0; k < 9; k++) {
				flag |= (sudoku[k][j] == n);
				flag |= (sudoku[i][k] == n);
				flag |= (sudoku[(i/3)*3 + k/3][(j/3)*3 + k%3] == n);
				if(flag) break;
			}
			if(flag) continue;
			
			sudoku[i][j] = n;
			fill(i + (j+1)/9, (j + 1)%9);
			if(complete) return;
			sudoku[i][j] = 0;
		}
	}

}
