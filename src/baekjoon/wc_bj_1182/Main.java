package baekjoon.wc_bj_1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int N = 0;
		int S = 0;
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();
		
		//�Է� ����
		try {
			s = br.readLine();
			String[] sArr = s.split(" ");
			N = Integer.parseInt(sArr[0]);
			S = Integer.parseInt(sArr[1]);
			s = br.readLine();
			sArr = s.split(" ");
			for(int i = 0; i < N; i++) {
				int temp = Integer.parseInt(sArr[i]) - S;
				if(temp >= 0) right.add(temp);
				else left.add(-temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�Է� �Ϸ�
		for(int i = 1; i <= right.size(); i++) {
			int[] comb = new int[i];
			int j;
			for(j = 0; j < i; i++) {
				comb[j] = j;
			}
			while(j >= 0) {
				comb[j]++;
				while(comb[j] >= right.size()) {
					if(--j < 0) break;
					comb[++j] = ++comb[j] + 1;
				}
				if(j < 0) break;
				int sum = 0;
				for(int k : comb) {
					sum += right.get(k);
				}
			}
		}
	}

}
