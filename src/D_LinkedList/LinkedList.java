package D_LinkedList;

public class LinkedList {
    class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node Head, Tail;
    int size = 0;

    /// /////////// methods ///////////////////
    //addFirst
    public void addFirst(int data) {
        //Step 1:create New Node
        Node newNode = new Node(data);
        if (Head == null) {
            //when linked list is empty
            Head = Tail = newNode;
            size++;
            return;
        }
        //Step 2:New Node's next will point to previous head
        newNode.next = Head;
        //Step 3:Set head to this node
        Head = newNode;
        size++;
    }

    //addLast
    public void addLast(int data) {
        //Step 1:create new node
        Node newNode = new Node(data);

        if (Head == null) {
            Head = Tail = newNode;
            size++;
            return;
        }
        //Step 2:link next of prev tail to this new node
        Tail.next = newNode;
        //Step 3:reinitialize the tail
        Tail = newNode;
        size++;
    }

    //print a LinkedList
    public void print() {
        if (Head == null) {
            System.out.println("Linked List is empty.");
            return;
        }
        Node temp = Head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    //add(int idx,int data)
    public void add(int idx, int data) {

        if (idx < 0) {
            System.out.println("Invalid index");
            return;
        }

        Node newNode = new Node(data);

        // insert at head
        if (idx == 0) {
            if (Head == null) {
                Head = Tail = newNode;
            } else {
                newNode.next = Head;
                Head = newNode;
            }
            size++;
            return;
        }

        Node temp = Head;
        int cIdx = 0;

        while (cIdx < idx - 1 && temp != null) {
            temp = temp.next;
            cIdx++;
        }

        if (temp == null) {
            System.out.println("Index out of bounds");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;

        // update tail if inserted at end
        if (newNode.next == null) {
            Tail = newNode;
        }
        size++;
    }

    //removeFirst()
    public void removeFirst() {
        if (size == 0) {
            return;
        }
        if (size == 1) {
            Head = Tail = null;
        } else {
            Head = Head.next;
        }
        size--;
    }

    //removeLast()
    public void removeLast() {
        if (size == 0) return;
        if (size == 1) {
            Head = Tail = null;
        } else {
            Node temp = Head;
            while (temp.next != Tail) {
                temp = temp.next;
            }
            temp.next = null;
            Tail = temp;
        }
        size--;
    }

    //search(key) iterative
    public int search(int key) {
        if (size == 0) return -1;
        Node temp = Head;
        for (int idx = 0; idx < size; idx++) {
            if (temp.data == key) return idx;
            temp = temp.next;
        }
        /*
        More robust:
        int idx=0;
        while(temp!=null){
            if(temp.data==key) return idx;
            temp=temp.next;
            idx++;
        }
        */
        return -1;
    }

    //search recursive
    public int search(Node head, int key) {
        //base case
        if (head == null) return -1;
        //main logic
        if (head.data == key) return 0;
        //recursion
        int next = search(head.next, key);
        return next != -1 ? next + 1 : next;
    }

    //reverse the linked list
    public void reverse() {
        if (Head == null) return;

        Node prev = null;
        Node current = Tail = Head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        Head = prev;
    }

    //merge sort on linked list ( T.C = O(NlogN) and S.C. = O(1))
    public Node mergeSort(Node head) {
        //base case
        if (head == null || head.next == null) return head;
        //find mid
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        //ms left and right
        Node newLeftHead = mergeSort(head);
        Node newRightHead = mergeSort(rightHead);
        //merge sorted left and right parts
        return merge(newLeftHead, newRightHead);
    }

    public Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public Node merge(Node leftHead, Node rightHead) {
        // -1 is used as a dummy node , later it will be removed
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;
        while (leftHead != null && rightHead != null) {
            if (leftHead.data < rightHead.data) {
                temp.next = leftHead;
                temp = temp.next;
                leftHead = leftHead.next;
            } else {
                temp.next = rightHead;
                temp = temp.next;
                rightHead = rightHead.next;
            }
        }
        while (leftHead != null) {
            temp.next = leftHead;
            temp = temp.next;
            leftHead = leftHead.next;
        }
        while (rightHead != null) {
            temp.next = rightHead;
            temp = temp.next;
            rightHead = rightHead.next;
        }
        return mergedLL.next;
    }

    /////////////////////////////////////////////////////////////////////////////


    /// ////// MEDIUM PROBLEMS OF LL /////////////////

    // REVERSE THE LL ITERATIVE
    public Node reverseIterative(Node head) {
        if (head == null || head.next == null) return head;
        Node prev = null, current = head, next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;

    }

    //REVERSE THE LL RECURSIVE
    public Node reverseRecursive(Node head) {
        //base case
        if (head == null || head.next == null) return head;
        //recursive call
        Node newHead = reverseRecursive(head.next);

        Node front = head.next;
        front.next = head;
        head.next = null;
        return newHead;

    }

    //DETECT A CYCLE IN LINKED LIST
    public boolean isCycle(Node head) {
        if (head == null || head.next == null) return false;

        Node slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; //+1
            fast = fast.next.next; //+2
            if (slow == fast) return true;
        }
        return false;
    }

    //STARTING POINT OF LOOP IN LL
    public Node loopStart(Node head) {
        if (head == null || head.next == null) return null;
        boolean flag = false;
        Node slow, fast;
        slow = fast = head;
        //floyd's cycle detection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                flag = true;
                break;
            }
        }
        if (!flag) return null;

        //finding starting point of cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        //slow and fast both will be pointing to start of cycle
        return slow;
    }

    //LENGTH OF LOOP IN LINKED LIST
    public int loopLength(Node head){
        if (head == null || head.next == null) return 0;
        boolean flag = false;
        Node slow, fast;
        slow = fast = head;
        //floyd's cycle detection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                flag = true;
                break;
            }
        }
        if (!flag) return 0;
        //finding starting point of cycle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        //slow and fast both will be pointing to start of cycle
        Node temp= slow;
        //length 1 because slow is also a node of cycle
        int length= 1;
        while(temp.next!=slow){
            temp=temp.next;
            length++;
        }
        return length;
    }

    //CHECK IF THE GIVEN LL IS PALINDROME
    public boolean isPalindrome(Node head){
        if(head==null || head.next==null) return true;

        // finding mid of ll
        Node slow,fast;
        slow=fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        //(for odd size LL slow will point to mid, for even size LL slow will point to second mid of LL)
        // now reverse the second half
        Node current,prev,next;
        current=slow;
        prev=null;
        while(current!=null){
            next=current.next;
            current.next=prev;
            prev=current;
            current=next;
        }
        Node rightHead=prev;
        Node leftHead= head;

        // checking if palindrome
        while(rightHead!=null && leftHead!=null){
            if(rightHead.data!=leftHead.data) return false;
            rightHead=rightHead.next;
            leftHead=leftHead.next;
        }
        return true;
    }



}