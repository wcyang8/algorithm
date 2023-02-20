package swea.wc_swea_1873;

/**
 * 
문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
 */
/**
 * 
문자	동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

	static String tank = "^v<>";
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static char[][] map;
	static int H, W, cur_i, cur_j, cur_d;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		String[] sArr;
		String s;

		for (int test_case = 1; test_case <= T; test_case++) {
			sArr = br.readLine().split(" ");
			H = Integer.parseInt(sArr[0]);
			W = Integer.parseInt(sArr[1]);

			map = new char[H][W];

			cur_i = -1;
			cur_j = -1;
			cur_d = -1;
			for (int h = 0; h < H; h++) {
				s = br.readLine();
				for (int w = 0; w < W; w++) {
					map[h][w] = s.charAt(w);
					if (tank.indexOf(map[h][w]) != -1) {
						cur_i = h;
						cur_j = w;
						cur_d = tank.indexOf(map[h][w]);
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			s = br.readLine();

			for (int k = 0; k < N; k++) {
				command(s.charAt(k));
			}
			sb.append('#').append(test_case).append(' ');
			for(char[] m : map) {
				for(char c : m)
					sb.append(c);
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

	static void command(char c) {
		int d = 0; // tank.charAt(0) = '^', 1 : 'v', 2 : '<', 3 : '>'
		switch (c) {
		case 'R':
			d++;
		case 'L':
			d++;
		case 'D':
			d++;
		case 'U':
			move(d);
			break;
		case 'S':
			shoot();
		}
	}

	static void move(int d) {
		cur_d = d;
		int next_i = cur_i + di[cur_d];
		int next_j = cur_j + dj[cur_d];
		if ((next_i >= 0 && next_i < H) && (next_j >= 0 && next_j < W) && map[next_i][next_j] == '.') {
			map[cur_i][cur_j] = '.';
			cur_i = next_i;
			cur_j = next_j;
		}
		map[cur_i][cur_j] = tank.charAt(cur_d);
	}

	static void shoot() {
		int bomb_i = cur_i + di[cur_d];
		int bomb_j = cur_j + dj[cur_d];
		while ((bomb_i >= 0 && bomb_i < H) && (bomb_j >= 0 && bomb_j < W)) {
			if (map[bomb_i][bomb_j] == '*') {
				map[bomb_i][bomb_j] = '.';
				break;
			}
			if (map[bomb_i][bomb_j] == '#') {
				break;
			}
			bomb_i += di[cur_d];
			bomb_j += dj[cur_d];
		}
	}
}
