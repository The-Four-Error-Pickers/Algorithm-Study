## [프로그래머스 Lv3. 모두 0으로 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/76503)

> 문제의 키워드

- 모든 가중치를 -1 or +1을 하여 0으로 만들어라
- 트리로 구성되어 있음 → 모든 노드들이 연결되어 있음

<br/>


> 접근법 분석
- 우선 각 노드의 가중치의 합이 0이 되지 않으면 불가능하다고 판단 → -1 반환
- 0일 경우에는, 모든 노드를 확인해봐야하는데 모든 노드들이 연결되어 있으므로
- 그냥 0번 노드를 루트 노드로 잡고 리프노드가 나올때까지 재귀를 탄 다음에 하나씩 올라오면서 가중치를 계산해줌
- 위의 접근법을 재귀함수를 통해 풀었는데, 하나의 테스트케이스에서 <strong> 런타임 에러 </strong> 가 뜸!
- 그래서 재귀함수를 스택으로 바꾸려고 했는데,, 후위순회 처리가 어려워서 위상정렬로 다시 접근..

> 알고리즘

#### 위상정렬, DFS(틀린 코드)


<br/>

> 시간복잡도
#### O(N)

<br/>

### 구현 코드(틀린 코드)

```java
class Solution {
    
    class Node {
        int vertex;
        Node next;
        
        Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
    
    static long answer = 0;
    static long[] value;
    static boolean[] visited;
    static Node[] adjList;
    
    public long solution(int[] a, int[][] edges) {
        value = new long[a.length];  // DFS를 진행할때 각 노드마다 연산을 진행한 횟수를 담는 배열
        visited = new boolean[a.length];    // DFS 진행시 방문체크를 위한 배열
        adjList = new Node[a.length];   // 트리 구성을 위한 연결리스트
        
        // 불가능한 경우 계산
        int tempSum = 0;
        for(int i = 0; i < a.length; i++) {
            value[i] = a[i];
            tempSum += a[i];
        }
        if(tempSum != 0) return -1;
        
        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }
        
        dfs(0);
        
        return answer;
    }
    
    public long dfs(int vertex) {
        visited[vertex] = true;
        
        for(Node temp = adjList[vertex]; temp != null; temp = temp.next) {
            if(visited[temp.vertex]) continue;
            value[vertex] += dfs(temp.vertex);
        }
        
        answer += Math.abs(value[vertex]);
        return value[vertex];
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/76503.%20%EB%AA%A8%EB%91%90%200%EC%9C%BC%EB%A1%9C%20%EB%A7%8C%EB%93%A4%EA%B8%B0/JunHo/2024-12-27T123451)
