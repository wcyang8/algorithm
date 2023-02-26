package baekjoon.wc_bj_10830_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] A;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		A = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken()) % 1000;		// 원소가 전부 1000인 케이스 때문에 미리 1000
			}
		}
		// A^B 각 원소 출력
		for(int[] t : pow(B)) {
			for(int i : t) {
				sb.append(i).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	/**
	 * A^B를 구하는 함수
	 * @param B : 제곱할 수
	 * @return A^B
	 */
	static int[][] pow(long B) {
		if(B == 1) return A;			//B = 1이면 A를 return
		
		int[][] res = pow(B/2);			//pow(B/2)를 재귀적으로 호출
		res = mul(res,res);				//res * res
		if(B % 2 == 1) {
			res = mul(res,A);			//B가 홀수면 A를 한번 더 곱해준다.
		}
		return res;						//res를 return
	}
	/**
	 * 행렬을 곱해주는 함수
	 * @param dest : 곱해질 행렬
	 * @param src : 곱할 행렬
	 * @return dest * src 행렬
	 */
	static int[][] mul(int[][] dest, int[][] src){
		int[][] result = new int[N][N];						//result : 결과를 받을 2차원 배열
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					result[i][j] += dest[i][k]*src[k][j];	//행렬 곱셈
				}
				result[i][j] %= 1000;						//곱셈 후 1000으로 나누기
			}
		}
		
		return result;			//결과 return
	}
}