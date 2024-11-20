## [프로그래머스 Lv3. 110 옮기기](https://school.programmers.co.kr/learn/courses/30/lessons/77886)

> 문제의 키워드

- 문자열 x에서 '110'을 뽑아서 임의의 위치에 넣어야 함
- 1 ≤ s의 모든 원소의 길이의 합 ≤ 1,000,000

<br/>
<br/>

> 접근법 분석
- 처음에는 접근을 하지 못하였고 스터디원들의 의견을 종합해서 접근할 수 있었음.
- 문제 조건을 만족할려면 '110'들을 적절히 옮겨서 <strong> 최솟값을 만들어야함 </strong>.
- 마지막 0이 나오는 위치 바로 뒤에다가 넣으면 최솟값이 됨.
- 만약 0이 나오지 않는다면 제일 앞에 넣는게 최솟값


<br/>
<br/>

> 알고리즘

#### 문자열, 그리디


<br/>

> 시간복잡도

#### O(N^2)

<br/>

### 구현 코드

```java
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i = 0; i < s.length; i++) {
            char[] word = new char[s[i].length()];
            int idx = 0;
            int count = 0;
            
            for(int j = 0; j < word.length; j++) {
                if((s[i].charAt(j) == '0') && (idx >= 2) && 
                   (word[idx - 1] == '1') && (word[idx - 2] == '1')) {
                    idx -= 2;
                    count++;
                } else {
                    word[idx++] = s[i].charAt(j);
                }
            }
            
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < idx; j++) {
                sb.append(word[j]);
            }
            
            StringBuilder temp = new StringBuilder();
            for(int j = 0; j < count; j++) {
                temp.append("110");
            }
            
            int zeroIndex = sb.lastIndexOf("0");
          
            if(zeroIndex < 0) {
                sb.insert(0, temp.toString());
            } else {
                sb.insert(zeroIndex + 1, temp.toString());
            }
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}

```

### 제출 결과

![제출결과](./result.png)

#### 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77886.%20110%20%EC%98%AE%EA%B8%B0%EA%B8%B0/JunHo/2024-11-18T21738)
