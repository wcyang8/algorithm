package baekjoon.wc_bj_2589_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static String[] map;
	static int N, M;
	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");

		N = Integer.parseInt(sArr[0]);
		M = Integer.parseInt(sArr[1]);

		map = new String[N];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}

		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i].charAt(j) == 'L') {
					max = Math.max(max, BFS(i,j));
				}
			}
		}
		System.out.println(max);
	}
	private static int BFS(int i, int j) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		int dist = 0;
		q.add(i);
		q.add(j);
		q.add(0);
		visited[i][j] = true;
		while(!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();
			dist = q.poll();
			
			for(int[] d: dir) {
				int ni = ci + d[0];
				int nj = cj + d[1];
				if(ni >= 0 && ni < N && nj >= 0 && nj < M 
						&& !visited[ni][nj] && map[ni].charAt(nj) == 'L') {
					q.add(ni);
					q.add(nj);
					q.add(dist+1);
					visited[ni][nj] = true;
				}
			}
		}
		return dist;
	}
}
