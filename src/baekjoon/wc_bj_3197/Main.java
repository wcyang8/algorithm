package baekjoon.wc_bj_3197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, day;
	static int[][] lake, swan, dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		lake = new int[R][C];
		swan = new int[2][2];

		int num = 2;
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = s.charAt(j);
				if (c == '.') {
					lake[i][j] = 1;
				} else if (c == 'L') {
					swan[num - 2][0] = i;
					swan[num - 2][1] = j;
					lake[i][j] = num++;
				}
			}
		}

		fill(swan[0][0], swan[0][1]);
		fill(swan[1][0], swan[1][1]);
//		while (true) {
			melt();
			++day;
//		}
	}

	private static void melt() {
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> q2 = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (lake[i][j] == 0) {
					for (int[] d : dir) {
						int ni = i + d[0];
						int nj = j + d[1];

						if (ni >= 0 && ni < R && nj >= 0 && nj < C) {
							if(lake[ni][nj] > 0) {
							if (lake[i][j] == 0) {
								lake[i][j] = -lake[ni][nj];
								q.add(i);
								q.add(j);
							}else if(lake[i][j] <= -2){
								lake[i][j] = -lake[ni][nj];
								q.add(i);
								q.add(j);
								q2.add(i);
								q2.add(j);
							}
							}
						}
					}
				}
			}
		}
		while(!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();
			lake[ci][cj] = Math.abs(lake[ci][cj]);
		}
		System.out.println(q2);
		while(!q2.isEmpty()) {
			int ci = q2.poll();
			int cj = q2.poll();
			fill(ci, cj);
		}
		print();
	}
	private static void print() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(lake[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void fill(int i, int j) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(i);
		q.add(j);
		while (!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();

			for (int[] d : dir) {
				int ni = ci + d[0];
				int nj = cj + d[1];
				if (ni >= 0 && ni < R && nj >= 0 && nj < C) {
					if(lake[ni][nj] == 0) continue;		// 얼음
					else if (lake[ni][nj] == 1) {		// 물
						lake[ni][nj] = lake[i][j];
						q.add(ni);
						q.add(nj);
					} else if (lake[ni][nj] != lake[i][j]) {	// 다른 백조의 공간
						print();
						System.out.println("where i, j : "+i+" "+j);
						System.out.println("where ni, nj : "+ni+" "+nj);
						System.out.println(day);
						System.exit(0);
					}
				}
			}
		}
	}

}
