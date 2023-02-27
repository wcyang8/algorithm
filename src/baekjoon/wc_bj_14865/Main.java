package baekjoon.wc_bj_14865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class pair implements Comparable<pair> {
	int open;
	int close;

	public pair(int open, int close) {
		super();
		this.open = open;
		this.close = close;
	}

	@Override
	public int compareTo(pair o) {
		return (this.open > o.open) ? 1 : ((this.open == o.open) ? 0 : -1);
	}

	@Override
	public String toString() {
		return "pair [open=" + open + ", close=" + close + "]";
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		List<pair> l = new ArrayList<pair>();

		st = new StringTokenizer(br.readLine());
		int px = Integer.parseInt(st.nextToken());
		int py = Integer.parseInt(st.nextToken());
		int open = Integer.MIN_VALUE;
		boolean flag = false;
		for (int k = 1; k < N; k++) {
			st = new StringTokenizer(br.readLine());
			int cx = Integer.parseInt(st.nextToken());
			int cy = Integer.parseInt(st.nextToken());

			if (py < 0 && cy > 0) {
				flag = true;
				open = cx;
			}
			if (py > 0 && cy < 0) {
				flag = false;
				if (open > cx)
					l.add(new pair(cx, open));
				else
					l.add(new pair(open, cx));
			}
			px = cx;
			py = cy;
		}
		if(l.get(0).open == Integer.MIN_VALUE) {		//내려만 가는 경우
			flag = false;
			if(l.get(0).close < px) {
				l.get(0).open = l.get(0).close;
				l.get(0).close = px;
			}
			else l.get(0).open = px;
		}
		if(flag) {										//올라만 가는 경우
			if(open > px) l.add(new pair(px,open));
			else l.add(new pair(open, px));
		}

		Collections.sort(l);

		int[] type = new int[l.size()];

		int notContain = 0;
		int notContained = 0;
		for (int i = 0; i < l.size(); i++) {
			pair temp = l.get(i);
			for (int j = i + 1; j < l.size(); j++) {
				if (l.get(j).open > temp.close) { // 포함 하는지
					break;
				}
				type[i] |= 2; // 포함 안되고 포함 한다.
				if(type[j] == 1) break;
				type[j] |= 1; // 포함 된다.
			}
		}
		
		for(int i = 0; i < l.size(); i++) {
			if(type[i] == 0) {
				notContain++;
				notContained++;
			}
			if(type[i] == 1) {
				notContain++;
			}
			if(type[i] == 2) {
				notContained++;
			}
		}
		System.out.println(notContained+" "+notContain);
	}

}