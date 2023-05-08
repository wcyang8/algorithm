package baekjoon.wc_bj_2749;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BigInteger N = new BigInteger(br.readLine());
		
		if(N.equals(BigInteger.ONE)) {
			System.out.println(1);
			return;
		}
		
		long[][] mat= {{1,1},{1,0}};
		
		System.out.println(pow(mat, N.subtract(BigInteger.ONE))[0][0]);
	}

	private static long[][] pow(long[][] mat, BigInteger n) {
		if(n.equals(BigInteger.ONE)) {
			return mat;
		}
		BigInteger nn = n.divide(BigInteger.valueOf(2));
		long[][] half = pow(mat, nn);
		
		half = mul(half, half);
		if(n.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE)) half = mul(half, mat);
		
		return half;
	}

	private static long[][] mul(long[][] A, long[][] B) {
		long[][] res = new long[2][2];
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					res[i][j] += A[i][k] * B[k][j];
					res[i][j] %= 1_000_000;
				}
			}
		}
		return res;
	}

}
