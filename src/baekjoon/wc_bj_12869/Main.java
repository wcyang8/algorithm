package baekjoon.wc_bj_12869;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] attack = { 9, 3, 1 };

		int N = Integer.parseInt(br.readLine());

		int[][][] SCV = new int[2][3][3]; // 이전 : 0, 다음 : 1

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			SCV[0][0][i] = Integer.parseInt(st.nextToken());
		}

		int time = 0;
		while (true) {
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (SCV[0][j][i] > 0) {
						SCV[1][j][i] = SCV[0][j][i] - attack[(i + j) % N];
						flag = true;
					}
				}
			}
			if (!flag)
				break;
			++time;
		}
		System.out.println(time);
	}

}
