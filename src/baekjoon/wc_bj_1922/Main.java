package baekjoon.wc_bj_1922;


/**
 * 요약
 * 1. 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축.
 * 2. 컴퓨터와 컴퓨터를 직접 연결해야함.
 * 3. 컴퓨터 간 연결에는 비용이 든다.
 * 4. 모든 컴퓨터를 연결하는 최소 비용을 구하자.
 * 
 * 풀이
 * 1. 최소신장트리를 써서 풀자.
 * 
 * 풀기 전 유의
 * 1. Input
 * 	1.1. 그래프 문제 -> 모두 연결할 수 있는 Input만 주어지는가? O
 * 	1.2. 그래프 문제 -> a != b인 간선만 주어지는가? X
 * 2. Output
 * 	2.1. 결과값이 int 최대값을 넘지 않는가? O
 * 
 * @author SSAFY
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		// 크루스칼 사용
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)-> {
			return Integer.compare(o1[2],o2[2]);
		});	// edge 저장할 pq
		int[] djset = new int[N + 1];		// 서로소 집합
		
		for(int v = 0; v < N; v++) djset[v] = v;
		for(int e = 0; e < M; e++) {		// 간선 저장
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			pq.add(new int[] {from,to,cost});
		}
		
		int sum = 0;
		for(int e = 1; e < N; e++) {		//간선 N-1개
			int[] cur = pq.poll();
			while(!union(djset, cur[0], cur[1])) cur = pq.poll();
//			System.out.println(Arrays.toString(djset));
//			System.out.println(Arrays.toString(cur));
			sum += cur[2];
		}
		System.out.println(sum);
	}
	static int find(int[] djset, int v) {
		if(djset[v] != v) return djset[v] = find(djset,djset[v]);
		return v;
	}
	static boolean union(int[] djset, int src, int dest) {
		int s = find(djset,src);
		int d = find(djset,dest);
		
		if(s == d) {
			return false;
		}
		else {
			djset[d] = s;
			return true;
		}
	}
}
