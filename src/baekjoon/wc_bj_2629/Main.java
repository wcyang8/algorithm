package baekjoon.wc_bj_2629;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 요약
 * 1. 양팔 저울과 몇개의 추가 주어진다.
 * 2. 주어진 구슬의 무게를 확인할 수 있는지를 결정한다.
 * 
 * 풀이
 * 1. 추의 무게로 dp
 * 2. (탐색한 추, 무게) = 가능 여부
 * 3. (탐색한 추, 무게) = (탐색한 추 - 1, 무게) or (탐색한 추 - 1, 무게 - 추무게)
 * 
 * @author SSAFY
 *
 */

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		
	}
}
