package baekjoon.wc_bj_17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 새 집의 크기는 N x N
 * 2. (행:r,열:c) 번호는 1부터 시작
 * 3. 파이프는 가로, 세로, 역슬래시 방향 가능.
 * 4. 파이프를 밀 수 있는데, 45도만 회전 가능.
 * 
 * 풀이
 * 1. 3차원 visit[N][N][3] 배열 사용
 * 2. 그 위치를 어떤 방향으로 몇번 visit을 했는지 visit[i][j][방향]에 저장.
 * 3. 이전 위치 3개 탐색
 * 4. 이전 위치의 진입 방향과 현재 위치의 진입 방향의 각도가 45도가 넘지 않도록 해야함.
 * 5. 현재 위치의 진입 방향이 대각선 방향이면 위, 왼쪽에 벽 있는지 확인.
 * 6. (1,1)부터 (N,N)까지 dp로 넣어줌.
 * 7. visit[N][N][1~3]합 출력
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] d = {{0,1},{1,1},{1,0}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] wall = new boolean[N][N];
		int[][][] visit = new int[N][N][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				wall[i][j] = st.nextToken().equals("1");
			}
		}
		
		visit[0][1][0] = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(wall[i][j]) continue;
				for(int k = 0; k < 3; k++) {
					if(k == 1 && ((i-1 >= 0 && wall[i-1][j])||(j-1>=0) && wall[i][j-1])) continue;
					int bi = i - d[k][0];
					int bj = j - d[k][1];
					
					if(bi >= 0 && bj >= 0 && !wall[bi][bj]) {
						for(int l = 0; l < 3; l++) {
							if(Math.abs(k-l) < 2) visit[i][j][k] += visit[bi][bj][l];		// 45도 이상 불가
						}
					}
				}
			}
		}
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(Arrays.toString(visit[i][j]) + " ");
//			}
//			System.out.println();
//		}
		System.out.println(visit[N-1][N-1][0] + visit[N-1][N-1][1] + visit[N-1][N-1][2]);
	}
}
