import java.util.*;
class Solution {
    static List<List<Integer>> list;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        /**
         각 지역은 유일한 번호로 구분
         걸리는 시간은 모두 1로 동일
         최단시간에 부대로 복귀
         임무의 시작 때와 다르게 되돌아오는 경로가 없어져 복귀가 불가능한 부대원도 있을 수 있습니다.
         
         **/
        
        list = new ArrayList<>();
        for(int i=0; i<n; i++) list.add(new ArrayList<>());
        
        for(int i=0; i<roads.length; i++) {
            int[] road = roads[i];
            list.get(road[0]-1).add(road[1]-1);
            list.get(road[1]-1).add(road[0]-1);
        }
        
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<sources.length; i++) {
            res.add(dijk(n, sources[i]-1, destination-1));
        }
        
        int[] ress = new int[res.size()];
        for(int i=0; i<res.size(); i++) {
            ress[i] = res.get(i);
        }
        return ress;
    }
    
    private static int dijk(int n, int start, int target) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
        boolean[] v = new boolean[n];
        pq.offer(new int[]{start,0});
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if(cur[0] == target) {
                return cur[1];
            }
            
            if(v[cur[0]]) continue;
            v[cur[0]] = true;
            
            for(int next : list.get(cur[0])) {
                if(!v[next]) {
                    pq.offer(new int[]{next, cur[1]+1});
                }
            }
        }
        
        return -1;
    }
}