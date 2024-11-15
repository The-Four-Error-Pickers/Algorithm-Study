## [프로그래머스 Lv3. 가장 긴 펠린드롬](https://school.programmers.co.kr/learn/courses/30/lessons/12904)

> 문제의 키워드

- 문자열 s 가 주어질 때, 부분 문자열 중 가장 긴 팰린드롬의 길이를 return
- s의 길이는 최대 2,500

<br/>

> 접근법 분석

- s의 최대 길이는 2,500으로 N² 풀이 가능
- 문자열의 인덱스별 좌우 포인터를 통해 완전탐색, N²

<br/>

> 구현 접근법

- 문자열의 인덱스별 l, r 포인터를 두어 각 포인터의 문자가 동일하다면 양쪽으로 진행
- 팰린드롬의 길이가 홀수일 때와 짝수일 때의 포인터 시작 인덱스가 다른 점 고려

<br/>

> 시간 복잡도

#### N²

문자열의 길이: N

<br/>

### 구현 코드

```java
class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        char[] str = s.toCharArray();
        int n = s.length();
        int l, r, cnt;
        for(int i = 0; i < n; i++) {
            l = i;
            r = i + 1;
            cnt = 0;

            while(true) {
                if(l < 0 || r >= n)
                    break;

                if(str[l] != str[r])
                    break;
                cnt += 2;
                answer = answer > cnt ? answer : cnt;
                l--;
                r++;
            }

            l = i - 1;
            r = i + 1;
            cnt = 1;

            while(true) {
                 if(l < 0 || r >= n)
                    break;

                if(str[l] != str[r])
                    break;
                cnt += 2;
                answer = answer > cnt ? answer : cnt;
                l--;
                r++;
            }
        }

        return answer;
    }
}
```

### 제출 결과

<br>

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/12904.%20%EA%B0%80%EC%9E%A5%20%EA%B8%B4%20%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC/ChaNyeok1225)
