package baekjoon.wc_bj_1967;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 트리의 모든 경로 중에 가장 긴 것의 길이 구하기
 * 
 * 유의
 * 1. 트리 경로 간에 가중치 존재.
 * 2. 루트 번호는 항상 1
 * 3. 간선 가중치는 100 이하
 * 4. 입력은 항상 부모노드가 작은 것이 먼저 입력, 자식노드 번호가 작은 것이 먼저 입력.
 * 
 * 풀이
 * 1. 자식부터 올라간다.
 * 2. 분할 정복 느낌으로 서브트리의 까지의 최장, 차장 거리 저장.
 * 3. 각 자식들의 최장거리 + 부모까지 거리를 다시 저장해서 최장 차장 저장
 * 4. 저장하면서 최대 지름을 더한다.
 * 
 * 배운 것
 * 1. 부모가 항상 높은 번호를 갖지 않는 것을 체크하지 못함.
 * 2. 자식부터 visit = dfs로 변환 가능.
 * 
 * @author SSAFY
 *
 */

public class Main {

	static List<int[]>[] tree;
	static int[][] height;
	static int max;
	public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 
		 int n = Integer.parseInt(br.readLine());
		 
		 tree = new LinkedList[n+1];
		 
		 height = new int[n+1][2];		// 최장, 차장 높이 저장
		 
		 for(int i = 1; i <= n; i++) tree[i] = new LinkedList<int[]>();
		 
		 // 트리 입력
		 for(int i = 2; i <= n; i++) {
			 st = new StringTokenizer(br.readLine());
			 
			 int parent = Integer.parseInt(st.nextToken());
			 int child = Integer.parseInt(st.nextToken());
			 int weight = Integer.parseInt(st.nextToken());
			 
			 tree[parent].add(new int[] {child, weight});
		 }
		 
		 dfs(1);
		 System.out.println(max);
	}
	static int[] dfs(int parent) {
		int[] parentR = new int[2];
				
		for(int[] child: tree[parent]) {
			
			int[] childR = dfs(child[0]);
			int next = childR[0] + child[1];
			if(next >= parentR[0]) {
				parentR[1] = parentR[0];
				parentR[0] = next;
			}else if(next > parentR[1]) {
				parentR[1] = next;
			}
		}
		if(max < parentR[0] + parentR[1]) max = parentR[0] + parentR[1];
		return parentR;
	}
}
