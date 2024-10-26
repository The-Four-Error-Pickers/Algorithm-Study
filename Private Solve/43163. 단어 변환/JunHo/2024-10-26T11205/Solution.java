import java.util.*;

class Solution {
    
    static class Node {
        String word;
        int count;
        
        Node(String word, int count) {
            this.word = word; 
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        q.offer(new Node(begin, 0));
        
        while(!q.isEmpty()) {
            Node temp = q.poll();
            
            if(temp.word.equals(target)) {
                return temp.count;
            }
            
            for(int i = 0; i < words.length; i++) {
                if(visited[i]) continue;
                if(check(temp.word, words[i])) {
                    visited[i] = true;
                    q.offer(new Node(words[i], temp.count + 1));
                }
            }
        }
        
        return 0;
    }
    
    public boolean check(String word1, String word2) {
        int count = 0;
        for(int i = 0; i < word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) count++;
        }
        
        return count == 1;
    }
}