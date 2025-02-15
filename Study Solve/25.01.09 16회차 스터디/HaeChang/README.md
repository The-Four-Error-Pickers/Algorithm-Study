## [프로그래머스 Lv3. 스타 수열](https://school.programmers.co.kr/learn/courses/30/lessons/70130)

> 문제의 키워드

- 어떤 수열 x의 부분 수열(Subsequence)이란, x의 몇몇 원소들을 제거하거나 그러지 않고 남은 원소들이 원래 순서를 유지하여 얻을 수 있는 새로운 수열
- 다음과 같은 조건을 모두 만족하는 수열 x를 스타 수열이라고 정의
- x의 길이가 2 이상의 짝수입니다. (빈 수열은 허용되지 않습니다.)
- x의 길이를 2n이라 할 때, 다음과 같은 n개의 집합 `{x[0], x[1]}, {x[2], x[3]}, ..., {x[2n-2], x[2n-1]}` 의 교집합의 원소의 개수가 1 이상
- `x[0] != x[1], x[2] != x[3], ..., x[2n-2] != x[2n-1]` 입니다.
-  a의 모든 부분 수열 중에서 가장 길이가 긴 스타 수열의 길이를 return
- a의 길이는 1 이상 500,000 이하입니다.
- a의 모든 수는 0 이상 (a의 길이) 미만입니다.
<br/>

> 접근법 분석

여기에는 두가지 솔루션이 있다.

- sol1: 스타수열 내에 n개의 집합들의 교집합 원소로서 가능성이 있는 숫자들을 전처리하고, 해당 교집합 원소를 기준으로 만들 수 있는 모든 스타수열을 찾는 방법
    - 미리 a 수열 내에 어떤 원소들이 분포해 있는지 찾아야 한다. -> 교집합용 원소 찾기
    - 그런 다음, 해당 숫자를 a 수열내에서 찾아서 만들수 있는 최대 집합의 개수를 찾는다.
        - 그럼 즉, 해당 숫자를 교집합 원소로 두고 있는 스타 수열의 길이를 구할 수 있다.

- sol2 (ChaNyeok 풀이 참조): 길이에 집중했을 때 최대한 길게 스타수열을 만들 수 있어야 한다는것은, 곧바로 연속적인 a 배열내에 숫자 `a[i], a[i + 1]` 이거나, `a[i - X], a[i]` 이거나 `a[i + 1 - X], a[i + 1]` 쌍들로 만들 수 있음
    - 여기서 `a[i]` 를 기준으로 i번째 원소까지의 최장 스타수열 길이가 계산이 가능하다.
    - 위의 sol1 풀이와는 다른점은 굳이 교집합 원소를 기준으로 모든 a 수열내에 계산을 곧바로 진행해야 하는가? 이다.
    - 어짜피 a 수열을 순회하면서 교집합용 기준원소가 몇번 등장하는지가 중요하고, 해당 교집합 기준 원소를 가지고 현재 인덱스까지 최장길이를 얼마나 만들 수 잇는지 체크하면 된다.
    - 그러면 혹시나 있을 겹치는 경우를 고민하기만 하면 된다.

<br/>

> 구현 접근법

- sol1: `preprocessing`, `brute force`
    - a 배열 내에서 순회하면서 스타 수열 내 집합들의 교집합 원소가 될 가능성이 있는 수에 대해서 빈도수 배열을 활용하여 전처리
    - 그러한 교집합 원소들을 하나씩 소모하며, 문제에 조건에 부합하는 a 배열내에 모든 가능한 집합의 개수를 찾으면 됨

- sol2: `greedy`
    - 애초에 특정 인덱스까지 현재 바라보고있는 `a[i]` 숫자로 만들 수 있는 집합쌍의 개수를 찾으면 됨
    - 중요한 점은, 최장길이를 만들어야 하기 때문에 최대한 인접해있을 수록 유리함
    - 또한 이미 다른 집합으로서 사용했는지 여부를 표현하기 위해 마지막으로 사용되었던 인덱스를 저장할 배열과 집합쌍의 개수를 측정할 카운트 배열이 필요함


