package baekjoon.wc_bj_9663;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	
	static int[] queen;
	static int N, result = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		queen = new int[N];
		
		Nqueen(0);
		System.out.println(result);
	}
	static void Nqueen(int cnt) {
		if(cnt == N) {
			result++;
			return;
		}
		for(int j = 0; j < N; j++) {
			if(canSet(cnt, j)) {
				queen[cnt] = j;
				Nqueen(cnt+1);
			}
		}
	}
	static boolean canSet(int cnt, int j) {
		for(int i = 0; i < cnt; i++) {
			if(queen[i] == j || cnt - i == Math.abs(queen[i]-j)) return false;
		}
		return true;
	}
}
