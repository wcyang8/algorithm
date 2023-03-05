package baekjoon.wc_bj_7576;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = {{0,1},{1,0},{-1,0},{0,-1}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());				// M 입력 (main)
		int N = Integer.parseInt(st.nextToken());				// N 입력 (main)
		
		int[][] tomato = new int[N][M];							// 토마토 농장 초기화 (main)
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {							// 토마토 농장 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
				if(tomato[i][j] == 1) {
					q.add(i);
					q.add(j);
					q.add(0);
				}
			}
		}
		
		int t = 0;
		while(!q.isEmpty()) {
//			printT(tomato);
			int ci = q.poll();
			int cj = q.poll();
			t = q.poll();
			for(int k = 0; k < 4; k++) {
				int ni = ci + d[k][0];
				int nj = cj + d[k][1];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M) {
					if(tomato[ni][nj] == 0) {
						tomato[ni][nj] = 1;
						q.add(ni);
						q.add(nj);
						q.add(t+1);
					}
				}
			}
		}
		boolean flag = false;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(tomato[i][j] == 0) flag = true;
			}
		}
		if(flag) t = -1;
		System.out.println(t);
	}
//	static void printT(int[][] tomato) {
//		for(int[] t : tomato) {
//			for(int i : t) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
//	}
}
