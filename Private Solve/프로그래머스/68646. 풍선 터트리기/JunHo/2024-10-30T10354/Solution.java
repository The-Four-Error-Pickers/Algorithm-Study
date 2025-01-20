class Solution {
    public int solution(int[] a) {
        
        // 불가능한 경우 : 양 옆 풍선이 그 풍선 보다 작은 경우
        int answer = 0;
        int[] leftMin = new int[a.length];  // 왼쪽 최솟값을 담는 배열
        int[] rightMin = new int[a.length]; // 오른쪽 최솟값을 담는 배열
        
        leftMin[0] = rightMin[a.length - 1] = Integer.MAX_VALUE;    // 
        
        for(int i = 1; i < a.length; i++) { // 왼쪽부터 탐색하면서 최솟값 갱신
            leftMin[i] = Math.min(leftMin[i - 1], a[i - 1]);
        }
        
        for(int i = a.length - 2; i >= 0; i--) { // 오른쪽부터 탐색하면서 최솟값 갱신
            rightMin[i] = Math.min(rightMin[i + 1], a[i + 1]);
        }
        
        for(int i = 0; i < a.length; i++) {
            if((leftMin[i] > a[i]) || (rightMin[i] > a[i])) answer++;
        }
        
        return answer;
    }
}