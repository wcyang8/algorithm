package swea.wc_swea_4014_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 요약 
 * 1. N*N 지형 
 * 2. 각 숫자는 지형의 높이 
 * 3. 활주로는 지형의 행 열 방향으로 건설 
 * 3. 높이가 다르면 활주로가 끊어져서
 * 경사로를 설치해야함. 
 * 4. 경사로의 높이는 1, 길이는 X로 고정 
 * 5. 활주로를 건설할 수 있는 경우의 수를 계산해보자.
 * 
 * 풀이
 * 1. 낮아지는 경우 -> 앞으로 X칸 이상 같아야함
 * 2. 올라가는 경우 -> 지금까지 X칸 이상 같아야함
 * 3. 
 * 
 * 
 * @author SSAFY
 *
 */

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;
			boolean flag = true;
			Stack<Integer> stack = new Stack<>();

			// 가로
			for (int i = 0; i < N; i++) {
				int con = 0;
				for (int j = 0; j < N; j++) {
					System.out.println("가로 " + i + "," + j + " = " + map[i][j]);
					System.out.println(stack);
					int len = 0;
					int last = map[i][j];
					while (!stack.isEmpty() && stack.peek() != map[i][j]) {		// peek가 같으면 그냥 넣고 아니면 계속 뺀다.
						last = stack.pop();
						len++;
					}
					if (Math.abs(last - map[i][j]) > 1) {
						flag = false;
						break; // 차이가 1 넘으면 불가능
					}
					if (last < map[i][j] && len < X) {	// last보다 map이 더 크면
						flag = false;
						break;	 
					}
					if(last != map[i][j] && stack.size() < con) {
						flag = false;
						break;
					}
					if (last > map[i][j]) {	// last가 map보다 크면
						con = X;
					}
					stack.add(map[i][j]);
				}
				if (flag) {
					System.out.println("ok");
					cnt++;
				}
			}

			// 세로
			for (int j = 0; j < N; j++) {
				int con = 0;
				for (int i = 0; i < N; i++) {
					System.out.println("세로 " + i + "," + j + " = " + map[i][j]);
					System.out.println(stack);
					int len = 0;
					int last = map[i][j];
					while (!stack.isEmpty() && stack.peek() != map[i][j]) {		// peek가 같으면 그냥 넣고 아니면 계속 뺀다.
						last = stack.pop();
						len++;
					}
					if (Math.abs(last - map[i][j]) > 1) {
						flag = false;
						break; // 차이가 1 넘으면 불가능
					}
					if (last < map[i][j] && len < X) {	// last보다 map이 더 크면
						flag = false;
						break;	 
					}
					if(last != map[i][j] && stack.size() < con) {	// con을 다 못채웠는데 값이 바뀌면
						flag = false;
						break;
					}
					if (last > map[i][j]) {	// last가 map보다 크면
						con = X;
					}
					stack.add(map[i][j]);
				}
				if (flag) {
					System.out.println("ok");
					cnt++;
				}
			}

			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		System.out.print(sb);
	}

}
