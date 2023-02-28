package swea.wc_swea_1238;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		boolean[][] adjList = new boolean[100][100];
		boolean[] visited = new boolean[100];
		
		int T = 10;
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken())/2;		//N : data 개수
			int start = Integer.parseInt(st.nextToken()) - 1;	//start : 당번
			
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++) {					//인접행렬 입력
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				
				adjList[from][to] = true;
			}
			
			int max = start;
			int prevLv = 0;
			Queue<Integer> q = new ArrayDeque<>();
			visited[start] = true;
			q.add(start);
			q.add(0);
			while(!q.isEmpty()) {
				int cur = q.poll();
				int level = q.poll();
				if(prevLv < level) {
					max = cur;
					prevLv = level;
				}
				if(max < cur) max = cur;			//max 갱신
				for(int next = 0; next < 100; next++) {
					if(adjList[cur][next] && !visited[next]) {
						q.add(next);
						q.add(level + 1);
						visited[next] = true;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(max + 1).append('\n');	//출력
			if(tc < 10) {
				for(int i = 0; i < 100; i++) {
					Arrays.fill(adjList[i], false);
				}
				Arrays.fill(visited, false);
			}
		}
		System.out.println(sb);
	}

}
