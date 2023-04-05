package baekjoon.wc_bj_1038_2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		
		Queue<Long> q = new ArrayDeque<>();
		
		int cnt = 0;
		for(long i = 0; i < 10; i++) q.add(i);
		while(!q.isEmpty()) {
			long cur = q.poll();
			if(cnt == N) {
				System.out.println(cur);
				return;
			}
			++cnt;
			for(int i = 0; i < 10; i++) {
				if(cur % 10 > i) q.add(cur * 10 + i);
			}
		}
		System.out.println(-1);
	}

}
