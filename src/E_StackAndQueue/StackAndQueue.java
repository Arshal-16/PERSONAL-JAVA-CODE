package E_StackAndQueue;

import java.util.*;

public class StackAndQueue {

    // STACK IMPLEMENTATION USING ARRAYLIST
    class StackUsingArrayList {
        ArrayList<Integer> al = new ArrayList<>();
        int top = -1;

        //isEmpty
        boolean isEmpty() {
            if (top == -1) return true;
            return false;
        }

        //size
        int size() {
            return top + 1;
        }

        //push
        void push(int data) {
            al.add(data);
            top++;
        }

        //pop
        int pop() {
            if (isEmpty()) return -1;
            int result = al.get(top);
            al.remove(top);
            top--;
            return result;
        }

        //peek
        int peek() {
            if (isEmpty()) return -1;
            return al.get(top);
        }


    }

    //STACK IMPLEMENTATION USING LINKED LIST
    class StackUsingLinkedList {
        LinkedList<Integer> ll = new LinkedList<>();

        //isEmpty
        boolean isEmpty() {
            return ll.isEmpty();
        }

        //push
        void push(int data) {
            ll.addFirst(data);
        }

        //pop
        int pop() {
            if (ll.isEmpty()) throw new EmptyStackException();
            return ll.removeFirst();
        }

        //peek
        int peek() {
            if (ll.isEmpty()) throw new EmptyStackException();

            return ll.getFirst();
        }


    }


    //QUEUE IMPLEMENTATION USING LL
    class QueueUsingLL {
        LinkedList<Integer> ll = new LinkedList<>();

        boolean isEmpty() {
            return ll.isEmpty();
        }

        void push(int data) {
            ll.addLast(data);
        }

        int remove() {
            if (ll.isEmpty()) {
                System.out.println("QUEUE IS EMPTY");
                return -1;
            }
            return ll.removeFirst();
        }

        int peek() {
            if (ll.isEmpty()) {
                System.out.println("QUEUE IS EMPTY");
                return -1;
            }
            return ll.getFirst();
        }
    }


    /// /////////////////////// QUESTIONS //////////////////////////////

    /// /////////////// LEARNING /////////////////

    //IMPLEMENT STACK USING ARRAYS
    class StackUsingArray {
        int arr[] = new int[50];
        int top = -1;

        boolean isEmpty() {
            return top == -1;
        }

        void push(int data) {
            if (top == arr.length - 1) return;
            arr[++top] = data;
        }

        int peek() {
            if (top == -1) return Integer.MAX_VALUE;
            return arr[top];
        }

        int pop() {
            if (top == -1) return Integer.MAX_VALUE;
            return arr[top--];
        }
    }

    //IMPLEMENT QUEUE USING ARRAY
    class QueueUsingArray {
        int arr[];
        int front, rear, size;

        QueueUsingArray(int size) {
            arr = new int[size];
            front = rear = -1;
            this.size = size;
        }

        boolean isEmpty() {
            return front == -1;
        }

        void add(int data) {

            //if queue is full
            if ((rear + 1) % size == front) return;

            rear = (rear + 1) % size;
            arr[rear] = data;
            //if queue is empty
            if (front == -1) front = 0;
        }

        int remove() {
            if (isEmpty()) return Integer.MAX_VALUE;
            int result = arr[front];
            //if last ele is being removed
            if (front == rear) front = rear = -1;
            else front = (front + 1) % size;
            return result;
        }

        int peek() {
            if (isEmpty()) return Integer.MAX_VALUE;
            return arr[front];
        }
    }

    //IMPLEMENT STACK USING QUEUE
    class StackUsingQueue {
        Queue<Integer> q = new ArrayDeque<>();

        boolean isEmpty() {
            return q.isEmpty();
        }

        void push(int data) {
            q.add(data);
            for (int i = 0; i < q.size() - 1; i++) {
                q.add(q.remove());
            }
        }

        int peek() {
            if (isEmpty()) return Integer.MAX_VALUE;
            return q.peek();
        }

        int pop() {
            if (isEmpty()) return Integer.MAX_VALUE;
            return q.remove();
        }

    }

    //IMPLEMENT QUEUE USING TWO STACKS
    class QueueUsingStack {
        Stack<Integer> sMain = new Stack<>();
        Stack<Integer> sSecondary = new Stack<>();

        boolean isEmpty() {
            return sMain.isEmpty();
        }

        void add(int data) {
            while (!sMain.isEmpty()) {
                sSecondary.push(sMain.pop());
            }
            sMain.push(data);
            while (!sSecondary.isEmpty()) {
                sMain.push(sSecondary.pop());
            }
        }

        int remove() {
            if (sMain.isEmpty()) return Integer.MAX_VALUE;
            return sMain.pop();
        }

        int peek() {
            if (sMain.isEmpty()) return Integer.MAX_VALUE;
            return sMain.peek();
        }


    }

    //IMPLEMENT STACK USING LINKED LIST


}



