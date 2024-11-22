## [프로그래머스 Lv3. 110 옮기기](https://school.programmers.co.kr/learn/courses/30/lessons/77886)

> 키워드 유추

- 문자열 중 "110" 추출 및 임의의 위치에 삽입
- 위의 행동을 통해 만들 수 있는 사전 순 가장 작은 문자

> 접근법
- s의 길이 1,000,000 -> O(NlogN)이지만
- 특수한 경우 O(N^2)도 가능
- 입력에서 뽑을 수 있는 모든 "110"을 추출
- 남은 문자열에서 마지막 0의 위치를 탐색 후 뒤에 뽑았던 "110"의 개수만큼 삽입 후 return

<br/>

> 시간 복잡도

#### O(N)

문자열의 길이: N

<br/>

### 구현 코드

```java
import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        
        /**
        x를 최대한 사전 순으로
        x에 있는 "110"을 뽑아서
        임의의 위치에 다시 삽입
        접근법
        110을 뽑을 수 있는대로 전부 추출
        남은 x에서 뽑은 110들을 삽입할 위치 탐색
        마지막 0의 위치 뒤에 삽입
        **/
        
        String[] res = new String[s.length];
        int p = 0;
        for(String idx : s) {
            res[p++] = process(idx);
        }
        return res;
    }
    
    private static String process(String idx) {
        String remainStr = extract110(idx);
        int cnt110 = (idx.length() - remainStr.length()) / 3;
        //삽입위치 탐색
        int zeroIdx = 0;
        for(int i=0; i<remainStr.length(); i++) {
            if(remainStr.charAt(i) == '0') {
                zeroIdx = i+1;
            }
        }
        
        StringBuilder res = new StringBuilder();
        for(int i=0; i<zeroIdx; i++) {
            res.append(remainStr.charAt(i));
        }
        for(int i=0; i<cnt110; i++) {
            res.append("110");
        }
        for(int i=zeroIdx; i<remainStr.length(); i++) {
            res.append(remainStr.charAt(i));
        }
        return res.toString();
    }
    
    private static String extract110(String idx) {
        Stack<Character> st = new Stack<>();
        
        for(int i=0; i<idx.length(); i++) {
            if(st.size() >= 2 && idx.charAt(i) == '0') {
                char mid = st.pop();
                char first = st.pop();
                if(first == '1' && mid == '1' && idx.charAt(i) == '0') continue;
                else {
                    st.push(first);
                    st.push(mid);
                    st.push(idx.charAt(i));
                }
            }
            else {
                st.push(idx.charAt(i));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(char x : st) {
            sb.append(x);
        }
        
        return sb.toString();
    }
    
}
```

> 제출 결과
![제출결과](./result.png)
> 

> 스터디 정리
- 이번 문제를 통해 그리디는 내가 생각한게 틀리면 어쩔 수 없구나 라는 생각을 할 수 있었음.
- 또한 모두가 풀지 못했던 문제로 다같이 토론함으로써 정답을 유추했던 문제로 재밌었음..
- 추가로 이번 문제를 구현하면서 굉장히 복잡하게 코드를 작성하고 있다는 느낌을 받았음.
- 생각한 것을 그대로 효율적으로 구현할 수 있는 실력 또한 중요하고, 리팩토링 과정을 통해서 클린코드 작성에 대해 학습할 예정


> 구현 알고리즘
<p> 그리디 </p>

> 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77886.%20110%20%EC%98%AE%EA%B8%B0%EA%B8%B0/Be-HinD(Ryo))
