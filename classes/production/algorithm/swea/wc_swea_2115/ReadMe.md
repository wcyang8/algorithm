# SWEA 2115 벌꿀 채취

### 문제 이해

```
1. N x N 벌통

2. 각 칸에는 꿀의 양을 나타내는 수

3. 2명의 일꾼
    - 가로로 연속된 M개의 벌통
    - 벌통은 겹치면 안됨.

4. 하나의 벌통에서 채취한 꿀은 서로 다른 용기에 담아야 함.

5. 각 일꾼이 채취할 수 있는 최대 꿀의 양 = C

6. 수익 = 용기 꿀의 양 제곱

7. 최대 수익을 구해보자.
```

### Input

```
4 2 13
6 1 9 7
9 8 5 8
3 4 5 3
8 2 6 7
```

### Output

```
174
```

### 시간 복잡도

```
50 * 100 * 99 / 2 = 500000

```

### Idea

```
1. 연속한 벌통 선택.

2. 가장 큰 세트 2개 선택.

3. 연속 안되도록, C 안넘기도록.

//

벌통 고르기

1. M은 최대 5이므로 그냥 부분집합으로 구하자.

세트 고르기

1. 벌통 중 최대치를 고르고 그 다음 가능한 것 중 최대치를 고르면 그게 최대치인가?

```