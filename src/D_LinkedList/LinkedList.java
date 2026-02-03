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
            if (slow == fast) {
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
    public int loopLength(Node head) {
        if (head == null || head.next == null) return 0;
        boolean flag = false;
        Node slow, fast;
        slow = fast = head;
        //floyd's cycle detection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
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
        Node temp = slow;
        //length 1 because slow is also a node of cycle
        int length = 1;
        while (temp.next != slow) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    //CHECK IF THE GIVEN LL IS PALINDROME
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // finding mid of ll
        Node slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //(for odd size LL slow will point to mid, for even size LL slow will point to second mid of LL)
        // now reverse the second half
        Node current, prev, next;
        current = slow;
        prev = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        Node rightHead = prev;
        Node leftHead = head;

        // checking if palindrome
        while (rightHead != null && leftHead != null) {
            if (rightHead.data != leftHead.data) return false;
            rightHead = rightHead.next;
            leftHead = leftHead.next;
        }
        return true;
    }

    //SEGREGATE EVEN AND ODD NODES IN A LL
    public Node segregateEvenOdd(Node head) {
        if (head == null || head.next == null) return head;

        Node OddHead = head;
        Node EvenHead = head.next;
        Node tempOdd = OddHead;
        Node tempEven = EvenHead;
        Node evenTail = tempEven;

        while (tempEven != null && tempEven.next != null) {
            //step 1
            tempOdd.next = tempOdd.next.next;
            //step 2
            tempEven.next = tempEven.next.next;
            //step 3
            evenTail = tempEven;
            tempEven = tempEven.next;
            tempOdd = tempOdd.next;
        }
        evenTail.next = OddHead;
        tempOdd.next = null;

        return EvenHead;
    }

    //REMOVE Nth NODE FROM THE END OF LL
    public Node deleteNthFromLast(Node head, int n) {
        if (head == null) return null;

        Node fast = head;
        Node slow = head;

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast == null) return head; // n > size
            fast = fast.next;
        }

        // If fast is null, we need to delete the head
        if (fast == null) {
            return head.next;
        }

        // Move both pointers until fast reaches the last node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete nth node from end, ( slow will be just behind the node we want to delete)
        slow.next = slow.next.next;

        return head;
    }

    //DELETE THE MIDDLE NODE OF THE LINKED LIST
    public Node deleteMiddle(Node head) {
        if (head == null || head.next == null) return null;
        Node slow = head;
        Node fast = head;
        Node behindMiddle = head;
        while (fast != null && fast.next != null) {
            behindMiddle = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        behindMiddle.next = behindMiddle.next.next;
        return head;
    }

    //SORT THE LL (MERGE SORT)
    public Node sortLL(Node head) {

        //base case
        if (head == null || head.next == null) return head;

        // LEFT-HEAD---LEFT-PART------RIGHT-HEAD---RIGHT-PART
        Node leftHead = head;
        //finding Middle
        Node mid = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        Node rightHead = mid.next;
        mid.next = null;

        // SORT LEFT AND RIGHT PARTS
        leftHead = sortLL(leftHead);
        rightHead = sortLL(rightHead);

        // MERGE THE SORTED PARTS
        return mergeLL(leftHead, rightHead);
    }

    public Node mergeLL(Node leftHead, Node rightHead) {
        Node newHead = new Node(-1);
        Node temp = newHead;

        while (leftHead != null && rightHead != null) {
            if (leftHead.data <= rightHead.data) {
                temp.next = leftHead;
                leftHead = leftHead.next;
            } else {
                temp.next = rightHead;
                rightHead = rightHead.next;
            }
            temp = temp.next;
        }
        while (leftHead != null) {
            temp.next = leftHead;
            leftHead = leftHead.next;
            temp = temp.next;
        }
        while (rightHead != null) {
            temp.next = rightHead;
            rightHead = rightHead.next;
            temp = temp.next;
        }
        return newHead.next;
    }

    //LL CONTAINS ONLY 0'S, 1'S, AND 2'S, SORT THE LL  BY REARRANGING THE LINKS
    public Node reLinkLL(Node head) {
        if (head == null || head.next == null) return head;

        // Dummy heads
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);

        // Tails
        Node zero = zeroHead;
        Node one = oneHead;
        Node two = twoHead;

        Node temp = head;

        while (temp != null) {
            Node nextNode = temp.next;   // save next
            temp.next = null;            // detach node

            if (temp.data == 0) {
                zero.next = temp;
                zero = zero.next;
            } else if (temp.data == 1) {
                one.next = temp;
                one = one.next;
            } else {
                two.next = temp;
                two = two.next;
            }

            temp = nextNode;
        }

        // connect the lists
        zero.next = (oneHead.next != null) ? oneHead.next : twoHead.next;
        one.next = twoHead.next;

        // decide correct head
        if (zeroHead.next != null) return zeroHead.next;
        if (oneHead.next != null) return oneHead.next;
        return twoHead.next;
    }
    //concept learned "Detach before reattach"

    //FIND INTERSECTION OF TWO LL
    public Node findIntersection(Node headA, Node headB) {
        if (headA == null || headB == null) return null;

        //finding size of both LL
        int sizeA = 0;
        Node temp = headA;
        while (temp != null) {
            sizeA++;
            temp = temp.next;
        }
        int sizeB = 0;
        temp = headB;
        while (temp != null) {
            sizeB++;
            temp = temp.next;
        }
        //make bigger LL size equal to size of smaller LL
        if (sizeA >= sizeB) {
            for (int i = 1; i <= sizeA - sizeB; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 1; i <= sizeB - sizeA; i++) {
                headB = headB.next;
            }
        }
        //now move both heads by 1 , both of them will become null together
        while (headA != null) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }
        return null; // no intersection
    }

    //ADD 1 TO A NUMBER REPRESENTED BY LL
    public Node addOneToNum(Node head) {
        //base case
        if (head == null) return null;
        //reverse the LL
        head = reverseLL(head);

        int carry = 1;
        Node temp = head;
        while (temp != null) {
            int newCurrNum = temp.data + carry;
            carry = 0;
            if (newCurrNum == 10) {
                carry = 1;
                newCurrNum = 0;
            }
            temp.data = newCurrNum;
            if (temp.next == null && carry == 1) {
                Node newNode = new Node(1);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return reverseLL(head);
    }

    public Node reverseLL(Node head) {
        Node prev = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    //ADD TWO NUMBERS REPRESENTED AS LINKED LISTS
    public Node addTwoNum(Node headA, Node headB) {
        if (headA == null) return headB;
        if (headB == null) return headA;

        headA = reverseLL(headA);
        headB = reverseLL(headB);

        Node dummy = new Node(0);
        Node curr = dummy;

        int carry = 0;

        while (headA != null || headB != null || carry != 0) {
            int sum = carry;

            if (headA != null) {
                sum += headA.data;
                headA = headA.next;
            }

            if (headB != null) {
                sum += headB.data;
                headB = headB.next;
            }

            carry = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
        }

        return reverseLL(dummy.next);
    }

}