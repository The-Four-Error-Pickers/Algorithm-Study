class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        
        for (int m = 0; m < cookie.length - 1; m++) {
            int leftSum = cookie[m];
            int rightSum = cookie[m + 1];
            int left = m;
            int right = m + 1;

            while (left >= 0 && right < cookie.length) {
                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
                
                if (leftSum <= rightSum && left > 0) {
                    left--;
                    leftSum += cookie[left];
                } else if (right < cookie.length - 1) {
                    right++;
                    rightSum += cookie[right];
                } else {
                    break;
                }
            }
        }
        return answer;
    }
}
