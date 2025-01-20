## [프로그래머스 Lv3. 미로 탈출 명령어](https://school.programmers.co.kr/learn/courses/30/lessons/150365)

> 문제의 키워드

- n x m 격자 미로가 주어집니다. 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
- 미로를 탈출하는 조건이 세 가지 있습니다.
    - 격자의 바깥으로는 나갈 수 없습니다.
    - (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
    - 미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.
- 이동 경로는 다음과 같이 문자열로 바꿀 수 있습니다.
    - l: 왼쪽으로 한 칸 이동
    - r: 오른쪽으로 한 칸 이동
    - u: 위쪽으로 한 칸 이동
    - d: 아래쪽으로 한 칸 이동

- 미로를 탈출하기 위한 경로를 return 하도록 solution 함수를 완성해주세요.

- 단, 위 조건대로 미로를 탈출할 수 없는 경우 "impossible"을 return 해야 합니다.

<br/>

> 접근법 분석

- k의 범위가 심상치 않다는것은, 출발점부터 도착점까지의 거리보다 K가 작을수도, 클 수도 있다는 것
- 정확히 k번째에 도착지에 잇어야 하므로, 그전에 도착지에 도착하는건 정답 경로로 return 할 수 없음
- 그렇다면 애초에 출발점 부터 도착점까지 거리가 k보다 크다면? 도달 불가능한것
- 그럼 문자열 사전순은 어떻게 해야하는가? 일단 사전순이면 문자열이 짧을수록 좋고 이는 곧 최단거리로 이동해야 함을 의미한다.
- 또한 `d < l < r < u` 순서라는것 도 알 수 있다.
- 격자의 크기가 적당하므로 완전탐색이 가능한데, 최단거리로 이동했을 때 k보다 적게 비용이 발생한다면? 좌우 혹은 상하로 왕복수행을 해야 한다.
    - 처음에 여기서 k번만큼 완전탐색으로 이동한 뒤에 lr, rl, ud, du 문자열 중 격자 밖으로 안나가면서 최소인 문자를 append 하려고 했다.
    - 이게 잘못된 것은 아니나, 완전탐색인데 굳이 완전탐색 중간을 끊고 마지막을 그리디하게 해를 구하는 과정을 넣을 필요가 없는것
    - 어짜피 방문체크를 안하기 때문에 좌우 반복에서는 문제가 없다. 다만 `잘못된 경로로 갔을 때 커팅해주는 결정 조건`이 필요하다.
    - 여기서 생각해보면 왕복수행에 필요한 수치는 언제나 짝수이다.
        - 이는 곧 `k - (현재 남은거리)` 가 언제나 짝수여야 함을 의미한다.
        - 여기서 말하는 `언제나`는 칸을 이동했으면, k를 하나씩 감소시키는것을 말한다.
        - 방문체크를 할 필요가 없는 이유가 바로 이 이유때문이다.
        - 대부분의 상황에서 위 증명으로 인해 이미 해당경로의 선택지가 쓸모없어지기 때문이다. 
        - 마지막으로 dfs이기 때문에 그리고 `d < l < r < u` 순으로 반복문을 수행하기 때문에 첫 번째로 구한 정답이 해가 된다. 따라서 flag를 써야하고 안쓰면 시간초과

<br/>

> 구현 접근법

쓸모가 있는 경로를 찾는 방법은 예로부터 DFS이 좋은 선택지이다.

<br/>

> 시간복잡도

#### O(K): 최소한 K번은 움직여야함.

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    static int N;
    static int M;
    static int K;
    static String answer;
    static boolean find;
    static int[] dy = {0,-1,1,0};
    static int[] dx = {1,0,0,-1};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {        
        int totalCnt = Math.abs(x - r) + Math.abs(y - c);
        
        if (k < totalCnt) return "impossible";
        if (((totalCnt - k) & 1) == 1) return "impossible";
        N = n;
        M = m;
        K = k;
        answer = null;
        dfs(x, y, r, c, k, -1, new StringBuilder());
        
        return answer;
    }
    static void dfs(int x, int y, int r, int c, int depth, int prevDir, StringBuilder sb) {
        if (depth < 0 || find) return; // 이미 찾았거나 k번 이상 소모한 경우
        int remain = Math.abs(x - r) + Math.abs(y - c);
        if (((depth - remain) & 1) == 1) return; // 현재 남은 k와 남은 칸수를 뺏을 때 홀수인지
        if (remain > depth) return; // 남은 칸수가 k보다 더 많은 경우 도달 불가능
        if (depth == 0 && x == r && y == c) {
            if (answer == null || sb.toString().compareTo(answer) < 0) {
                find = true;
                answer = sb.toString();
            }
            return;
        }
        
        
        for(int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx > N || nx <= 0 || ny > M || ny <= 0) continue;
            sb.append(getDir(dir));
            dfs(nx, ny, r, c, depth - 1, dir, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
    static String getDir(int dir) {
        if (dir == 0) return "d";
        if (dir == 1) return "l";
        if (dir == 2) return "r";
        else return "u";
    }
}
```

### 제출 결과

<img src="./result.png"/>


#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/76503.%20%EB%AA%A8%EB%91%90%200%EC%9C%BC%EB%A1%9C%20%EB%A7%8C%EB%93%A4%EA%B8%B0/HaeChang/2024-12-24T144148)
