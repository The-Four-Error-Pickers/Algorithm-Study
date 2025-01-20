# 지난 스터디(12.24) 회고

전원 구현 완료

# 선정 문제

<b> 프로그래머스 Lv3. 미로 탈출 명령어</b>
<br>
<b> [문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/150365) </b>

# 문제 출제 및 서기

<b> 정현(Be-HinD) </b>

# 20분 결과

<p> 정현 : 접근 </p>
<p> 회창 : 접근 </p>
<p> 준호 : 접근 </p>
<p> 찬혁 : 접근 및 풀이 </p>

# 토론내용 정리

### 유형

<P> DFS, BFS </P>

### 시간복잡도

<p> O(N^2) </p>

인접행렬

### 접근법

> 준호

<b> DFS </b>

<p> 출발지와 도착지와의 거리가 되지 않거나 홀수라면 impossible </p>
<p> 오름차순 기준으로 먼저 탐색할 수 있도록 벡터 구성 </p>
<p> 가지치기 : 현재 위치와 도착위치까지의 거리 고려 </p>
<p> 포인트 : 거리차이와 홀수/짝수 여부에 따른 백트래킹으로 시간 단축 </p>

> 회창

<b> DFS </b>

<p> 위 접근법과 유사 </p>

> 찬혁

<b> DFS </b>

<p> 위 접근법과 유사 </p>

> 정현

<b>BFS</b>

<p> 완전탐색 + 결과값 갱신 </p>

# 풀이 링크

<a href="https://github.com/The-Four-Error-Pickers/Algorithm-Study/tree/main/Private%20Solve/프로그래머스/150365.%20%EB%AF%B8%EB%A1%9C%20%ED%83%88%EC%B6%9C%20%EB%AA%85%EB%A0%B9%EC%96%B4">링크</a>
