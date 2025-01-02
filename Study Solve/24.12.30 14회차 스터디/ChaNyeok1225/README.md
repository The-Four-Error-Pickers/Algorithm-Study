## [프로그래머스 Lv3. 표 편집](https://school.programmers.co.kr/learn/courses/30/lessons/81303)

> 문제 키워드

-   표의 선택, 삭제, 복구 명령어 구현
-   복구 명령어를 여러 번 가능

<br/>

> 접근법 분석

-   더블 링크드 리스트 또는 배열로 구현
-   [n][2] [0] : 이전 [1] : 다음
-   삭제된 노드는 스택에 삽입하고 복구에 활용

<br/>

> 구현 접근법

-   [n][2]의 int 배열을 사용해서 연결리스트를 구현
-   삭제된 내역을 저장하기 위한 스택 선언
-   boolean 배열을 통한 O, X 판별별

<br/>

> 시간 복잡도

#### O(N)

명령어의 개수: N

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        int[][] nodes = new int[n+1][2];
        boolean[] deleteFlag = new boolean[n];
        for(int i = 0; i < n; i++) {
            nodes[i][0] = i - 1;
            nodes[i][1] = i + 1;
        }
        nodes[n-1][1] = -1;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int cursor = k;
        int ins = 0, prev, next, z;
        for(String cm : cmd) {
            String[] s = cm.split(" ");
            switch(s[0]) {

            case "U" :
                    ins = Integer.parseInt(s[1]);
                    for(int i = 0; i < ins; i++) {
                        if(nodes[cursor][0] == -1)
                            break;
                        cursor = nodes[cursor][0];
                    }
                    break;

            case "D" :
                    ins = Integer.parseInt(s[1]);
                    for(int i = 0; i < ins; i++) {
                        if(nodes[cursor][1] == -1)
                            break;
                        cursor = nodes[cursor][1];
                    }
                    break;

            case "C" :
                    stack.offerLast(cursor);
                    deleteFlag[cursor] = true;

                    prev = nodes[cursor][0];
                    next = nodes[cursor][1];
                    if(prev != -1) {
                        nodes[prev][1] = next;
                        cursor = prev;
                    }
                    if(next != -1) {
                        nodes[next][0] = prev;
                        cursor = next;
                    }
                    break;

            case "Z" :
                    z = stack.pollLast();
                    deleteFlag[z] = false;

                    prev = nodes[z][0];
                    next = nodes[z][1];

                    if(prev != -1) {
                        nodes[prev][1] = z;
                    }
                    if(next != -1) {
                        nodes[next][0] = z;
                    }
                    break;
            }
        }


        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(deleteFlag[i])
                sb.append('X');
            else
                sb.append('O');
        }


        return sb.toString();
    }
}

