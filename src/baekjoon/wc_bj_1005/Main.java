package baekjoon.wc_bj_1005;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약 1. 건설 순서와 규칙이 주어진다. 2. 건물은 동시에 지을 수 있다. 3. 특정 건물을 짓기 위한 최소 시간을 알아내는 프로그램을
 * 작성해보자.
 * 
 * 풀이 1. DP 2. 어떤 건물을 지을 때 까지 걸리는 최소 시간 = max(선행 건물들을 전부 짓는데까지 걸리는 최소 시간) + 선택한
 * 건물을 짓는데 걸리는 시간
 * 
 * 
 * @author 양우철
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());		// N, K

			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());		// D

			int[] D = new int[N + 1]; // 각 건물 짓는 데 걸리는 시간
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken());
			}

			List<Integer>[] adjList = new ArrayList[N + 1]; // 인접 리스트
			for (int i = 1; i <= N; i++)
				adjList[i] = new ArrayList<>();

			int[] inDegree = new int[N + 1]; // 진입 차수

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjList[from].add(to);
				++inDegree[to];
			} 
			int W = Integer.parseInt(br.readLine());	// 선택된 건물
			// 입력 완료

			// 위상 정렬
			int[] dp = new int[N + 1]; // 선택 건물을 짓는데 걸리는 최소 시간
			Queue<Integer> q = new ArrayDeque<>(); // 위상 정렬을 위한 queue
			for (int i = 1; i <= N; i++) {
				if (inDegree[i] == 0) { // 진입 차수가 0이면
					q.add(i);			// q에 넣어준다.
				}
			}
			while (!q.isEmpty()) {
				int i = q.poll();
				
				// 선행 건물을 짓는데 걸리는 최소 시간은 이미 저장되어 있음.
				dp[i] += D[i];	// 현재 건물을 짓는데 걸리는 시간만 더해준다.
				
				if(i == W) {	// 현재 건물이 건물 W면
					sb.append(dp[i]).append("\n");		// 건물 W를 짓는데 걸리는 최소 시간 출력
					break;
				}
				for (int j = adjList[i].size() - 1; j >= 0; j--) { // 간선 제거 (list는 뒤부터 제거하는게 좋다)
					int dest = adjList[i].get(j); // dest : 간선의 목적지
					dp[dest] = Math.max(dp[dest], dp[i]); // 선행 건물 중 가장 오래 걸리는 시간 갱신
					adjList[i].remove(j); // 뒤의 간선 제거
					--inDegree[dest]; // 진입 차수 1 감소
					if(inDegree[dest] == 0)q.add(dest);		//목적 건물의 차수가 0이 되면 q에 삽입
				}
			}
		}
		System.out.println(sb);
	}

}
