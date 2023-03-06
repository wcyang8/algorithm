package baekjoon.wc_bj_14713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		Queue<String>[] parrot = null;
		Queue<String> L = null;
		
		int N;
		try {
			s = br.readLine();
			N = Integer.parseInt(s);
			parrot = new LinkedList[N];		//�޹��� N������ �־��� ������ �ܾ���� ���� ť�� �迭
			L = new LinkedList<String>();		//���� L�� �ܾ���� �־��� ť
			
			for(int i = 0; i < N; i++) {
				s = br.readLine();
				String[] word = s.split(" ");
				parrot[i] = new LinkedList<String>();
				for(String w : word) {
					parrot[i].add(w);
				}
			}
			s = br.readLine();
			String[] word = s.split(" ");
			for(String w : word) {
				L.add(w);
			}												//�Է� �Ϸ�
		} catch (IOException e) {
			e.printStackTrace();
		}							//���� ó��
		for(String w : L) {			//���� �Ǻ� ���� : ���� L�� �ܾ� w�� �ϳ��� �˻�.
			boolean flag = false;				//for���� 2�� �������;� �ϹǷ� flag ����
			for(Queue<String> q : parrot) {		//w�� ���� �ܾ �� parrot�� peek�� �ִ� �ܾ�� ���ƾ� ��. why?
												//w�� w�� ������ parrot�� �ܾ�� �� ���� ���� ���� �ܾ��� �� �ۿ� ���� ����.
				if(!q.isEmpty()) {			//�� parrot�� peek�� ������ üũ�ϴ� ����
					if(w.equals(q.peek())) {
						q.remove();
						flag = true;
						break;
					}
				}
			}
			if(!flag) {		//���� peek�� �� w�� ��ġ�ϴ� �ܾ ������? Impossible
				System.out.println("Impossible");
				return;
			}
		}
		boolean checkEmpty = true;			//parrot�� ������ ���� empty���� ��. why?
		for(Queue<String> q : parrot) {		//���ǿ� "�޹����� �ڽ��� ����ϰ� �ִ� ������ ������ ����"
			if(!q.isEmpty()) {
				checkEmpty = false;		//�ϳ��� empty�� �ƴϸ� false
				break;
			}
		}
		if(checkEmpty)
			System.out.println("Possible");		//���� ��������� Possible
		else
			System.out.println("Impossible");	//�ƴ϶�� Impossible
	}

}
