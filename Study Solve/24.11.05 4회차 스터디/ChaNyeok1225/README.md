## [프로그래머스 Lv3. 등대](https://school.programmers.co.kr/learn/courses/30/lessons/133500)

> 문제의 키워드

-   N개의 정점, N-1의 간선 존재
    -   사이클이 없는 트리 구조
-   적어도 양쪽 끝(인접한 두 개)의 등대 중 하나는 켜져 있어야 함
-   켜야하는 등대의 개수는 최소

<br/>

> 접근법 분석

-   가장 최소로 등대를 켜야하기 때문에 각 정점으로의 진입 차수를 계산하여 진입 차수가 가장 큰 정점부터 켜나가면 최소가 될 것이라 판단
    -   `트리, 위상 정렬` 문제로 판단

<br/>

> 위상 정렬 접근법

-   트리를 연결할때 In-Degree 배열을 통해 해당 정점으로의 진입 차수를 계산
-   계산이 끝난 배열에서 가장 높은 수를 가지는 정점부터 불을 켜고, 연결된 정점들의 진입 차수를 감소시킨다
-   이후 위의 과정을 모든 정점의 진입차수가 0이 될때까지 반복

<br/>

> 구현 접근법

-   In-Degree 계산
-   반복문을 돌며 모든 In-Degree가 0인지 확인
-   0이라면 종료,
-   0이 아니라면 최대 값을 가지는 정점에 불을 켜고 반복

<br/>

> 시간복잡도

#### BFS : O(V)

정점의 개수: V

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        int[] degree = new int[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1];
        for(int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] edge : lighthouse) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int max, select;
        while(true) {

            max = 0;
            select = 0;

            for(int i = 1; i < n + 1; i++) {
                if(max < degree[i]) {
                    max = degree[i];
                    select = i;
                }
            }

            if(select == 0)
                break;

            degree[select] = 0;

            for(int conn : graph[select])
                degree[conn]--;
            answer++;
        }

        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="149908"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result failed">실패 (4464.79ms, 118MB)</td></tr><tr data-testcase-id="149909"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (4115.97ms, 110MB)</td></tr><tr data-testcase-id="149910"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result failed">실패 (4473.37ms, 124MB)</td></tr><tr data-testcase-id="149911"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (1773.05ms, 125MB)</td></tr><tr data-testcase-id="149912"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (2687.60ms, 122MB)</td></tr><tr data-testcase-id="149913"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (1024.28ms, 121MB)</td></tr><tr data-testcase-id="149914"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (143.55ms, 109MB)</td></tr><tr data-testcase-id="149915"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result failed">실패 (5262.40ms, 116MB)</td></tr><tr data-testcase-id="149916"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result failed">실패 (6507.16ms, 119MB)</td></tr><tr data-testcase-id="149917"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result failed">실패 (6393.55ms, 116MB)</td></tr><tr data-testcase-id="149918"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result failed">실패 (1788.01ms, 103MB)</td></tr><tr data-testcase-id="149919"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result failed">실패 (455.83ms, 91.3MB)</td></tr><tr data-testcase-id="149920"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result failed">실패 (59.64ms, 102MB)</td></tr><tr data-testcase-id="149921"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (0.04ms, 74.3MB)</td></tr><tr data-testcase-id="149922"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result failed">실패 (5.45ms, 79.5MB)</td></tr><tr data-testcase-id="149923"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result failed">실패 (26.92ms, 89.6MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 37.5</div><div class="console-message">합계: 37.5 / 100.0</div></pre>

<br>

### 실패 이유

<p>위 처럼 푼다면 연결되어 있는 등대 중 인접한 하나의 등대만 불이 켜져있는 경우에 만족함.</p>
<p>즉, 1 - 2 - 3 - 4의 구조에서 1, 4가 켜진 등대일 경우, 2와 3 중에서 하나의 등대를 더 켜야한다는 것을 인지하지 못함.  </p>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/133500.%20%EB%93%B1%EB%8C%80/ChaNyeok1225)
