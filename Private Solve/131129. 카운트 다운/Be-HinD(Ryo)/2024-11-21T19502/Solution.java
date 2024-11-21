import java.util.*;
class Solution {
    static int[] arr;
    public int[] solution(int target) {
        int[] answer = new int[2];
        
        /**
        키워드
        남은 점수보다 큰 점수로 득점하면 버스트가 되며 실격
        같은 라운드에 0점을 만들면 두 선수 중 "싱글" 또는 "불"을 더 많이 던진 선수가
        그마저도 같다면 선공인 선수가 승리
        접근법
        거스름돈 그리디 문제와 동일
        맞출 수 있는 점수의 내림차순으로 정렬
        **/
        
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for(int i=1; i<=20; i++) {
            set.add(i);
            set.add(i*2);
            set.add(i*3);
        }
        set.add(50);
        
        return Dijk(target);
        
    }
    
    private static int[] Dijk(int t) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1[1] == o2[1]) {
                return o2[2] - o1[2];
            }
            return o1[1] - o2[1];
        });
        pq.offer(new int[]{t,0,0});
        int[][] v = new int[100055][2];
        for(int i=0; i<v.length; i++) {
            Arrays.fill(v[i], Integer.MAX_VALUE);
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(cur[0] == 0) {
                return new int[]{cur[1], cur[2]};
            }
            if(v[cur[0]][0] < cur[0]) continue;
            
            for(int i=1; i<=20; i++) {
                if(cur[0] - i >= 0) {
                    pq.offer(new int[]{cur[0]-i, cur[1]+1, cur[2] + 1});
                }
            }
            
            for(int i=1; i<=20; i++) {
                if(cur[0] - (i*2) >= 0) {
                    pq.offer(new int[]{cur[0]-(i*2), cur[1]+1 , cur[2]});
                }
            }
            
            for(int i=1; i<=20; i++) {
                if(cur[0] - (i*3) >= 0) {
                    pq.offer(new int[]{cur[0]-(i*3), cur[1]+1, cur[2]});
                }
            }
            if(cur[0] - 50 >= 0) {
                pq.offer(new int[]{cur[0]-50, cur[1]+1, cur[2] + 1});
            }
        }
        
        return new int[]{-1,-1};
    }
}