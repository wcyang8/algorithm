package baekjoon.wc_bj_1149_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 풀이
 * 1. 다음 집이 빨간색 = 이전의 초록 파랑 집 선택 비용 + 현재 빨강 집 비용
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < 3; j++) {
				cost[i][j] += Math.min(cost[i-1][(j+1)%3], cost[i-1][(j+2)%3]);
			}
		}
		
		System.out.println(Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2])));
	}

}
