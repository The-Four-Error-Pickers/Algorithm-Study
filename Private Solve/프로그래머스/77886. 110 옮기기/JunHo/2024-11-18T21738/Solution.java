class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i = 0; i < s.length; i++) {
            char[] word = new char[s[i].length()];
            int idx = 0;
            int count = 0;
            

            for(int j = 0; j < word.length; j++) {
                if((s[i].charAt(j) == '0') && (idx >= 2) && 
                   (word[idx - 1] == '1') && (word[idx - 2] == '1')) {
                    idx -= 2;
                    count++;
                } else {
                    word[idx++] = s[i].charAt(j);
                }
            }
            
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < idx; j++) {
                sb.append(word[j]);
            }
            
            StringBuilder temp = new StringBuilder();
            for(int j = 0; j < count; j++) {
                temp.append("110");
            }
            
            int zeroIndex = sb.lastIndexOf("0");
          
            if(zeroIndex < 0) {
                sb.insert(0, temp.toString());
            }
            else {
                sb.insert(zeroIndex + 1, temp.toString());
            }
            
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}