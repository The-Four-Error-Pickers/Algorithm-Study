## [프로그래머스 Lv3. 등대](https://school.programmers.co.kr/learn/courses/30/lessons/133500)

> 문제의 키워드

- 등대와 등대 사이를 오가는 뱃길은 n개
- 어느 등대에서 출발해도 모든 등대까지 이동가능
- 하나의 뱃길 양쪽 끝 등대 중 적어도 하나는 켜져 있도록 등대를 켜두어야 함
- 위 조건을 만족하는 등대 개수의 최솟값

<br/>

> 접근법 분석

- 초기 접근법으로는 각 노드별로 차수를 계산해서 차수의 내림차순 정렬 후 누적합이 전체 노드개수 이상이 되는 순간이 정답이 된다고 생각함
    - 이는 "양쪽 끝 등대 중 적어도 하나는 켜져 있도록" 을 항상 만족하지 못한다.
    - 왜냐하면 노드번호를 신경안쓰기 때문이다.

- 두번째 접근으로 하나의 간선으로 이어진 두 노드들 중 하나는 무조건 켜야 함에 집중한다.
    - 만약 노드가 1 -> 2 -> 3 으로 되어있을 때 1에 등대를 켜는것은 비효율적이다.
    - 2를 켜야지 효율적이고, 이는 곧 `리프노드로 진입하기 전 노드를 켜야함` 이 증명이 된다.
    - 리프노드를 찾아서 진입 전 노드들을 전부 켜고, 그러고도 조건에 안맞는 노드가 발생하면 불을 켜준다.
    

<br/>

> 깊이우선탐색 접근법

- 리프노드를 찾아야한다.
    - 리프노드란 결국 더이상 뻗어갈 길이 없는 노드를 뜻하므로 드릴링에 유리한 `깊이우선탐색`을 사용한다.
    - 리프노드를 발견하면 이전노드에 불을 켜주고, `되돌아 가면서 자신이 지나갔었던 노드가 켜지지 않았다면 본인 노드는 켜야한다.`

<br/>

> 구현 접근법

- 인접리스트를 사용해서 그래프를 구성하고 깊이 우선 탐색을 시전한다.
    - 깊이우선탐색에서 방문 체크를 해야하고, 지금 방문한 노드와 해당 노드를 방문하기 전 노드를 `한 상태로 관리`해야한다.
    - 불켜진 노드에 대한 관리를 `boolean[]` 로 관리한다.
- 깊이우선 탐색 시 현재 방문한 노드에서, 인접한 노드중 미방문 노드가 없는 경우 리프노드로 간주한다.
    - 리프노드의 경우 이전노드를 켜준다.
    - 되돌아 가면서, 자신이 나아갔었던 노드가 켜지지 않았다면 현재 노드를 켜주어야 한다.

<br/>

> 시간복잡도

#### O(2n)

<br/>

### 구현 코드

```java
class Solution {
    static ArrayList<Integer>[] g;
    static boolean[] isLight;
    static boolean[] v;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        v = new boolean[n + 1];
        isLight = new boolean[n + 1];
        g = new ArrayList[n + 1];
        for(int i = 1;i<n+1;i++) g[i] = new ArrayList<>();
        for(int[] house: lighthouse) {
            g[house[0]].add(house[1]);
            g[house[1]].add(house[0]);
        }
        
        dfs(1, -1); // 첫번째 노드는 아무거나 상관없고, 첫 노드를 방문한 이전노드는 없으므로 -1
        
        for(int i = 1;i<isLight.length;i++) {
            answer += (isLight[i] ? 1 : 0);
        }
        
        return answer;
    }
    static void dfs(int node, int prev) {
        
        boolean isLeaf = true;
        
        for(int next: g[node]) {
            if (v[next]) continue;
            if (next == prev) continue;
            isLeaf = false;
            v[next] = true;
            dfs(next, node);
            v[next] = false;
            if (!isLight[next]) {
                // 되돌아 가면서 뻗어나갔었던 노드가 꺼져있다면 현재 노드를 켜야함
                isLight[node] = true;
            }
        }
        
        if(isLeaf) {
            // 리프노드에 대한 처리
            isLight[prev] = true;
        }
    }
}
```

### 제출 결과

![제출결과](./result.png)

<br>

# 토론 접근법 풀이

<p>토론에서 리프노드가 핵심 키워드 였고, 하나의 간선으로 이어진 두 노드 사이에 리프노드의 반대노드가 반드시 불을 켜야함을 증명하는데 시간이 걸렸다.</p>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/12938.%20%EC%B5%9C%EA%B3%A0%EC%9D%98%20%EC%A7%91%ED%95%A9/HaeChang)
