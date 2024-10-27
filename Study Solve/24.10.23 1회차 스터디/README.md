# 선정 문제
<b> 프로그래머스 Lv3. 풍선 터트리기 </b>
<br>
<b> [문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/68646) </b>


# 20분 결과
<p> 정현 : 접근 O </p>
<p> 찬혁 : 접근 및 풀이 O </p>
<p> 준호 : 접근 </p>
<p> 회창 : 접근 X </p>

# 토론내용 정리
### 유형
<P> 그리디 </P>

### 자료구조
<P> 배열 </P>

### 시간복잡도
<p> O(N) </p>

### 접근법
입력으로 주어지는 a의 길이는 최대 1,000,000
<br>
일반적인 완탐은 불가능하기 때문에, 그리디로 생각해야함.
<br>
#### 그리디 명제 조건
<p>$\bf{\normalsize{\color{red} 특정 i를 기준으로 왼쪽과 오른쪽에 나보다 작은 수가 없어야한다.}}$</p>
세그먼트 트리로 나를 제외한 구간의 1 ~ i-1 , i + 1 ~ N의 구간 비교
<br>
값의 업데이트가 일어나지 않음 -> 세그먼트 트리 필요 없음
<br>
<br>
<b> 세그먼트 트리 대신 누적 배열을 활용. </b>
<br>
- 왼쪽에서부터 누적 최저 배열
- 오른쪽에서부터 누적 최저 배열

# 풀이 링크
[링크](https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/68646.%20%ED%92%8D%EC%84%A0%20%ED%84%B0%ED%8A%B8%EB%A6%AC%EA%B8%B0)
