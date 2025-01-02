import java.util.*;

class Solution {
    
    static class Node {
        Node prev;  // 이전 노드를 가리키는 변수
        Node next;  // 다음 노드를 가리키는 변수
        boolean removed;    // 삭제됐는지 확인하는 변수, 이후 result에 사용하기 위함
        
        Node(Node prev, Node next, boolean removed) {
            this.prev = prev;
            this.next = next;
            this.removed = removed;
        }
        
        Node up(int count) {    // 포인터 위로 이동
            Node current = this;
            for(int i = 0; i < count; i++) current = current.prev;
            return current;
        }
        
        Node down(int count) {  // 포인터 아래로 이동
            Node current = this;
            for(int i = 0; i < count; i++) current = current.next;
            return current;
        }
        
        Node delete() { // 행 삭제
            Node current = this;
            this.removed = true;
            if(current.prev != null) current.prev.next = current.next;
            if(current.next != null) current.next.prev = current.prev;
            
            if(current.next != null) return current.next;   // 포인터 이동
            else return current.prev;
        }
        
        void z() {  // 행 복구
            Node current = this;
            this.removed = false;
            
            if(current.prev != null) current.prev.next = current;
            if(current.next != null) current.next.prev = current;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {
        Stack<Node> stack = new Stack<>();  // 삭제된 노드를 담는 Stack
        StringBuilder sb = new StringBuilder();
        Node[] linkedList = new Node[n];    // 연결리스트 구성
        linkedList[0] = new Node(null, null, false);
        
        for(int i = 1; i < n; i++) {
            linkedList[i] = new Node(null, null, false);
            linkedList[i - 1].next = linkedList[i];
            linkedList[i].prev = linkedList[i - 1];
        }
        
        Node temp = linkedList[k];
        StringTokenizer st;
        for(int i = 0; i < cmd.length; i++) {
            st = new StringTokenizer(cmd[i], " ");
            String s = st.nextToken();
            int x = 0;
            switch(s) {
                case "U":
                    x = Integer.parseInt(st.nextToken());
                    temp = temp.up(x);
                    break;
                case "D":
                    x = Integer.parseInt(st.nextToken());
                    temp = temp.down(x);
                    break;
                case "C":
                    stack.push(temp);
                    temp = temp.delete();
                    break;
                case "Z":
                    stack.pop().z();
                    break;
            }
        }
        
        for(Node node : linkedList) {
            if(node.removed) sb.append('X');
            else sb.append('O');
        }
        
        return sb.toString();
    }
}