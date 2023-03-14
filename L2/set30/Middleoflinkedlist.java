package set30;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

class LinkedList {
    Node head;

    // Find the middle element of the list
    Node findMiddle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    void insert(int data, int position) {
        Node newNode = new Node(data);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }


    // Sort the list in ascending order
    public void sort()
    {

        // Node current will point to head
        Node current = head, index = null;

        int temp;

        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.next;

                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (current.data > index.data) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }

                    index = index.next;
                }
                current = current.next;
            }
        }
    }


    // Reverse the list
    void reverse() {
        Node current = head;
        Node previous = null;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        head = previous;
    }
    void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    void removeDuplicates() {
        if (head == null) {
            return;
        }

        Node curr = head;
        while (curr != null) {
            Node innerCurr = curr;
            while (innerCurr.next != null) {
                if (curr.data == innerCurr.next.data) {
                    innerCurr.next = innerCurr.next.next;
                } else {
                    innerCurr = innerCurr.next;
                }
            }
            curr = curr.next;
        }
    }

    // Remove duplicates from the list
    void removeDuplicatess() {
        Node current = head;
        Node previous = null;
        Node next = null;
        Set<Integer> set = new HashSet<>();

        while (current != null) {
            next = current.next;
            if (set.contains(current.data)) {
                // Remove the current node
                previous.next = next;
            } else {
                set.add(current.data);
                previous = current;
            }
            current = next;
        }
    }
}

class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        for (int i=0;i<6;i++)
        {
            list.insert(i);
        }
        list.insert(3);


        System.out.println("Original list:");
        list.printList();

        System.out.println("Middle element: " + list.findMiddle().data);


        list.sort();
        System.out.println("Sorted list:");
        list.printList();

        list.reverse();
        System.out.println("Reversed list:");
        list.printList();

        list.removeDuplicates();
        System.out.println("List with duplicates removed:");
        list.printList();
    }
}
