package baekjoon.wc_bj_3109;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int R, C;
	static int[] di = { -1, 0, 1 };
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr = br.readLine().split(" ");

		R = Integer.parseInt(sArr[0]);
		C = Integer.parseInt(sArr[1]);

		map = new char[R][];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		System.out.println(connect());
	}

	static int connect() {
		int result = 0;
		
		for (int i = 0; i < R; i++) { // 각 행에서 출발
			if (connectPipe(i, 1))
				result++;
		}

		return result;
	}

	static boolean connectPipe(int i, int j) {
		if(j == C - 1) return true;
		for (int k = 0; k < 3; k++) { 				// 0, 1, 2는 각각 오른쪽 위, 오른쪽, 오른쪽 아래
			if (i + di[k] >= R || i + di[k] < 0)
				continue; 						// di만큼 이동했을 때 map 밖이 아니고
			if (map[i + di[k]][j] != 'x') { 	// 다음 진행 루트가 건물이 아니면
				map[i + di[k]][j] = 'x'; 		// 다음 위치를 x로 만들고 또 한발만 앞으로
				if (!connectPipe(i + di[k], j + 1)) {
					map[i + di[k]][j] = '.';
					return false;
				}
				break; 							// 찾았으므로 break;
			}
		}
		return false;
	}
}
