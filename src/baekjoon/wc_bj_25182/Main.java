package baekjoon.wc_bj_25182;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int N;
	static int[] arr;
	static int[] cleanArr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		cleanArr = new int[2*N];
		
		Arrays.fill(arr, 2);
		
		fill(0);
	}
	private static void fill(int cur) {
		if(cur == 2*N) {
			System.out.println(Arrays.toString(cleanArr));
			int[] sum = new int[N];
			for(int i = 0; i < N; i++) {
				
			}
			return;
		}
		for(int i = 0; i < N; i++) {
			if(arr[i] > 0) {
				arr[i]--;
				cleanArr[cur] = i+1;
				fill(cur+1);
				arr[i]++;
			}
		}
	}

}
