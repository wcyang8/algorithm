package baekjoon.wc_bj_17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, score = 0;
	static int[][] result;
	static int[] selected;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 이닝 수

		result = new int[N][9]; // 입력되는 결과
		selected = new int[9]; // 선택된 순열 저장할 배열
		visited = new boolean[9]; // 이미 방문

		for (int i = 0; i < N; i++) { // 결과 배열 입력
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		selected[3] = 0;		// 4번 타자 = 1번 선수 미리 선택
		visited[0] = true;
		System.out.println(perm(0));
	}

	private static int perm(int cnt) {
		if (cnt == 3) {				// 3번째는 패스
			return perm(cnt+1);
		}
		if (cnt == 9) {
			return simul();
		}
		int max = 0;
		for (int i = 1; i < 9; i++) {
			if (visited[i]) continue;	// i번 선수는 이미 선택 완료
			visited[i] = true;		// i번 선수 선택
			selected[cnt] = i;		// cnt번 타자 = i번 선수
			max = Math.max(max, perm(cnt + 1));
			visited[i] = false;
		}
		return max;
	}

	private static int simul() { // 선택된 순열로 N이닝 시뮬레이션
		boolean[] lu = new boolean[4]; // 루 상황
		int score = 0; // N이닝 총 점수
		int cur = 0; // cur번 타자
		for (int i = 0; i < N; i++) { // N이닝 진행
			int oc = 0;
			while (oc < 3) { // 3아웃 될 때 까지
				int num = selected[cur]; // cur번 타자 = num번 선수
				int temp = result[i][num];
				lu[0] = true; // 타석
				if (temp > 0) {	// 안타라면
					for (int j = 3; j >= 0; j--) { // 루 마다
						if (lu[j]) {
							lu[j] = false;
							if (j + temp >= 4)
								score++; // 결과 실행
							else
								lu[j + temp] = true;
						}
					}
				}
				else { // 아웃이라면
					lu[0] = false;
					oc++; // 아웃카운트 1 증가
				}
				++cur; // 다음 타자
				cur %= 9;
			}
			Arrays.fill(lu, false);
		}
		return score;
	}
}
