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

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // N 입력
		int M = Integer.parseInt(st.nextToken()); // M 입력

		List<Integer> house = new LinkedList<Integer>();
		PriorityQueue<pair>[] road = new PriorityQueue[N]; // 간선 생성
		for (int i = 0; i < N; i++) {
			road[i] = new PriorityQueue<pair>(); // 간선 초기화
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

		int sum = 0;
		house.add(0); // 0에서 시작해보자.
		int maxCost = 0;
		while (house.size() < N) { // 점이 N개 선택될 때까지 반복
			int min = Integer.MAX_VALUE;
			int minV = -1;
			for (int i : house) {
				while (!road[i].isEmpty()) {
					if (house.contains(road[i].peek().dest))
						road[i].poll();
					else
						break;
				}
				if (road[i].isEmpty())
					continue;
				if (min > road[i].peek().cost) {
					minV = i;
					min = road[i].peek().cost;
				}
			}
			pair temp = road[minV].poll();
			house.add(temp.dest);
			sum += temp.cost;
			maxCost = Math.max(maxCost, temp.cost);
		}
		sum -= maxCost;
		System.out.println(sum);
	}

}
