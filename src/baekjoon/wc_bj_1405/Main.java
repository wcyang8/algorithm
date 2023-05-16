package baekjoon.wc_bj_1405;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] visited;
	static int N, dir[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	static double[] P = new double[4];
	static double res;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N*2+1][N*2+1];
		
		for(int i = 0; i < 4; i++) {
			P[i] = Double.parseDouble(st.nextToken()) / 100.0000000;
		}
		
		getP(N,N,0,1);
		System.out.println(res);
	}
	private static void getP(int i, int j, int k, double p) {
		visited[i][j] = true;
		if(k == N) {
			res += p;
			visited[i][j] = false;
			return;
		}
		for(int d = 0; d < 4; d++) {
			if(P[d] == 0) continue;
			int ni = i + dir[d][0];
			int nj = j + dir[d][1];
			if(!visited[ni][nj]) {
				getP(ni,nj,k+1,p*P[d]);
			}
		}
		visited[i][j] = false;
	}
}
