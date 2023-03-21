package baekjoon.wc_bj_23288;

/**
 * 요약
 * 1. 크기가 N x M인 지도
 * 2. 지도 오른쪽 = 동쪽, 위쪽은 북쪽
 * 3. 지도 좌표 (r,c)에서 r은 북쪽으로부터 떨어진 칸의 개수
 * 4. c는 서쪽으로부터 떨어진 칸의 개수
 * 5. 방향은 동쪽으로 시작
 * 
 * 풀이
 * 1. 주사위 방향 전환 함수
 * 2. 주사위 진행 함수
 * 3. 다음 방향 결정 함수
 * 4. 점수 구하는 함수
 * 
 * 유의
 * 1. 최대 점수 9점을 400칸 먹는걸 1000번해도 3600000 이므로 int 사용
 * 
 * @author 양우철
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, dir, ci, cj, totalScore;
	static int[] dice = { 6, 5, 1, 2, 4, 3 }; // 0: 밑, 1: 남, 2: 위, 3: 서, 4: 북, 5: 동(진행방향)
	static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][] map;
	static int[][] score;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		score = new int[N][M];
		visited= new boolean[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < K; k++) {
			// 이동 방향으로 한 칸 진행
			move();
			// 점수 획득
			score();
			// 다음 방향 전환
			dirDecide();
			// 디버그
			//printAll();
		}
		System.out.println(totalScore);
	}
	private static void dirChange() {
		//시계방향 90도
		dir += 1;
		dir %= 4;
		
		int temp = dice[1];
		dice[1] = dice[4];
		dice[4] = dice[3];
		dice[3] = dice[5];
		dice[5] = temp;
	}
	private static void dirDecide() {
		if(dice[0] > map[ci][cj]) {
//			dir += 1;
//			dir %= 4;
//			
//			//swap
//			int temp = dice[1];
//			dice[1] = dice[4];
//			dice[4] = dice[3];
//			dice[3] = dice[5];
//			dice[5] = temp;
			dirChange();
		}
		if(dice[0] < map[ci][cj]) {
//			dir += 3;
//			dir %= 4;
//			
//			//swap
//			int temp = dice[1];
//			dice[1] = dice[5];
//			dice[5] = dice[3];
//			dice[3] = dice[4];
//			dice[4] = temp;
			dirChange();
			dirChange();
			dirChange();
		}
	}
	private static void score() {
		// 이미 점수가 기록되어 있으면
		if(score[ci][cj] != 0) {
			totalScore += score[ci][cj];
			return;
		}
		
		// 점수 획득 BFS
		Queue<Integer> q = new ArrayDeque<>();
		Queue<Integer> q2 = new ArrayDeque<>();		// 방문한 좌표 score에도 채워주기 위한 queue 
		int num = 0;
		int start = map[ci][cj];
		q.add(ci);
		q.add(cj);
		q2.add(ci);
		q2.add(cj);
		visited[ci][cj] = true;
		while(!q.isEmpty()) {
			int curi = q.poll();
			int curj = q.poll();
			num++;
			
			for(int k = 0; k < 4; k++) {
				int ni = curi + d[k][0];
				int nj = curj + d[k][1];
				
				// map 밖 아니어야 하고, 밑의 수와 같아야 하고, 방문하지 않았어야 함.
				if(ni >= 0 && ni < N && nj >= 0 && nj < M 
						&& map[ni][nj] == start && !visited[ni][nj]) {
					q.add(ni);
					q.add(nj);
					q2.add(ni);
					q2.add(nj);
					visited[ni][nj] = true;
				}
			}
		}
		
		// totalScore에 더해준다.
		totalScore += num*start;
		
		// score에 각 점수 구해준다.
		while(!q2.isEmpty()) {
			int curi = q2.poll();
			int curj = q2.poll();
			
			score[curi][curj] = num*start;
		}
	}
	private static void move() {
		// 현재 위치 이동
		// 벽에 부딪히면 방향 반대
		if(ci+d[dir][0] < 0 || ci+d[dir][0] >= N || cj+d[dir][1] < 0 || cj+d[dir][1] >= M) {
			dirChange();
			dirChange();	//180도 회전
		}
		ci += d[dir][0];
		cj += d[dir][1];
		
		// swap
		int temp = dice[0];
		dice[0] = dice[5];
		dice[5] = dice[2];
		dice[2] = dice[4];
		dice[4] = temp;
	}
	private static void printAll() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("밑의 수 : "+dice[0]);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i == ci && j == cj) System.out.print('*');
				System.out.print(score[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
