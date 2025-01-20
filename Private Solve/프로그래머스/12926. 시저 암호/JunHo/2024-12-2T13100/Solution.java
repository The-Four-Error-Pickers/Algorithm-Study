class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            
            if(temp == ' ') sb.append(' ');
            else if((int) temp >= 97) {
                if((temp + n) > 122) sb.append((char) (97 + (temp + n - 122 - 1)));
                else sb.append((char) (temp + n));
            } else {
                if((temp + n) > 90) sb.append((char) (65 + (temp + n - 90 - 1)));
                else sb.append((char) (temp + n));
            }
        }
            
        return sb.toString();
    }
}