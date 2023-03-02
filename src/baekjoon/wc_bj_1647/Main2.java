package baekjoon.wc_bj_1647;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//class pair implements Comparable<pair> {
//	int dest;
//	int cost;
//
//	public pair(int dest, int cost) {
//		super();
//		this.dest = dest;
//		this.cost = cost;
//	}
//
//	@Override
//	public int compareTo(pair o) {
//		return (this.cost > o.cost) ? 1 : (this.cost == o.cost) ? 0 : -1;
//	}
//
//}

public class Main2 {

	static int[] house;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // N 입력
		int M = Integer.parseInt(st.nextToken()); // M 입력

		house = new int[N];
		List<pair>[] road = new LinkedList[N]; // 간선 생성
		for (int i = 0; i < N; i++) {
			house[i] = i;
			road[i] = new LinkedList<pair>(); // 간선 초기화
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
		PriorityQueue<pair> selected = new PriorityQueue<>();

		int sum = 0;
		for (pair p : road[0])
			selected.add(p);
		int maxCost = 0;
		for (int cnt = 1; cnt < N; cnt++) { // 점이 N개 선택될 때까지 반복
			while (!selected.isEmpty() && !union(0, selected.peek().dest)) {
				selected.poll();
			}
			pair temp = selected.poll();
			house[temp.dest] = 0;
			for (pair p : road[temp.dest])
				selected.add(p);
			sum += temp.cost;
			maxCost = Math.max(maxCost, temp.cost);
		}
		sum -= maxCost;
		System.out.println(sum);
	}

	static int findSet(int v) {
		if (house[v] != v)
			return house[v] = findSet(house[v]);
		else
			return v;
	}

	static boolean union(int a, int b) {
		if (findSet(a) == findSet(b))
			return false;
		house[b] = a;
		return true;
	}
}