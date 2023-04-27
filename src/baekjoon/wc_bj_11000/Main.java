package baekjoon.wc_bj_11000;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 1. 수업을 정렬
 * 2. i개의 강의까지 강의실 최소 f(i)
 * 3. i번째 강의가 지금까지의 강의실에 넣을 수 있는지 아닌지
 * 4. 점화식 사용 불가
 * 
 * 풀이2
 * 1. 수업을 정렬
 * 2. pq에 하나씩 넣는다 (T)
 * 3. 현재 값의 S가 pq의 top보다 크거나 같으면 pop
 * 4. pq의 사이즈 = 수업의 개수
 * 
 * @author SSAFY
 *
 */

public class Main {

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
		
		Arrays.sort(C, (o1,o2)->Integer.compare(o1[0], o2[0]));
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		pq.add(C[0][1]);
		for(int i = 1; i < N; i++) {
			if(pq.peek() <= C[i][0]) {
				pq.poll();
			}
			pq.add(C[i][1]);
		}
		System.out.println(pq.size());
	}
}
