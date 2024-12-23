import java.util.*;
class Solution {
    static int v; //홈의 개수
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        /**
        접근법
        시계방향으로 4방향 돌리고 맵 변경 후 체크
        **/
        for(int i=0; i<lock.length; i++) {
            for(int j=0; j<lock.length; j++) {
                if(lock[i][j] == 0) v++;
            }
        }
        
        
        
        //돌린 결과값에 따라 비교 진행
        boolean flag = false;
        
        for(int i=0; i<4; i++) {
            int[][] temp = rotate(key);
            key = temp;
            flag = checkValitdate(key, lock);
            if(flag) return true;
        }
    
        return flag;
    }
    
    static int[][] rotate(int[][] arr) {
    int n = arr.length;
    int[][] temp = new int[n][n]; // 회전된 배열 저장

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            temp[j][n - 1 - i] = arr[i][j];
        }
    }

    return temp;
}

    
    
    static boolean checkValitdate(int[][] diff, int[][] origin) {
        /*diff를 origin에 모든 영역에 빗대어 비교
        시작점을 0,0 ~ N,N에 전부 탐색
        */
        int n = origin.length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                // i, j가 시작점
                int cnt = v;
                boolean flag = false;
                
                for(int k=0; k<diff.length; k++) {
                    for(int l=0; l<diff.length; l++) {
                        int nx = i+k;
                        int ny = j+l;
                        if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
                        if(origin[nx][ny] == 1 && diff[k][l] == 1) {
                            //겹치는 경우
                            flag = true;
                            break;
                        }
                        else if(origin[nx][ny] == 0 && diff[k][l] == 1) {
                            //메꾸는 경우
                            cnt--;
                        }
                    }
                    if(flag) break;
                }
                
                //비교가 끝난 후
                if(!flag && cnt == 0) return true;
                
            }
        }
        return false;
    }
    
    
}