## [프로그래머스 Lv3. 110 옮기기](https://school.programmers.co.kr/learn/courses/30/lessons/77886)

> 키워드 유추

- 문자열 중 "110" 추출 및 임의의 위치에 삽입
- 위의 행동을 통해 만들 수 있는 사전 순 가장 작은 문자

> 접근법
- s의 길이 1,000,000 -> O(NlogN)이지만
- 특수한 경우 O(N^2)도 가능
- "110"의 경우, 두 개를 사용했을 때, "110110"이 가장 최적의 방법
- 모든 "110"을 제거한 후 "110"보다 우선순위가 낮은 곳을 찾아 해당 위치에 모든 "110"을 삽입

<br/>

> 시간 복잡도

#### O(N)

문자열의 길이: N

<br/>

### 구현 코드

```java
import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i=0; i<s.length; i++){
            String ans = solve(s[i]);   
            answer[i] = ans;
        }
        
        return answer;
    }
    
    public String solve(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder ooz = new StringBuilder();
        
        for(int i=0; i<s.length(); i++){
            Character c = s.charAt(i);
            if(sb.length()>=2 && c=='0' && sb.charAt(sb.length()-2)=='1' && sb.charAt(sb.length()-1)=='1'){
                ooz.append("110");
                sb.delete(sb.length()-2, sb.length());
            }
            else{
                sb.append(c);
            }
        }
        
        if(ooz.length()>0){
            //0이 없으면
            if(sb.indexOf("0")==-1){
                sb.insert(0, ooz);
            }
            else{
                int idx = sb.lastIndexOf("0");
                sb.insert(idx+1, ooz);
            }
        }
        
        return sb.toString();
    }
}
```

> 제출 결과
![제출결과](./result.png)
> 

> 스터디 정리
- 이번 문제를 통해 그리디는 내가 생각한게 틀리면 어쩔 수 없구나 라는 생각을 할 수 있었음.
- 또한 모두가 풀지 못했던 문제로 다같이 토론함으로써 정답을 유추했던 문제로 재밌었음..


> 구현 알고리즘
<p> 그리디 </p>

> 풀이 링크

[Private Solve](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/77886.%20110%20%EC%98%AE%EA%B8%B0%EA%B8%B0/Be-HinD(Ryo))
