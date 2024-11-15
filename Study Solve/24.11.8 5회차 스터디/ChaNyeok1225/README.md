## [프로그래머스 Lv4. 쿠키 구입](https://school.programmers.co.kr/learn/courses/30/lessons/49995)

> 문제의 키워드

- 1번부터 N번까지 차례로 번호가 붙은 바구개 N 개 (1 <= N <= 2000)
- 첫째 아들에게는 l 부터 m, 둘째 아들에게는 m + 1 부터 r
- 두 아들이 받는 쿠키의 개수가 동일할 것
- 줄 수 있는 가장 많은 쿠키의 개수

<br/>

> 접근법 분석

- N은 최대 2,000 이므로 N²의 풀이가 가능
- 두 아들에게 줄 쿠키의 누적합 필요
- m 을 기준으로 l과 r 포인터를 생성해 누적 합을 하며 탐색 `투 포인터`

<br/>

> 투 포인터 접근법

- 주어지는 쿠키 배열을 0 부터 n - 1 까지 탐색
- 각 인덱스마다 l = i, r = i + 1을 통해 탐색 시작
- l 과 r의 위치에 있는 값들을 누적합
- l 누적합이 작다면 l을 왼쪽으로 진행, r 누적합이 작다면 r을 오른쪽으로 진행

<br/>

> 시간복잡도

#### O(N²)

쿠키 배열의 길이: N

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;

        int l, r, ltotal, rtotal;
        for(int m = 0; m < n - 1; m++) {
            l = m;
            r = m + 1;
            ltotal = cookie[m];
            rtotal = cookie[m+1];

            while(true) {
                if(ltotal == rtotal) {
                    answer = answer > ltotal ? answer : ltotal;
                }

                if(ltotal <= rtotal) {
                    l--;
                    if(l < 0)
                        break;
                    ltotal += cookie[l];
                } else {
                    r++;
                    if(r >= n)
                        break;
                    rtotal += cookie[r];
                }
            }
        }


        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="27723"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.03ms, 73.8MB)</td></tr><tr data-testcase-id="27724"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.05ms, 72.3MB)</td></tr><tr data-testcase-id="27725"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.05ms, 78.5MB)</td></tr><tr data-testcase-id="27726"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.07ms, 67MB)</td></tr><tr data-testcase-id="27727"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.10ms, 66.7MB)</td></tr><tr data-testcase-id="27728"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (2.87ms, 85.4MB)</td></tr><tr data-testcase-id="27729"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (2.64ms, 76.6MB)</td></tr><tr data-testcase-id="27730"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (3.58ms, 79.6MB)</td></tr><tr data-testcase-id="27731"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (11.57ms, 82.7MB)</td></tr><tr data-testcase-id="27732"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (0.03ms, 82.5MB)</td></tr><tr data-testcase-id="27733"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (0.03ms, 74.5MB)</td></tr><tr data-testcase-id="27734"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (0.03ms, 74.3MB)</td></tr><tr data-testcase-id="27735"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (0.02ms, 80.3MB)</td></tr><tr data-testcase-id="27736"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (0.24ms, 65.5MB)</td></tr><tr data-testcase-id="27737"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (0.21ms, 72.7MB)</td></tr><tr data-testcase-id="27738"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (0.21ms, 74.3MB)</td></tr><tr data-testcase-id="27739"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (0.19ms, 71.8MB)</td></tr><tr data-testcase-id="27740"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (0.24ms, 72.5MB)</td></tr><tr data-testcase-id="27741"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (0.16ms, 73.5MB)</td></tr><tr data-testcase-id="27742"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (0.18ms, 71.5MB)</td></tr><tr data-testcase-id="27743"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (0.23ms, 66.3MB)</td></tr><tr data-testcase-id="27744"><td valign="top" class="td-label">테스트 22 <span>〉</span></td><td class="result passed">통과 (0.11ms, 78MB)</td></tr><tr data-testcase-id="27745"><td valign="top" class="td-label">테스트 23 <span>〉</span></td><td class="result passed">통과 (6.73ms, 66.8MB)</td></tr><tr data-testcase-id="27746"><td valign="top" class="td-label">테스트 24 <span>〉</span></td><td class="result passed">통과 (0.17ms, 79MB)</td></tr><tr data-testcase-id="27747"><td valign="top" class="td-label">테스트 25 <span>〉</span></td><td class="result passed">통과 (0.24ms, 69.5MB)</td></tr><tr data-testcase-id="27748"><td valign="top" class="td-label">테스트 26 <span>〉</span></td><td class="result passed">통과 (7.01ms, 83.9MB)</td></tr></tbody></table><div class="console-message">효율성  테스트</div><table class="console-test-group" data-category="effectiveness"><tbody><tr data-testcase-id="27749"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (9.07ms, 52.5MB)</td></tr><tr data-testcase-id="27750"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (9.86ms, 52.1MB)</td></tr><tr data-testcase-id="27751"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (8.45ms, 52.2MB)</td></tr><tr data-testcase-id="27752"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (9.24ms, 60.8MB)</td></tr><tr data-testcase-id="27753"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (10.62ms, 52.8MB)</td></tr><tr data-testcase-id="27754"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (17.08ms, 52.7MB)</td></tr><tr data-testcase-id="27755"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (16.54ms, 52.2MB)</td></tr><tr data-testcase-id="27756"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (11.91ms, 52.2MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 66.7</div><div class="console-message">효율성: 33.3</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/49995.%20%EC%BF%A0%ED%82%A4%20%EA%B5%AC%EC%9E%85/ChaNyeok1225)
