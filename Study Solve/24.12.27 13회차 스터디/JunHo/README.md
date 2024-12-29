## [프로그래머스 Lv3. 미로 탈출 명령어](https://school.programmers.co.kr/learn/courses/30/lessons/150365)

> 문제의 키워드

- 출발지부터 도착지까지 거리가 k여야 함
- 출발지, 도착지 격자를 포함한 모든 격자를 두 번 이상 방문해도 됨
- 문자열이 사전 순으로 가장 빠른 경로로 탈출해야함

<br/>


> 접근법 분석
- 우선 출발지 → 도착지까지 거리가 k보다 크다면 불가능, k - 도착지 - 출발지 = 홀수인 경우도 불가능
- 불가능한 경우를 제외하고 DFS를 진행할 건데, 여기서 k가 2500이기 때문에 최대 시간복잡도는 4^2500이므로 가지치기를 해줘야함.
1. 문제의 키워드에 맞게 사전 순으로 했을 때 가장 빠른 경로로 탈출해야되기 때문에 d, l, r, u 순서로 탐색
2. 임시 격자까지 왔을때, 지금까지 온 거리 + 거리(도착지 - 임시격자) > k인 경우 탐색 중지
3. answer을 사전순으로 가장 마지막 단어인 z로 해놓고 매 탐색마다 answer과 현재까지의 경로를 비교하여 탐색 중지

> 알고리즘

#### 백트래킹


<br/>

> 시간복잡도
#### O(4^K)

<br/>

### 구현 코드

```java
class Solution {
    
    static int[] dx = {1, 0, 0, -1};  // d, l, r, u
    static int[] dy = {0, -1, 1, 0};
    static String[] s = {"d", "l", "r", "u"};
    static String answer = "z";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        int distance = getDistance(x - 1, y - 1, r - 1, c - 1);
        if((distance > k) || ((k - distance) & 1) == 1) return "impossible";
        
        dfs(0, "", n, m, x - 1, y - 1, r - 1, c - 1, k);
        return answer;
    }
    
    // distance : 현재까지 거리, route : 현재까지의 경로
    public void dfs(int distance, String route, int n, int m, int tempX, int tempY, int endX, int endY, int k) {        
        if((distance + getDistance(tempX, tempY, endX, endY)) > k) return;
        if(answer.compareTo(route) < 0) return;
        if((distance == k) && (tempX == endX) && (tempY == endY)) {
            answer = route;
            return;
        }
        
        for(int d = 0; d < 4; d++) {
            int currentX = tempX + dx[d], currentY = tempY + dy[d];
            if((currentX < 0) || (currentX > n - 1) || (currentY < 0) || (currentY > m - 1)) continue;
            dfs(distance + 1, route + s[d], n, m, currentX, currentY, endX, endY, k);
        }
    }
    
    public int getDistance(int tempX, int tempY, int endX, int endY) {
        return Math.abs(endX - tempX) + Math.abs(endY - tempY);
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/150365.%20%EB%AF%B8%EB%A1%9C%20%ED%83%88%EC%B6%9C%20%EB%AA%85%EB%A0%B9%EC%96%B4/JunHo/2024-12-30T73239)
