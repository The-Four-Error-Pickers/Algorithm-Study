## [프로그래머스 Lv3. 최고의 집합](https://school.programmers.co.kr/learn/courses/30/lessons/12938)

> 문제의 키워드

- 자연수 n개로 이루어진 <strong>중복 집합</strong>
- 집합 내 원소의 합이 S 이면서 곱이 최대가 되는 집합
- 제한 사항
    - <strong>오름차순으로 정렬</strong>된 1차원 배열로 return
    - 조건을 만족하는 답이 없다면 -1 return


<br/>

> 접근법 분석

- 합이 일정할때 곱이 최대가 되어야 함
    - 집합 내 원소가 균등하게 분배되어야한다
- `그리디 알고리즘`으로 풀이

<br/>

> 그리디 접근법

- 집합 내의 원소가 가장 균등하게 분배되기 위해서 합 S를 원소의 개수 N으로 나눈다.
- 나누어 나온 몫이 집합의 원소들이 되고 배열의 뒤에서부터 나머지의 개수만큼의 원소들를 +1 해준다.


<br/>

> 구현 접근법

- -1 리턴되는 상황 예외처리
    - S를 N으로 나눈 몫이 0이 나올 때
- 구현 과정
    - 정답 배열을 몫으로 가득 채워나가며 현재의 인덱스가 나머지의 값에 도달하면 그때부터 +1 시킨 값으로 채워 나가기

<br/>

> 시간복잡도

#### BFS : O(N)

원소의 개수: N

<br/>

### 구현 코드

```java
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {-1};
        
        int d = s / n;
        int e = s % n;
        
        if(d != 0) {
            answer = new int[n];
            
            for(int i = 0; i < n; i++) {
                answer[i] = d + (i > n - e - 1? 1 : 0);
            }
            
        }
        
        return answer;
    }
}
```

### 제출 결과

<pre class="console-content"><div class="console-message">정확성  테스트</div><table class="console-test-group" data-category="correctness"><tbody><tr data-testcase-id="18243"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.12ms, 82.9MB)</td></tr><tr data-testcase-id="18244"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.14ms, 76.8MB)</td></tr><tr data-testcase-id="18245"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.12ms, 75.5MB)</td></tr><tr data-testcase-id="18246"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.10ms, 77.3MB)</td></tr><tr data-testcase-id="18247"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.04ms, 76.1MB)</td></tr><tr data-testcase-id="18248"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (0.10ms, 84.4MB)</td></tr><tr data-testcase-id="18249"><td valign="top" class="td-label">테스트 7 <span>〉</span></td><td class="result passed">통과 (0.03ms, 76.2MB)</td></tr><tr data-testcase-id="18250"><td valign="top" class="td-label">테스트 8 <span>〉</span></td><td class="result passed">통과 (0.05ms, 73.8MB)</td></tr><tr data-testcase-id="18251"><td valign="top" class="td-label">테스트 9 <span>〉</span></td><td class="result passed">통과 (0.07ms, 77.1MB)</td></tr><tr data-testcase-id="18252"><td valign="top" class="td-label">테스트 10 <span>〉</span></td><td class="result passed">통과 (0.12ms, 76.8MB)</td></tr><tr data-testcase-id="18253"><td valign="top" class="td-label">테스트 11 <span>〉</span></td><td class="result passed">통과 (0.20ms, 74MB)</td></tr><tr data-testcase-id="18254"><td valign="top" class="td-label">테스트 12 <span>〉</span></td><td class="result passed">통과 (0.08ms, 76.1MB)</td></tr><tr data-testcase-id="18255"><td valign="top" class="td-label">테스트 13 <span>〉</span></td><td class="result passed">통과 (0.23ms, 82.4MB)</td></tr><tr data-testcase-id="18256"><td valign="top" class="td-label">테스트 14 <span>〉</span></td><td class="result passed">통과 (0.02ms, 94MB)</td></tr></tbody></table><div class="console-message">효율성  테스트</div><table class="console-test-group" data-category="effectiveness"><tbody><tr data-testcase-id="18257"><td valign="top" class="td-label">테스트 1 <span>〉</span></td><td class="result passed">통과 (0.21ms, 52.6MB)</td></tr><tr data-testcase-id="18258"><td valign="top" class="td-label">테스트 2 <span>〉</span></td><td class="result passed">통과 (0.23ms, 53.1MB)</td></tr><tr data-testcase-id="18259"><td valign="top" class="td-label">테스트 3 <span>〉</span></td><td class="result passed">통과 (0.15ms, 53.1MB)</td></tr><tr data-testcase-id="18260"><td valign="top" class="td-label">테스트 4 <span>〉</span></td><td class="result passed">통과 (0.26ms, 53.2MB)</td></tr><tr data-testcase-id="18261"><td valign="top" class="td-label">테스트 5 <span>〉</span></td><td class="result passed">통과 (0.36ms, 53.5MB)</td></tr><tr data-testcase-id="18262"><td valign="top" class="td-label">테스트 6 <span>〉</span></td><td class="result passed">통과 (0.02ms, 51.9MB)</td></tr></tbody></table><div class="console-heading">채점 결과</div><div class="console-message">정확성: 70.0</div><div class="console-message">효율성: 30.0</div><div class="console-message">합계: 100.0 / 100.0</div></pre>

<br>


#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/12938.%20%EC%B5%9C%EA%B3%A0%EC%9D%98%20%EC%A7%91%ED%95%A9/ChaNyeok1225/2024-11-1T19123)
