## [프로그래머스 Lv3. 110 옮기기](https://school.programmers.co.kr/learn/courses/30/lessons/77886)

> 문제의 키워드

- 문자열 중 "110"을 뽑아서 임의의 위치에 삽입
- 위의 행동을 통해 만들 수 있는 사전 순 가장 작은 문자

<br/>

> 접근법 분석

- s의 길이 1,000,000
  - N 또는 NlogN 풀이 가능
  - 그리디로 판단
- "110"의 경우, 두 개를 사용했을 때, "110110"이 가장 최적의 방법
- 모든 "110"을 제거한 후 "110"보다 우선순위가 낮은 곳을 찾아 해당 위치에 모든 "110"을 삽입

<br/>

> 구현 접근법

- 선형탐색을 하며 현재 인덱스에서 자신의 앞을 탐색가능한 스택 자료구조나 해당 방식을 사용
- 배열을 통해 문자를 담아나가며 배열의 현재 담긴 문자가 [ ..., '1', '1', '0']로 이루어 진다면 '1', '1', '0'을 제거하고 제거 카운트 증가 후 선형탐색 지속
- 모든 "110"을 제거하고 남은 문자 배열에서 "110" 보다 사전 순으로 낮은 곳을 찾아 "110"을 제거 카운트만큼 삽입

<br/>

> 시간 복잡도

#### O(N)

문자열의 길이: N

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        StringBuilder sb;

        int index, cnt, insert;
        char[] chars, arr = new char[1_000_001];
        for(int t = 0; t < s.length; t++) {
            index = cnt = insert = 0;
            chars = s[t].toCharArray();

            for(int i = 0; i < chars.length; i++) {
                arr[index] = chars[i];
                if(index > 1) {
                    if(arr[index - 2] == '1' && arr[index-1] == '1' && arr[index] == '0') {
                        cnt++;
                        index -= 3;
                    }
                }
                index++;
            }


            for(int i = index - 1; i >= 0; i--) {
                if(arr[i] == '0') {
                    insert = i + 1;
                    break;
                }
            }

            sb = new StringBuilder();
            for(int i = 0; i < insert; i++) {
                sb.append(arr[i]);
            }
            for(int i = 0; i < cnt; i++) {
                sb.append("110");
            }
            for(int i = insert; i < index; i++) {
                sb.append(arr[i]);
            }

            answer[t] = sb.toString();

        }


        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="89312"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (40.19ms, 108MB)</td></tr><tr data-testcase-id="89313"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (31.64ms, 109MB)</td></tr><tr data-testcase-id="89314"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (38.61ms, 102MB)</td></tr><tr data-testcase-id="89315"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (39.26ms, 110MB)</td></tr><tr data-testcase-id="89316"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (37.92ms, 108MB)</td></tr><tr data-testcase-id="89317"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (30.11ms, 94.3MB)</td></tr><tr data-testcase-id="89318"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (23.99ms, 97.9MB)</td></tr><tr data-testcase-id="89319"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (23.29ms, 93.5MB)</td></tr><tr data-testcase-id="89320"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (46.22ms, 143MB)</td></tr><tr data-testcase-id="89321"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (55.58ms, 136MB)</td></tr><tr data-testcase-id="89322"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (33.83ms, 133MB)</td></tr><tr data-testcase-id="89323"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (37.08ms, 127MB)</td></tr><tr data-testcase-id="89324"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (39.69ms, 127MB)</td></tr><tr data-testcase-id="89325"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (34.34ms, 126MB)</td></tr><tr data-testcase-id="89326"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (38.94ms, 115MB)</td></tr><tr data-testcase-id="89327"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (35.00ms, 131MB)</td></tr><tr data-testcase-id="89328"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (47.76ms, 115MB)</td></tr><tr data-testcase-id="89329"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (42.73ms, 89.5MB)</td></tr><tr data-testcase-id="89330"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (56.16ms, 102MB)</td></tr><tr data-testcase-id="89331"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (28.02ms, 96.4MB)</td></tr><tr data-testcase-id="89332"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (32.14ms, 110MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77886.%20110%20%EC%98%AE%EA%B8%B0%EA%B8%B0/ChaNyeok1225)
