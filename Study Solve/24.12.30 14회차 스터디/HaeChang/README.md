## [프로그래머스 Lv3. 표 편집](https://school.programmers.co.kr/learn/courses/30/lessons/81303?language=java)

> 문제의 키워드

- 단, 한 번에 한 행만 선택할 수 있으며, 표의 범위(0행 ~ 마지막 행)를 벗어날 수 없습니다.
- 다음과 같은 명령어를 이용하여 표를 편집합니다.
    - "U X": 현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
    - "D X": 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
    - "C" : 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다.
    - "Z" : 가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
- 처음에 선택된 행의 위치를 나타내는 정수 k
- 모든 명령어를 수행한 후 표의 상태와 처음 주어진 표의 상태를 비교하여 삭제되지 않은 행은 O, 삭제된 행은 X로 표시하여 문자열 형태로 return 하도록

- X는 1 이상 300,000 이하인 자연수이며 0으로 시작하지 않습니다.
- cmd에 등장하는 모든 X들의 값을 합친 결과가 1,000,000 이하인 경우만 입력으로 주어집니다.
- 표의 모든 행을 제거하여, 행이 하나도 남지 않는 경우는 입력으로 주어지지 않습니다.
- 표의 범위를 벗어나는 이동은 입력으로 주어지지 않습니다.
- 원래대로 복구할 행이 없을 때(즉, 삭제된 행이 없을 때) "Z"가 명령어로 주어지는 경우는 없습니다.

<br/>

> 접근법 분석

- 삭제가 되었을 때, 삭제노드의 다음노드가 삭제된 노드로 땡겨지는 것, 이것은 다른말로 하자면 삭제 이전노드가 삭제 다음노드를 next로 가지는것이다.
    - 즉, 양방향 연결리스트로 표현할 수 있다.

- 원복의 경우, 가장 나중에 삭제된 노드가 먼저 복구되기 때문에 `LIFO` 자료구조를 사용할 수 있다.
    - 단, 삭제된 노드의 당시 이전노드가 누구였는지, 다음 노드가 누구였는지는 함께 저장할 필요가 있다.

<br/>

> 구현 접근법

양방향 연결리스트를 `Node`클래스를 통해 구성한다.

삭제 노드에 대한 관리는 `LIFO` 자료구조인 `Stack`을 사용하여 구현한다

단, 삭제 당시의 삭제노드의 `prev`와 `next`가 누구인지에 대한 정보를 함께 저장한다.

<br/>

> 시간복잡도

#### O(SUM(X)): U와 D 명령어에서 수행되는 이동 칸 수의 합, MAX는 100만

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    static class Node {
        Node prev;
        Node next;
        int idx;
    }
    static Node[] nodes;
    static Stack<int[]> remove;
    static char[] result;
    public String solution(int n, int k, String[] cmd) {
        remove = new Stack<>(); 
        nodes = new Node[n];
        nodes[0] = new Node();
        nodes[0].idx = 0;
        nodes[n - 1] = new Node();
        nodes[n - 1].idx = n - 1;
        for(int i = 1;i < n; i++) {
            // 연결리스트 노드 초기화
            nodes[i] = new Node();
            nodes[i].idx = i;
            nodes[i - 1].next = nodes[i];
            nodes[i].prev = nodes[i - 1];
        }
        result = new char[n];
        Arrays.fill(result, 'O');
        Node cursor = nodes[k]; // 현재의 커서
        int cnt;
        for(String c: cmd) {
            if (c.startsWith("U")) {
                cnt = Integer.parseInt(c.split(" ")[1]);
                while(cnt-- > 0) {
                    cursor = cursor.prev;
                }
            } else if (c.startsWith("D")) {
                cnt = Integer.parseInt(c.split(" ")[1]);
                while(cnt-- > 0) {
                    cursor = cursor.next;
                }
            } else if (c.startsWith("C")) {
                Node prev = cursor.prev;
                Node next = cursor.next;
                result[cursor.idx] = 'X';
                remove.push(new int[]{prev == null ? -1 : prev.idx, cursor.idx, next == null ? -1 : next.idx});
                if (prev != null) {
                    // 첫번째 노드의 경우 prev이 null인것 조심
                    prev.next = next;    
                }
                if (next != null) {
                    // 마지막 노드의 경우 next가 null 인것 조심
                    next.prev = prev;    
                }
                
                cursor = cursor.next == null ? cursor.prev : cursor.next;
                
            } else {
                int[] info = remove.pop();
                Node removed = nodes[info[1]];
                result[info[1]] = 'O';
                if (info[0] != -1) {
                    nodes[info[0]].next = removed;    
                }
                if (info[2] != -1) {
                    removed.next = nodes[info[2]];    
                }
                if (info[2] != -1) {
                    nodes[info[2]].prev = removed;    
                }
                if (info[0] != -1) {
                    removed.prev = nodes[info[0]];    
                }
            }
        }
        
        StringBuilder ans = new StringBuilder();
        for(char r: result) ans.append(r);
        return ans.toString();
    }
}
```

### 제출 결과

<img src="./result.png"/>


#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/81303.%20%ED%91%9C%20%ED%8E%B8%EC%A7%91/HaeChang/2024-12-30T151233)
