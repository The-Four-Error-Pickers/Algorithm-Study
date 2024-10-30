## [프로그래머스 Lv3. 풍선 터트리기](https://school.programmers.co.kr/learn/courses/30/lessons/68646)

> 문제의 키워드

- 지역이 있고 각 지역들은 길로 이어져 있고, 길을 지나는데 걸리는 시간은 1이다. 또한 길은 양쪽에서 통행이 가능하다.
    - 즉, 그래프로 표현이 가능하고, 간선은 양방향이며 가중치는 1이다.
- 강철부대의 부대원들은 유일한 번호로 구분된다.
    - 즉, 각 부대원들이 중복된 지역에 존재할 경우는 없다는 것

- 각 강철부대원들이 강철부대에 복귀하는데 걸리는 최단시간을 구하기

<br/>

> 접근법 분석

- 우선 그래프이기 때문에 인접리스트로 그래프를 표현해야함
- 최단거리를 구하는데에 BFS, 다익스트라 둘다 가능함
  - BFS가 가능한 이유는 모든 가중치가 동일하기 때문임
- 부대원들로부터 최단거리 알고리즘을 돌리면 시간초과가 발생할 것
    - 어짜피 노드에서 노드까지의 최단거리는 특정 노드에서 다른 모든 노드까지의 최단거리이기도 하고, 최단거리 알고리즘으로 강철부대로부터 모든 부대원까지의 최단거리를 구하면 됨
    - 이것이 가능한 이유는 양방향 그래프이기 때문

<br/>

> 다익스트라 접근법

- 결국 다익스트라를 하기 위해서 인접리스트를 roads 배열을 통해 만들고 강철부대로 지정된 노드번호 부터 다익스트라를 수행하면 됨

<br/>

> 구현 접근법

- 그래프를 인접리스트로 표현하기 위해 ArrayList 배열을 사용하고, 각 노드별로 정점의 번호와 가중치를 저장하기 위해 Node 클래스를 사용한다.
- 다익스트라를 처리하기 위해 Node 클래스의 Comparable 인터페이스를 구현하고 비교기준을 가중치 기준으로 잡는다.
- 다익스트라의 최단거리를 갱신할 테이블인 cost 테이블은 절대 나올 수 없는 큰 가중치 값으로 미리 저장하고 갱신되지 않아 여전히 INF라면 도달할 수 없는 것으로 판단하고 answer 배열 구성시 -1로 채워줌

<br/>

> 시간복잡도

#### O(ELogE)

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int e;
        int w;
        public Node(int e, int w) {
            this.e = e;
            this.w = w;
        }
        
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static ArrayList<Node>[] g;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        int[] cost = new int[n+1];
        g = new ArrayList[n+1];
        for(int i = 1;i<n+1;i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0;i<roads.length;i++) {
            int s = roads[i][0];
            int e = roads[i][1];
            g[s].add(new Node(e, 1));
            g[e].add(new Node(s, 1));
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(cost, 987654321);
        cost[destination] = 0;
        pq.add(new Node(destination, cost[destination]));
        int cnt = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if (cost[now.e] < now.w) continue;
            if (++cnt == n) break;
            for(Node node: g[now.e]) {
                if (cost[node.e] > now.w + node.w) {
                    cost[node.e] = now.w + node.w;
                    pq.add(new Node(node.e, cost[node.e]));
                }
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i = 0;i<sources.length;i++) {
            answer[i] = (cost[sources[i]] == 987654321 ? -1 : cost[sources[i]]);
        }
        return answer;
    }
}
```

### 제출 결과

![제출결과](./result.png)

<br>

# 토론 접근법 풀이

<p></p>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/132266.%20%EB%B6%80%EB%8C%80%EB%B3%B5%EA%B7%80/HaeChang)
