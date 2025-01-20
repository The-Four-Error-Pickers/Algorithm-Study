## [프로그래머스 Lv3. 카운트 다운](https://school.programmers.co.kr/learn/courses/30/lessons/131129#)

> 문제의 키워드

- 점수를 깎아내려가면서 정확히 0점을 만들어야함
- 싱글, 더블, 트리플, 불 중 최소한의 횟수이면서 싱글과 불을 가장 많이 던질 수 있는 경우를 찾아야함

<br/>
<br/>

> 접근법 분석
- 처음에는 그리드 + 조건 분기 느낌으로 접근할려고 했으나(9회차 스터디 Read.me 참조) <strong> 너무 많은 조건 처리 </strong>가 발생할 것 같아서 방법을 변경
- dp를 생각했는데 알고리즘 당시에는 접근을 하지 못해서, 그래서 타블로그를 참고하여 top-down 방식을 bottom-up으로 변경해서 해봄.
- 1. 우선 dp 테이블을 나올 수 없는 최댓값으로 설정
- 2. 다음으로 다트 점수를 계산하는데, 우선 불을 현재 계산해야하는 점수 값이 50이 넘는 경우 불부터 맞추고 생각
- 3. 20부터 ~ 1까지 1~3을 곱하면서 dp 테이블 값을 초기화
- 여기서 dp 테이블 값을 초기화할때는 다트 수와 싱글 + 불 카운트를 가지고 비교


> 알고리즘

#### dp


<br/>

> 시간복잡도

#### O(N)

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];  // dp[i][0] : 점수 i를 만들때 최소 다트 수, dp[i][2] : i를 만들때 최대 싱글/불 횟수
        
        for(int i = 1; i <= target; i++) dp[i][0] = 100001; // 나올수 없는 최댓값으로 설정
        
        // 다트 점수를 계산
        for (int n = 1; n <= target; n++) {
            // 1. 불을 맞추는 경우 (점수 50)
            if (n >= 50) {
                int[] temp = dp[n - 50];
                update(dp, n, temp[0] + 1, temp[1] + 1); // 불은 싱글/불 횟수에 포함
            }

            // 2. 싱글, 더블, 트리플을 맞추는 경우
            int start = Math.min(20, n); // 다트 점수는 최대 20(탐색 최솟값을 위한 코드
            for (int i = start; i >= 1; i--) {
                for (int j = 1; j <= 3; j++) { // 싱글, 더블, 트리플
                    if (n >= i * j) { // 점수를 초과하지 않는 경우만 계산
                        int[] temp = dp[n - i * j];
                        int singleCount = (j == 1) ? 1 : 0; // 싱글이면 카운트 증가
                        update(dp, n, temp[0] + 1, temp[1] + singleCount);
                    }
                }
            }
        }
        
        return dp[target];
    }
    
    // dp 테이블을 업데이트하는 함수
    public void update(int[][] dp, int n, int dartCount, int singleBullCount) {
        // 1. 최소 다트 수가 더 적은 경우 갱신, 2. 다트가 같은 경우는 싱글, 불 카운트 비교
        if (dartCount < dp[n][0] || (dartCount == dp[n][0] && singleBullCount > dp[n][1])) {
            dp[n][0] = dartCount;
            dp[n][1] = singleBullCount;
        }
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/131129.%20%EC%B9%B4%EC%9A%B4%ED%8A%B8%20%EB%8B%A4%EC%9A%B4/JunHo/2024-11-26T174314)
