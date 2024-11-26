## [프로그래머스 Lv3. 다단계 칫솔 판매](https://school.programmers.co.kr/learn/courses/30/lessons/77486)

> 문제의 키워드

- 민호는 center이며
- 모든 판매원은 칫솔의 판매에 의하여 발생하는 이익에서 10% 를 계산하여 자신을 조직에 참여시킨 추천인에게 배분하고 나머지는 자신이 가집니다.
- 자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10% 까지 자신에 이익이 됩니다. 자신에게 발생하는 이익 또한 마찬가지의 규칙으로 자신의 추천인에게 분배됩니다. 단, 10% 를 계산할 때에는 원 단위에서 절사하며, 10%를 계산한 금액이 1 원 미만인 경우에는 이득을 분배하지 않고 자신이 모두 가집니다.
- 판매원에게 배분된 이익금의 총합을 계산하여(정수형으로), 입력으로 주어진 enroll에 이름이 포함된 순서에 따라 나열

<br/>

> 접근법 분석

- 단순 구현문제니까 부모노드를 타고 올라가면서 이익금을 계산해주면 된다고 생각한다.
- 그래서 어떤노드의 부모가 누군지만 잘 기록해주면 된다.
- 절삭만 조심해서 구현하자

<br/>

> 구현 접근법

`HashMap`을 사용해서 부모노드가 누군지 미리 저장해두고, 판매원에 해당하는 누적금액을 계산할 배열을 만든뒤

판매원노드로 부터 부모노드를 타고 올라가면서 구현한다.

<br/>

> 시간복잡도

#### O(100_000 * 약 5): 개수에 대해서 모두 판매자가 되어 최대치에 amount에 * 100 의 경우

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    static HashMap<String, Integer> result;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        result = new HashMap<>();
        HashMap<String, String> parent = new HashMap<>();
        for(int i = 0;i<enroll.length;i++) {
            if (referral[i].equals("-")) {
                parent.put(enroll[i], null);
            } else {
                parent.put(enroll[i], referral[i]);
            }
        }
        
        for(int i = 0;i<seller.length;i++) {
            int a = amount[i] * 100;
            String s = seller[i];
            while(true) {
                int gain = a < 10 ? a : (a - (int)(a * 0.1));
                result.put(s, result.getOrDefault(s, 0) + gain);
                if (a < 10 || parent.get(s) == null) break;
                a = (int)(a * 0.1);
                s = parent.get(s);
            }
        }
        
        
        answer = new int[enroll.length];
        for(int i = 0;i<enroll.length;i++) {
            answer[i] = result.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
    
}
```

### 제출 결과

<img src="./result.png"></img>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77486.%20%EB%8B%A4%EB%8B%A8%EA%B3%84%20%EC%B9%AB%EC%86%94%20%ED%8C%90%EB%A7%A4/HaeChang)
