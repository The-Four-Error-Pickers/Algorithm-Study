/**
삭제된 행 기록(순서에 맞게) + 인덱스도 갱신
현재 선택된 행 갱신
복구는 삭제되었던 인덱스 그대로 복구
결과값 : 삭제된 행 x , 삭제되지 않은 행 o

행 최대개수 1,000,000
명령어 200,000
행 이동은 최대 1,000,000 까지
**/
import java.util.*;
class Solution {
    static List<Integer> list;
    public String solution(int n, int k, String[] cmd) {
        /**
        기존 개수 == n
        삭제 행 == PQ 관리
        복구 == 인덱스를 미는 작업
        인덱스는 고정
        boolean으로 삭제된 행 false 처리
        삭제된 prev, next도 갱신을 해야하나?
        **/
        
        list = new ArrayList<>();
        for(int i=0; i<n; i++) list.add(i);
        int now = k;
        Stack<Integer> st = new Stack<>();
        
        for(int i=0; i<cmd.length; i++) {
            String[] idx = cmd[i].split(" ");
            
            if(idx[0].equals("D")) {
                int roop = Integer.parseInt(idx[1]);
                now += roop;
                if(now >= list.size()) now = list.size() - 1;
            }
            else if(idx[0].equals("U")) {
                int roop = Integer.parseInt(idx[1]);
                now -= roop;
                if(now < 0) now = 0;
            }
            else if(idx[0].equals("C")) {
                st.push(list.get(now));
                list.remove(now);
                if(now >= list.size()) now = list.size()-1;
            }
            else {
                int p = st.pop();
                int value = upper(p);
                list.add(value, p);
                if(value <= now) now++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[n];
        for(int idx : list) {
            arr[idx] = 1;
        }
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 1) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }
    
    static int upper(int key) {
        int low = 0;
        int high = list.size();
        
        while(low < high) {
            final int mid = low + (high-low)/2;
            
            if(list.get(mid) <= key) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
            return high;
    }
}