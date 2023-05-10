package programmers.wc_pg_N으로표현;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Solution s = new Solution();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int number = sc.nextInt();

		System.out.println(s.solution(N, number));
	}

	int number;
	int[] visited;

	public int solution(int N, int number) {
		List<Integer>[] list = new ArrayList[9];
		int e = N;
		visited = new int[300000];
		this.number = number;
		list[0] = new ArrayList<>();
		list[0].add(0);
		visited[0] = -1;
		for (int i = 1; i <= 8; i++) {
			list[i] = new ArrayList<Integer>();
			if(check(e, i, list[i])) {
				return i;
			}
			e = e * 10 + N;
		}

		for (int i = 2; i <= 8; i++) {
			for (int j = 1; j <= i / 2; j++) {
				for (int k : list[j]) {
					for (int l : list[i - j]) {
						if (check(k + l, i, list[i]))
							return i;
						if (check(k * l, i, list[i]))
							return i;
						if (check(Math.abs(k - l), i, list[i]))
							return i;
						if (check(k / l, i, list[i]))
							return i;
						if (check(l / k, i, list[i]))
							return i;
					}
				}
			}
		}
		return -1;
	}

	public boolean check(int x, int i, List<Integer> list) {
		if (x > 299999) {
			return false;
		}
		if (x == number) {
			return true;
		}
		if (visited[x] == 0) {
			list.add(x);
			visited[x] = i;
		}
		return false;
	}
}
