package swea.wc_swea_5656;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, W, H;
	static int[][] blocks;
	static int[][] sim;
	static int[] selected;
	static int blockCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			blocks = new int[H][W];
			sim = new int[H][W];
			selected = new int[N];

			blockCnt = 0;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					blocks[i][j] = Integer.parseInt(st.nextToken());
					if(blocks[i][j] != 0) blockCnt++;
				}
			}
			blockCnt -= simulation(0);
			sb.append('#').append(tc).append(' ').append(blockCnt).append('\n');
		}
		System.out.println(sb);
	}

	static int simulation(int cnt) {
		if (cnt == N) {
			for (int i = 0; i < blocks.length; i++) {
				System.arraycopy(blocks[i], 0, sim[i], 0, sim[i].length);
			}
			return shoot();
		}
		int max = 0;
		for (int i = 0; i < W; i++) {
			selected[cnt] = i;
			max = Math.max(max, simulation(cnt + 1));
			if(max == blockCnt) break;
		}
		return max;
	}

	static int shoot() {
		int result = 0;
		for (int s = 0; s < N; s++) {
			for (int h = 0; h < H; h++) {
				if (sim[h][selected[s]] != 0) {
					result += Break(h, selected[s]);
					break;
				}
			}
			down();
		}
		return result;
	}

	static int Break(int i, int j) {
		int result = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(i);
		q.add(j);
		sim[i][j] *= -1;
		while (!q.isEmpty()) {
			int ci = q.poll();
			int cj = q.poll();
			
			for (int[] sp : d) {
				for (int k = 0; k <= Math.abs(sim[ci][cj]) - 1; k++) {
					int ni = ci + sp[0] * k;
					int nj = cj + sp[1] * k;
					if (ni >= 0 && ni < H && nj >= 0 && nj < W) {
						if(sim[ni][nj] <= 0) continue;
						q.add(ni);
						q.add(nj);
						sim[ni][nj] *= -1;
					}
				}
			}
		}
		for(int h = 0; h < H; h++) {
			for(int w = 0; w < W; w++) {
				if(sim[h][w] < 0) {
					sim[h][w] = 0;
					result++;
				}
			}
		}
		return result;
	}

	static void down() {
		for (int j = 0; j < W; j++) {
			int zero = -1;
			for (int i = H - 1; i >= 0; i--) {
				if (sim[i][j] == 0 && zero < i)
					zero = i;
				if (zero != -1 && sim[i][j] != 0) {
					sim[zero][j] = sim[i][j];
					sim[i][j] = 0;
					zero--;
				}
			}
		}
	}
}
