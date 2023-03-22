package baekjoon.wc_bj_2001_2;

/**
 *  * 요약
 * 1. n개의 섬이 m개의 다리로 이어져 있음.
 * 2. 그중 k개의 섬에 보석이 1개씩 있음.
 * 3. 다리마다 견딜 수 있는 무게가 있음.
 * 4. 1번섬에서 출발하여 1번섬으로 돌아와야 함.
 * 5. 주울 수 있는 보석 수의 최대값은?
 * 
 * 풀이
 * 1. 같은 상태로 같은 섬을 방문할 필요가 없다.
 * 2. 섬에 대한 visited가 아닌 현재 상태에 대한 visited 사용
 * 3. 상태를 bitmask를 통해 나타낸다.
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		boolean[][] visited = new boolean[N][(1 << K)];		// 어떤 섬을 어떤 상태로 방문했는지 저장

		List<Integer> jewelIsland = new LinkedList<>();		// 보석이 있는 섬을 저장
		for (int i = 0; i < K; i++) {
			jewelIsland.add(Integer.parseInt(br.readLine()) - 1);
		}

		List<int[]>[] adjList = new ArrayList[N];			// 인접리스트

		for(int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<int[]>();			// 인접리스트 생성
		}
		
		for (int i = 0; i < M; i++) {						// 인접리스트 입력
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { to, weight });
			adjList[to].add(new int[] { from, weight });
		}

		// BFS 시작
		int max = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { 0, 0, 0 });		// index 0 : 현재 섬, 1 : 현재 보석 상태, 2 : 현재 보석 개수
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if(cur[0] == 0) {				// 1번 섬을 도착하면 매번 갱신해준다.
				max = Math.max(max, cur[2]);
			}
			
			// 보석 줍기
			int temp = 0;
			if ((temp = jewelIsland.indexOf(cur[0])) != -1) { // 현재 섬에 보석이 있다면
				if ((cur[1] & (1 << temp)) == 0) {			// 아직 안주웠다면
					q.add(new int[] 
							{ cur[0], cur[1] | (1 << temp), cur[2] + 1 }); // 보석 줍기
					visited[cur[0]][1 << temp] = true;			// 방문 처리
				}
			}

			// 보석 안줍고 다음 섬으로
			for (int[] next : adjList[cur[0]]) {
				// 다음 섬에 건널 수 있고, 현재 상태로 방문하지 않았다면
				if(cur[2] <= next[1] && !visited[next[0]][cur[1]]) {
					q.add(new int[] {next[0], cur[1], cur[2]});		// 다음 섬, 보석 상태 동일
					visited[next[0]][cur[1]] = true;				// 현재 보석 상태로 방문 체크
				}
			}
		}
		System.out.println(max);
	}
}
