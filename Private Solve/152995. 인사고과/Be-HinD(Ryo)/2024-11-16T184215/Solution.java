import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        /**
        키워드
        근무 태도 점수와 동료 평가 점수
        만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 그 사원은 인센티브를 받지 못함
        인센티브가 차등 지급
        동석차의 수만큼 다음 석차는 건너 뜀
        접근법
        그리디 문제로 명제를 세우는게 중요
        인센티브를 받지 못하는 사람을 먼저 거르기
        두 점수 중 하나의 점수로 내림차순 정렬
        점수가 같다면 다른 점수를 기준으로 오름차순 -> 
        [3,2],[3,2],[2,1],[2,2],[1,4]
        i번째 인덱스를 기준으로 봐야하는건 내 앞에서 나온 최대값
        **/

        int[] wanho = new int[2];
        wanho[0] = scores[0][0];
        wanho[1] = scores[0][1];
        
        Arrays.sort(scores, (o1,o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });
        
        int[] memo = new int[scores.length+1];  //동료 평가 점수의 최대값
        memo[0] = scores[0][1];
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o2[0] - o1[0]);
        pq.offer(new int[]{scores[0][0]+scores[0][1], scores[0][0], scores[0][1]});
        
        for(int i=1; i<scores.length; i++) {
            int[] cur = scores[i];
            memo[i] = Math.max(memo[i-1], cur[1]);
            
            for(int j=i; j>=0; j--) {
                if(scores[j][0] > cur[0]) {
                    if(memo[j]>cur[1]) continue;
                }
            }
            pq.offer(new int[]{cur[0]+cur[1], cur[0], cur[1]});
        }
        
        int pointer = 1;
        int rank = 0;
        int prev = Integer.MAX_VALUE;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(prev > cur[0]) {
                prev = cur[0];
                rank = pointer;
            }
            pointer++;
            
            if(cur[1] == wanho[0] && cur[2] == wanho[1]) {
                return rank;
            }
        }
        if(pq.isEmpty()) return -1;
        return rank;
    }
}