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

    public  Node Head, Tail;
    int size=0;

    ////////////// methods ///////////////////
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
    public void addLast(int data){
        //Step 1:create new node
        Node newNode = new Node(data);

        if(Head==null){
            Head=Tail=newNode;
            size++;
            return;
        }
        //Step 2:link next of prev tail to this new node
        Tail.next=newNode;
        //Step 3:reinitialize the tail
        Tail=newNode;
        size++;
    }

    //print a LinkedList
    public void print(){
        if(Head==null){
            System.out.println("Linked List is empty.");
            return;
        }
        Node temp = Head;
        while(temp!=null){
            System.out.print(temp.data+" ");
            temp=temp.next;
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


}
