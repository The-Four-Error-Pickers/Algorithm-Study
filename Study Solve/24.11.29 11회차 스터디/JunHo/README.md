## [프로그래머스 Lv3. 연속 펄스 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/161988)

> 문제의 키워드

- 연속 펄스 부분 수열 = 부분 수열 + 펄스 수열(-1, 1, -1, ... 연달아 나오는 수열)
- 연속 펄스 부분 수열의 합 중 가장 큰 것을 고르시오.

<br/>


> 접근법 분석
- 처음에는 구간합과 투 포인터를 사용하려고 했는데 n도 크고 접근법도 떠오르지 않았다.
- 그래서 세그먼트 트리로 풀려고 해봤는데, 구간의 합을 구하는건 logN이지만 모든 구간을 탐색해야하므로 N^2이다.
- 그래서 DP로 접근함.
- DP로 접근 (2개) -1로 ㅅ기작하는거랑 1로시작한거
- 우선 DP배열을 두개를 만든다. 펄스 부분 수열이 -1로 시작할때랑, 1로 시작할때
- -1로 시작한다면, 
- -2, 3, 6, 1, -3, -1, -2, 4
- dp[i] : i인덱스까지 왔을때 최댓값
- dp[0] = -2를 넣어주고 dp[1]은 0인덱스값과 1인덱스값을 더하거나, 1인덱스로 다시 시작하는 경우 2가지가 있음.
- dp[1] = Math.max(-2 + 3, 3)
- dp[1] = Math.max(dp[0] + sequence[1] * 펄스값에 맞는 것, sequence[1] * 펄스값에 맞는 것)
- 계산하면서 정답 최댓값을 갱신하기

> 알고리즘

#### DP


<br/>

> 시간복잡도
#### O(N)

<br/>

### 구현 코드

```java
class Solution {
    public long solution(int[] sequence) {
        
        // dp[i] : i번째 인덱스까지 왔을 때 부분수열의 최댓값으로 갱신
        long[] dp1 = new long[sequence.length];   // -1로 시작하는 펄스 부분 수열
        long[] dp2 = new long[sequence.length];   // 1로 시작하는 펄스 부분 수열
        
        dp1[0] = sequence[0] * -1;  
        dp2[0] = sequence[0] * 1;
        long answer = Math.max(dp1[0], dp2[0]);
        
        for(int i = 1; i < sequence.length; i++) { 
            if((i & 1) == 1) { // i가 홀수
                dp1[i] = Math.max(dp1[i - 1] + sequence[i], sequence[i]);
                dp2[i] = Math.max(dp2[i - 1] + (sequence[i] * -1), (sequence[i] * -1));
            } else {
                dp1[i] = Math.max(dp1[i - 1] + (sequence[i] * -1), (sequence[i] * -1));
                dp2[i] = Math.max(dp2[i - 1] + sequence[i], sequence[i]);
            }

            answer = Math.max(answer, Math.max(dp1[i], dp2[i]));
        }
            
        return answer;
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/161988.%20%EC%97%B0%EC%86%8D%20%ED%8E%84%EC%8A%A4%20%EB%B6%80%EB%B6%84%20%EC%88%98%EC%97%B4%EC%9D%98%20%ED%95%A9/JunHo/2024-11-29T19454)
