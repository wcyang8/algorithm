package baekjoon.wc_bj_20056;

/**
 * 1. 파이어볼 이동
 * 	1.1. 맵에 파이어볼을 어떻게 표시할까?
 * 2. 2개 이상의 파이어볼이 있는지 확인
 * 3. 2개 이상의 파이어볼이 있는 칸에서 분리
 * 	3.1. 질량 분배
 * 	3.2. 속력 분배
 * 	3.3. 방향 정하기
 * 	3.4. 질량 0 되면 삭제
 * 4. 남아있는 파이어볼 질량 구하기
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static List<int[]> fireball = new ArrayList<>();
	static int[][] dir = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int N, weight;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			weight += m;
			
			fireball.add(new int[] { r, c, m, s, d }); // [2] : 질량 m, [3] : 속력 s, [4] : 방향 d
		}

		/* 입력 끝 */

		for (int k = 0; k < K; k++) {
//			System.out.println("=======");
//			System.out.println("k : "+ k);
			// move
			move();
		}
		
		System.out.println(weight);
	}

	private static void move() {
		Map<Integer, List<int[]>> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		
		for (int[] cur : fireball) {
			// 위치 이동
			cur[0] = (cur[0] + N + (cur[3] * dir[cur[4]][0])%N) % N;
			cur[1] = (cur[1] + N + (cur[3] * dir[cur[4]][1])%N) % N;

			int pos = cur[0] * N + cur[1];

			if (map.get(pos) == null) {
				map.put(pos, new ArrayList<>(Arrays.asList(cur)));
			}else {
				map.get(pos).add(cur);
				set.add(pos);
			}
		}
//		System.out.println(map);
//		System.out.println("====before");
//		for(int[] arr : fireball) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println(set);
		// merge and spread
		mns(set, map);
	}

	private static void mns(Set<Integer> set, Map<Integer, List<int[]>> map) {
		for(int cur : set) {
			int ci = cur / N;
			int cj = cur % N;
			
			List<int[]> temp = map.get(cur);
			
			int msum = 0;
			int ssum = 0;
			int same = 0;
			int size = temp.size();
			boolean odd = temp.get(0)[4] % 2 == 1;
			for(int[] fb : temp) {
				if(odd != (fb[4] % 2 == 1)) same = 1;
				msum += fb[2];
				ssum += fb[3];
				fireball.remove(fb);
			}
			weight -= msum;
			msum /= 5;
			ssum /= size;
			
			if(msum == 0) continue;
			
			weight += msum * 4;
			for(int d = 0; d < 8; d += 2) {
				fireball.add(new int[] {ci,cj,msum,ssum,d+same});
			}
		}
//		System.out.println("====after");
//		for(int[] arr : fireball) {
//			System.out.println(Arrays.toString(arr));
//		}
	}
}
