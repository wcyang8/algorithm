package baekjoon.wc_bj_16236_2;

/**
 * 실패한 알고리즘.
 * 위 아래 나눠서
 * 위 왼 오
 * 왼 오 아래
 * 탐색하려고 했는데 위로 갔다가 아래로 가는 케이스, 그 반대가 있었음.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class node implements Comparable<node>{
	int i;
	int j;
	int dist;
	public node(int i, int j, int dist) {
		super();
		this.i = i;
		this.j = j;
		this.dist = dist;
	}
	@Override
	public int compareTo(node o) {
		return (this.dist < o.dist)? -1: (this.dist > o.dist)? 1 : (this.i < o.i)? -1 : (this.i > o.i)? 1 : (this.j < o.j)? -1 : (this.j > o.j)? 1:0;
	}
	@Override
	public String toString() {
		return "node [i=" + i + ", j=" + j + ", dist=" + dist + "]";
	}
}

public class Main {

	static int N, bsi, bsj, nti, ntj, curLV = 2, curEXP = 0;
	static int[][] d = {{-1,0},{0,-1},{0,1},{1,0}};
	static int[][] Area;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		Area = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				Area[i][j] = Integer.parseInt(st.nextToken());
				if(Area[i][j] == 9) {
					bsi = i;
					bsj = j;
				}
			}
		}
		System.out.println(simulation());
	}

	static int simulation() {
		int T = 0;
		node temp;
		while((temp = findTarget()) != null) {
			nti = temp.i;
			ntj = temp.j;
			T += temp.dist;
			eat();
			printArea(T);
		}
		return T;
	}
	static node findTarget() {						// BFS
		boolean[][] visited = new boolean[N][N];
		Queue<node> q = new ArrayDeque<node>();
		node up = null;
		q.add(new node(bsi,bsj,0));
		visited[bsi][bsj] = true;
		while(!q.isEmpty()) {
			node cur = q.poll();
			int ci = cur.i;
			int cj = cur.j;
			for(int k = 0; k < 3; k++) {
				int ni = ci + d[k][0];
				int nj = cj + d[k][1];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
					if(!visited[ni][nj] && Area[ni][nj] <= curLV) {			// 다음 영역의 물고기가 현재 레벨보다 낮으면
						if(Area[ni][nj] != 0 && Area[ni][nj] != curLV) {
							up = new node(ni,nj,cur.dist+1);
							break;
						}
						q.add(new node(ni,nj,cur.dist+1));
						visited[ni][nj] = true;
					}
				}
			}
			if(up!=null) break;
		}
		q.clear();
		node down = null;
		q.add(new node(bsi,bsj,0));
		while(!q.isEmpty()) {
			node cur = q.poll();
			int ci = cur.i;
			int cj = cur.j;
			for(int k = 1; k < 4; k++) {
				int ni = ci + d[k][0];
				int nj = cj + d[k][1];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
					if(!visited[ni][nj] && Area[ni][nj] <= curLV) {			// 다음 영역의 물고기가 현재 레벨보다 낮으면
						if(Area[ni][nj] != 0 && Area[ni][nj] != curLV) {
							down = new node(ni,nj,cur.dist+1);
							break;
						}
						q.add(new node(ni,nj,cur.dist+1));
						visited[ni][nj] = true;
					}
				}
			}
			if(down!=null) break;
		}
		if(up == null) return down;
		if(down == null) return up;
		return (up.compareTo(down)<0)? up : down;
	}
	static void eat() {
		Area[bsi][bsj] = 0;			// 위치 이동
		Area[nti][ntj] = 9;
		bsi = nti;
		bsj = ntj;
		
		curEXP++;					// 경험치 1 증가
		if(curEXP >= curLV) {		// 경험치가 꽉 찼으면 레벨업
			curEXP = 0;
			curLV++;
		}
	}
	static void printArea(int T) {
		for(int[] a : Area) {
			for(int i : a) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		System.out.println("===============");
		System.out.println("LV : "+curLV+" EXP : "+curEXP+" totalTime : "+T);
		System.out.println("===============");
	}
}
