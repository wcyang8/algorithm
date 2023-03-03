package baekjoon.wc_bj_17144;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] d = {{0,1},{-1,0},{0,-1},{1,0}};
	static int R, C, T, up, down, sum;
	static int[][] A;
	static int[][] temp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		temp = new int[R][C];
		
		up = -1;
		down = -1;
		sum = 0;
		for(int i = 0; i < R; i++) {			// 방 입력
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if(up == -1 && A[i][j] == -1) {
					up = i;						// 공기 청정기 위 아래 입력 받기
					down = i + 1;
				}
				else if(A[i][j] > 0) sum += A[i][j];
			}
		}
		
		System.out.println(simulation());
	}

	static int simulation() {				// 남은 미세먼지를 return
		int result = 0;
		for(int t = 0; t < T; t++) {		// 1초 실행
			for(int i = 0; i < R; i++) {		// 탐색하며 0 이상이면 확산
				for(int j = 0; j < C; j++) {
					diffusion(i, j);
				}
			}
			apply();
			result += wind();					// 바람 불기 (제거한 미세먼지 return)
		}
		return sum - result;
	}
	static void printRoom() {				//debug를 위한 print 함수
		for(int[] i : A) {
			for(int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println("======================");
	}
	static void diffusion(int i, int j) {		// 확산 구현
		int share = A[i][j] / 5;		// 방향 당 1/5 분산
		for(int k = 0; k < 4; k++) {			// 4방향
			int ni = i + d[k][0];				// 분산될 위치
			int nj = j + d[k][1];
			
			if(ni >= 0 && ni < R && nj >= 0 && nj < C 
					&& A[ni][nj] != -1) {		// 벽, 공기청정기 아닐 때만 분산
				temp[i][j] -= share;
				temp[ni][nj] += share;
			}
		}
	}
	static void apply() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(temp[i][j] == 0) continue;
				A[i][j] += temp[i][j];
				temp[i][j] = 0;
			}
		}
		
	}
	static int wind() {						// 전체 미세먼지 수에 영향을 주는건 wind함수 뿐
		int result = 0;
		int ctemp = 0;		// 위치 이동 시 교체해줄 변수
		int ntemp = 0;
		int i = up;
		int j = 0;
		// 위쪽 바람
		for(int k = 0; k < 4; k++) {	// k : 방향을 나타냄. 0 1 2 3  -> 오른 위 왼 아래
			int ni = i + d[k][0];
			int nj = j + d[k][1];
			while(ni >= 0 && ni < R && nj >= 0 && nj < C) {
				if(A[ni][nj] == -1) {
					result += ctemp;
					break;
				}
				ntemp = A[ni][nj];
				A[ni][nj] = ctemp;
				ctemp = ntemp;
				i = ni;
				j = nj;
				ni = i + d[k][0];
				nj = j + d[k][1];
			}
		}
		// 아래쪽 바람
		ctemp = 0;
		ntemp = 0;
		i = down;
		j = 0;
		for(int k = 4; k > 0; k--) {	// k : 0 3 2 1	-> 오른 아래 왼 위
			int ni = i + d[k%4][0];
			int nj = j + d[k%4][1];
			while(ni >= 0 && ni < R && nj >= 0 && nj < C) {
				if(A[ni][nj] == -1) {
					result += ctemp;
					break;
				}
				ntemp = A[ni][nj];
				A[ni][nj] = ctemp;
				ctemp = ntemp;
				i = ni;
				j = nj;
				ni = i + d[k%4][0];
				nj = j + d[k%4][1];
			}
		}
		return result;
	}
}
