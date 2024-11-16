import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        //O(N^2) 풀이까지 가능.
        //예제를 보고 자리수를 기준으로 탐색 == 홀수개만 탐색가능.
        //정답이 짝수개일 경우 탐색이 안됨. ("aaaab")
        //그럼 어떻게? == 두개의 연속되는 값이 있다면 l, r을 연속되는 값으로 잡고 탐색
        
        
        for(int idx=1; idx<s.length(); idx++) {
            int l = idx-1;
            int mid = idx;
            int r = idx+1;
            int evenRes = 1, oddRes = 0;
            while(l>=0) {
                if(mid<s.length() && s.charAt(l) == s.charAt(mid)) {
                    oddRes += 2;
                    answer = Math.max(answer, oddRes);
                    mid++;
                }
                if(r<s.length() && s.charAt(l) == s.charAt(r)) {
                    evenRes += 2;
                    answer = Math.max(answer, evenRes);
                    r++;
                }
                l--;
            }
        }

        return answer;
    }
}