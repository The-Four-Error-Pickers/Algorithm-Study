## [프로그래머스 Lv3. 스타 수열](https://school.programmers.co.kr/learn/courses/30/lessons/70130)

> 문제 키워드

- x의 길이가 2 이상의 짝수
- x[0] != x[1], x[2] != x[3]
- 교집합의 원소의 개수가 1 이상
- 1 <= a <= 500,000
- a의 모든 수는 0 이상 (a의 길이) 미만

<br/>

> 접근법

- 1 <= a <= 500,000
- 스타수열의 부분수열이 될 수 있는 x를 기준으로 카운팅
- 1회 반복 -> idx, idx+1을 기준으로 서로 수가 다르다면 교집합 x를 기준으로 +1

<br/>

> 시간 복잡도

#### O(NlogN)

입력값 a의 길이가 500,000

<br/>

### 구현 코드

```java
class Solution {
    public int solution(int[] a) {
        int res = 0;
        
        int[] arr = new int[a.length]; //a의 모든 수는 0 이상 (a의 길이) 미만
        
        for(int i=0; i<a.length; i++) {
            arr[a[i]]++;
        }
        
        for(int i=0; i<a.length; i++) { // i -> 교집합 x
            if(arr[i] <= res) continue; // 교집합 x의 최소값 처리
            
            int temp = 0;
            for(int j=0; j<a.length-1; j++) {
                if(a[j]!=a[j+1]) { //연속된 두 수가 다르고
                    if(i == a[j] || i == a[j+1]) { //두 수 중 x가 포함
                        temp++; //x를 포함하는 부분집합 + 1
                        j++; // 연속된 두 수는 포함하면 안됨.
                    }
                }
            }
            
            res = Math.max(res, temp);
        }
        
        
        return res*2;
    }
}
```

### 제출 결과

> 제출 결과
![제출결과](./result.png)
> 

> 스터디 정리
- 그리디 알고리즘의 느낌이 났음.
- 교집합 x 기준이 아닌 배열에서 가장 많이 등장한 수가 키가 될거라 생각하고 탐
- 실제 풀이에도 그리디적인 사고가 들어가긴 함 -> 떨어진 수를 비교하지 않아도 된다.
- 20분안에 접근 + 검증 + 코드 구현은 힘든 문제.
- 또한 완전탐색 접근 -> 최적화 과정을 항상 거쳤는데, 방식을 바꿔야하나 생각도 들었음. (그리디 사고가 필요한 문제들에 한해서)


> 구현 알고리즘
<p> 그리디 + 구현 </p>

> 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/70130.%20%EC%8A%A4%ED%83%80%20%EC%88%98%EC%97%B4/Be-HinD)
