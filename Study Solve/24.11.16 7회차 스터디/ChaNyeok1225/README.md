## [프로그래머스 Lv3. 인사고과](https://school.programmers.co.kr/learn/courses/30/lessons/152995)

> 문제의 키워드

- 두 개의 점수를 통한 평가
- 어떤 사원이 가진 두 개의 점수가 다른 사원보다 둘 다 낮다면 지급하지 않음
- 나머지 사원들에 대해서 합이 높은 순으로 석차
- 합이 동일하다면 동석차, 다음 석차는 건너 뛴다
- scores[0]의 등수는 몇 등인가, 제외되는 경우 -1

<br/>

> 접근법 분석

- scores 길이는 최대 100,000
	- n² 풀이가 불가능하다.
	- 이진 탐색이나 그리디 추측
- 하나의 점수에 대한 다른 점수의 `누적 최고 값`이 필요하다고 판단
- 누적 최고 값을 구했다면 점수의 합으로 내림차순하고 선형 탐색

<br/>

> 구현 접근법

- 하나의 점수에 대한 다른 점수의 최고 값 배열 연산
- 연산한 최고 값 배열을 마지막 인덱스부터 첫 인덱스까지 누적하며 최고 값 연산
- scores 배열을 합 내림차순 정렬
- 정렬된 scores 배열을 누적 최고 값과 비교하며 선형 탐색

<br/>

> 시간 복잡도

#### N(점수 별 최댓값) + M(누적 연산) + NlogN(정렬) + N(선형 탐색)
#### O(NlogN + M)

scores 배열의 길이: N

부여 가능한 점수의 최댓값: M



<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    
    public int solution(int[][] scores) {
        int n = scores.length;
        
        int[] wonho = {scores[0][0], scores[0][1]};
        
        int MAX_VAL = 100_005;
        int[] acc = new int[MAX_VAL];
        
        for(int i = 0; i < n; i++) {
            acc[scores[i][0]] = Math.max(acc[scores[i][0]], scores[i][1]);
        }
        for(int i = MAX_VAL - 2; i >= 0; i--) {
            acc[i] = Math.max(acc[i], acc[i+1]);
        }
        
        Arrays.sort(scores, (a,b) -> {
            return -Integer.compare(a[0]+a[1], b[0]+b[1]);
        });
        
        int grade = 0;
        int cnt = 0;
        int prev = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(scores[i][1] < acc[scores[i][0] + 1])
                continue;
            cnt++;
            
            if(prev > scores[i][0] + scores[i][1]) {
                grade += cnt;
                cnt = 0;
                prev = scores[i][0] + scores[i][1];
            }
            if(wonho[0] == scores[i][0] && wonho[1] == scores[i][1])
                return grade;
        }
        
        return -1;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="180421"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (3.68ms, 75.3MB)</td></tr><tr data-testcase-id="180422"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (3.55ms, 77.3MB)</td></tr><tr data-testcase-id="180423"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (3.69ms, 75.3MB)</td></tr><tr data-testcase-id="180424"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (3.71ms, 72.1MB)</td></tr><tr data-testcase-id="180425"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (5.30ms, 79.1MB)</td></tr><tr data-testcase-id="180426"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (3.56ms, 73.5MB)</td></tr><tr data-testcase-id="180427"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (3.66ms, 89.1MB)</td></tr><tr data-testcase-id="180428"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (3.58ms, 71.7MB)</td></tr><tr data-testcase-id="180429"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (5.75ms, 78.8MB)</td></tr><tr data-testcase-id="180430"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (3.77ms, 74.3MB)</td></tr><tr data-testcase-id="180431"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (4.37ms, 74.4MB)</td></tr><tr data-testcase-id="180432"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (4.33ms, 80MB)</td></tr><tr data-testcase-id="180433"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (5.83ms, 82.4MB)</td></tr><tr data-testcase-id="180434"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (4.77ms, 84MB)</td></tr><tr data-testcase-id="180435"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (9.86ms, 92MB)</td></tr><tr data-testcase-id="180436"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (9.97ms, 84.6MB)</td></tr><tr data-testcase-id="180437"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (15.79ms, 88MB)</td></tr><tr data-testcase-id="180438"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (14.38ms, 83.9MB)</td></tr><tr data-testcase-id="180439"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (39.79ms, 100MB)</td></tr><tr data-testcase-id="180440"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (42.11ms, 101MB)</td></tr><tr data-testcase-id="180441"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (10.09ms, 119MB)</td></tr><tr data-testcase-id="180442"><td valign="top" class="td-label">테스트 22 <span>〉</span></td><td class="result passed">통과 (140.77ms, 127MB)</td></tr><tr data-testcase-id="180443"><td valign="top" class="td-label">테스트 23 <span>〉</span></td><td class="result passed">통과 (79.77ms, 110MB)</td></tr><tr data-testcase-id="180444"><td valign="top" class="td-label">테스트 24 <span>〉</span></td><td class="result passed">통과 (84.57ms, 111MB)</td></tr><tr data-testcase-id="180445"><td valign="top" class="td-label">테스트 25 <span>〉</span></td><td class="result passed">통과 (84.72ms, 135MB)</td></tr><tr data-testcase-id="249447"><td valign="top" class="td-label">테스트 26 <span>〉</span></td><td class="result passed">통과 (3.60ms, 75.6MB)</td></tr><tr data-testcase-id="249448"><td valign="top" class="td-label">테스트 27 <span>〉</span></td><td class="result passed">통과 (4.86ms, 78.9MB)</td></tr><tr data-testcase-id="249449"><td valign="top" class="td-label">테스트 28 <span>〉</span></td><td class="result passed">통과 (3.56ms, 83MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/12904.%20%EA%B0%80%EC%9E%A5%20%EA%B8%B4%20%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC/ChaNyeok1225)
