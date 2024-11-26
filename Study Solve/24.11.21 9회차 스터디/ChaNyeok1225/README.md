## [프로그래머스 Lv3. 카운트 다운](https://school.programmers.co.kr/learn/courses/30/lessons/131129)

> 문제의 키워드

-   점수를 깎아 정확히 0점으로 만들기
-   0점보다 낮아지면 버스트 실격
-   1 ~ 20 싱글, 더블, 트리플 각각의 배수, 그리고 50점
-   먼저 0점, 같은 라운드면 싱글이나 불 많이 던지기

<br/>

> 접근법 분석

-   일반적인 BFS로 판단

<br/>

> 구현 접근법

-   현재 점수를 Queue에 넣고 가능한 점수를 체크해나가며 BFS 진행
-   방문 체크 배열을 통해 같은 점수는 방문하지 않도록

> 풀이 실패 및 해결 법

-   기존에는 일반적인 BFS를 사용하여 이후에 업데이트되는 더 좋은 값을 사용하지 못함
-   이를 막기위해 배열을 두고 해당 배열의 값을 통해 BFS를 진행하여 항상 최선의 값을 가질 수 있도록 수정함

<br/>

> 시간 복잡도

#### O(N)

점수: N

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] answer = new int[2];

        int[] count = new int[target];
        int[] number = new int[target];
        Arrays.fill(count, -1);
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {target, 0, 0});

        int num;
        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            if(cur[0] == 0) {
                return new int[] {cur[1], count[0]};
            }

            num = cur[0] - 50;
            if(num >= 0) {
                if(number[num] == cur[1] + 1)
                    count[num] = Math.max(count[num], cur[2] + 1);

                if(count[num] == -1) {
                    number[num] = cur[1] + 1;
                    count[num] = cur[2] + 1;
                    q.offer(new int[] {num, cur[1] + 1, count[num]});
                }
            }

            for(int i = 1; i < 4; i++) {
                for(int j = 1; j < 21; j++) {
                    num = cur[0] - (i * j);
                    if(num >= 0) {
                        if(number[num] == cur[1] + 1)
                            count[num] = Math.max(count[num], cur[2] + (i == 1 ? 1 : 0));

                        if(count[num] == -1) {
                            number[num] = cur[1] + 1;
                            count[num] = cur[2] + (i == 1 ? 1 : 0);
                            q.offer(new int[] {num, cur[1] + 1, count[num]});
                        }
                    }
                }
            }


        }


        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="161396"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.04ms, 86.6MB)</td></tr><tr data-testcase-id="161397"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (69.89ms, 94.8MB)</td></tr><tr data-testcase-id="161398"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (44.68ms, 91.9MB)</td></tr><tr data-testcase-id="161399"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (34.49ms, 86MB)</td></tr><tr data-testcase-id="161400"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (63.18ms, 80.3MB)</td></tr><tr data-testcase-id="161401"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (40.20ms, 88.6MB)</td></tr><tr data-testcase-id="161402"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (42.58ms, 78.6MB)</td></tr><tr data-testcase-id="161403"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (14.30ms, 78.9MB)</td></tr><tr data-testcase-id="161404"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (58.27ms, 77.3MB)</td></tr><tr data-testcase-id="161405"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (41.33ms, 76MB)</td></tr><tr data-testcase-id="161406"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (40.76ms, 82MB)</td></tr><tr data-testcase-id="161407"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (62.32ms, 81.4MB)</td></tr><tr data-testcase-id="161408"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (34.54ms, 84.8MB)</td></tr><tr data-testcase-id="161409"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (50.72ms, 79MB)</td></tr><tr data-testcase-id="161410"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (41.60ms, 79.3MB)</td></tr><tr data-testcase-id="161411"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (34.89ms, 73.8MB)</td></tr><tr data-testcase-id="161412"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (31.44ms, 78.7MB)</td></tr><tr data-testcase-id="161413"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (49.32ms, 93.7MB)</td></tr><tr data-testcase-id="161414"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (33.60ms, 81.6MB)</td></tr><tr data-testcase-id="161415"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (61.94ms, 83.1MB)</td></tr><tr data-testcase-id="161416"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (0.19ms, 82.5MB)</td></tr><tr data-testcase-id="161417"><td valign="top" class="td-label">테스트 22 <span>〉</span></td><td class="result passed">통과 (0.17ms, 68MB)</td></tr><tr data-testcase-id="161418"><td valign="top" class="td-label">테스트 23 <span>〉</span></td><td class="result passed">통과 (0.12ms, 78.4MB)</td></tr><tr data-testcase-id="161419"><td valign="top" class="td-label">테스트 24 <span>〉</span></td><td class="result passed">통과 (0.24ms, 76.8MB)</td></tr><tr data-testcase-id="161420"><td valign="top" class="td-label">테스트 25 <span>〉</span></td><td class="result passed">통과 (0.13ms, 73MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/131129.%20%EC%B9%B4%EC%9A%B4%ED%8A%B8%20%EB%8B%A4%EC%9A%B4/ChaNyeok1225)
