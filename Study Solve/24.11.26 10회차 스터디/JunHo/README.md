## [프로그래머스 Lv3. 다단계 칫솔 판매](https://school.programmers.co.kr/learn/courses/30/lessons/77486)

> 문제의 키워드

- 판매원은 90%를 자기 수익으로 가지고 나머지 10%는 부모노드의 수익
- 부모노드의 수익은 10원 미만이 될때까지 계속 부모를 타고 가야함

<br/>


> 접근법 분석
- 처음에는 연결리스트를 사용하려고 했으나 Map으로도 충분히 가능할 것 같아서(노드에 포함되는 변수가 1개) Map으로 구현
- 부모노드를 저장하는 Map과 인덱스 번호를 저장하는 Map을 사용하여 부모노드를 타고 올라가면서 이익금 계산
- 구현 코드에 주석된 부분은 절삭을 잘못하여 틀린 코드


> 알고리즘

#### 구현, 자료구조


<br/>

> 시간복잡도
#### O(NlogN)

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] profit = new int[enroll.length];
        
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> personIdx = new HashMap<>();
        
        for (int i = 0; i < enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
            personIdx.put(enroll[i], i);
        }
        
        for (int i = 0; i < seller.length; i++) {
            String currentSeller = seller[i];
            int currentPrice = amount[i] * 100;
            
            while (!currentSeller.equals("-")) {
                int parentPrice = currentPrice / 10;   // 부모에게 줄 금액
                // int sellerProfit = currentPrice * 0.9; ← 틀린 코드
                int sellerProfit = currentPrice - parentPrice;
                
                profit[personIdx.get(currentSeller)] += sellerProfit;
                currentSeller = parent.get(currentSeller);
                
                if (parentPrice == 0) break; 
                currentPrice = parentPrice;
            }
        }
        
        return profit;
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77486.%20%EB%8B%A4%EB%8B%A8%EA%B3%84%20%EC%B9%AB%EC%86%94%20%ED%8C%90%EB%A7%A4/JunHo/2024-11-26T195946)
