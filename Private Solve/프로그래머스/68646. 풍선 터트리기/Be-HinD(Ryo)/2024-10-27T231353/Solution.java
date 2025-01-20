class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        boolean[] v;
        for(int idx : a) {
            v = new boolean[a.length];
            boolean minCnt = false;
            for(int i=0; i<a.length; i++) {
                if(a[i] == idx || v[i]) continue;
                
                for(int j=i+1; j<a.length; j++) {
                    if(v[j]) continue;
                    if(a[j] == idx && a[j] > a[i]) break;
                    if(a[i] > a[j]) v[i] = true;
                    else v[j] = true;
                }
            }
            
            boolean minFlag = false;
            boolean flag = true;
            
            for(int i=0; i<a.length; i++) {
                if(v[i]) continue;
                for(int j=i+1; j<a.length; j++) {
                    if(v[j]) continue;
                    if(a[i] == idx) {
                        if(a[i] > a[j]) {
                            if(minFlag) {
                                flag = false;
                                break;
                            }
                            minFlag = true;
                            v[j] = true;
                        }
                        else {
                            v[j] = true;
                        }
                    }
                    else {
                        if(a[i] > a[j]) {
                            if(!minFlag) {
                                flag = false;
                                break;
                            }
                            minFlag = true;
                            v[j] = true;
                        }
                        else {
                            v[i] = true;
                        }
                    }
                }
                if(!flag) break;
            }
            
            int cnt = 0;
            
            for(int k=0; k<v.length; k++) {
                if(!v[k]) cnt++;
            }
            if(cnt == 1) {
                answer++;
            }
            
        }
        return answer;
    }
}