package baekjoon.wc_bj_15961;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 요약 1. 벨트 위에 번호로 표현되는 여러 초밥이 있음. 2. 손님은 자기가 좋아하는 초밥을 골라서 먹음. 3. 벨트 위에는 같은 종류의
 * 초밥이 둘 이상 있을 수 있음. 4. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹으면 할인된 가격 5. 연속해서 먹을 수 있는
 * 초밥의 최대 가짓수를 구해보자
 * 
 * 풀이 1. k개의 접시 + 쿠폰의 초밥이 최대한 서로 달라야함. 2. 덱에 오른쪽에 넣고 왼쪽에서 빼면서 가짓수만 변경
 * 
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken()) - 1;

		int[] sp = new int[d];

		++sp[c];
		int max = 0;
		int kind = 1;
		Deque<Integer> dq = new ArrayDeque<>(); // 연속해서 먹는 k개의 접시
		Deque<Integer> temp = new ArrayDeque<>();	// 처음 k개를 넣어줄 덱
		for (int i = 0; i < N + k; i++) {
			int cur;
			if(i < N) cur = Integer.parseInt(br.readLine()) - 1;
			else cur = temp.pollFirst();
			
			// k개가 들어온 이후부터 왼쪽을 뺀다.
			if (dq.size() >= k) {
				int last = dq.pollFirst();
				if (--sp[last] == 0)
					kind--;
			}else {
				temp.add(cur);
			}

			// 오른쪽에 추가
			dq.add(cur);
			if (sp[cur]++ == 0)
				kind++;

			max = Math.max(max, kind);
		}

		System.out.println(max);
	}

}
