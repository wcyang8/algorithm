package baekjoon.wc_bj_1238;
/**
 * 요약
 * 1. N개의 숫자로 구분된 각 마을에 1명의 학생이 살고 있음.
 * 2. N명의 학생이 X번 마을에 모여서 파티를 벌이기로 함.
 * 3. M개의 단방향 도로가 있고 i번째 길은 T_i 시간 소비
 * 4. 최단거리로 걸어갔다가 다시 자신의 마을로 돌아와야 함.
 * 5. 가장 많은 시간을 소비하는 학생은 누구일지 구해보자.
 * 
 * 유의
 * 1. 시작점과 끝점이 같은 도로는 없다.
 * 2. 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개
 * 3. 모든 학생들은 집에서 X에 갈 수 있고, X에서 집으로 돌아올 수 있다.
 * 4. T=100으로 모든 도로를 돌아도 최대 100만이므로 int 사용
 * 
 * 풀이
 * 1. X에서 각 집으로 가는데엔 VlogE
 * 2. 각 집에서 X로 가는데엔 V * VlogE
 * 3. 최대 1000000이므로 전부 다익스트라 돌리자.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static List<int[]>[] edges;
	static int[] sum;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken()) - 1;
		
		sum = new int[N];			// 오가는 거리 합
		
		edges = new ArrayList[N];	// 간선 정보 저장
		
		// 간선 배열 초기화
		for(int v = 0; v < N; v++) {
			edges[v] = new ArrayList<int[]>();
		}
		
		// 간선 입력
		for(int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			
			edges[from].add(new int[] {to,cost});
		}
		
		dijkstra(X,N);			// X에서 전체로
		for(int i = 0; i < N; i++) {		// 전체에서 X로
			dijkstra(i,X);
		}
		
		Arrays.sort(sum);		// 정렬 해주고
		
		System.out.println(sum[N-1]);		//맨 마지막 사람 = 최대 시간
	}
	// 다익스트라 함수
	private static void dijkstra(int start, int end) {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[1], o2[1]));
		
		dist[start] = 0;
		pq.add(new int[] {start,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(cur[0] == end) {			// end에 도달하면 sum에 더해주고 함수 종료
				sum[start] += cur[1];
				return;
			}
			
			for(int[] next : edges[cur[0]]) {
				if(dist[next[0]] > dist[cur[0]] + next[1]) {
					dist[next[0]] = dist[cur[0]] + next[1];
					pq.add(new int[] {next[0],dist[next[0]]});
				}
			}
		}
		
		for(int i = 0; i < N; i++) {	// end = N으로 두면 while문을 전부 돌고 나온다.
			sum[i] += dist[i];			// X에서 모든 점으로 가는 최단거리를 전부 더해준다.
		}
	}

}