<br/>

> 시간복잡도

#### sol1: O(N^2): 숫자는 a 배열의 길이만큼 저장될 수 있으므로

#### sol2: O(N): a 배열 크기에 의거함

<br/>

### 구현 코드: sol1

```java
import java.util.*;
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int size = 0;
        int[] cnt = new int[a.length];
        for(int num: a) cnt[num]++; // 전처리
        for(int i = 0; i < cnt.length; i++) {
            if (cnt[i] * 2 <= answer) continue; 
            // 어짜피 답이 될 수 없는 교집합원소는 아래의 추가 시간복잡도를 save 해야한다.
            int elem = i;
            int c = 0;
            for(int j = 0; j < a.length - 1; j++) {
                // 배열을 순회하면서 조건에 부합하는 집합을 찾는다.
                if ((elem == a[j] || elem == a[j + 1]) && (a[j] != a[j + 1])) {
                    // 두개씩 고려하는 이유는 조건에 의하여 스타 수열 내 x[0] != x[1]  이어야 하기 때문이다.
                    // 그리고 교집합용 원소가 하나라도 있으면 그 전 혹은 뒤의 원소와 결합되어 스타 수열 내 집합으로 추가 될 수 있다.
                    c += 2;
                    j++;
                    // 집합이 자신의 j + 1 원소와 맺어졌기 때문에, 한번 더 포인터를 옮겨야 한다.
                }
            }
            answer = Math.max(answer, c);
        }
        
        return answer;
    }
}
```

### 구현 코드: sol2

```java
import java.util.*;
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int size = 0;
        int[] cnt = new int[a.length]; // 해당 교집합 원소로 만들 수 있는 집합 쌍의 개수를 저장
        int[] index = new int[a.length]; // 마지막으로 해당 원소를 사용하여 집합을 만든 인덱스를 저장
        Arrays.fill(index, -1);
        for(int i = 0; i < a.length - 1; i++) {
            // 각각의 숫자를 사용해서 만들 수 있는 최대 쌍의 개수
            // 만약 아래의 조건이 사라지면 틀리게 된다.
            if (a[i] == a[i + 1]) continue;
            // 반례로 [5, 5, 5, 5, 5, 5] 가 있다.
            // 만약 스타수열을 넣을려면 스타 수열 내 원소에서 2n - 2 과 2n - 1은 절대로 같아선 안된다.
            // 하지만 여기서 위 조건이 없다면 아래의 조건들은 단순히 선택된 원소 두 가지의 집합가능성을 보는것이기 때문에
            // 정답이 위의 반례기준으로 6이 나와버린다.
            
            if (index[a[i]] < i) {
                cnt[a[i]]++;
                index[a[i]] = i + 1;
            }
            
            if (index[a[i + 1]] < i) {
                // 여기서 a[i]의 조건에 함께 업데이트하면 되지 않나? 라는 생각이 들 수 있다.
                // 그러면 안되는것이 인덱스를 하나씩 밀면서 2원소를 한번에 보기 때문에, 
                // a[i]는 겹쳐서 안되더라도, a[i + 1]은 새로운 집합으로 만들 수 있다.
                cnt[a[i + 1]]++;
                index[a[i + 1]] = i + 1;
                
            }
        }
        
        for(int i = 0; i < cnt.length; i++) {
            answer = Math.max(answer, cnt[i]);
        }
         
        return answer * 2;
    }
}
```
### 제출 결과

<img src="./result.png"/>


#### 풀이 링크

sol1: [Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/blob/main/Private%20Solve/프로그래머스/70130.%20%EC%8A%A4%ED%83%80%20%EC%88%98%EC%97%B4/HaeChang/2025-1-10T0451/Solution.java)

sol2: [Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/blob/main/Private%20Solve/프로그래머스/70130.%20%EC%8A%A4%ED%83%80%20%EC%88%98%EC%97%B4/HaeChang/2025-1-10T14633/Solution.java)
