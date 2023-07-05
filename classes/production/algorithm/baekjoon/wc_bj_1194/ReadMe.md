# BJ 1194 달이 차오른다, 가자.

### 문제 이해
```
 * 1. 미로 N x M 
 * 2. 열쇠를 들고있으면 대응하는 문을 지나갈 수 있다. 
 * 3. 0은 민식이의 현재 위치 
 * 4. 1에 도착하면 끝
 * 5. 이동 횟수의 최솟값을 구해보자.
```

### Input
```
7 8             // N, M
a#c#eF.1        // map
.#.#.#..        // a~f : 열쇠, A~F : 문, # : 벽
.#B#D###        // 0 : 현재 위치, 1: 출구
0....F.1
C#E#A###
.#.#.#..
d#f#bF.1
```

### Output
```
55
```

### Idea
```
 * 1. 열쇠 보유 상태를 비트마스크로 표현. 
 * 2. visit 배열을 [N][M][1<<key] 로 나타냄. (키를 먹은 상태로 방문했는가?)
 * 3. BFS
```

### Tseudo-code
```
//BFS
q.add(0의 위치, 0, 0);
while(!q.isEmpty()){
    (ci, cj, dist, state) = q.poll();

    for(4방 탐색){
        다음 좌표 = (ni, nj)
        if(다음 좌표가 맵 밖이 아니면){
            next = 다음 좌표에 뭐가 있는지
            if(next == 출구) -> dist 출력 후 return;
            if(다음 좌표 = 벽 아니고 방문 안했으면){
                if(문이면){
                    if(열쇠가 있으면){
                        q.add(ni, nj, dist+1, state);
                        visited[ni][nj][state];
                    }
                }
            }else{
                q.add(ni, nj, dist+1)
                if(다음 좌표 = 열쇠){
                    q.add(열쇠 먹은 상태);
                    visited[ni][nj][열쇠 먹은 state] = true;
                }else{ // 열쇠가 아니면
                    q.add(state);
                    visited[ni][nj][state] = true;
                }
            }
        }
    }
}


```