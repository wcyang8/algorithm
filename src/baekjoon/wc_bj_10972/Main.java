package baekjoon.wc_bj_10972;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] per = new int[N];
		
		for(int i = 0; i < N; i++) {
			per[i] = sc.nextInt();
		}
		
		
		int index = 1;
		for(int i = 0; i < N - 1; i++) {
			index += offset(per[i],i + 1,per) * fact(N-i-1);
		}
		if(index==fact(N)) System.out.println(-1);
		int[] result = new int[N];
		for(int i = N - 1; i >= 0; i--) {
			int count = 0;
			int temp = 0;
			if(fact(i) != 0)
				temp = index/fact(i);
			for(int j = 0; j < N; j++) {
				if(result[j] == 0) {
					if(count == temp) {
						result[j] = 1;
						System.out.print(j + 1 + " ");
						break;
					}
					else {
						count++;
					}
				}
			}
			if(fact(i) != 0)
				index %= fact(i);
		}
	}
	
	public static int offset(int n, int m, int[] temp) {
		int result = 0;
		for(int i = m; i < temp.length; i++) {
			if(n > temp[i]) result++;
		}
		return result;
	}
	public static int fact(int n) {
		int result = 1;
		
		for(int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
}
