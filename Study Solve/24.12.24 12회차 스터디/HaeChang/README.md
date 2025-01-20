## [프로그래머스 Lv3. 모두 0으로 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/76503)

> 문제의 키워드

- 중치가 부여된 트리가 주어집니다.
- 다음 연산을 통하여, 이 트리의 모든 점들의 가중치를 0으로 만들고자 합니다.
    - 임의의 연결된 두 점을 골라서 한쪽은 1 증가시키고, 다른 한쪽은 1 감소시킵니다.
- 모든 점들의 가중치를 0으로 만들 수 있는 것은 아닙니다. 
- 만약 가능하다면 최소한의 행동을 통하여 모든 점들의 가중치를 0으로 만들고자 합니다.
- 주어진 행동을 통해 트리의 모든 점들의 가중치를 0으로 만드는 것이 불가능하다면 -1을, 가능하다면 최소 몇 번만에 가능한지를 찾아 return
<br/>

> 접근법 분석

- 각 노드들에서 중간 노드들은 이어진 다른 노드들로부터 어떻게 연산을 할지 굉장히 경우의수가 많다.
- 그렇다면 리프노드들로 부터 차근차근히 0으로 만들고 가중치를 앞으로 땡겨가다가 마지막까지 모두가 0이 안되었다면 -1을 반환하면 되지 않을까?
- 왜냐하면 그렇게 연산한 횟수가 최소한 필요한 연산이니까

<br/>

> 구현 접근법

리프노드의 특징은 양방향 그래프에서 진입 차수가 1인 노드들을 의미한다.

그러면 계속해서 리프노드는 0의 가중치를 가질꺼고, 그렇게 해당 노드들을 제외하고...

리프들로부터 깎아 들어가는것은 위상정렬이다.

위상정렬을 시행하면서 각 리프노드들을 가중치 0으로 만들고 

해당 가중치를 0으로 만들기 위해 사용한 양 혹은 음의 값을 연결된 노드에 더한다.

그렇게 이어가다가 결국 위상정렬에 끝에 도달할 것이고, 가중치 배열을 순회하면서 검사한다.

<br/>

> 시간복잡도

#### O(N): 모든 노드에 대해서 위상정렬 수행하면 됨

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    static ArrayList<Integer>[] g;
    public long solution(int[] a, int[][] edges) {
        long answer = 0;

        // 그래프 형성
        int[] indegree = new int[300_001];
        g = new ArrayList[300_001];
        for(int i = 0;i < 300_001;i ++) g[i] = new ArrayList<>();
        for(int i = 0;i < edges.length; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            g[v1].add(v2);
            g[v2].add(v1);
            indegree[v1]++;
            indegree[v2]++;
        }
        // 위상정렬 시행
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < 300_001;i ++) {
            if (indegree[i] == 1 && a[i] != 0) queue.add(i);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int node = queue.poll();
                for(int next: g[node]) {
                    if (indegree[next] == 1) continue;
                    indegree[next]--;
                    int cha = Math.abs(0 + a[node]);
                    if (a[node] < 0) {
                        // 현재 리프노드가 음수라면 연결된 노드엔 양수를 더한다.
                        a[next] -= cha;
                        answer += cha;
                    } else if (a[node] > 0) {
                        // 그 반대 시행
                        a[next] += cha;
                        // 어느걸 시행하더라도 시행은 한번당 +1, -1 이므로 누적
                        answer += cha;
                    } 
                    a[node] = 0;
                    if (indegree[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }
        for(int i = 0;i < a.length;i++) {
            // 마지막 순회하면서 검사
            if (a[i] != 0) return -1;
        }
        return answer;
    }
}
```

### 제출 결과

<img src="./wrong_answer.png"/>

### 왜 틀렸는지?

각 리프로 부터 가운데 노드사이가 짝수냐 홀수냐에 따라 앞으로 넘겨진 두 가중치가 마지막에 합쳐지지 않을 수도 있다.

특히 위의 코드로 1번 테스트케이스를 돌리고 마지막에 a배열을 출력해 보면 3번 노드와 0번 노드가 위상정렬 이라서 처리되지 못한 부분이 있다.

이를 처리하기 위해서 마지막에 자신과 인접한 노드에 합쳐서 0이 되는 노드가 있으면 연산을 수행한것으로 치고 answer 를 누적한다.

### 100점 풀이

```java
import java.util.*;

class Solution {
    static ArrayList<Integer>[] g;
    public long solution(int[] a, int[][] edges) {
        
        long answer = 0;
        long[] arr = new long[a.length];
        for(int i = 0; i < a.length; i++) arr[i] = a[i];
        int[] indegree = new int[300_001];
        g = new ArrayList[300_001];
        for(int i = 0;i < 300_001; i++) g[i] = new ArrayList<>();
        for(int i = 0;i < edges.length; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            g[v1].add(v2);
            g[v2].add(v1);
            indegree[v1]++;
            indegree[v2]++;
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < 300_001; i++) {
            if (indegree[i] == 1) queue.add(i);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int node = queue.poll();
                for(int next: g[node]) {
                    if (indegree[next] == 1) continue;
                    indegree[next]--;
                    long cha = Math.abs(0 + arr[node]);
                    if (arr[node] < 0) {
                        arr[next] -= cha;
                        answer += cha;
                    } else if (arr[node] > 0) {
                        arr[next] += cha;
                        answer += cha;
                    } 
                    
                    arr[node] = 0;
                    if (indegree[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }
        // 마지막에 서로 인접 해 있는 부분 처리
        for(int i = 0;i < arr.length;i++) {
            if (arr[i] != 0) {
                boolean find = false;
                for(int node: g[i]) {
                    if (arr[i] + arr[node] == 0) {
                        answer += Math.abs(arr[node]);
                        arr[i] = 0;
                        arr[node] = 0;
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    return -1;
                }
            }
        }
        return answer;
    }
}
```

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/76503.%20%EB%AA%A8%EB%91%90%200%EC%9C%BC%EB%A1%9C%20%EB%A7%8C%EB%93%A4%EA%B8%B0/HaeChang/2024-12-24T144148)
