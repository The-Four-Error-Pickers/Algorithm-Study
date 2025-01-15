class Solution {
    public int solution(int[] a) {
        int answer = 0;
        //a의 모든 부분 수열 중에서 가장 길이가 긴 스타 수열의 길이를 return
        int[] cnt = new int[a.length];
        for(int i=0; i<a.length; i++){
            cnt[a[i]]++;
        }
        
        for(int i=0; i<cnt.length; i++){
            if(cnt[i] <= answer) continue; //이미 등장한 숫자보다 더 빈도가 적거나 같은 경우
            
            int ans = 0;
            for(int j=0; j<a.length-1; j++){
                if(a[j]!=a[j+1] && (i==a[j] || i==a[j+1])){
                    ans++;
                    j++;
                }
            }
            
            answer = Math.max(answer, ans);
        }
        
        return answer*2;
    }
}