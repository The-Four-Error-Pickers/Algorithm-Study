## [프로그래머스 Lv3. 카운트 다운](https://school.programmers.co.kr/learn/courses/30/lessons/131129)

> 문제의 키워드

- 다트를 던져서 점수를 깎아서 정확히 0으로 만드는 게임
- 다트 과녁에는 1 부터 20까지의 수가 하나씩 있고 각 수 마다 "싱글", "더블", "트리플" 칸이 있음
- "싱글"을 맞히면 해당 수만큼 점수를 얻고 "더블"을 맞히면 해당 수의 두 배만큼 점수를 얻고 "트리플"을 맞히면 해당 수의 세 배만큼 점수를 얻음
- 가운데에는 "불"과 "아우터 불"이 있는데 "카운트 다운" 게임에서는 구분 없이 50점을 얻음
-  최소한의 다트로 0점을 만드는 게 가장 중요하고, 그러한 방법이 여러가지가 있다면 "싱글" 또는 "불"을 최대한 많이 던지는 방법을 선택해야 함

- `1 ≤ target ≤ 100,000`

<br/>

> 접근법 분석

- 결국 50점을 제외하면 1 ~ 20점을 가지고 각각 배율이 있다.
- 숫자가 너무커서 O(N) 혹은 O(1) 로 끊어야 한다고 생각했다.
- 어짜피 target에서 0점이나 0 에서 target이나 상관 없다고 생각해서 점수를 쌓아올려가면서 최소 다트수와 싱글 또는 불의 합을 관리하는 DP가 아닐까 생각함
- 이는 곧 DP 점화식이 안떠올랐기에 포기, 곧 바로 수학으로 최대한 큰수로 target을 깎아 내려가면 되지않을까 라고 사고함

<br/>

> 구현 접근법

target을 기준으로 0이 될때까지, 20 ~ 1 그리고 싱글, 더블, 트리플 배율의 전달된 값 그리고 불을 맞춘 경우인 50점을 기준으로 target을 가장 나눌 수 있는 최대수로 모듈러 연산하여

불 또는 싱글을 사용한 개수 및 총 다트수를 구하기

<br/>

> 시간복잡도

#### O(target / 60)

<br/>

### 구현 코드

구현한 코드가 없음

### 제출 결과

이하 생략

<br>

# 토론 접근법 풀이

target의 수는 10만이지만, 어짜피 10만을 0으로 만드는데 필요한 도구는 그렇게 다양하지 않다.

50의 경우와 1 ~ 20까지의 각각 싱글, 더블, 트리플의 경우인 60개, 즉 61가지 경우를 가지고 숫자를 깎아야 한다.

또한 어떤 수 까지의 도달할 때 걸렸던 총 다트수는 최소로, 같다면 싱글 또는 불로 던진수를 최대로 해야하기 때문에, 적절한 기록이 필요하다.

수는 깎으면 깎을수록 내려가는건 분명하고, 또한 특정수 까지의 도달하는데 볼필요 없는 경우는 지워버릴 수 있는 조건이 분명하다.

따라서 백준에 숨바꼭질 처럼, 수직선 BFS 풀이 및 DP풀이가 가능하다.

### 토론 based 구현 코드 - 1 (BFS)

```java
import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] answer = {};
        return bfs(target);
    }
    static int[] bfs(int target) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{target, 0, 0}); // now[1]: 싱글 + 불, now[2]: 총 개수
        int[] v = new int[100_001];
        int[] sb = new int[100_001];
        
        Arrays.fill(v, 987654321);
        v[target] = 0;
        sb[target] = 987654321;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i = 20; i > 0 ; i--) {
                for(int j = 1;j<=3;j++) {
                    if (now[0] < i * j) continue;
                    if (v[now[0] - (i * j)] > now[2] + 1) {
                        sb[now[0] - (i * j)] = now[1] + (j == 1 ? 1 : 0);
                        v[now[0] - (i * j)] = now[2] + 1;
                        queue.add(new int[]{now[0] - (i * j), sb[now[0] - (i * j)] ,v[now[0] - (i * j)]});
                    } else if (v[now[0] - (i * j)] == now[2] + 1
                               && sb[now[0] - (i * j)] < now[1] + (j == 1 ? 1 : 0) )
                     {
                        sb[now[0] - (i * j)] = now[1] + (j == 1 ? 1 : 0) ;
                        queue.add(new int[]{now[0] - (i * j), sb[now[0] - (i * j)] , v[now[0] - (i * j)]});
                    }
                }
            }
            
            if (now[0] >= 50 && v[now[0] - 50] > now[2] + 1) {
                
                sb[now[0] - 50] = now[1] + 1;
                v[now[0] - 50] = now[2] + 1;
                queue.add(new int[]{now[0] - 50, sb[now[0] - 50], v[now[0] - 50]});
            } else if (now[0] >= 50 && v[now[0] - 50] == now[2] + 1 && sb[now[0] - 50] < now[1] + 1) {
                sb[now[0] - 50] = now[1] + 1;
                queue.add(new int[]{now[0] - 50, sb[now[0] - 50], v[now[0] - 50]});
            }
            
            
        }
        return new int[]{v[0], sb[0]};
    }
}
```
### 토론 based 구현 코드 - 2 (DFS, DP)
```java
import java.util.*;

class Solution {

    static int[][] dp;
    public int[] solution(int target) {
        int[] answer = {};
        dp = new int[2][target + 1]; // 0: 총 다트의 합, 1: 싱글 또는 불 다트의 개수
        Arrays.fill(dp[0], 987654321);
        Arrays.fill(dp[1], -987654321);
        dfs(target);
        
        return new int[]{dp[0][target], dp[1][target]};
    }
    static int[] dfs(int num) {
        if (dp[0][num] != 987654321 && dp[1][num] != -987654321) {
            return new int[]{dp[0][num], dp[1][num]};
        }
        
        if (num == 0) {
            return new int[]{0, 0};
        }
        
        if (num - 50 >= 0) {
            int[] result = dfs(num - 50);
            if (result[0] + 1 < dp[0][num]) {
                dp[0][num] = result[0] + 1;
                dp[1][num] = result[1] + 1;
            } else if (result[0] + 1 == dp[0][num] && dp[1][num] < result[1] + 1) {
                dp[1][num] = result[1] + 1;
            }
        }
        
        for(int i = 20;i > 0; i--) {
            for(int j = 3;j > 0;j--) {
                int t = i * j;
                if (num < t) continue;
                int[] result = dfs(num - t);
                if (result[0] + 1 < dp[0][num]) {
                    dp[0][num] = result[0] + 1;
                    dp[1][num] = result[1] + (j == 1 ? 1 : 0);
                } else if (result[0] + 1 == dp[0][num] && dp[1][num] < result[1] + (j == 1 ? 1 : 0)) {
                    dp[1][num] = result[1] + (j == 1 ? 1 : 0);
                }
            }
        }
        return new int[]{dp[0][num], dp[1][num]};
        
    }
}
```

### 토론 based 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/131129.%20%EC%B9%B4%EC%9A%B4%ED%8A%B8%20%EB%8B%A4%EC%9A%B4/HaeChang)
