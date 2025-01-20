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
    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        
        /**
        기존 개수 == n
        삭제 행 == PQ 관리
        복구 == 인덱스를 미는 작업
        인덱스는 고정
        boolean으로 삭제된 행 false 처리
        
        **/
        
        boolean[] v = new boolean[n];
        Arrays.fill(v, true);
        Stack<Integer> st = new Stack<>();
        
        for(int i=0; i<cmd.length; i++) {
            String[] idx = cmd[i].split(" ");
            
            if(idx[0].equals("D")) {
                int roop = Integer.parseInt(idx[1]);
                for(int j=0; j<roop; j++) {
                    if(k+1 == n) break;
                    if(!v[++k]) {
                        j--;
                        continue;
                    }
                }
            }
            else if(idx[0].equals("U")) {
                int roop = Integer.parseInt(idx[1]);
                for(int j=0; j<roop; j++) {
                    if(k-1 == -1) break;
                    if(!v[--k]) {
                        j--;
                        continue;
                    }
                }
            }
            else if(idx[0].equals("C")) {
                v[k] = false;
                st.push(k);
                boolean flag = false;
                for(int j=k; j<n; j++) {
                    if(!v[j]) continue;
                    k = j;
                    flag = true;
                    break;
                }
                if(!flag) {
                    for(int j=k; j>=0; j--) {
                        if(!v[j]) continue;
                        k = j;
                        break;
                    }
                }
            }
            else { //복구
                int p = st.pop();
                v[p] = true;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<v.length; i++) {
            if(v[i]) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }
}