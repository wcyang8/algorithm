package baekjoon.wc_bj_10972;

import java.util.Arrays;
import java.util.Scanner;


public class Main4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];
		int i;
		for (i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		// �Է� �Ϸ�
		// 1. ������ �������� ã��
		for (i = N - 2; i >= 0; i--) {
			if (input[i + 1] > input[i]) { // ã�� �Ϸ� : i+1~N-1 ������ ��������
				int minIndex = -1; // 2. minIndex ã�� : input[i+1~N-1] ���� input[i]���� ū �ּҰ� input�� ������ index
				int min = N; // min : min = input[j]�� ��ǥ. input�� ��� ���� N���� �۰ų� �����Ƿ� N���� ����.
				for (int j = i + 1; j < N; j++) {
					if (input[i] < input[j]) {
						min = Math.min(min, input[j]); // input[i]���� ũ��, min���� ���� input[j]�� ������ ����
						minIndex = j;
					}
				}
				int temp = input[i]; // 3. i�� minIndex�� swap
				input[i] = input[minIndex];
				input[minIndex] = temp;
				Arrays.sort(input, i + 1, N); // 4. i+1~n-1���� ������������ ������
				break;
			}
		}
		if (i == -1) {				// 5. i == -1 ; ������ �������� ���� �� input[i]���� �� ���� ���� ����
			System.out.println(-1); // ; �̹� input ���� ��ü�� ��������; ������ ����
			return;
		}
		for (int in : input)
			System.out.print(in + " ");
		System.out.println();
	}

}
