/**
키워드
펄스 수열 : 1또는 -1로 시작, 두 수가 번갈아 나오는 수열
연산 : 연속 부분 수열 * 펄스 수열
합 중 가장 큰 것을 return
접근법
수열 길이 <= 500,000 O(NlogN)까지 가능
원소 크기 <= 100,000 == long
정렬 x
완탐 : 모든 부분 수열의 펄스 수열 구하기 -> 값 갱신 (2^N)
각 수열에 +1, -1곱한 값을 미리 구하기 2차원 배열

**/
import java.util.*;
class Solution {
    public long solution(int[] sequence) {

        int n = sequence.length;
        int[] plus = new int[n];
        int[] minus = new int[n];
        
        for(int i=0; i<n; i++) {
            if(i % 2 == 0) {
                plus[i] = sequence[i] * 1;
                minus[i] = sequence[i] * -1;
            }
            else {
                plus[i] = sequence[i] * -1;
                minus[i] = sequence[i] * 1;
            }
        }
        
        int[] sum = new int[n];
        sum[0] = minus[0];
        for(int i=1; i<n; i++) {
            sum[i] = minus[i] + sum[i-1];
        }
        //구해진 배열에서 각각 부분수열의 최대합 구하기
        //
        
        // System.out.println(Arrays.toString(plus));
        
        long res = -200000;
        int l=0, r=0, v=minus[l];
        while(l<=r && r<n-1) {
            res = Math.max(res, v);
            v += minus[++r];
            if(v < v - minus[l] && l < r-1) {
                v -= minus[l++];
            }
        }
        
        l=0; r=0; v=plus[l];
        while(l<=r && r<n-1) {
            res = Math.max(res, v);
            v += plus[++r];
            if(v < v - plus[l] && l < r-1) {
                v -= plus[l++];
            }
        }
        
        
        return res;
    }
}