/**
키워드
문자열 s에서 "110" 추출 -> 임의 위치 삽입 행동 1가지
사전순으로 가장 앞에 오는 문자열 == 최소값
모든 행동에 대한 완전탐색
s의 길이 백만 -> O(N^2) 1,000,000,000,000 -> O(NlogN) 까지 가능
초기 문자열 s에서 110의 개수를 탐색
각 110에 대해서
<우선 현재 s값이 최소값>
맨앞을 0으로 만드는게 중요
110을 추출했다면 왼쪽에서부터 처음으로 나오는 1앞으로 이동
-> 110 탐색 O(N) + O(N)
110
**/

import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        // 1 ≤ s의 모든 원소의 길이의 합 ≤ 1,000,000  중요!
        
        for(int i=0; i<s.length; i++) { //O(N)
            String x = s[i];    //1110
            //110탐색 이전에 1이 존재하지 않는다면 옮길 필요없음.
            searchMin(x);
        }
        
        
        return answer;
    }
    
    private static void searchMin(String s) {
        
        //s에 대해서 110탐색
        //탐색된 110 앞에 1이 있다면 왼쪽으로 move
        //없다면 다음 110탐색 - 종료?
        for(int i=0; i<s.length()-3; i++) {
            String idx = s.substring(i,i+3);
        }
    }
}