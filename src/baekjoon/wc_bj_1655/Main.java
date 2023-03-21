package baekjoon.wc_bj_1655;

/**
 * 요약
 * 1. 백준이가 외치는 수 중에 중간값을 연속적으로 말하는 프로그램.
 * 
 * 풀이
 * 1. 수를 add할 때 마다 계속 sort되어 있어야 한다.
 * 2. pq를 2개 사용해서 하나는 오름차순 하나는 내림차순으로 구현
 * 3. pq 2개가 서로 균형이 맞아야함. (크기 차가 2 이상이면 안됨)
 * 
 * 유의
 * 1. 정수 -10000부터 10000까지 이므로 int 사용
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> leftpq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
		PriorityQueue<Integer> rightpq = new PriorityQueue<>();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(br.readLine());

			int gap = leftpq.size() - rightpq.size();

			if (leftpq.isEmpty())
				leftpq.add(cur);
			else if (rightpq.isEmpty()) { // 오른쪽이 비어있는데
				if (cur < leftpq.peek()) { // 왼쪽이 더 크면
					rightpq.add(leftpq.poll());
					leftpq.add(cur);
				}
				else rightpq.add(cur);
			} else {
				if (cur - leftpq.peek() < 0) { // 왼쪽에 넣어야하는데
					if (gap > 0) // 왼쪽이 더 많으면
						rightpq.add(leftpq.poll()); // 왼쪽에서 빼서 오른쪽에 넣어줌.
					leftpq.add(cur);
				} else if (rightpq.peek() - cur < 0) { // 오른쪽에 넣어야 하는데
					if (gap <= 0) // 오른쪽이 더 많으면
						leftpq.add(rightpq.poll()); // 오른쪽에서 빼서 왼쪽에 넣어줌.
					rightpq.add(cur);
				} else { // 둘다 상관 없을 때
					if (gap > 0) // 왼쪽이 더 많으면
						rightpq.add(cur); // 오른쪽에 넣기
					else // 같거나 오른쪽이 더 많으면
						leftpq.add(cur); // 왼쪽에 넣기
				}
			}
//			
//			if(i % 2 == 0) leftpq.add(cur);
//			else rightpq.add(cur);
//			
//			
//			if(!rightpq.isEmpty() && leftpq.peek() > rightpq.peek()) {
//				int temp = leftpq.poll();
//				leftpq.add(rightpq.poll());
//				rightpq.add(temp);
//			}
			
			sb.append(leftpq.peek()).append('\n');
		}
//		while(!leftpq.isEmpty()) System.out.println(leftpq.poll());
//		System.out.println();
//		while(!rightpq.isEmpty()) System.out.println(rightpq.poll());
		System.out.println(sb);
	}

}
