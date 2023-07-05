# BJ 1074 Z

## 설명

```
Z 모양으로 배열을 방문할 때, r행 c열을 몇 번째로 방문했는지 출력해보자.
```

### Input

```
2 3 1

// 배열의 크기는 2^N x 2^N 이다.
```

### Output

```
11
```

### Idea

```
1. BinarySearch와 같은 방식으로 구할 수 있다.

2. r과 c가 4구역 중 어디에 있는지 계속 찾아주면 몇 번째로 방문하는지 찾을 수 있다.

3. 찾은 구역을 왼쪽 위로 옮겨준다.

4. 찾은 구역마다 index값에 다르게 더해준다. 왼쪽 위는 0, 오른쪽 위는 넓이의 1/4, 왼쪽 아래는 넓이의 2/4, 오른쪽 아래는 넓이의 3/4를 더해준다.

5. 2~4를 반복하여 N = 0이 되면 0을 return한다.
```

### 시간 복잡도

```
O(log N)
```

### Tseudo-Code

```
int get(int N, int r, int c)

if(N == 0) return 0;
half = 1<<(N-1);        //2^(N-1)

if(왼쪽 위)
    return 0 + get(half,r,c);
if(오른쪽 위)
    return half*half*1 + get(half,r,c-half)

if(왼쪽 아래)
    return half*half*2 + get(half,r-half)

if(오른쪽 아래)
    return half*half*3 + get(half,r-half,c-half)

```
