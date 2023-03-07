# BJ 17281 야구

### 문제 이해

```
1. 9명으로 이루어진 두 팀이 공격과 수비를 번갈아 함.

2. N 이닝 진행

3. 한 이닝에 3아웃 = 이닝 종료

4. 경기 전 타순 정하고 경기 중엔 타순 변경 X

5. 9번 타자까지 ...

1~5. 야구 룰.

6. 1~9번 선수까지 있고, 4번 타자 = 1번 선수

7. 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있을 때,

8. 가장 많은 득점을 하는 타순을 찾고, 그 때의 득점을 찾자.
```

### Idea & 시간복잡도

```
1. 완탐 - 순열 = 9! = 36만

2. 최악의 경우 : N = 50 x 36만 = 1800만
```

### Tseudo - code

```
Main():
    perm(0);

perm(int cnt){
    if(cnt == 8){
        simul()
    }
    for(1~9번 선수){
        if(visited) continue;
        visited[i] = true;
        selected[cnt] = i;
        visited[i] = false;
    }
}

simul(){
    boolean lu[3];
    int out;
    for(N 이닝){
        실행 -> b, out, lu, 점수 갱신
    }
}

```
