## [프로그래머스 Lv3. 스타 수열](https://school.programmers.co.kr/learn/courses/30/lessons/70130)

> 문제 키워드

-   부분 수열: 순서를 유지한 채, n개의 원소를 제거하여 얻을 수 있는 수열
-   스타 수열
    -   x의 길이는 2 이상의 짝수
    -   길이가 2n일때 순서대로 2개씩 묶었을 때, 모두 같은 하나 이상의 같은 원소를 포함한다.
    -   단, 집합 내의 원소는 서로 다르다
-   가장 긴 스타 수열의 길이를 return

<br/>

> 접근법 분석
-   주어지는 수열의 길이 a는 500,000
    -   완전 탐색이 불가능
-   그리디 알고리즘
    -   LIS 방식을 적용하여 하나의 원소 X를 교집합으로 하는 집합을 개수를 계속해서 저장
    -   [5, 2, 3, 3, 5, 3] 에서 [5, 2]를 2와 5에 카운트와 인덱스를 저장하는 방식

<br/>

> 구현 접근법

-   인덱스를 교집합의 숫자로 사용하는 배열 count와 index 선언
    -   count: 해당 숫자를 교집합으로 사용하였을 때, 만들 수 있는 수열의 집합들
    -   index: 해당 숫자를 교집합으로 사용하였을 때, 마지막으로 삽입한 집합의 인덱스 위치
-   반복문을 돌며 count와 index 배열을 업데이트

<br/>

> 시간 복잡도

#### O(M*N)

격자의 크기: M*N

<br/>

### 구현 코드

```java
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int len = a.length;
        
        int[] count = new int[len]; 
        int[] index = new int[len];
        for(int i = 0; i < len; i++)
            index[i] = -1;
        
        int num1, num2, idx;
        for(int i = 0; i < len - 1; i++) {
            num1 = a[i];
            num2 = a[i+1];
            
            if(num1 == num2)
                continue;
            
            if(index[num1] < i) {
                count[num1]++;
                index[num1] = i + 1;
            }
            if(index[num2] < i) {
                count[num2]++;
                index[num2] = i + 1;
            }
            
        }
        
        for(int i = 0; i < len; i++) {
            answer = answer > count[i] ? answer : count[i];
        }
        
        return answer * 2;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="84795"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.02ms, 79.3MB)</td></tr><tr data-testcase-id="84796"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.03ms, 73.2MB)</td></tr><tr data-testcase-id="84797"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.02ms, 82.6MB)</td></tr><tr data-testcase-id="84798"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.03ms, 88.9MB)</td></tr><tr data-testcase-id="84799"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.02ms, 77.8MB)</td></tr><tr data-testcase-id="84800"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (0.02ms, 79.2MB)</td></tr><tr data-testcase-id="84801"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (0.02ms, 73.2MB)</td></tr><tr data-testcase-id="84802"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (0.03ms, 82.8MB)</td></tr><tr data-testcase-id="84803"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (0.02ms, 86.2MB)</td></tr><tr data-testcase-id="84804"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (0.03ms, 84.9MB)</td></tr><tr data-testcase-id="84805"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (0.04ms, 73.5MB)</td></tr><tr data-testcase-id="84806"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (0.03ms, 80.3MB)</td></tr><tr data-testcase-id="84807"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (22.66ms, 101MB)</td></tr><tr data-testcase-id="84808"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (18.57ms, 119MB)</td></tr><tr data-testcase-id="84809"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (21.56ms, 120MB)</td></tr><tr data-testcase-id="84810"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (21.15ms, 126MB)</td></tr><tr data-testcase-id="84811"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (9.18ms, 101MB)</td></tr><tr data-testcase-id="84812"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (6.25ms, 78.8MB)</td></tr><tr data-testcase-id="84813"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (9.62ms, 87.9MB)</td></tr><tr data-testcase-id="84814"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (50.57ms, 117MB)</td></tr><tr data-testcase-id="84815"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (18.05ms, 136MB)</td></tr><tr data-testcase-id="84816"><td valign="top" class="td-label">테스트 22 <span>〉</span></td><td class="result passed">통과 (18.00ms, 115MB)</td></tr><tr data-testcase-id="84817"><td valign="top" class="td-label">테스트 23 <span>〉</span></td><td class="result passed">통과 (22.94ms, 115MB)</td></tr><tr data-testcase-id="84818"><td valign="top" class="td-label">테스트 24 <span>〉</span></td><td class="result passed">통과 (14.65ms, 122MB)</td></tr><tr data-testcase-id="84819"><td valign="top" class="td-label">테스트 25 <span>〉</span></td><td class="result passed">통과 (20.96ms, 114MB)</td></tr><tr data-testcase-id="84820"><td valign="top" class="td-label">테스트 26 <span>〉</span></td><td class="result passed">통과 (21.20ms, 97.5MB)</td></tr><tr data-testcase-id="84821"><td valign="top" class="td-label">테스트 27 <span>〉</span></td><td class="result passed">통과 (10.10ms, 100MB)</td></tr><tr data-testcase-id="85841"><td valign="top" class="td-label">테스트 28 <span>〉</span></td><td class="result passed">통과 (0.04ms, 73MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/70130.%20%EC%8A%A4%ED%83%80%20%EC%88%98%EC%97%B4/ChaNyeok1225)
