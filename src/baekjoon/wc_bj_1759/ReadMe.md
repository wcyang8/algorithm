# BJ 1759 감시

### 문제 이해

```
1. 암호

2. 서로 다른 L개의 알파벳 소문자

3. 최소 1개의 모음, 2개의 자음

4. 가능성 있는 암호 = 오름차순

5. 암호 문자 C가지가 주어질 때, 가능성 있는 암호 구하기.
```

### Input

```
4 6
a t c i s w
```

### Output

```
acis
acit
aciw
acst
acsw
actw
aist
aisw
aitw
astw
cist
cisw
citw
istw
```

### 시간복잡도

```
최악의 경우
L = 9
C = 15
자음 7개 모음 8개

자 6 모 6

12 C 6 = 924

7C1 x 8C2 = 7 x 28 = 약 18만

완전탐색으로 해결 가능
//포기

다른 방법
최악의 경우
15 C 7 = 약 6천

완탐 해결 가능
```

### Idea

```
1. 자음 모음 구분

2. 자음 C 1

3. 모음 C 2

4. (전체-3) C (L-3)
//복잡한 풀이라 포기

1. C comb L 후에 모 1 자 2 체크

```

### Tseudo-code

```

```