package baekjoon.wc_bj_1423;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 요약 
 * 1. 캐릭터를 D일 동안 훈련, 레벨은 1이상 N이하 
 * 2. 하루에 한 캐릭터를 골라 훈련 = 1렙업 
 * 3. 게임을 하지 않고 쉬어도 됨. 
 * 4. 캐릭터마다 시작 레벨이 있고, 레벨 별 힘이 주어진다. 
 * 5. 레벨 최대값은 N일 때, 
 * 6. D일 후 힘의 합 중 최댓값을 구해보자.
 * 
 * 유의 
 * 1. 50레벨 x 캐릭터수 100만 x 힘 100만 = int 초과이므로 long 사용. 
 * 2. 레벨이 높다고 항상 힘이 높지 않음.
 * 
 * 풀이
 * 1. (from, gap, getStr) 을 pq에 넣는다. 
 * 2. pq는 (얻는 힘 / 레벨업)이 높은 순으로 배열. 
 * 3. -면 그냥 현재 값을 리턴
 * 
 * 테케
 * 50
0 10 100 0 1000 0 1 1000 10000 10000 0 1000000 10000 0 100000 1000000 100000 100000 10000 1000000 0 1000000 1000000 0 1000000 1000000 1000000 1000000 10000 1000000 0 1000000 1000000 0 1000000 1000000 1000000 1000000 10000 100000 0 1000000 10000 0 1000000 1000000 100000 10000 10000 1000000 0 1000000 1000000 0 1000000 1000000 1000000 1000000 10000 10000
1000000 100000 1000 1000000 1000000 0 1000000 100000 1000000 1000000 0 1000000 1000000 0 1000000 1000000 1000000 1000000 10000 1000000 0 1000000 1000000 0 1000000 1000000 1000000 1000000 10000 1000000 0 1000000 1000000 0 1000000 1000000 1000000 1000000 10000 1000000 0 1000000 1000000 0 10000 10 100000 10000 100 1000 0 10000 10000 0 100 100000 00 1000 0 0
100
 * 
 * @author SSAFY
 *
 */

class levelUp implements Comparable<levelUp> {
	int from;
	int gap;
	int getStr;

	public levelUp(int from, int to, int getStr) {
		super();
		this.from = from;
		this.gap = to;
		this.getStr = getStr;
	}

	@Override
	public int compareTo(levelUp o) {
		return Integer.compare(o.getStr * this.gap, this.getStr * o.gap);
	}

	@Override
	public String toString() {
		return "levelUp [from=" + from + ", gap=" + gap + ", getStr=" + getStr + "]";
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] playersByLevel = new int[N];
		int[] strByLevel = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			playersByLevel[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			strByLevel[i] = Integer.parseInt(st.nextToken());
		}

		int D = Integer.parseInt(br.readLine());

		PriorityQueue<levelUp> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				pq.add(new levelUp(i, j - i, strByLevel[j] - strByLevel[i]));
			}
		}

		while (!pq.isEmpty()) {
			System.out.println(pq.peek());
			levelUp cur = pq.poll(); // 효율이 가장 좋은 레벨업
			if (cur.getStr <= 0 || D == 0)
				break;

			if (playersByLevel[cur.from] > 0) { // 그 레벨업의 시작에 캐릭터가 있으면
				int time = D / cur.gap;			// 그 레벨업을 몇번 할 수 있는가?
				int temp = Math.min(time, playersByLevel[cur.from]);		// D의 가능한 레벨업 횟수 vs 그 레벨 유저 수
				D -= temp * cur.gap;									// D에서 빼준다.
				playersByLevel[cur.from] -= temp;						// 레벨업 전 유저 수에서 빼준다.
				playersByLevel[cur.from + cur.gap] += temp;				// 레벨업 후 유저 수에 더해준다.
			}
		}
		System.out.println(Arrays.toString(playersByLevel));
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += (long)playersByLevel[i] * (long)strByLevel[i];
		}
		System.out.println(sum);
	}
}
