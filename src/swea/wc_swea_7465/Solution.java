package swea.wc_swea_7465;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
	static boolean[][] know;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			know = new boolean[N][N];
			visited = new boolean[N];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				
				know[from][to] = true;
				know[to][from] = true;
			}
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {		//모두 방문
				if(!visited[i]) {				//방문 안했으면 DFS
					cnt++;
					DFS(i);						//i가 아는 모두를 방문
				}
			}
			sb.append('#').append(test_case).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}

	static void DFS(int cur) {
		visited[cur] = true;			//방문
		for(int i = 0; i < N; i++) {
			if(know[cur][i] && !visited[i]) {			//방문 안한 사람 중, 아는 모두를 방문
				DFS(i);									//재귀
			}
		}
	}
}
