package baekjoon.wc_bj_20057;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int out, N;
	static int[][] map, dir = {{0,-1},{1,0},{0,1},{-1,0}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(N/2, N/2, 0, 0, 1);
		
		System.out.println(out);
	}
	private static void move(int i, int j, int d, int cur, int time) {
		// 1,1 도착하면 종료
		if(i == 0 && j == 0) return;
		// cur이 time을 채우면
		if(cur == time) {
			// 왼쪽 아래, 오른쪽 위 묶기
			if(d / 2 != (d + 1)/2) {
				++time;
			}
			cur = 0;
			d = (d+1)%4;
		}
		// 모래 분산
		sand(i, j, d);
//		print();
		
		move(i+dir[d][0], j+dir[d][1], d, cur+1, time);
	}
	private static void print() {
		System.out.println("=====after sand=====");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("====================");
		System.out.println(out);
	}
	private static void sand(int i, int j, int d) {
		int ni = i + dir[d][0];
		int nj = j + dir[d][1];
		
		// 이동 위치의 모래 양
		int y = map[ni][nj];
		int remove = 0;
		
		// 5%
		int ti = i + 3*dir[d][0];
		int tj = j + 3*dir[d][1];
		int temp = y * 5 / 100;
		remove += temp;			// y에서 모래 이동
		spread(ti, tj, temp);
		
		// 위 아래 동시 처리
		for(int k = 1; k <= 3; k+=2) {
			int td = (d + k) % 4;
			
			ti = i + dir[td][0];
			tj = j + dir[td][1];
			// 10%, 7%, 1%
			for(int w = 0; w < 3; w++) {
				temp = y * (10 - (w==0?3:(2-w)) * 3) / 100;
				remove += temp;
				spread(ti, tj, temp);
				ti += dir[d][0];
				tj += dir[d][1];
			}
			
			// 2%
			ti = i + 2 *dir[td][0] + dir[d][0];
			tj = j + 2 *dir[td][1] + dir[d][1];
			
			temp = y * 2 / 100;
			remove += temp;
			spread(ti, tj, temp);
		}
		
		// 마지막으로 알파 자리
		ti = i + 2*dir[d][0];
		tj = j + 2*dir[d][1];
		temp = y - remove;			// 분배 후 남은 모래
		spread(ti, tj, temp);
		map[ni][nj] = 0;
	}
	private static void spread(int i, int j, int temp) {
		if(i >= 0 && i < N && j >= 0 && j < N) {// map 안이면
			map[i][j] += temp;	// 모래 분산
		}else {	// map 밖이면
			out += temp;	// out
		}
	}
}
