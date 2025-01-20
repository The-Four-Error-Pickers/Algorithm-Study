import java.util.*;
class Solution {
    static List<List<Integer>> list;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        /**
        키워드 추출
        어느 등대에서 출발해도 다른 모든 등대까지 이동가능
        한 뱃길의 양쪽 끝 등대 중 적어도 하나는 켜져 있도록 등대를 켜 두어야 합니다.
        
        **/
        list = new ArrayList<>();
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());
        for(int i=0; i<lighthouse.length; i++) {
            int[] edge = lighthouse[i];
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i=1; i<list.size(); i++) {
            if(list.get(i).size() == 1) {
                if(!set.contains(list.get(i).get(0))) {
                    answer++;
                    set.add(list.get(i).get(0));
                }
            }
        }
        
        return answer;
    }
}