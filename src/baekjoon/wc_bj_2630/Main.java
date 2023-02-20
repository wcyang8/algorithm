package baekjoon.wc_bj_2630;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int white = 0;
	static int green = 0;
	static int[][] spaces;
	
	// 주어진 영역의 공간의 셀 체크하여 모두 초록색이나 하얀색으로 이루어져있는지 확인 후
	// 4개로 쪼개기.
	// 하얀색 0 , 초록색 1

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] sArr = null;
		
		spaces = new int[n][n];
		for (int i = 0; i < n; i++) {
			sArr = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				spaces[i][j] = Integer.parseInt(sArr[j]);
			}
		}
		
		cut(n,0,0);
		
		System.out.println(white);
		System.out.println(green);
	}
	
	static void cut(int N, int i, int j) {
		
		int sum = 0;
		for(int ci = i; ci < i+N; ci++) {
			for(int cj = j; cj < j+N; cj++) {
				sum += spaces[ci][cj];
			}
		}
		
		if(sum == N * N) {
			green++;
			return;
		}
		if(sum == 0) {
			white++;
			return;
		}
		cut(N/2,i,j);
		cut(N/2,i + N/2, j);
		cut(N/2,i, j+N/2);
		cut(N/2,i+N/2, j+N/2);
	}
}
