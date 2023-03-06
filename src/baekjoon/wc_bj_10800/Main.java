package baekjoon.wc_bj_10800;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class boll implements Comparable<boll> {
	int number;
	int color;
	int size;

	public boll(int number, int color, int size) {
		super();
		this.number = number;
		this.color = color;
		this.size = size;
	}

	@Override
	public int compareTo(boll o) {
		return Integer.compare(this.size, o.size);
	}

	@Override
	public String toString() {
		return "boll [number=" + number + ", color=" + color + ", size=" + size + "]";
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[] result = new int[N];
		int[] colors = new int[N];

		PriorityQueue<boll> pq = new PriorityQueue<boll>();
		Queue<boll> temp = new ArrayDeque<boll>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new boll(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}

		int totalSum = 0;
		while (!pq.isEmpty()) {
			boll cur = pq.poll();
			if (!temp.isEmpty() && cur.size > temp.peek().size) {
				while (!temp.isEmpty()) {
					boll tcur = temp.poll();
					totalSum += tcur.size;
					colors[tcur.color] += tcur.size;
				}
			}
			temp.add(cur);
			result[cur.number] = totalSum - colors[cur.color];
		}
		for (int i : result)
			sb.append(i).append('\n');
		System.out.println(sb);
	}

}
