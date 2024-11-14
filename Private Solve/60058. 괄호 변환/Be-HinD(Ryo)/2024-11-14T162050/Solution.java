import java.util.*;
class Solution {
    public String solution(String p) {
        String answer = "";
        
        if(!isBalanced(p)) return p;
        if(isBalanced(p)) return p;
        
        return answer;
    }
    
    private static boolean isBalanced(String s) {
        int left = 0, right = 0;
        for(char idx : s.toCharArray()) {
            if(idx == '(') left++;
            else right++;
        }
        
        if(left == right) return true;
        return false;
    }
    
    private static boolean isCorrect(String s) {
        int left = 0;
        
        for(char idx : s.toCharArray()) {
            if(idx == ')' && left == 0) return false;
            if(idx == ')' && left > 0) left--;
            if(idx == '(') left++;
        }
        
        return false;
    }
    
}