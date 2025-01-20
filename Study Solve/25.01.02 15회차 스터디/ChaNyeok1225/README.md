## [프로그래머스 Lv3. 보행자 천국](https://school.programmers.co.kr/learn/courses/30/lessons/1832)

> 문제 키워드

-   경로의 전체 개수
-   도로의 종류 0, 1, 2
-   m, n 은 500 이하

<br/>

> 접근법 분석
-   경로의 전체 개수 탐색 문제, DFS 또는 DP
-   맵 크기로 인해 DFS는 불안함, DP로 판단
-   오른쪽 또는 아래로만 갈 수 있기 때문에 [n][m][2]의 DP
<br/>

> 구현 접근법

-   [2][m+1][n+1] 크기의 dp 배열 선언
-   [차의 방향][격자R][격자C]로 정의
-   도로의 타입에 따라 상향식 계산

<br/>

> 시간 복잡도

#### O(M*N)

격자의 크기: M*N

<br/>

### 구현 코드

```java
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[2][m+1][n+1];
        dp[0][0][0] = 1;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                dp[0][i][j] %= MOD;
                dp[1][i][j] %= MOD;
                
                if(cityMap[i][j] == 0) {
                    dp[1][i+1][j] += dp[0][i][j] + dp[1][i][j];
                    dp[0][i][j+1] += dp[0][i][j] + dp[1][i][j];
                } else if(cityMap[i][j] == 2) {
                    dp[1][i+1][j] += dp[1][i][j];
                    dp[0][i][j+1] += dp[0][i][j];
                }
                
            }
        }
        
        answer = (dp[0][m-1][n-1] + dp[1][m-1][n-1]) % MOD;
        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="13174"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (64.07ms, 102MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/1832.%20%EB%B3%B4%ED%96%89%EC%9E%90%20%EC%B2%9C%EA%B5%AD/ChaNyeok1225)
