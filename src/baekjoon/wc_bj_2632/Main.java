package baekjoon.wc_bj_2632;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};
	static boolean[][] visited, map;
	static int N, M, time;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		visited = new boolean[N][M];
		
		int cheese = 0;									//치즈 개수 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = (st.nextToken().charAt(0) == '1');
				if(map[i][j]) cheese++;
			}
		}
		
		int lastCheese = 0;
		for(int cnt = 0; cnt < cheese;) {
			lastCheese = melt();
			cnt += lastCheese;
			time++;
		}
		
		System.out.println(time);
		System.out.println(lastCheese);
	}
	
	static int melt() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		q.add(0); q.add(0);
		
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();
			
			for(int k = 0; k < 4; k++) {
				int ni = ci + d[k][0];
				int nj = cj + d[k][1];
				
				if(ni >= 0 && ni < N && nj >= 0 && nj < M) {
					if(!visited[ni][nj]) {
						visited[ni][nj] = true;
						if(!map[ni][nj]) {
							q.add(ni); q.add(nj);
						}
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j]) {
					visited[i][j] = false;
					if(map[i][j]) {
						map[i][j] = false;
						++cnt;
					}
				}
			}
		}
		return cnt;
	}

}
