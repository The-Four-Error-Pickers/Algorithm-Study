class Solution {
    public int[] solution(int n, int s) {
        int[] res = new int[n];
        
        if(s/n == 0) return new int[]{-1};
    
        int idx = 0;
        n++;
        while(--n != 0) {
            res[idx++] = s / n;
            s -= res[idx-1];
        }
        
        return res;
    }
}