package baekjoon.wc_bj_1793;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * 요약
 * 1. 2xn 직사각형을 2x1과 2x2 타일로 채우는 방법 수
 * 
 * 값
 * 1. n = 200 만 되어도 값이 말도안되게 커지므로 long 사용.
 * 
 * 풀이
 * 1. 점화식 : f(n) = f(n-2) * 2 + f(n-1) * 1;
 * 2. 초기값 : f(0) = 0, f(1) = 1, f(2) = 3
 * 
 * 배운 것
 * 1. Long보다 크면 BigInteger
 * 2. BigInteger는 사칙연산도 함수를 사용해야함.
 * 3. 입력 개수를 모를 때, while((s = br.readLine()) != null) { if(s.equals(""))break; } 사용할 것.
 * 
 * @author SSAFY
 *
 */

public class Main {

	static BigInteger[] tile = new BigInteger[251];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(tile, BigInteger.valueOf(-1));
		tile[0] = BigInteger.valueOf(1);
		tile[1] = BigInteger.valueOf(1);
		String s = null;
		while((s = br.readLine()) != null) {
			if(s.equals(""))break;
			int n = Integer.parseInt(s);
			System.out.println(tileCase(n));
		}
	}
	static BigInteger tileCase(int n) {
		if(tile[n] != BigInteger.valueOf(-1)) return tile[n];
		tile[n] = tileCase(n-1).add(tileCase(n-2).multiply(BigInteger.valueOf(2)));
		return tile[n];
	}
}
