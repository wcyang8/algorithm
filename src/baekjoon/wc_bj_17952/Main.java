package baekjoon.wc_bj_17952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int N;
		int score = 0;
		Stack<List<Integer>> hwSchedule = new Stack<>();
		try {	//BufferedReader�� ����ؼ� �Է� �ޱ�
			s = br.readLine();	//���� ó�� �ʼ�
			N = Integer.parseInt(s);
			for(int i = 0; i < N; i++) {	//i�� ���
				s = br.readLine();
				String[] sArr = s.split(" ");
				if(Integer.parseInt(sArr[0]) == 1) {	//���� ���� ù ĭ = 1 : ���� ����
					List<Integer> hw = new ArrayList<Integer>();
					hw.add(Integer.parseInt(sArr[1]));
					hw.add(Integer.parseInt(sArr[2]));
					hwSchedule.add(hw);
				}
				if(!hwSchedule.empty()) {						//���� ������ ����
					List<Integer> temp = hwSchedule.peek();
					hwSchedule.pop();
					if(temp.get(1) == 1) {		//������ �̹� ���࿡ ����
						score += temp.get(0);	//���� ȹ��
						continue;
					}
					temp.set(1, temp.get(1) - 1);	//���� ����
					hwSchedule.add(temp);			//���� �����ٿ� �ٽ� �־���.
				}
			}
		} catch (IOException e) {	//���� ó��
			e.printStackTrace();
		}
		System.out.println(score);
	}

}
