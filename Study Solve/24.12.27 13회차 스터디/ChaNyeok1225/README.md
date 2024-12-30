## [프로그래머스 Lv3. 미로 탈출 명령어](https://school.programmers.co.kr/learn/courses/30/lessons/150365)

> 문제 키워드

-   (x, y)에서 출발해 (r, c)로 이동
-   이동하는 거리는 반드시 총 k 번
-   같은 격자를 방문해도 됨
-   탈출한 경로를 리턴, 문자열이 사전 순으로 가장 빠르게 탈출할 것
-   d(아래), l(왼), r(오), u(위) 순
-   탈출이 불가능하다면 "impossible" return

<br/>

> 접근법 분석

-   k번 이동으로 탈출 가능한지 판단하기 위해서 k - (x, y)와 (r, c) 가 짝수인지 확인 - 음수거나 짝수가 아니면 "impossible"
-   DFS를 통해 경로를 탐색 / d(아래), l(왼), r(오), u(위) - 각 탐색 격자마다 남은 거리 < 남은 이동횟수, 남은 이동 횟수 - (curX, curY)와 (r, c)가 짝수인지 확인을 통해 검정 및 백트래킹

<br/>

> 구현 접근법

-   impossible 검정
-   dfs를 경로의 우선순위로 하기위해 d, l, r, u 순으로 방향 배열 선언
-   dfs(x, y, k, "")를 통해 경로 탐색
-   각 탐색마다 k - 남은 거리가 음수 또는 짝수가 아닌지 확인하여 return
-   이동할 때마다 x, y 값의 변경 및 남은 이동 가능 거리 k 감소, 경로는 string에 덧 붙임
-   (x, y)와 (r, c)가 같을 때, 남은 이동거리 k 가 0이라면 정답
-   남은 경로 탐색을 멈추기 위해 종료 플래그 true

<br/>

> 시간 복잡도

#### O(4^K)

이동거리: K, 가지치기를 통한 최적화

<br/>

### 구현 코드

```cpp
#include <string>
using namespace std;

void Dfs(int curX, int curY, int k, string str);

int N, M, R, C, K;
int dr[] = {1, 0, 0, -1}, dc[] = {0, -1, 1, 0};
char dir[] = {'d', 'l', 'r', 'u'};
int map[50][50];

bool flag = false;
string answer = "";

string solution(int n, int m, int x, int y, int r, int c, int k) {

    N = n;
    M = m;
    x--; y--;
    R = r - 1;
    C = c - 1;
    K = k;

    int calc = abs(x - R) + abs(y - C);
    if(k < calc || ((k - calc) & 1) == 1)
        return "impossible";

    Dfs(x, y, k, "");

    return answer;
}

void Dfs(int curX, int curY, int k, string str) {
    if(flag || k < 0)
        return;
    if(curX < 0 || curX >= N || curY < 0 || curY >= M)
        return;

    if(curX == R && curY == C && k == 0) {
       answer = str;
       flag = true;
       return;
    }

    int calc = abs(curX - R) + abs(curY - C);
    if(k < calc)
        return;
    if(((k - calc) & 1) == 1)
        return;

    for(int d = 0; d < 4; d++) {
        Dfs(curX + dr[d], curY + dc[d], k - 1, str + dir[d]);
    }

}

```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="150047"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.06ms, 4.13MB)</td></tr><tr data-testcase-id="150048"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.07ms, 4.21MB)</td></tr><tr data-testcase-id="150049"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.01ms, 4.2MB)</td></tr><tr data-testcase-id="150050"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.01ms, 4.13MB)</td></tr><tr data-testcase-id="150051"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.01ms, 4.21MB)</td></tr><tr data-testcase-id="150052"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (0.01ms, 4.14MB)</td></tr><tr data-testcase-id="150053"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (0.01ms, 4.14MB)</td></tr><tr data-testcase-id="150054"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (0.01ms, 4.21MB)</td></tr><tr data-testcase-id="150055"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (4.87ms, 9.68MB)</td></tr><tr data-testcase-id="150056"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (5.28ms, 9.79MB)</td></tr><tr data-testcase-id="150057"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (5.10ms, 9.72MB)</td></tr><tr data-testcase-id="150058"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (4.71ms, 9.71MB)</td></tr><tr data-testcase-id="150059"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (5.19ms, 9.66MB)</td></tr><tr data-testcase-id="150060"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (5.01ms, 9.73MB)</td></tr><tr data-testcase-id="150061"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (5.34ms, 9.87MB)</td></tr><tr data-testcase-id="150062"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (5.22ms, 9.84MB)</td></tr><tr data-testcase-id="150063"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (5.06ms, 9.84MB)</td></tr><tr data-testcase-id="150064"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (5.05ms, 9.73MB)</td></tr><tr data-testcase-id="150065"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (5.39ms, 9.75MB)</td></tr><tr data-testcase-id="150066"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (5.42ms, 9.68MB)</td></tr><tr data-testcase-id="150067"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (5.12ms, 9.84MB)</td></tr><tr data-testcase-id="150068"><td valign="top" class="td-label">테스트 22 <span>〉</span></td><td class="result passed">통과 (4.69ms, 9.61MB)</td></tr><tr data-testcase-id="150069"><td valign="top" class="td-label">테스트 23 <span>〉</span></td><td class="result passed">통과 (5.10ms, 9.55MB)</td></tr><tr data-testcase-id="150070"><td valign="top" class="td-label">테스트 24 <span>〉</span></td><td class="result passed">통과 (5.07ms, 9.8MB)</td></tr><tr data-testcase-id="150071"><td valign="top" class="td-label">테스트 25 <span>〉</span></td><td class="result passed">통과 (5.15ms, 9.75MB)</td></tr><tr data-testcase-id="150072"><td valign="top" class="td-label">테스트 26 <span>〉</span></td><td class="result passed">통과 (4.82ms, 9.77MB)</td></tr><tr data-testcase-id="150073"><td valign="top" class="td-label">테스트 27 <span>〉</span></td><td class="result passed">통과 (5.15ms, 9.82MB)</td></tr><tr data-testcase-id="150074"><td valign="top" class="td-label">테스트 28 <span>〉</span></td><td class="result passed">통과 (6.37ms, 9.82MB)</td></tr><tr data-testcase-id="150075"><td valign="top" class="td-label">테스트 29 <span>〉</span></td><td class="result passed">통과 (5.21ms, 9.75MB)</td></tr><tr data-testcase-id="150076"><td valign="top" class="td-label">테스트 30 <span>〉</span></td><td class="result passed">통과 (5.20ms, 9.65MB)</td></tr><tr data-testcase-id="150077"><td valign="top" class="td-label">테스트 31 <span>〉</span></td><td class="result passed">통과 (0.01ms, 3.68MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 100.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/150365.%20%EB%AF%B8%EB%A1%9C%20%ED%83%88%EC%B6%9C%20%EB%AA%85%EB%A0%B9%EC%96%B4/ChaNyeok1225)
