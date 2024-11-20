## [프로그래머스 Lv3. 등대](https://school.programmers.co.kr/learn/courses/30/lessons/133500)

> 문제의 키워드

- 등대는 n개이고 오가는 뱃길은 n-1개
- 사이클이 없다는 뜻이고 리프노드와 연결된 친구를 타고타고~ 불을 켜주면 됨

<br/>
<br/>

> 접근법 분석
- 처음에는 차수를 계산해서 차수와 1인 노드와 연결된 노드마다 불을 켜줄 생각을 하였음.
- 위의 경우 그럼 켜진 노드에 대한 방문은 어떻게 할까? 라는 고민이 있었는데, <strong>사실 그럴 필요가 없었다..</strong>
- 방문한 경우 차수자체를 0으로 바꿔버린다면 굳이 방문 체크를 안해도 되고 이 경우만 확인 해주면 됨
- 그리고 불을 켠 등대과 연결되어 있는 등대들의 차수를 감소시키고 이 과정을 계속 반복


<br/>

<br/>

> 알고리즘

#### 위상정렬, 그래프 탐색(BFS, DFS)


<br/>

> 시간복잡도

#### O(N)

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {

    class Node {
        int vertex; // 연결된 정점 번호 저장
        Node next;  // 연결리스트 유지를 위한 변수

        Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        Node[] adjList = new Node[n + 1];   // 연결정보를 저장하기 위한 배열
        int[] degree = new int[n + 1];  // 각 번호마다 차수 저장
        for (int i = 0; i < lighthouse.length; i++) {
            int from = lighthouse[i][0];
            int to = lighthouse[i][1];

            // 차수 증가 및 연결정보 저장
            degree[from]++;
            degree[to]++;
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        // 리프 노드랑 연결된 노드를 그때마다 갱신
        Set<Integer> set = new HashSet<>();

        while (true) {
            // 리프 노드를 찾기
            for (int i = 1; i <= n; i++) {
                if (degree[i] == 1) {    // 차수가 1이면 리프노드
                    for (Node temp = adjList[i]; temp != null; temp = temp.next) {
                        if (degree[temp.vertex] > 0) set.add(temp.vertex);   // 차수가 0 이상인 연결된 노드 추가
                    }
                }
            }

            // 더 이상 불을 킬게 없는 경우
            if (set.isEmpty()) break;

            // 불을 켜는 작업
            for (int vertex : set) {
                if (degree[vertex] <= 0) continue; // 이미 처리된 노드는 무시
                degree[vertex] = 0; // 불을 켬
                answer++;

                // 연결된 노드 차수 감소
                for (Node temp = adjList[vertex]; temp != null; temp = temp.next) {
                    degree[temp.vertex]--;
                }
            }
            
            // set을 비우고 다시 갱신하기
            set.clear();
        }

        return answer;
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/133500.%20%EB%93%B1%EB%8C%80/JunHo/2024-11-7T9853)
