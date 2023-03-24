package baekjoon.wc_bj_14891;

/**
 * 요약
 * 1. 4개의 톱니바퀴 8개의 톱니
 * 2. 앞의 2번째 톱니 vs 뒤의 6번째 톱니
 * 3. K번 회전해서 각 점수 계산
 * 
 * 풀이
 * 1. 각 톱니바퀴 회전 방향 지정 함수
 * 2. 회전 함수 3번 비교
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer>[] gears;
	static int[] dir = new int[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		gears = new LinkedList[4];

		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			gears[i] = new LinkedList<Integer>();
			for (int j = 0; j < 8; j++) {
				gears[i].add(s.charAt(j) - '0');
			}
		}

		int K = Integer.parseInt(br.readLine());

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			
			Arrays.fill(dir, 2);
			dir[num] = Integer.parseInt(st.nextToken());

			nextDir(num);
			
			for(int i = 0; i < 4; i++) rotate(i);
		}
		System.out.println(score());
	}

	private static void nextDir(int num) {
//		System.out.println(Arrays.toString(dir));
		// 왼쪽
		if (num-1 >= 0 && dir[num - 1] == 2) {
			if (gears[num].get(6) == gears[num - 1].get(2)) {
				dir[num - 1] = 0;
			} else {
				dir[num - 1] = -dir[num];
			}
			nextDir(num-1);
		}
		// 오른쪽
		if(num+1 < 4 && dir[num+1] == 2) {
			if (gears[num].get(2) == gears[num + 1].get(6)) {
				dir[num + 1] = 0;
			} else {
				dir[num + 1] = -dir[num];
			}
			nextDir(num+1);
		}
	}

	private static void rotate(int num) {
		// 시계 방향
		if (dir[num] == 1) {
//			System.out.println(gears[num]);
			int temp = gears[num].remove(7);
			gears[num].add(0, temp);
//			System.out.println(gears[num]);
		} else if(dir[num] == -1){ // 반시계 방향
//			System.out.println(gears[num]);
			int temp = gears[num].remove(0);
			gears[num].add(temp);
//			System.out.println(gears[num]);
		}
	}
	private static int score() {
		int sum = 0;
		for(int k = 0; k < 4; k++) {
			sum |= (gears[k].get(0) << k);
		}
		return sum;
	}
}
