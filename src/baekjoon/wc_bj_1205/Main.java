package baekjoon.wc_bj_1205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 랭킹 리스트에 점수가 주어진다 (비오름차순)
 * 2. 태수의 점수가 주어진다.
 * 3. 태수의 점수가 몇등인지 구한다.
 * 
 * 풀이
 * 1. binary search 사용
 * 2. 같은 점수일 때도  binary search 계속
 * 3. left 리턴
 * 4. right 리턴
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int[] board = new int[N];
		
		if(N == 0) {
			System.out.println(1);
			return;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			board[i] = Integer.parseInt(st.nextToken());
		}
		
		int rank, last;
		
		int left = 0;
		int right = N;
		while(left < right) {
			int mid = (left + right) / 2;
			if(board[mid] > score) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		rank = right+1;
		
		left = 0;
		right = N;
		while(left < right) {
			int mid = (left + right) / 2;
			if(board[mid] >= score) {
				left = mid + 1;
			}else {
				right = mid;
			}
		}
		last = left+1;
		
		if(last > P) {
			System.out.println(-1);
		}else {
			System.out.println(rank);
		}
	}

}