```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="92247"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.13ms, 76.6MB)</td></tr><tr data-testcase-id="92248"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.22ms, 87.7MB)</td></tr><tr data-testcase-id="92249"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.21ms, 83.7MB)</td></tr><tr data-testcase-id="92250"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.19ms, 80.2MB)</td></tr><tr data-testcase-id="92251"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.37ms, 72.6MB)</td></tr><tr data-testcase-id="92252"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (0.30ms, 94.8MB)</td></tr><tr data-testcase-id="92253"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (0.30ms, 80.6MB)</td></tr><tr data-testcase-id="92254"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (0.47ms, 80.8MB)</td></tr><tr data-testcase-id="92255"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (0.32ms, 88.9MB)</td></tr><tr data-testcase-id="92256"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (0.30ms, 74.3MB)</td></tr><tr data-testcase-id="92257"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (1.67ms, 73.4MB)</td></tr><tr data-testcase-id="92258"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (1.44ms, 81.7MB)</td></tr><tr data-testcase-id="92259"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (4.82ms, 73.1MB)</td></tr><tr data-testcase-id="92260"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (2.94ms, 78.2MB)</td></tr><tr data-testcase-id="92261"><td valign="top" class="td-label">테스트 15 <span>〉</span></td><td class="result passed">통과 (2.97ms, 84.3MB)</td></tr><tr data-testcase-id="92262"><td valign="top" class="td-label">테스트 16 <span>〉</span></td><td class="result passed">통과 (2.00ms, 77.2MB)</td></tr><tr data-testcase-id="92263"><td valign="top" class="td-label">테스트 17 <span>〉</span></td><td class="result passed">통과 (4.12ms, 88.1MB)</td></tr><tr data-testcase-id="92264"><td valign="top" class="td-label">테스트 18 <span>〉</span></td><td class="result passed">통과 (7.91ms, 71.9MB)</td></tr><tr data-testcase-id="92265"><td valign="top" class="td-label">테스트 19 <span>〉</span></td><td class="result passed">통과 (5.81ms, 93MB)</td></tr><tr data-testcase-id="92266"><td valign="top" class="td-label">테스트 20 <span>〉</span></td><td class="result passed">통과 (2.77ms, 75.8MB)</td></tr><tr data-testcase-id="92267"><td valign="top" class="td-label">테스트 21 <span>〉</span></td><td class="result passed">통과 (4.05ms, 74MB)</td></tr><tr data-testcase-id="92268"><td valign="top" class="td-label">테스트 22 <span>〉</span></td><td class="result passed">통과 (3.20ms, 87.8MB)</td></tr><tr data-testcase-id="92269"><td valign="top" class="td-label">테스트 23 <span>〉</span></td><td class="result passed">통과 (0.19ms, 74.3MB)</td></tr><tr data-testcase-id="92270"><td valign="top" class="td-label">테스트 24 <span>〉</span></td><td class="result passed">통과 (0.21ms, 88.8MB)</td></tr><tr data-testcase-id="93930"><td valign="top" class="td-label">테스트 25 <span>〉</span></td><td class="result passed">통과 (0.18ms, 84.2MB)</td></tr><tr data-testcase-id="93931"><td valign="top" class="td-label">테스트 26 <span>〉</span></td><td class="result passed">통과 (0.20ms, 73.2MB)</td></tr><tr data-testcase-id="93932"><td valign="top" class="td-label">테스트 27 <span>〉</span></td><td class="result passed">통과 (0.23ms, 74.9MB)</td></tr><tr data-testcase-id="93933"><td valign="top" class="td-label">테스트 28 <span>〉</span></td><td class="result passed">통과 (0.31ms, 77.5MB)</td></tr><tr data-testcase-id="93934"><td valign="top" class="td-label">테스트 29 <span>〉</span></td><td class="result passed">통과 (0.21ms, 84MB)</td></tr><tr data-testcase-id="93935"><td valign="top" class="td-label">테스트 30 <span>〉</span></td><td class="result passed">통과 (0.16ms, 69.2MB)</td></tr></tbody></table><div class="console-message">효율성  테스트</div><table class="console-test-group" data-category="effectiveness"><tbody><tr data-testcase-id="92700"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (231.13ms, 132MB)</td></tr><tr data-testcase-id="92701"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (267.45ms, 130MB)</td></tr><tr data-testcase-id="92702"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (213.54ms, 127MB)</td></tr><tr data-testcase-id="92703"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (303.48ms, 149MB)</td></tr><tr data-testcase-id="92704"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (315.51ms, 146MB)</td></tr><tr data-testcase-id="92705"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (253.29ms, 144MB)</td></tr><tr data-testcase-id="93927"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (146.78ms, 106MB)</td></tr><tr data-testcase-id="93928"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (206.75ms, 106MB)</td></tr><tr data-testcase-id="93929"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (348.88ms, 145MB)</td></tr><tr data-testcase-id="93936"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (267.47ms, 151MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 30.0</div><div class="console-message">효율성: 70.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/81303.%20%ED%91%9C%20%ED%8E%B8%EC%A7%91/ChaNyeok1225)
