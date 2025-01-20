## [프로그래머스 Lv3. 가장 긴 펠린드롬](https://school.programmers.co.kr/learn/courses/30/lessons/12904)

> 문제의 키워드

- 앞뒤를 뒤집어도 똑같은 문자열을 펠린드롬이라고 한다.
- 어떤 문자열의 부분문자열들 중 가장 긴 펠린드롬의 길이를 구하여라

<br/>

> 접근법 분석

- 펠린드롬을 해석하면 결국 기준이 되는 인덱스에서 같은 변위상에 존재하는 두 문자가 같아야 함
- 실제로 기준이 되는 반복과 그 속에서 변위 체크를 하는 반복이 필요하고, 문자열길이가 2500이므로 제곱까지는 충분히 가능함을 파악함

<br/>

> 두 포인터 접근법

- 위의 접근법때로 풀기에 적합한 알고리즘으로 두 포인터를 사용한다.

- 바깥 반복문은 안쪽의 두 포인터를 위한 기준 인덱스가 되고, 두 포인터를 사용해서 팰린드롬을 확인한다.
    - 두 같은 변위상의 문자가 같다면 현재 길이를 최대로 갱신해준다.

<br/>

> 구현 접근법

- 바깥 for문은 index 1부터 검사한다.
    - 이유는 왼쪽 포인터는 최소한 기준 index - 1이어야 하는데 0으로 잡으면 Out of Bound 가 발생한다.

- for문 안쪽에서 제한된 범위까지 두 포인터를 수행한다.
    - 두 포인터를 수행할 때, 두 포인터상 문자가 같으면 왼쪽 포인터는 내리고 오른쪽포인터는 동시에 올려야한다.
    - 왼쪽 포인터의 경우 `0`까지, 오른쪽 포인터의 경우 `s.length()` 까지 갈 수 있다.

<br/>

> 시간복잡도

#### O(N^2)

<br/>

### 구현 코드

```java
class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        char[] crr = s.toCharArray();
        
        for(int i = 1;i<crr.length;i++) {
            int start = i - 1;
            int end = i + 1;
            
            while(start > -1 && end < crr.length) {
                if (crr[start] != crr[end]) {
                    break;
                } else {
                    answer = Math.max(answer, end - start + 1);
                    start--;
                    end++;
                }
            }
            if (start < 0 && end >= crr.length) {
                break;
            }
            
        }

        return answer;
    }
}
```

### 제출 결과

![제출결과](./wrong_answer.png)

<br>

# 토론 접근법 풀이

<p>다른 분들 중 같은 접근으로 시작했으나, 펠린드롬 문자열이 <b>짝수</b> 인 경우도 고려해야함을 파악함</p>

- 짝수 펠린드롬: `기준 문자열이 2개 이상`이다.
    - 따라서 기준 인덱스와 위의 코드상에 왼쪽 start 문자가 동일하다면, 짝수 펠린드롬도 고려대상이다.
    - 그래서 펠린드롬 문자열 길이가 짝수인 경우도 함께 고려해주면 다음과 같은 반례를 해결할 수 있다.
    - ex1) "aaaaaa" 정답 6
    - ex2) "abcddcbaf" 정답 8

### 토론 based 구현 코드

```java
class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        char[] crr = s.toCharArray();
        
        for(int i = 1;i<crr.length;i++) {
            int start = i - 1;
            int end = i + 1;
            int len = 1;
            
            if (crr[i] == crr[start]) {
                // 팰린드롬으로 가정한 문자열이 짝수인 경우가 있을 수 있다.
                // 그러면 중앙문자열이 2개여야한다.
                // 그리고 해당 문자열이 입증이 되는순간 이러한 2개의 문자들도 길이 2인 펠린드롬이다.
                answer = Math.max(answer, check(start - 1, end, crr, len + 1));
            } 
            answer = Math.max(answer, check(start, end, crr, len));
        }
        return answer;
    }
    static int check(int s, int e, char[] crr, int l) {
        while(s > -1 && e < crr.length) {
            if (crr[s] != crr[e]) break;
            else {
                l += 2;
                s--;
                e++;
            }
        }
        return l;
    }
}
```

### 토론 based 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/12904.%20%EA%B0%80%EC%9E%A5%20%EA%B8%B4%20%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC/HaeChang/2024-11-13T115226)
