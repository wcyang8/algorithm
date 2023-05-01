package baekjoon.wc_bj_11000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] C = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			C[i][0] = Integer.parseInt(st.nextToken());
			C[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(C, (o1,o2)->Integer.compare(o2[1], o1[1]));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o2,o1));
		
		pq.add(C[0][0]);
		for(int i = 1; i < N; i++) {
			if(pq.peek() >= C[i][1]) {
				pq.poll();
			}
			pq.add(C[i][0]);
		}
		System.out.println(pq.size());
	}
}
