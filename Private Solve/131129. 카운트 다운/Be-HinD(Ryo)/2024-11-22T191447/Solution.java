/**
키워드
최소한의 다트로 0점을 만드는 게 가장 중요
그러한 방법이 여러가지가 있다면 "싱글" 또는 "불"을 최대한 많이 던지는 방법

접근법
받을 수 있는 점수의 종류는 정해져있음.
처음 target점수에서 bfs탐색을 통해 0에 도달하는 가짓수 중 최적해를 탐색
PQ를 통해 같은 횟수에 대해서는 싱글,불 카운트 내림차순으로 추출
**/

import java.util.*;
class Solution {
    public int[] solution(int target) {

        DART res = bfs(target);
        
        return new int[]{res.cnt, res.spCnt};
    }
    
    static class DART implements Comparable<DART> {
        int score;
        int cnt;
        int spCnt;
        public DART(int score, int cnt, int spCnt) {
            this.score = score;
            this.cnt = cnt;
            this.spCnt = spCnt;
        }
        @Override
        public int compareTo(DART o) {
            if(this.cnt == o.cnt) {
                return o.spCnt - this.spCnt;
            }
            return this.cnt - o.cnt;
        }
    }
    static DART bfs(int start) {
        PriorityQueue<DART> pq = new PriorityQueue<>();
        pq.offer(new DART(start, 0, 0));
        int[] memo = new int[start+1];  //던진 횟수에 대해서만
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[start] = 0;
        
        while(!pq.isEmpty()) {
            DART cur = pq.poll();
            
            if(cur.score == 0) return cur;
            if(memo[cur.score] < cur.cnt) continue;
            memo[cur.score] = cur.cnt;
            
            int nextScore = cur.score - 50;
            if(nextScore >= 0) {
                pq.offer(new DART(nextScore, cur.cnt+1, cur.spCnt+1));
            }
            
            for(int i=1; i<=20; i++) {
                nextScore = cur.score - i;
                if(nextScore >= 0) {
                    pq.offer(new DART(nextScore, cur.cnt+1, cur.spCnt+1));
                }
                nextScore = cur.score - (i*2);
                if(nextScore >= 0) {
                    pq.offer(new DART(nextScore, cur.cnt+1, cur.spCnt));
                }
                nextScore = cur.score - (i*3);
                if(nextScore >= 0) {
                    pq.offer(new DART(nextScore, cur.cnt+1, cur.spCnt));
                }
            }
            
            
        }
        
        return null;
    }
}