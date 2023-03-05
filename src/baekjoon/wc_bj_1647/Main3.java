package baekjoon.wc_bj_1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class pair implements Comparable<pair> {
	int dest;
	int cost;

	public pair(int dest, int cost) {
		super();
		this.dest = dest;
		this.cost = cost;
	}

	@Override
	public int compareTo(pair o) {
		return (this.cost > o.cost) ? 1 : (this.cost == o.cost) ? 0 : -1;
	}

}

public class Main3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // N 입력
		int M = Integer.parseInt(st.nextToken()); // M 입력

		boolean[] house = new boolean[N];		// visited 배열 생성
		List<pair>[] road = new LinkedList[N];	// 간선 리스트 배열 생성
		for (int i = 0; i < N; i++) {
			road[i] = new LinkedList<pair>();	// 간선 리스트 생성
		}

		int A, B, C;
		for (int i = 0; i < M; i++) { // 간선 입력
			st = new StringTokenizer(br.readLine()); // 1개의 간선 입력

			A = Integer.parseInt(st.nextToken()) - 1; // A 받기
			B = Integer.parseInt(st.nextToken()) - 1; // B 받기
			C = Integer.parseInt(st.nextToken()); // C 받기

			road[A].add(new pair(B, C));
			road[B].add(new pair(A, C));
		}
		PriorityQueue<pair> selected = new PriorityQueue<>();	// 선택된 점과 연결된 간선을 넣어줄 우선순위 큐

        house[0] = true;
		int sum = 0;	// 최소 신장 트리 비용 총 합
		for (pair p : road[0])		// 0부터 시작
			selected.add(p);		// 0과 연결된 간선 전부 pq에 add
		int maxCost = 0;			// 연결된 간선 중 가장 높은 유지비
		for (int cnt = 1; cnt < N; cnt++) { // 점이 N개 선택될 때까지 반복
			while (!selected.isEmpty()	// pq에 원소가 있는 동안
					&& house[selected.peek().dest]) {	// 간선의 목적지가 0과 연결된 정점이면
				selected.poll();
			}
			pair temp = selected.poll();		// temp : 선택된 간선, temp.dest = 선택된 정점
			house[temp.dest] = true;			// visited 처리
			for (pair p : road[temp.dest])		// 인접리스트를 통해 다음 정점에 연결된 간선들 pq에 넣기
				if(!house[p.dest]) selected.add(p);		// 방문 안한 것만
			sum += temp.cost;					// MST의 비용 총 합
			maxCost = Math.max(maxCost, temp.cost);		// 가장 높은 유지비 저장
		}
		sum -= maxCost;				// 가장 높은 유지비만 빼준다.
		System.out.println(sum);	// 출력.
	}
}