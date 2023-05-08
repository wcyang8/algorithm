package baekjoon.wc_bj_1103;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약 1. 1~9까지 숫자가 있다. 2. 동전이 있는 곳에 쓰여있는 숫자 X를 본다. 3. 위, 아래, 왼쪽, 오른쪽 방향 중 한가지를
 * 고른다. 4. X칸 만큼 움직인다. 5. 최대 몇번 동전을 움직일 수 있는지 구하는 프로그램을 작성해보자.
 * 
 * 풀이 
 * 1. DFS 
 * 2. visit 한 위치를 또 방문하면? 
 * 	2.1 무한루프 : max = 1 & return 
 * 	2.2 다른 경로로 방문 : visited 갱신
 * 
 * 무한 루프와 다른 경로 방문을 어떻게 구분해야되나?
 * 
 * 
 * 
 * @author SSAFY
=======
 * 
 * @author 양우철
 *
 */

public class Main {

	static String[] board;
	static int[][] visited, d = {{1,0},{0,1},{-1,0},{0,-1}};
	static int N, M, max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		board = new String[N];
		
		visited = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			board[i] = br.readLine();
		}
		
		visited[0][0] = 1;
		max = 1;
		DFS(0,0);
		System.out.println(max > 2500 ? -1: max);
	}
	private static void DFS(int i, int j) {
		if(max > 2500) return;
		int X = board[i].charAt(j) - '0';
		for(int k = 0; k < 4; k++) {
			int ni = i + d[k][0] * X;
			int nj = j + d[k][1] * X;
			
			if(ni >= 0 && ni < N && nj >= 0 && nj < M && board[ni].charAt(nj) != 'H') {
				if(visited[ni][nj] < visited[i][j] + 1) {
					visited[ni][nj] = visited[i][j] + 1;
					max = Math.max(max, visited[ni][nj]);
					DFS(ni,nj);
				}
			}
		}
		
	}

}
