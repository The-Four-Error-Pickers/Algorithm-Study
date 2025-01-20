## [프로그래머스 Lv3. 부대 복귀](https://school.programmers.co.kr/learn/courses/30/lessons/132266)

> 문제의 키워드

- 지역은 1 부터 N까지 유일한 번호로 구분
- 길을 통과하는데 <strong>걸리는 시간은 1로 동일</strong>
- <strong>최단 시간</strong>으로 복귀
- 두 지역을 <strong>왕복할 수 있는 길 정보</strong>를 담은 2차원 정수 배열

<br/>

> 접근법 분석

- 정점의 개수 N 최대 100,000개
  - 인접 리스트로 구현
- 간선의 개수 최대 500,000
  - 희소 그래프
- 최단 경로를 구하는 문제이기 때문에 관성적으로 다익스트라를 사용하려고 할 수 있지만 다익스트라는 간선의 가중치가 다를 때 적용하는 알고리즘으로 이 문제는 BFS로 풀리는 간단한 문제

<br/>

> BFS 접근법

- 각 부대원이 위치한 sources 위치에서 도착지인 destination 까지 거리를 구하는 것이 아닌 destination에서 시작하는 BFS를 실행하여 BFS의 depth를 측정하면 문제에서 요구하는 거리를 쉽게 구할 수 있다.

<br/>

> 구현 접근법

- BFS
  - 일반적인 BFS 알고리즘으로 구현

<br/>

> 시간복잡도

#### BFS : O(V + E)

정점의 개수 V, 간선의 개수 E

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int len = sources.length;
        int[] answer = new int[len];

        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : roads) {
            graph[edge[1]].add(edge[0]);
            graph[edge[0]].add(edge[1]);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        int[] cost = new int[n+1];
        Arrays.fill(cost, -1);

        q.offer(destination);
        cost[destination] = 0;

        int cur, size, step = 0;
        while(!q.isEmpty()) {

            size = q.size();
            step++;
            for(int i = 0; i < size; i++) {
                cur = q.poll();
                for(int nxt : graph[cur]) {
                    if(cost[nxt] != -1) continue;
                    cost[nxt] = step;
                    q.offer(nxt);
                }
            }
        }

        for(int i = 0; i < len; i++) {
            answer[i] = cost[sources[i]];
        }

        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="147384"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.14ms, 73.2MB)</td></tr><tr data-testcase-id="147385"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.10ms, 73.8MB)</td></tr><tr data-testcase-id="147386"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.15ms, 77.1MB)</td></tr><tr data-testcase-id="147387"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.10ms, 78.9MB)</td></tr><tr data-testcase-id="147388"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.09ms, 72.6MB)</td></tr><tr data-testcase-id="147389"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (19.56ms, 96.3MB)</td></tr><tr data-testcase-id="147390"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (22.07ms, 96.1MB)</td></tr><tr data-testcase-id="147391"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (25.37ms, 119MB)</td></tr><tr data-testcase-id="147392"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (14.45ms, 94.8MB)</td></tr><tr data-testcase-id="147393"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (18.74ms, 101MB)</td></tr><tr data-testcase-id="147394"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (174.52ms, 176MB)</td></tr><tr data-testcase-id="147395"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (182.32ms, 173MB)</td></tr><tr data-testcase-id="147396"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (144.62ms, 175MB)</td></tr><tr data-testcase-id="147397"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (178.87ms, 174MB)</td></tr><tr data-testcase-id="147398"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (151.57ms, 168MB)</td></tr><tr data-testcase-id="148228"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (69.13ms, 117MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

# 토론 접근법 풀이

<p>간단한 문제로 다익스트라에 대해 복습해보는 시간을 가졌음.</p>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/132266.%20%EB%B6%80%EB%8C%80%EB%B3%B5%EA%B7%80/ChaNyeok1225/2024-10-30T212649)
