## [프로그래머스 Lv3. 110 옮기기](https://school.programmers.co.kr/learn/courses/30/lessons/77886)

> 문제의 키워드

- 0과 1로 이루어진 문자열 x
- x에 있는 "110"을 뽑아서, 임의의 위치에 다시 삽입합니다.
- 변형시킬 문자열 x가 여러 개 들어있는 문자열 배열 s가 주어질때, 각 문자열에 대해서 위의 행동으로 변형해서 만들 수 있는 문자열 중 사전순으로 가장 앞에오는 문자열을 배열에 담아 return

- `1 <= s.length <= 1_000_000`
- `1 <= s[idx].length() <= 1_000_000`
- `S(Array) 를 Array 내 모든 원소에 대한 합이라 가정했을 때, 1 <= S(s[idx].length()) <= 1_000_000`

<br/>

> 접근법 분석

- 배열 s 내에 문자열 길이의 합이 100만이란건, 결국 s의 길이가 1이면 `s[0].length()`는 100만이란 의미이다.
- 결국 배열 내 원소들에 대해서 최악의 경우여도 O(N) 수준 선에서 쿼리를 처리해야한다.
- 문자열 사전순으로 앞설려면, 1과 0으로 이뤄져있을 때 가능한한 0이 먼저 등장해야 한다.
- 또한 "110을 뽑아서 임의의 위치에 다시 삽입" 한다는 것은 이러한 행위를 반복하더라도 전체 문자열의 길이는 변화가 없다.

- "110" 보다 느린 문자열은 "111"이 나오는 경우 뿐이다. 따라서 어떠한 "110" 도 "111" 앞에 설 수있다면 사전순으로 앞서는 문자열이다.
- 만약 "110"을 모두 추출했었고 남은 문자열에 "111"이 없는 경우라면, 남은 문자열의 맨앞 혹은 맨뒤에 넣어서 서로 비교후 더 작은것을 출력한다.

<br/>

> 구현 접근법

구현을 하다가 시간이 지나버림

<br/>

> 시간복잡도

#### O(N)

<br/>

### 구현 코드

구현한 코드가 없음

### 제출 결과

이하 생략

<br>

# 토론 접근법 풀이

문득 토론 도중, "앞에서부터 마지막으로 나온 0 뒤에 모든 추출된 110을 넣으면 되지 않나" 라는 의견이 발생

이것이 가능한 이유는 앞서서 얘기했지만, 최대한 0이 앞에 나와야하기 때문이며 이는 곧 1은 뒤로 미뤄야 하기 때문이다.

어쩔수없이 1들이 쌓여있다면, 그들은 110을 넣음으로서 뒤로 밀어야한다.

따라서 모든 110을 추출해내고 빈공간 없이 합쳐진 문자열에서, 뒤에서부터 처음 0이 등장하는 순간에 삽입 하여야 한다.

만약 0이 없다면, 110에서만 0을 삽입할 수 있으므로 젤 앞에 삽입한다.

### 토론 based 구현 코드

```java
import java.util.*;

class Solution {
    static int zzoCnt;
    static final char EMPTY = '2';
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        int index = 0;
        for(String str: s) {
            zzoCnt = 0;
            char[] exts = extract(str);
            int findIdx = -1;
            for(int i = exts.length - 1; i  > -1 ; i--) {
                if (exts[i] == EMPTY) continue;
                if (exts[i] == '0') {
                    findIdx = i;
                    break;
                }
            }
            StringBuilder sb =new StringBuilder();
            if (findIdx == -1) {
                for(int i = 0;i<zzoCnt;i++) {
                    sb.append("110");
                }
                for(int i = 0;i<exts.length;i++) {
                    if (exts[i] == EMPTY) continue;
                    if (exts[i] == '\u0000') break;
                    sb.append(exts[i]);
                }
            } else {
                for(int i = 0;i<=findIdx;i++) {
                    sb.append(exts[i]);
                }
                for(int i = 0;i<zzoCnt;i++) {
                    sb.append("110");
                }
                for(int i = findIdx + 1;i<exts.length;i++) {
                    if (exts[i] == EMPTY) continue;
                    if (exts[i] == '\u0000') break; // 빈배열 공백문자 들어간거때매
                    sb.append(exts[i]);
                }
            }
            answer[index++ ] = sb.toString(); // 공백 제거할 것
        }
        return answer;
    }
    
    static char[] extract(String target) {
        // 110 찾기
        char[] ts = target.toCharArray();
        char[] result = new char[ts.length];
        for(int i = 0;i<Math.min(target.length(), 2);i++) {
            result[i] = ts[i];
        }
        int idx = 2;
        
        
        for(int i = 2;i<target.length();i++) {
            if (idx > 1 && result[idx - 2] == '1' && result[idx - 1] == '1' && ts[i] == '0'){
                zzoCnt++;
                result[idx - 2] = EMPTY;
                result[idx - 1] = EMPTY;
                result[idx] = EMPTY;
                idx -= 2;
            } else {
                result[idx++] = ts[i];
            }
        }
        
        return result;
    }
}
```

### 토론 based 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77886.%20110%20%EC%98%AE%EA%B8%B0%EA%B8%B0/HaeChang)
