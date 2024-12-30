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
        
        int[][] v = new int[n][3]; //전,후,유무
        for(int i=0; i<n; i++) {
            v[i][0] = i-1;
            v[i][1] = i+1;
            v[i][2] = 1;
        }
        
        Stack<Integer> st = new Stack<>();
        
        for(int i=0; i<cmd.length; i++) {
            // for(int j=0; j<v.length; j++) {
            //     if(v[j][2] == 1) System.out.print("O");
            //     else System.out.print("X");
            // }
            // System.out.println();
            String[] idx = cmd[i].split(" ");
            
            if(idx[0].equals("D")) {
                int roop = Integer.parseInt(idx[1]);
                for(int j=0; j<roop; j++) {
                    if(v[k][1] >= n) continue;
                    k = v[k][1];
                }
            }
            else if(idx[0].equals("U")) {
                int roop = Integer.parseInt(idx[1]);
                for(int j=0; j<roop; j++) {
                    if(v[k][0] < 0) continue;
                    k = v[k][0];
                }
            }
            else if(idx[0].equals("C")) {
                v[k][2] = 0;
                st.push(k);
                if(k != 0) v[k-1][1] = v[k][1];
                if(k != n-1) v[k+1][0] = v[k][0];
                
                if(v[k][1] == n) {
                    k = v[k][0];
                }
                else k = v[k][1];
            }
            else { //복구
                int p = st.pop();
                v[p][2] = 1;
                for(int j=p-1; j>=0; j--) {
                    if(v[j][2] == 0) continue;
                    v[p][0] = j;
                    break;
                }
                for(int j=p+1; j>n; j++) {
                    if(v[j][2] == 0) continue;
                    v[p][1] = j;
                    break;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<v.length; i++) {
            if(v[i][2] == 1) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }
}