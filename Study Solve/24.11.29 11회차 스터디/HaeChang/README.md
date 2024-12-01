## [프로그래머스 Lv3. 연속 펄스 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/161988)

> 문제의 키워드

- 펄스 수열이란 [1, -1, 1, -1 …] 또는 [-1, 1, -1, 1 …] 과 같이 1 또는 -1로 시작하면서 1과 -1이 번갈아 나오는 수열
- 어떤 수열의 연속 부분 수열에 같은 길이의 펄스 수열을 각 원소끼리 곱하여 연속 펄스 부분 수열
- 연속 펄스 부분 수열의 합 중 가장 큰 것을 return

<br/>

> 접근법 분석

- 전체 수열에서 부분수열의 합이 가장 큰 구간 혹은 그 합의 값을 구하는것은 DP 웰 노운 문제임
- 여기에 펄스 수열이라는 조건만 붙었기에, 결국 자기 앞까지 -1을 곱한 숫자를 사용할 것인가, +1을 곱한 숫자를 사용할 것인가, 현재 수 부터 다시 시작할 것인가로 나뉨
- 마지막 dp 결과를 구할 때 일반적 dp와는 달리 dp배열 전체 순회하면서 정답을 구해야하는것이 중요함

<br/>

> 구현 접근법

`long`타입의 2차원 배열을 만든다.

`dp[i번째 숫자][+부호로 썻는지, -부호로 썻는지]`

그래서 i = 0 의 경우에 arr[0] 을 +로 쓸지 -로 쓸지에 따라 시작이 달라지기에 초기값을 

```
dp[0][0] = arr[0];
dp[0][1] = arr[0] * -1
```

로 설정해두어야 한다.

그렇다면 다음과 같은 점화식이 형성된다.

```
// 현재수를 지금까지의 부분수열에 포함시키는 경우

dp[i][0] = Math.max(dp[i - 1][1] + sequence[i], dp[i][0]);
-> 현재 수를 +로 포함시키면서 최대 펄스 부분수열의 합
dp[i][1] = Math.max(dp[i - 1][0] + (sequence[i] * -1), dp[i][0]);
-> 현재 수를 -로 포함시키면서 최대 펄스 부분수열의 합

// 현재수 부터 다시 부분수열을 형성 해 나가는 경우

dp[i][0] = Math.max(sequence[i], dp[i][0]);
-> 현재 수를 +로 두어 새로운 펄스 부분수열의 합을 시작
dp[i][1] = Math.max(sequence[i] * -1, dp[i][1]);
-> 현재 수를 -로 두어 새로운 펄스 부분수열의 합을 시작
```

이렇게 채운 dp 테이블을 순회하면서 최대값을 찾는데

배열 길이가 1인것을 고려해야 하므로 

```
return Math.max(arr[0], Math.max(dp[n - 1][0], dp[n - 1][1])) 
```
이 정답이 된다.

<br/>

> 시간복잡도

#### O(N)

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;

        long[][] dp = new long[sequence.length][2];
        dp[0][0] = sequence[0];     // 1을 사용한 경우
        dp[0][1] = sequence[0] * -1; // 앞에 -1을 사용한 경우
        
        answer = Integer.MIN_VALUE;
        
        for(int i = 1;i<sequence.length;i++) {
            dp[i][0] = Math.max(dp[i][0], sequence[i]);
            // 선택안한 것
            dp[i][1] = Math.max(dp[i][1], sequence[i] * -1);
            // 현재수 -1로 새롭게 시작하는것
            dp[i][0] = Math.max(dp[i - 1][1] + sequence[i], dp[i][0]); 
            // 자기 앞까지 -1를 사용한 숫자에서 현재수를 더한것과 현재값
            dp[i][1] = Math.max(dp[i - 1][0] + (sequence[i] * -1), dp[i][1]);
            // 자기 앞까지 1을 사용한 숫자에서 현재수의 -1을 곱한것과 현재값
            answer = Math.max(Math.max(dp[i][0], dp[i][1]), answer);
        }
                
        return Math.max(dp[0][0], Math.max(dp[0][1], answer));
    }
}
```

### 제출 결과

<img src="./result.png"></img>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/161988.%20%EC%97%B0%EC%86%8D%20%ED%8E%84%EC%8A%A4%20%EB%B6%80%EB%B6%84%20%EC%88%98%EC%97%B4%EC%9D%98%20%ED%95%A9/HaeChang)
