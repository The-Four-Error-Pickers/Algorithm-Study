        /**
        키워드 : 구간합, 누적합, 투포인터
        접근법
        누적합 1차원 배열 탐색
        투포인터 활용
        l, m, r
        포인터 이동조건
        m의 위치별로 투포인터 탐색
        if(m == 2)
        
        
        **/
import java.util.*;
class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        

        int[] sum = new int[cookie.length];
        sum[0] = cookie[0];
        for(int i=1; i<cookie.length; i++) {
            sum[i] = cookie[i] + sum[i-1];
        }
        
        for(int m=0; m<cookie.length-1; m++) {
            for(int l=0; l<=m; l++) {
                int left = sum[m];
                if(l != 0) left -= sum[l-1];
                //left를 만족하는 오른쪽 구간 탐색
                for(int r=m+1; r<cookie.length; r++) {
                    int right = sum[r] - sum[m];
                    if(left == right) {
                        answer = Math.max(answer, left);
                    }
                    else if(left < right) break;
                }
            }
        }
        
        
        return answer;
    }
}