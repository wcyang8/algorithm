# SWEA 1263 사람 네트워크2

### 문제 이해

```
요약

 * 1. CC(i) = dist(i,j) 의 합 (dist(i,j) = i부터 j까지의 최단거리
 * 2. 각 사람마다 최단거리를 전부 더해 그 중 최소값을 구해보자.
```

### Input

```
20               // 테스트 케이스 개수
5 0 1 1 0 0 1 0 1 1 1 1 1 0 0 0 0 1 0 0 0 0 1 0 0 0         // N, 인접행렬
5 0 0 1 1 0 0 0 1 0 0 1 1 0 0 1 1 0 0 0 1 0 0 1 1 0         // ...
...
```

### Output

```
4
5
```

### 시간 복잡도

```
N 최대 1000 이므로

1000 x 1000 x 1000 = 10억

약 3초이므로 통과 가능.
```

### Idea

```
 1. 거리를 1000으로 초기화

 2. 서로 연결되어 있으면 거리 1로 갱신.

 3. 플로이드 워셜 알고리즘으로 각 사람간 최단거리 구하기.
 
 4. for문으로 사람간 최단거리 합을 구해서 합의 최소값 구하기.
```