## [프로그래머스 Lv3. 연속 펄스 부분 수열의 합](https://school.programmers.co.kr/learn/courses/30/lessons/161988)

> 문제 키워드

-   펄스 수열
-   연속 부분 수열의 합이 최대

<br/>

> 접근법 분석

-   단순하게 연속 부분 수열의 합에서 펄스 수열이라는 구조를 추가한 것
-   1과 -1 값을 번갈아 가지는 값을 가지고 연속 부분 수열의 최댓값을 탐색

<br/>

> 구현 접근법

-   펄스 수열을 표현할 변수 선언
-   1로 시작하는 펄스 수열의 합을 연산할 변수와 -1로 시작하는 펄스 수열의 합을 연산할 변수를 선언
-   변수를 선형 탐색하며 연속 부분 수열의 최댓값 탐색

<br/>

> 시간 복잡도

#### O(N)

수열의 길이: N

<br/>

### 구현 코드

```java
class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;

        long sum1 = 0;
        long sum2 = 0;

        int seq = 1;

        for(int i = 0; i < n; i++) {
            if(sum1 < 0)
                sum1 = 0;
            if(sum2 < 0)
                sum2 = 0;

            sum1 += sequence[i] * seq;
            sum2 += sequence[i] * -seq;

            answer = Math.max(answer, Math.max(sum1, sum2));

            seq = -seq;
        }

        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="192357"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.07ms, 83MB)</td></tr><tr data-testcase-id="192358"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.05ms, 76.8MB)</td></tr><tr data-testcase-id="192359"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.08ms, 81.2MB)</td></tr><tr data-testcase-id="192360"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.09ms, 75.9MB)</td></tr><tr data-testcase-id="192361"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.09ms, 88MB)</td></tr><tr data-testcase-id="192362"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (0.11ms, 81.3MB)</td></tr><tr data-testcase-id="192363"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (0.08ms, 71.3MB)</td></tr><tr data-testcase-id="192364"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (0.15ms, 82.3MB)</td></tr><tr data-testcase-id="192365"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (0.24ms, 88.6MB)</td></tr><tr data-testcase-id="192366"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (0.81ms, 86.5MB)</td></tr><tr data-testcase-id="192367"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (0.92ms, 82.2MB)</td></tr><tr data-testcase-id="192368"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (5.12ms, 86.4MB)</td></tr><tr data-testcase-id="192369"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (5.19ms, 95.6MB)</td></tr><tr data-testcase-id="192370"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (5.71ms, 82.4MB)</td></tr><tr data-testcase-id="192371"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (5.78ms, 88.9MB)</td></tr><tr data-testcase-id="192372"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (5.94ms, 78.6MB)</td></tr><tr data-testcase-id="192373"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (12.15ms, 114MB)</td></tr><tr data-testcase-id="192374"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (13.62ms, 132MB)</td></tr><tr data-testcase-id="192375"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (10.25ms, 117MB)</td></tr><tr data-testcase-id="192376"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (11.15ms, 114MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/161988.%20%EC%97%B0%EC%86%8D%20%ED%8E%84%EC%8A%A4%20%EB%B6%80%EB%B6%84%20%EC%88%98%EC%97%B4%EC%9D%98%20%ED%95%A9/ChaNyeok1225)
