package baekjoon.wc_bj_1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int N = 0;
		int S = 0;
		int[] arr = null;

		// �Է� ����
		try {
			s = br.readLine();
			String[] sArr = s.split(" ");
			N = Integer.parseInt(sArr[0]);
			S = Integer.parseInt(sArr[1]);
			arr = new int[N];
			s = br.readLine();
			sArr = s.split(" ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(sArr[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// �Է� �Ϸ�
		//ī���� ����
		int count = 0;
		for (int i = 1; i <= N; i++) {		//���Ұ� i���� �κ�����
			int[] comb = new int[i];		//i���� �̴� ����
			int j;
			for (j = 0; j < i; j++) {		//�ʱⰪ ����
				comb[j] = j;
			}
			j--;
			while (j >= 0) {			//i�� �̱� ����
				int sum = 0;		//���� ����
				for (int k : comb) {
					sum += arr[k];
				}
				if (sum == S)		//������ S�� ī����
					count++;
				//���� ����
				comb[j]++;
				while (comb[j] >= N + j - i + 1) {	//�� �ڸ����� �ִ밪(=N+j-i+1)�� �ѱ� ��� carry ó��
					if (--j < 0)			//j : ���� ����Ű�� �ڸ���. j�� 0 ������ �ڸ����� �������� break
						break;				//--j : ��ĭ ��������.
					comb[j]++;				//���� ĭ 1����
				}
				if (j >= 0) {	//j�� 0 �̸��̸� ���� ���� i+1 �κ��������� �Ѿ.
					while(j < i - 1) {		//j�� ����Ű�� �ڸ����� �� �ڸ��� �ƴ϶��; carry�� ���� ���
						comb[j + 1] = comb[j] + 1;		//���������� carry�� �ѱ� �ڸ� ������ ���� ���� ����.
						j++;				//����Ű�� �ڸ����� ���� 1����
					}
				}
			}
		}
		System.out.println(count);
	}

}
