package baekjoon.wc_bj_1992;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Main {

	static int[][] QT;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int N = Integer.parseInt(br.readLine());
		
		QT = new int[N][N];		//QT : QuadTree
		
		for(int i = 0; i < N; i++) {
			s = br.readLine();
			for(int j = 0; j < N; j++) {
				QT[i][j] = s.charAt(j) - '0';
			}
		}
		
		Zip(N,0,0);
		System.out.println(sb);
	}

	public static void Zip(int N, int i, int j) {
		int sum = 0;
		for(int ci = i; ci < i+N; ci++) {
			for(int cj = j; cj < j+N; cj++) {
				sum += QT[ci][cj];
			}
		}
		if(sum == N * N) {
			sb.append(1);
			return;
		}
		if(sum == 0) {
			sb.append(0);
			return;
		}
		sb.append("(");
		Zip(N/2,i,j);
		Zip(N/2,i,j+N/2);
		Zip(N/2,i+N/2,j);
		Zip(N/2,i+N/2,j+N/2);
		sb.append(")");
	}
}
