package baekjoon.wc_bj_9205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약 1. 한 박스 = 맥주 20개 들고 출발 2. 맥주 1개당 50미터 3. 편의점에 들러 맥주를 살 수 있는데 박스에 최대 20개 담을
 * 수 있음. 4. 페스티벌에 도착할 수 있는지 구해보자.
 * 
 * 입력 1. 테케 t개 2. 편의점 개수 n개 3. n+2개 줄에 집, 편의점, 페스티벌 좌표가 주어진다. 4. 각 좌표는 두 정수 x,
 * y로 이루어져 있고, -32768 <= x,y <= 32767 이다. 5. 두 좌표 사이 거리는 dx + dy
 * 
 * 유의 1. 좌표평면을 그대로 구현하면 36억
 * 
 * 풀이 1. 집, 편의점, 페스티벌을 정점으로 하는 그래프를 BFS를 통해 탐색하자. 2. 페스티벌에 도착하면 Happy 3. q가 비어있을
 * 때 까지 도착 못하면 Sad
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] graph = new int[N + 2][2];
			boolean[] visited = new boolean[N+2];
			boolean isHappy = false;
			
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				graph[i][0] = Integer.parseInt(st.nextToken());
				graph[i][1] = Integer.parseInt(st.nextToken());
			}
			
			
			Queue<Integer> q = new ArrayDeque<>();
			
			q.add(0);
			visited[0] = true;
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				if(cur == N+1) {
					isHappy = true;
					break;
				}
				
				for(int next = 0; next < N+2; next++) {
					if(!visited[next] && Math.abs(graph[cur][0] - graph[next][0]) 
							+ Math.abs(graph[cur][1] - graph[next][1]) <= 1000) {
						q.add(next);
						visited[next] = true;
					}
				}
			}
			sb.append(isHappy?"happy":"sad").append('\n');
		}
		System.out.println(sb);
	}

}
