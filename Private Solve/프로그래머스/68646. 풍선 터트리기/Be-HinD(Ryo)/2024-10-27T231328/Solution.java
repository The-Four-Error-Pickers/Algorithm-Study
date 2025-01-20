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
            
            System.out.print(idx + " : ");
            for(int k=0; k<v.length; k++) {
                if(v[k]) System.out.print(1 + " ");
                else System.out.print(0 + " ");
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
            System.out.println();
            System.out.print(idx + "종료 : ");
            for(int k=0; k<v.length; k++) {
                if(v[k]) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            for(int k=0; k<v.length; k++) {
                if(!v[k]) cnt++;
            }
            if(cnt == 1) {
                System.out.println("가능");
                answer++;
            }
            System.out.println();
            
        }
        return answer;
    }
}