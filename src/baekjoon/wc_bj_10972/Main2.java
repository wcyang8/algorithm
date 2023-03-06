package baekjoon.wc_bj_10972;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	public static int N;
	public static int[] input;
	public static int[] last;
	public static int flag = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		last = new int[N];
		for(int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		for(int i = 0; i < N; i++) {
			last[i] = N - i;
		}
		
		int[] state = new int[N];
		
		if(Arrays.equals(input,last)) {
			System.out.println(-1);
			return;
		}
		perm(0, state);
	}

	public static void perm(int cur, int[] state) {
		if(flag > 1) return;
		if(cur == N) {
			if(flag == 0) {
				if(Arrays.equals(input,convert(state))) flag++;
			}
			else {
				for(int i : convert(state)) {
					System.out.print(i+" ");
				}
				System.out.println();
				flag++;
			}
			return;
		}
		for(int i = 0; i < N; i++) {
			if(state[i] == 0) {
				int[] temp = state.clone();
				temp[i] = cur+1;
				perm(cur+1,temp);
			}
		}
	}
	
	public static int[] convert(int[] state) {
		int[] temp = new int[N];
		for(int i = 0 ; i < N; i++) {
			temp[state[i]-1] = i + 1;
		}
		return temp;
	}
}
