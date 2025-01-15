## [프로그래머스 Lv3. 스타 수열](https://school.programmers.co.kr/learn/courses/30/lessons/70130)

> 문제의 키워드

- 스타수열의 길이 : 2n (n = 1, 2, ...)
- 인접한 2개의 원소를 묶었을때, 교집합 원소 갯수가 적어도 1개 이상
- 인접한 2개의 원소를 묶었을때, 집합안에서 2개의 값은 서로 달라야함

<br/>


> 접근법 분석
- 나오는 숫자를 카운팅해서 가장 많이 나오는 값부터 확인해봄
- ex) 2번 같은 경우는 3이 3번 나오므로 3 * 2인 6으로 스타수열을 만들 수 있는지 확인
- 등장 횟수가 많은 숫자부터 스타 수열의 성립 가능성 확인
- (a[i] == i || a[i + 1] == i) 이면서 i와 i + 1의 인덱스 값이 다르면 스타수열 만들기 가능

> 알고리즘

#### 그리디


<br/>

> 시간복잡도
#### O(len(a))

<br/>

### 구현 코드

```java
class Solution {
    public int solution(int[] a) {
        
        int[] count = new int[a.length + 1];
        for(int i = 0; i < a.length; i++) count[a[i]]++;
        
        int answer = 0;
        for(int i = 0; i < count.length; i++) {
            if(count[i] < 2) continue; // 1개나 0개는 탐색할 필요 X
            if(answer >= count[i] * 2) continue;    // 현재 정답보다 만들 수 있는 스타수열의 길이가 작은 경우 다음꺼 탐색
            
            int temp = 0;   // 현재 i 값으로 만들 수 있는 길이
            for(int j = 0; j < a.length - 1; j++) {
                if(((a[j] == i) || (a[j + 1] == i)) && (a[j] != a[j + 1])) {
                    temp += 2;
                    j++;
                }
            }
            answer = Math.max(answer, temp);
        }
        
        return answer;
    }
}
```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/70130.%20%EC%8A%A4%ED%83%80%20%EC%88%98%EC%97%B4/JunHo/2025-1-15T175343)
