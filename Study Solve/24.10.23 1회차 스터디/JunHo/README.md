## [프로그래머스 Lv3. 풍선 터트리기](https://school.programmers.co.kr/learn/courses/30/lessons/68646)

> 문제의 키워드

- 서로 다른 숫자
- 인접한 수를 제거해나가는데, 작은 수는 1회, 큰수는 무한대로 제거 가능

<br/>

> 접근법 분석

- 입력 배열 a의 길이는 1 부터 1,000,000
  - 일반적인 배열 완전 탐색은 N²의 시간이 걸리기 때문에 무조건 시간 초과
  - <strong>그리디 문제로 판단</strong>

<br/>

> 그리디 접근법
- 현재 배열의 값이 그 수 부터 왼쪽까지의 최솟값과 오른쪽까지의 최솟값 보다 크다면 남길 수 없음 
    - <u> 문제의 키워드에서 작은 수는 1번 밖에 못 지우기 때문에! </u>
- 현재 배열의 값의 왼쪽, 오른쪽의 최솟값을 담을 수 있는 배열을 만들어서 <strong> 탐색하며 최솟값 최신화 </strong>!

<br/>

> 시간복잡도

#### O(N)

<br/>

### 구현 코드

```java
class Solution {
    public int solution(int[] a) {
        
        // 불가능한 경우 : 양 옆 풍선이 그 풍선 보다 작은 경우
        int answer = 0;
        int[] leftMin = new int[a.length];  // 왼쪽 최솟값을 담는 배열
        int[] rightMin = new int[a.length]; // 오른쪽 최솟값을 담는 배열
        
        leftMin[0] = rightMin[a.length - 1] = Integer.MAX_VALUE; 
        
        for(int i = 1; i < a.length; i++) { // 왼쪽부터 탐색하면서 최솟값 갱신
            leftMin[i] = Math.min(leftMin[i - 1], a[i - 1]);
        }
        
        for(int i = a.length - 2; i >= 0; i--) { // 오른쪽부터 탐색하면서 최솟값 갱신
            rightMin[i] = Math.min(rightMin[i + 1], a[i + 1]);
        }
        
        for(int i = 0; i < a.length; i++) {
            if((leftMin[i] > a[i]) || (rightMin[i] > a[i])) answer++;
        }
        
        return answer;
    }
}
```

### 제출 결과

![제출결과](./result.png)

<br>

# 토론 접근법 풀이

<p> 배열의 특정값을 기준으로 양쪽에 특정값보다 작으면 안됨</p>
<p> 카운팅정렬 처럼 특정값 기준 왼쪽, 오른쪽 최솟값을 갱신해주는 느낌으로 접근 </p>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/68646.%20%ED%92%8D%EC%84%A0%20%ED%84%B0%ED%8A%B8%EB%A6%AC%EA%B8%B0/JunHo/2024-10-30T10354)
