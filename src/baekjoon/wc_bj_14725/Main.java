package baekjoon.wc_bj_14725;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class tree implements Comparable<tree>{
	static StringBuilder sb = new StringBuilder();
	String name;
	List<tree> branches = new ArrayList<>();
	tree(){};
	tree(String name){
		this.name = name;
	};
	public void add(String[] sArr, int cnt) {
		int K = Integer.parseInt(sArr[0]);
		String name = sArr[cnt];
		tree temp = this.find(name);
		if(temp == null) {
			temp = new tree(name);
			branches.add(temp);
		}
		if(cnt<K)temp.add(sArr,cnt+1);
	}
	tree find(String s) {
		for(tree t : branches) {
			if(t.name.equals(s)) return t;
		}
		return null;
	}
	public void dfs() {
		Collections.sort(branches);
		for(tree t: branches) {
			t.dfs(0);
		}
		System.out.println(sb);
	}
	public void dfs(int depth) {
		for(int i = 0; i < depth; i++) {
			sb.append('-').append('-');
		}
		sb.append(name).append('\n');
		Collections.sort(branches);
		for(tree t : branches) {
			t.dfs(depth+1);
		}
	}
	@Override
	public int compareTo(tree o) {
		return this.name.compareTo(o.name);
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sArr;
		tree root = new tree();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			sArr = br.readLine().split(" ");
			root.add(sArr,1);
		}
		root.dfs();
	}

}
