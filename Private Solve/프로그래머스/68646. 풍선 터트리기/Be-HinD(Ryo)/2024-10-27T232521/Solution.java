class Solution {
    public int solution(int[] a) {
        int answer = 2; //왼쪽, 오른쪽 끝은 무조건 남길 수 있음.
        
        /**
        타겟 i를 기준으로 왼쪽, 오른쪽을 비교
        i보다 낮은값이 한쪽에라도 없어야함.
        즉 왼쪽 오른쪽 모두 i보다 작은값이 있다면 false
        **/
        if(a.length == 1) return 1;
        
        for(int i=1; i<a.length-1; i++) {
            int cnt = 0;
            for(int j=i-1; j>=0; j--) {
                if(a[j] < a[i]) {
                    cnt++;
                    break;
                }
            }
            
            for(int j=i+1; j<a.length; j++) {
                if(a[j] < a[i]) {
                    cnt++;
                    break;
                }
            }
            if(cnt==2) continue;
            answer++;
        }
        return answer;
    }
}