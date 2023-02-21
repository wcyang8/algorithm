package baekjoon.wc_bj_6987;

/**
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int[][] result;
	static boolean flag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		for(int tc = 0; tc < 4; tc++) {
			result = new int[6][3];
			s = br.readLine();
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					result[i][j] = s.charAt(i*6+j*2) - '0';
				}
			}
			flag = false;
			play(0,1);
			sb.append((flag?1:0) + " ");
		}
		System.out.println(sb);
	}
	
	public static void play(int i, int j) {
		if(flag == true) return;
		if(j > 5) {
			if(i >= 4) {
				for(int n = 0; n < 6; n++) {
					for(int m = 0; m < 3; m++) {
						if(result[n][m] != 0) return;
					}
				}
				flag = true;
				return;
			}
			i++;
			j = i+1;
		}
		
		if(result[i][0] > 0 && result[j][2] > 0) {
			result[i][0]--;
			result[j][2]--;
			play(i,j+1);
			result[i][0]++;
			result[j][2]++;
		}
		if(result[i][2] > 0 && result[j][0] > 0) {
			result[i][2]--;
			result[j][0]--;
			play(i,j+1);
			result[i][2]++;
			result[j][0]++;
		}
		if(result[i][1] > 0 && result[j][1] > 0) {
			result[i][1]--;
			result[j][1]--;
			play(i,j+1);
			result[i][1]++;
			result[j][1]++;
		}
	}
}
