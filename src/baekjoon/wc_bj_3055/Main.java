package baekjoon.wc_bj_3055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 비버 굴로 도망
 * 2. 지도는 R, C로 이루어짐.
 * 3. 빈 공간 . , 물 * , 돌 X , 비버굴 D , 고슴도치 S
 * 4. 4방 이동
 * 5. 물도 매분 비어있는 칸으로 확장
 * 6. 고슴도치는 물로 차있는 구역으로 갈 수 없다.
 * 7. 고슴도치가 안전하게 비버굴로 이동하는 최소 시간을 구해보자.
 * 
 * 풀이
 * 1. 단순 BFS 구현
 * 
 * 유의
 * 1. 도착할 수 없는 경우 KAKTUS 출력
 * 
 * @author SSAFY
 *
 */

public class Main {

	static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		Deque<int[]> dq = new ArrayDeque<>();
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == '*') {
					dq.addFirst(new int[] {r,c,map[r][c],0});
				}
				if(map[r][c] == 'S') {
					dq.addLast(new int[] {r,c,map[r][c],0});
				}
			}
		}
		
		while(!dq.isEmpty()) {
			int[] cur = dq.pollFirst();
			
//			if (cur[2] == 'S' && map[cur[0]][cur[1]] != 'S') {
//				continue;
//			}
			for(int[] d : dir) {
				int ni = cur[0] + d[0];
				int nj = cur[1] + d[1];
				
				
				if(ni >= 0 && ni < R && nj >= 0 && nj < C) {
					if(map[ni][nj] == '.' || (map[ni][nj] == 'S' && cur[2] == '*')) {
						map[ni][nj] = (char)cur[2];
						dq.addLast(new int[] {ni,nj,cur[2], cur[3]+1});
					}
					if(cur[2] == 'S' && map[ni][nj] == 'D') {
						System.out.println(cur[3]+1);
						return;
					}
				}
			}
		}
//		for(int i = 0; i < R; i++) {
//			for(int j = 0; j < C; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println("KAKTUS");
	}

}
