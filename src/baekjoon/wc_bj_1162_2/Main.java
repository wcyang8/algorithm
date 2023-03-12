package baekjoon.wc_bj_1162_2;

/**
 * 시간복잡도
 * 1. 다익스트라 MlogN
 * 2. 도로 하나 지울 때 마다 M개
 * 3. K*M
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] dist = new int[N]; // 1부터 i까지 최단거리를 저장할 배열

		Arrays.fill(dist, Integer.MAX_VALUE); // int 최대값으로 초기화

		List<Integer>[] route = new ArrayList[N]; // route저장할 리스트
		List<int[]>[] adjList = new ArrayList[N]; // 인접 리스트 배열 초기화
		for (int i = 0; i < N; i++) {
			route[i] = new ArrayList<Integer>(); // route 생성
			adjList[i] = new ArrayList<>(); // 인접 리스트 생성
		}

		for (int i = 0; i < M; i++) { // 간선 받기
			st = new StringTokenizer(br.readLine());

			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			adjList[from].add(new int[] { to, cost });
			adjList[to].add(new int[] { from, cost });
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Integer.compare(o1[1], o2[1]);
		}); // 다익스트라 돌릴 pq

		dist[0] = 0;
		route[0] = new ArrayList<>();
		// 다익스트라
		pq.add(new int[] { 0, 0 }); // 0부터 시작
		while (!pq.isEmpty()) {
			int[] cur = pq.poll(); // 현재 정점 및 이동거리 (index 0 : 정점, 1 : 총 이동 거리)

			if (cur[0] == N - 1)
				break; // 도착하면 끝
			for (int[] edge : adjList[cur[0]]) { // 현재 정점과 연결된 간선 (index 0 : 정점, 1 : 거리)
				if (dist[edge[0]] > dist[cur[0]] + edge[1]) { // 지금 경로가 목표 정점의 최단경로보다 짧으면
					dist[edge[0]] = dist[cur[0]] + edge[1]; // 갱신
					route[edge[0]] = new ArrayList<>(); // route도 갱신
					route[edge[0]].addAll(route[cur[0]]);
					route[edge[0]].add(edge[1]);
					pq.add(new int[] { edge[0], dist[edge[0]] }); // pq에 다시 넣어줌.
				}
			}
		} // 다익스트라 끝
			// 1~K개 까지 하나씩 지우며 최단거리 갱신
		System.out.println(Arrays.toString(dist));
		for (int k = 0; k < K; k++) {
			int temp;
			for (int i = 0; i < N; i++) {
				dist[i] = sumK(route[i], k);
			}
			//if(k == K) break;
			for (int i = 0; i < N; i++) {
				for (int[] edge : adjList[i]) {
					if (dist[edge[0]] > dist[i] + edge[1]) {
						dist[edge[0]] = dist[i] + edge[1];
						if(route[edge[0]].size() == 0)route[edge[0]].addAll(route[i]);
						route[edge[0]].add(edge[1]);
					}
				}
			}
			System.out.println(Arrays.toString(dist));
		}
		System.out.println(dist[N-1]);
	}

	static int sumK(List<Integer> l, int K) {
		Collections.sort(l);
		int sum = 0;
		int cnt = 0;
		for (int i = l.size() - 1; i >= 0; i--) {
			if (cnt++ < K)
				continue;
			sum += i;
		}
		return sum;
	}

}
