package baekjoon.wc_bj_2565;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 유의
 * 1. 전깃줄이 주어질 때,
 * 2. 서로 교차하지 않도록 없애야하는 전깃줄의 최소 개수를 구해보자.
 * 
 * 값
 * 1. 전깃줄의 개수는 100 이하의 자연수
 * 2. 위치의 번호는 500 이하의 자연수
 * 
 * 풀이
 * 1. 왼쪽 전봇대의 번호와 오른쪽 전봇대의 번호가 둘다 증가해야한다.
 * 2. 전깃줄을 정렬(왼쪽은 항상 증가), 오른쪽 전봇대의 번호에서 최장 증가 수열을 구하면 된다.
 * 
 * @author SSAFY
 *
 */

class line implements Comparable<line>{
	int left;
	int right;
	
	
	public line(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}
	@Override
	public int compareTo(line o) {
		return Integer.compare(this.left, o.left);
	}
	@Override
	public String toString() {
		return "line [left=" + left + ", right=" + right + "]";
	}
	
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		List<line> list = new LinkedList<line>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			list.add(new line(left,right));
		}
		
		Collections.sort(list);
		
		int[] C = new int[N+1];		// 길이가 i인 최장 증가 수열 중에 가장 작은 마지막 수
		
		int len = 1;
		C[1] = list.get(0).right;
		for(int i = 0; i < N; i++) {		// 주어진 수열
			if(list.get(i).right > C[len]) {		// 현재 길이의 최장 증가수열에 그대로 더해주면 최장 증가수열
				C[++len] = list.get(i).right;
				continue;
			}
			int start = 1;
			int end = len;
			while(start < end) {
				int mid = (start+end)/2;
				if(list.get(i).right > C[mid]) {
					start = mid+1;
				}else {
					end = mid;
				}
			}
			C[start] = list.get(i).right;
		}
		
		System.out.println(N - len);
	}
}
