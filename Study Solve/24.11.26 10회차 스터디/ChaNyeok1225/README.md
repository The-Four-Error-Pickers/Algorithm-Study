## [프로그래머스 Lv3. 다단계 칫솔 판매](https://school.programmers.co.kr/learn/courses/30/lessons/77486)

> 문제 키워드

- 트리 구조
- 10%를 추천인에게 배분
- 각 판매원의 이득을 리턴

<br/>

> 접근법 분석

- 단순한 트리 탐색
- 부모에서 자식으로는 이동할 필요가 없기 때문에, 자식에서 부모로 올라가는 구조로 구성

<br/>

> 구현 접근법

- 트리의 각 노드는 총 이득 값과 부모 노드를 가리킴
  - 자신의 이름은 배열의 인덱스로써 관리하기 때문에 이름을 저장할 필요는 없음
- 각 판매에 대하여 부모로 올라가며 10%로 값을 낮춤
  - 값이 0이 된다면 멈추어 편향 트리일때의 시간 복잡도를 개선

<br/>

> 시간 복잡도

#### O(N + M)

판매원 배열의 길이: N
판매 배열의 길이: M

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        int m = seller.length;

        int[] answer = new int[n];
        HashMap<String, Integer> index = new HashMap<>();

        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) {
            index.put(enroll[i], i);
            nodes[i] = new Node();
        }

        String str;
        for(int i = 0; i < n; i++) {
            if("-".equals(referral[i]))
                continue;
            nodes[i].next = nodes[index.get(referral[i])];
        }

        Node cur;
        int nextAmount;
        for(int i = 0; i < m; i++) {
            cur = nodes[index.get(seller[i])];
            amount[i] *= 100;
            while(cur != null && amount[i] > 0) {
                nextAmount = amount[i] * 10 / 100;
                if(nextAmount > 0) {
                    cur.total += amount[i] - nextAmount;
                } else {
                    cur.total += amount[i];
                }
                amount[i] = nextAmount;
                cur = cur.next;
            }
        }

        for(int i = 0; i < n; i++)
            answer[i] = nodes[i].total;

        return answer;
    }
}

class Node {
    int total = 0;
    Node next;
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="64494"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.40ms, 77.2MB)</td></tr><tr data-testcase-id="64496"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.30ms, 70.7MB)</td></tr><tr data-testcase-id="64497"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.39ms, 84.5MB)</td></tr><tr data-testcase-id="64498"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.39ms, 70.3MB)</td></tr><tr data-testcase-id="64499"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (1.02ms, 99.1MB)</td></tr><tr data-testcase-id="64500"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (4.90ms, 110MB)</td></tr><tr data-testcase-id="64501"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (4.56ms, 111MB)</td></tr><tr data-testcase-id="64502"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (4.79ms, 103MB)</td></tr><tr data-testcase-id="64503"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (9.45ms, 116MB)</td></tr><tr data-testcase-id="64504"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (27.42ms, 136MB)</td></tr><tr data-testcase-id="94916"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (16.63ms, 118MB)</td></tr><tr data-testcase-id="94918"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (16.63ms, 150MB)</td></tr><tr data-testcase-id="94928"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (17.50ms, 124MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77486.%20%EB%8B%A4%EB%8B%A8%EA%B3%84%20%EC%B9%AB%EC%86%94%20%ED%8C%90%EB%A7%A4/ChaNyeok1225)
