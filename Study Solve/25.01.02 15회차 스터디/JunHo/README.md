## [프로그래머스 Lv3. 보행자 천국](https://school.programmers.co.kr/learn/courses/30/lessons/1832)

> 문제의 키워드

- 도로의 상태에 주의
- 도착지에서 출발지까지 경우의 수를 20170805로 나눈 값을 출력

<br/>


> 접근법 분석
- 백준의 파이프옮기기의 DP 풀이와 유사하다고 생각
- 현재 도로의 상태에 따라 다음 도로에 갈 수 있는 경우의 수를 갱신해줌
- 예를 들면, 현재 도로가 0이면 다음 도로에는 왼쪽에서 현재 도로에 온 경우와 위에서 현재 도로에 온 경우 모두 합해주면 되고
- 현재 도로가 2이면 같은 방향에서 온 경우만 더해주면 됨

> 알고리즘

#### DP


<br/>

> 시간복잡도
#### O(m * n)

<br/>

### 구현 코드

```java
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m + 1][n + 1];  // 0 : ㅡ, 1 : ㅣ
        dp[0][0][0] = 1;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(cityMap[i][j] == 0) {
                    dp[0][i][j + 1] += (dp[0][i][j] + dp[1][i][j]) % MOD;
                    dp[1][i + 1][j] += (dp[0][i][j] + dp[1][i][j]) % MOD;
                } else if(cityMap[i][j] == 2) {
                    dp[0][i][j + 1] += (dp[0][i][j]) % MOD;
                    dp[1][i + 1][j] += (dp[1][i][j]) % MOD;
                }
            }
        }
        
        return (dp[0][m - 1][n - 1] + dp[1][m - 1][n - 1]) % MOD;
    }
}
```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/1832.%20%EB%B3%B4%ED%96%89%EC%9E%90%20%EC%B2%9C%EA%B5%AD/JunHo/2025-1-4T102752)
