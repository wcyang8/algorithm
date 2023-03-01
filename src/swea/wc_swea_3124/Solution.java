package swea.wc_swea_3124;

/**
 * Kruskal 알고리즘
 * 1. 모든 간선을 가중치에 따라 오름차순으로 정렬
 * 2. 가중치가 가장 낮은 간선부터 선택하면서 트리를 증가시킴
 * 3. n-1개의 간선이 선택될 때까지 2를 반복.
 * @author 양우철
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Edge implements Comparable<Edge>{
	int src;
	int dest;
	int cost;
	public Edge(StringTokenizer st) {
		this.src = Integer.parseInt(st.nextToken());
		this.dest = Integer.parseInt(st.nextToken());
		this.cost = Integer.parseInt(st.nextToken());
	}
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Solution {

	static int[] vertex;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//int T = Integer.parseInt(br.readLine());
		int T = 1;
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			vertex = new int[V];
			Edge[] edges = new Edge[E];
			
			for(int e = 0; e < E; e++) {					//간선 받기
				st = new StringTokenizer(br.readLine());
				edges[e] = new Edge(st);
			}
			
			for(int v = 0; v < V; v++) {					//Make set
				vertex[v] = v;
			}
			
			Arrays.sort(edges);
			
			int n = 0;
			long sum = 0;
			for(int e = 0; e < E; e++) {
				int dest = findSet(edges[e].dest);
				int src = findSet(edges[e].src);
				if(dest != src) {							//union set
					vertex[dest] = src;
					n++;
					sum += edges[e].cost;
				}
				if(n == V-1) break;							//V-1개 충족 시 종료
			}
			sb.append('#').append(tc).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);
	}
	static int findSet(int v) {
		if(vertex[v] != v) return vertex[v] = findSet(vertex[v]);
		return v;
	}
}
