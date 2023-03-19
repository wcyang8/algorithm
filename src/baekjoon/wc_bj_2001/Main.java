package baekjoon.wc_bj_2001;

/**
 * 요약
 * 1. n개의 섬이 m개의 다리로 이어져 있음.
 * 2. 그중 k개의 섬에 보석이 1개씩 있음.
 * 3. 다리마다 견딜 수 있는 무게가 있음.
 * 4. 1번섬에서 출발하여 1번섬으로 돌아와야 함.
 * 5. 주울 수 있는 보석 수의 최대값은?
 * 
 * 풀이
 * 1. 보석을 하나씩 주울 때 마다 층 수가 하나씩 올라간다고 하자.
 * 2. dfs를 사용해 방문하고, 1번 섬에 돌아오면 최대값을 갱신한다.
 * 
 * 유의사항
 * 1. 최대 14개의 층수까지만 있으면 된다.
 * 2. 한 층에서 O(m)의 시간복잡도를 가지고
 * @author 양우철
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] visited;
	static List<Integer>[][] edges;
	static boolean[] jewel;
	static int max, n, K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[K+1][n];		// 백트래킹에 사용할 visited 변수
		
		edges = new ArrayList[K+1][n];		// k개의 보석을 들고 지날 수 있는 edge
		
		for(int k = 0; k <= K; k++) {
			for(int i = 0; i < n; i++) {
				edges[k][i] = new ArrayList<Integer>();
			}
		}
		
		// 보석 입력
		jewel = new boolean[n];
		for(int k = 0; k < K; k++) {
			jewel[Integer.parseInt(br.readLine()) - 1] = true;
		}
		
		// 간선 입력
		for(int e = 0; e < m; e++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			
			for(int k = 0; k <= c && k <= K; k++) {					// c개 이하의 edges에 모두 기록
				edges[k][a].add(b);
				edges[k][b].add(a);
			}
		}
		
		dfs(0,0);
		System.out.println(max);
	}
	
	static void dfs(int curk, int curv) {
		if(max == K) return;
		if(curv == 0) {
			max = Math.max(max, curk);	// 1번 섬으로 돌아오면 일단 갱신
			if(curk == K) return;
		}
		
		visited[curk][curv] = true;		// 방문 처리
		
		if(jewel[curv]) {				// 현재 섬에 보석이 있으면
			jewel[curv] = false;
			dfs(curk+1,curv);
			jewel[curv] = true;
			for(int v = 0; v < n; v++) visited[curk+1][v] = false;		// 돌아갈 때 윗층 visited clear
		}
		
		for(int next : edges[curk][curv]) {			// 연결된 섬 탐색
			if(!visited[curk][next]) {
				dfs(curk,next);
			}
		}
	}
}
