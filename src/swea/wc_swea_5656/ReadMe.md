# SWEA 5656 벽돌 깨기

### 문제 이해

```
1. 구슬을 쏘아 벽돌을 깨뜨리는 게임.

2. 구슬은 N번 쏠 수 있다.

3. 벽돌의 정보는 W x H

4. 0은 빈 공간.

5. 구슬은 좌 우로만 움직일 수 있음.

6. 벽돌은 숫자 1~9로 표현

7. 구슬이 명중한 벽돌은 상하좌우로 벽돌에 적힌 숫자 - 1 칸 제거

8. 7로 인해 제거되는 벽돌도 마찬가지로 7번의 법칙을 따른다.

9. 빈 공간이 있으면 벽돌은 밑으로 떨어진다.

10. 최대한 많은 벽돌을 제거하고 남은 벽돌의 개수를 구하자.
```

### Input

```
// 1 <= N <= 4
// 2 <= W <= 12
// 2 <= H <= 15
5                   //T
3 10 10             //N, W, H
0 0 0 0 0 0 0 0 0 0 //벽돌
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
.
.
.
```

### Output

```
#1 12
.
.
.
```

### 시간복잡도

```
최대 12^4
-> 시뮬레이션 문제.
```

### Idea

```
1. 쏠 열을 고른다.

2. 쏘고 정리한다.

3. N번 쏜다.

4. 남은 벽돌의 최소값을 구한다.

//쏴도 의미 없는 벽돌 거르기
1. 위에서부터 남은 구슬 개수만큼 1인 열은 거른다.
```
