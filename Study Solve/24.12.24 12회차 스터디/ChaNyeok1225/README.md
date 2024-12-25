## [프로그래머스 Lv3. 모두 0으로 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/76503)

> 문제 키워드

-   가중치가 부여된 트리
-   모든 점의 가중치를 0으로
-   연결된 두 점을 골라서 한 쪽은 증가, 한 쪽은 감소
-   가능하다면 최소 횟수 리턴, 불가능하다면 -1 리턴
-   항상 트리 형태. 즉, 사이클 없음

<br/>

> 접근법 분석

-   트리 정점 개수는 최대 300,000
    -   n^2 안 됨
-   가능한지 판별: 제로섬

-   최소 이동 횟수: 가장 가까운 연산부터 차례대로
-   각 리프 노드부터 루트로 올라오면서 만나면 되지 않을까?
    -   DFS 또는 위상정렬로 리프노드 판단 후 BFS로 구현

<br/>

> 구현 접근법

-   각 정점의 가중치 합을 통해 0이 아니라면 불가능, -1 리턴
-   진입 차수를 연산하며 트리 구성
-   구성된 트리를 통해 진입 차수가 1인 노드부터 BFS 실행
-   BFS를 실행하며 자신의 가중치를 다음 노드로 전달

<br/>

> 시간 복잡도

#### O(N)

정점의 개수: N

<br/>

### 구현 코드

```java
class Node {
    int index;
    Node next = null;

    Node(int index) {
        this.index = index;
    }
}

class Queue {
    private int size = 0;
    private Node head;
    private Node tail;

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    int poll() {
        if(size == 0)
            return -1;
        size--;
        int ret = head.index;
        head = head.next;
        return ret;
    }

    void offer(int idx) {
        size++;
        if(size == 1) {
            head = tail = new Node(idx);
            return;
        }
        tail.next = new Node(idx);
        tail = tail.next;
        return;
    }

}


class Solution {
    public long solution(int[] a, int[][] edges) {
        long answer = 0;
        int n = a.length;


        // 가능한지 판별
        long sum = 0;
        for(int i = 0; i < n; i++)
            sum += a[i];
        if(sum != 0)
            return -1;

        // 가능한 경우
        long[] w = new long[n];

        int[] indegree = new int[n];
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) {
            w[i] = a[i];
            nodes[i] = new Node(i);
        }

        int n1, n2;
        Node tmp;
        for(int i = 0; i < n - 1; i++) {
            n1 = edges[i][0];
            n2 = edges[i][1];
            indegree[n1]++;
            tmp = new Node(n2);
            tmp.next = nodes[n1].next;
            nodes[n1].next = tmp;

            indegree[n2]++;
            tmp = new Node(n1);
            tmp.next = nodes[n2].next;
            nodes[n2].next = tmp;
        }

        // 위상정렬 BFS
        Queue q = new Queue();
        for(int i = 0; i < n; i++) {
            if(indegree[i] != 1)
                continue;
            q.offer(i);
        }

        int cur, idx, sel = 0;
        while(!q.isEmpty()) {
            cur = q.poll();

            tmp = nodes[cur].next;
            while(tmp != null) {
                idx = tmp.index;

                indegree[idx]--;
                tmp = tmp.next;
                if(indegree[idx] < 1) {
                    continue;
                }
                else if(indegree[idx] >= 1) {
                    if(indegree[idx] == 1)
                        q.offer(idx);
                    answer += w[cur] < 0 ? -w[cur] : w[cur];
                    w[idx] += w[cur];
                    sel = idx;
                }
            }
        }
        answer += w[sel] < 0 ? -w[sel] : w[sel];

        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="90267"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.06ms, 85.7MB)</td></tr><tr data-testcase-id="90268"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.78ms, 73.1MB)</td></tr><tr data-testcase-id="90269"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (3.20ms, 147MB)</td></tr><tr data-testcase-id="90270"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (180.16ms, 180MB)</td></tr><tr data-testcase-id="90271"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (178.43ms, 168MB)</td></tr><tr data-testcase-id="90272"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (4.63ms, 165MB)</td></tr><tr data-testcase-id="90273"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (177.23ms, 166MB)</td></tr><tr data-testcase-id="90274"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (195.05ms, 166MB)</td></tr><tr data-testcase-id="90275"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (3.33ms, 149MB)</td></tr><tr data-testcase-id="90276"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (142.76ms, 177MB)</td></tr><tr data-testcase-id="90277"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (183.69ms, 165MB)</td></tr><tr data-testcase-id="90278"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (3.11ms, 150MB)</td></tr><tr data-testcase-id="90279"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (107.10ms, 178MB)</td></tr><tr data-testcase-id="90280"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (112.01ms, 171MB)</td></tr><tr data-testcase-id="90281"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (3.10ms, 159MB)</td></tr><tr data-testcase-id="90282"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (127.71ms, 176MB)</td></tr><tr data-testcase-id="90283"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (144.45ms, 180MB)</td></tr><tr data-testcase-id="90284"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (52.61ms, 178MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/76503.%20%EB%AA%A8%EB%91%90%200%EC%9C%BC%EB%A1%9C%20%EB%A7%8C%EB%93%A4%EA%B8%B0/ChaNyeok1225)
