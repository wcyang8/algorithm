package baekjoon.wc_bj_2805;

/**
 * 요약
 * 1. 상근이는 나무 M미터가 필요하다.
 * 2. 목재절단기는 높이 H를 지정한다.
 * 3. 톱날이 땅부터 H미터 위로 올라가고 연속해있는 나무를 모두 절단한다.
 * 4. 상근이는 절단한 나무를 집으로 가져간다.
 * 5. 적어도 M미터를 가져가기 위해 설정할 수 있는 높이의 최댓값을 구하자.
 * 6. 나무의 수는 N개이다.
 *  
 * 풀이
 * 1. 높이는 0~10억 사이이므로 이분탐색을 사용한다.
 * 
 * 유의
 * 1. 값이 10억이므로 long을 사용한다.
 *  
 * @author SSAFY
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		
		long[] trees = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			trees[i] = Long.parseLong(st.nextToken());
		}
		
		long s = 0;
		long e = 1_000_000_000;
		long res = 0;
		while(s < e) {
			long mid = (s+e)/2;
			if(isEnough(trees,mid,M)) {
				s = mid + 1;
			}
			else {
				e = mid;
			}
			res = mid;
		}
		if(!isEnough(trees,res,M)) res--;
		System.out.println(res);
	}
	static boolean isEnough(long[] trees, long h, long M) {
		long sum = 0;
		for(int i = 0; i < trees.length; i++) {
			sum += (trees[i] > h)? (trees[i] - h) : 0;
		}
		if(sum >= M) return true;
		return false;
	}
}
