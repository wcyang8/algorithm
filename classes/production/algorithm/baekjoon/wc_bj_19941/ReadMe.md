# BJ 19941 햄버거 분배

### Input
```
20 1
HHPHPPHHPPHPPPHPHPHP
// N : 식탁의 길이
// K : 햄버거 선택 가능 거리
// 사람과 햄버거의 위치
// 1 <= N <= 20000
// 1 <= K <= 10
```

### Output
```
8
```

### 문제 이해
```
사람, 햄버거의 위치가 주어졌을 때,
햄버거를 먹을 수 있는 사람의 최대 수를 구해보자.
```

### 시간복잡도
```
완전 탐색의 경우
N = 20000, K = 10이면
햄버거와 사람이 10000개씩 있다고 할 때,
사람이 어떤 햄버거를 먹을 지 고르는 경우의 수 대략 10! x 2000 = 72억 이므로 시간초과
//
그리디 알고리즘으로 풀어보자.
```

### Idea
```
1. 햄버거든 사람이든 상관 없음

2. 무조건 가까우면 왼쪽거를 먹도록 하자.

3. 스택, 덱과 같은 자료구조? -> K라는 길이가 정해져 있기 때문에 좋은 선택지가 아니다.
```