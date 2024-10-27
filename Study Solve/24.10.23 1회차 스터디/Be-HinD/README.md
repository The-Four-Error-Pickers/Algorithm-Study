# 최초 접근법
배열을 한번 순회하면서 각 원소가 마지막까지 남을 수 있는지 비교하였음.

### 마지막까지 남을 수 있는지 비교 로직
<p> 현재 i값을 제외한 나머지 풍선들에 대해 차례로 큰 풍선들을 제거해나감 </p>
<p> 그 후 i값을 포함하여 남은 원소들에 대해 차례로 제거하는데, i보다 작은값에 대해 한번 제거 후 작은값이 남을 경우 false</p>
<p> 최종적으로 남은 풍선이 1개라면 true </p>

#### 시간복잡도
<p> O(N3)인듯? </p>

### 구현 코드
```java
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        boolean[] v;
        for(int idx : a) {
            v = new boolean[a.length];
            boolean minCnt = false;
            for(int i=0; i<a.length; i++) {
                if(a[i] == idx || v[i]) continue;
                
                for(int j=i+1; j<a.length; j++) {
                    if(v[j]) continue;
                    if(a[j] == idx && a[j] > a[i]) break;
                    if(a[i] > a[j]) v[i] = true;
                    else v[j] = true;
                }
            }
            
            boolean minFlag = false;
            boolean flag = true;
            
            for(int i=0; i<a.length; i++) {
                if(v[i]) continue;
                for(int j=i+1; j<a.length; j++) {
                    if(v[j]) continue;
                    if(a[i] == idx) {
                        if(a[i] > a[j]) {
                            if(minFlag) {
                                flag = false;
                                break;
                            }
                            minFlag = true;
                            v[j] = true;
                        }
                        else {
                            v[j] = true;
                        }
                    }
                    else {
                        if(a[i] > a[j]) {
                            if(!minFlag) {
                                flag = false;
                                break;
                            }
                            minFlag = true;
                            v[j] = true;
                        }
                        else {
                            v[i] = true;
                        }
                    }
                }
                if(!flag) break;
            }
            
            int cnt = 0;
            
            for(int k=0; k<v.length; k++) {
                if(!v[k]) cnt++;
            }
            if(cnt == 1) {
                answer++;
            }
            
        }
        return answer;
    }
}
```

### 제출 결과


<br>

# 토론 접근법으로 풀이
<p> 내가 이해한 내용은 특정값 i를 기준으로 양쪽에 i보다 작은값이 나오면 안됨.</p>
<p> 한쪽에만 작은값이 나온다면 조건을 통해 제거 가능하지만, 양쪽에서 나오면 제거 불가능 </p>
<p> 처음에는 왼쪽, 오른쪽 순회하면서 작은값 탐색했는데 이 방법 또한 O(N^2)으로 시간초과 </p>
<P> left, right 배열을 통해 각 i번째 원소에서의 최소값을 기록하여 풀이함 </P>

#### 시간복잡도
O(3N)

#### 구현 알고리즘
<p> 구현, 그리디 </p>

#### 풀이 링크
[Private Solve](http://)

