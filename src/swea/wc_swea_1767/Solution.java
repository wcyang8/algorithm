package swea.wc_swea_1767;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 요약
 * 1. 멕시노스 = N x N
 * 2. 1개의 셀에는 코어 혹은 전선이 올 수 있음.
 * 3. 가장자리에는 전원이 흐르고 있다.
 * 4. 전선은 직선. 교차해선 안됨.
 * 5. 연결된 코어가 최대가 되는 전선 수는? (코어 수가 같으면 전선 수는 적은 쪽으로)
 * 
 * 풀이
 * 1. 백트래킹
 * 2. 4방 뻗기
 * 3. if(ok) 다음 칸
 * 4. 4방 다 안될 때 그냥 다음칸
 * 5. 끝까지 갔을 때 core 개수
 * 
 * @author SSAFY
 *
 */


public class Solution {

	static int N, maxCore, minLen;
	static int[][] maxinos, dir = {{1,0},{0,1},{-1,0},{0,-1}};
	static List<int[]> cores;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			maxCore = 0;
			minLen = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			
			maxinos = new int[N][N];
			cores = new ArrayList<>();
			
			int already = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					maxinos[i][j] = Integer.parseInt(st.nextToken());
					if(maxinos[i][j] == 1) { 
							if(i==0 || i == N-1 || j == 0 || j == N-1)++already;
							else cores.add(new int[] {i,j});
					}
				}
			}
			
			search(0, already, 0);
			
			sb.append("#").append(tc).append(" ").append(minLen).append("\n");
		}
		System.out.print(sb);
	}
	private static void search(int listN, int coreCnt, int lenSum) {
		if(listN == cores.size()) {
			if(maxCore < coreCnt) {
				maxCore = coreCnt;
				minLen = lenSum;
			}else if(maxCore == coreCnt) {
				minLen = Math.min(minLen, lenSum);
			}
			return;
		}
		
		int[] cur = cores.get(listN);
		
		Queue<int[]> q = new ArrayDeque<>();
		for(int[] d : dir) {
			int len = 0;
			int ni = cur[0] + d[0];
			int nj = cur[1] + d[1];
			boolean ok = true;
			while(ni >= 0 && ni < N && nj >= 0 && nj < N) {
				if(maxinos[ni][nj] != 0) {
					ok = false;
					break;
				}
				q.add(new int[] {ni,nj});
				maxinos[ni][nj] = 2;
				++len;
				ni += d[0];
				nj += d[1];
			}
			if(ok) {
				// 다음 backtracking
				search(listN+1, coreCnt+1, lenSum + len);
			}
			// 지우기
			while(!q.isEmpty()) {
				int[] er = q.poll();
				maxinos[er[0]][er[1]] = 0;
			}
		}
		search(listN+1,coreCnt,lenSum);
	}
}
