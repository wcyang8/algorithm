# BJ 17143

### 문제 이해

```
1. R x C 격자판

2. 각 칸은 (r,c)로 나타냄

3. 칸에는 상어가 최대 한마리.

4. 상어는 크기와 속도가 있음.

5. 낚시왕은 왼->오 탐색

6. 1초마다 일어나는 일
    1. 낚시왕이 오른쪽으로 한 칸 이동.
    2. 같은 열의 상어 중 땅과 제일 가까운 상어 포획.
    3. 상어가 이동.

7. 상어는 주어진 속도로 이동.(칸/초)

8. 상어는 벽에 부딪히면 반대방향 같은 속력.

9. 이동 후 한 칸에 상어 여러 마리가 있으면 크기가 가장 큰 상어가 나머지를 잡아먹음.
```

### Input

```
4 6 8       // R C M
4 1 3 3 8   // r c s d z
1 3 5 2 9   // (r,c)
2 4 8 4 1   // s : 속도
4 5 0 1 4   // d : 방향
3 3 1 2 7   // z : 크기
1 5 8 4 3
3 6 2 1 2
2 2 2 3 5
// 2 <= R,C <= 100
// 0 <= M <= RxC
// 1 <= r <= R
// 1 <= c <= C
// 0 <= s <= 1000
// 1 <= d <= 4
// 1 <= z <= 10000
// d : 1 - 위, 2 - 아래, 3 - 오른, 4 - 왼
```

### Output

```
22          // 잡은 상어 크기 합
```

### 시간복잡도

```
최대 100 x 100 x 100 = 100만
```

### Idea

```
시뮬레이션.
```
