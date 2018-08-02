package me.ervin.test;

/**
 * Description: ListReverse
 *
 * @author ervin
 * @version 2018-07-2918:50
 */
public class ListReverse {
    public static class Node {
        public int value;
        public Node next;

        public Node() {
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node head = null;

        for (int i = 0; i < 10; i++) {
            head = new Node(i, head);
        }

        Node cur = head;
        while (cur != null) {
            System.out.println("args = [" + cur.value + "]");
            cur = cur.next;
        }

        Node reversedHead = reverse(head);

        while (reversedHead != null) {
            System.out.println("args = [" + reversedHead.value + "]");
            reversedHead = reversedHead.next;
        }
    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
