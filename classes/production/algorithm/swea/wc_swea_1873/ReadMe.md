# SWEA 1873 상호의 배틀필드

### Input

```
1           //Test_case
3 7         //H, W
***....     //H x W map
*-..#**
#<.****
23          //command 수
SURSSSSUSLSRSSSURRDSRDS //command
```

### Output

```
#1 **....v
.-..#..
#......
```

### Idea

```
1. 입력을 잘 받아서

2. command를 실행한 후

3. 결과를 출력하자.
```

### Tseudo-Code

```
//BufferedReader로 입력
//StringBuilder로 출력

입력 시
<>v^일 경우 -> 전차의 위치
(cur_i, cur_j) <- 전차 위치
cur_di, cur_dj <- <>v^ //전차의 방향

S(){
    전차의 방향 확인
    bomb_i <- cur_i
    bomb_j <- cur_j
    while(!(벽 or 맵 밖)){
        포탄 위치 cur_dir 방향으로 한칸 이동
    }

}
```
