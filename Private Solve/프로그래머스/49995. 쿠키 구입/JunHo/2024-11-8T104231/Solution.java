class Solution {
    public int solution(int[] cookie) {
        
        // 1 1 2 3
        // 1 2 4 7
        // 7 6 5 3
        // 세그먼트 트리 => logN * 2000 * 2000
        
        int h = (int) Math.ceil(Math.log(cookie.length + 1) / Math.log(2));
        int[] tree = new int[(int) Math.pow(2, h + 1)];
        init(tree, cookie, 1, 0, cookie.length - 1);

        int answer = 0;
        
        for (int m = 0; m < cookie.length - 1; m++) {
            int leftSum = 0, rightSum = 0;
            int left = m, right = m + 1;

            while (left >= 0 && right < cookie.length) {
                leftSum = sum(tree, 1, 0, cookie.length - 1, left, m);
                rightSum = sum(tree, 1, 0, cookie.length - 1, m + 1, right);

                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                }
                if (leftSum <= rightSum) left--;
                else right++;
            }
        }
        
        return answer;
    }
    
    public int init(int[] tree, int[] arr, int temp, int start, int end) { 
        if(start == end) return tree[temp] = arr[start];    // 리프 노드 일때
        return tree[temp] = init(tree, arr, temp * 2, start, (start + end) / 2)
                + init(tree, arr, temp * 2 + 1, (start + end) / 2 + 1, end);
    }
    
    public int sum(int[] tree, int temp, int start, int end, int left, int right) {
        if(left > end || right < start) return 0;
        if((left <= start) && (end <= right)) return tree[temp];

        return sum(tree, temp * 2, start, (start + end) / 2, left, right)
                + sum(tree, temp * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }
}