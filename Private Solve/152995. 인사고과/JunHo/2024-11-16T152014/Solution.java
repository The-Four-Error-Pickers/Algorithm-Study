import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        
        int[] wanho = scores[0];  // 원호 점수 저장
        
        Arrays.sort(scores, (o1, o2) -> {
            if(o1[0] == o2[0])  return Integer.compare(o2[1], o1[1]);
            else return Integer.compare(o2[0], o1[0]);
        });
        
        int score1Max = Integer.MIN_VALUE;   // 동료 평가 점수가 최댓값일때의 근태점수
        int score2Max = Integer.MIN_VALUE;  // 동료 평가 점수의 최댓값
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int[] arr : scores) {
            int tempScore = arr[0] + arr[1];
            
            if(tempScore < (wanho[0] + wanho[1])) continue; // 완호보다 점수 작은 사원 제외
            
            if(arr[1] > score2Max) {
                score2Max = arr[1];
                score1Max = arr[0];
            }    
            
            if((arr[0] != score1Max) && (score2Max > arr[1])) {
                if((arr[0] == wanho[0]) && (arr[1] == wanho[1])) return -1; // 원호면
                continue; // 근태점수가 최댓값이 아니면서 동료 평가점수가 현재 최댓값보다 작다면 제외
            }
                
            pq.offer(tempScore);
        }
        
        int rank = 0;  
        while(!pq.isEmpty()) {
            int score = pq.poll();
            if(score == (wanho[0] + wanho[1])) break;
            rank++;
        }
        
        return rank + 1;
    }
}