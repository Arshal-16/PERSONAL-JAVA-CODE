package E_StackAndQueue;

import java.util.*;

public class StackAndQueue {

    ////////////////// LEARNING /////////////////

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
    class StackUsingLL{
        LinkedList<Integer> ll = new LinkedList<>();

        boolean isEmpty(){
            return ll.isEmpty();
        }
        void push(int data){
            ll.addFirst(data);
        }
        int pop(){
            if(isEmpty()) throw new NoSuchElementException();
            return ll.removeFirst();
        }
        int peek(){
            if(isEmpty()) throw new NoSuchElementException();
            return ll.getFirst();
        }


    }

    //IMPLEMENT QUEUE USING LINKED LIST
    class QueueUsingLL{
    LinkedList<Integer> ll = new LinkedList<>();
    boolean isEmpty(){
        return ll.isEmpty();
    }
    void add(int data){
        ll.addLast(data);
    }
    int remove(){
        if (isEmpty()) throw new NoSuchElementException();
        return ll.removeFirst();
    }
    int peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return ll.getFirst();
    }

    }

    //CHECK FOR BALANCED PARENTHESES
    boolean checkForBalancedParenthesis(String str){
        if(str==null || str.length()==0) return true;
        Stack<Character> s = new Stack<>();
        for(int itr = 0;itr<str.length();itr++){
            char ch= str.charAt(itr);
            if(ch=='(' || ch=='{' || ch=='['){
                s.push(ch);
            }else if(ch==')'){
                if(s.isEmpty() || s.peek()!='(') return false;
                s.pop();
            }else if(ch=='}'){
                if(s.isEmpty() || s.peek()!='{') return false;
                s.pop();
            }else if(ch==']'){
                if(s.isEmpty() || s.peek()!='[') return false;
                s.pop();
            }
        }
        //at last stack should be empty if its a valid parenthesis
        return s.isEmpty();

        // why used stack?
        // Because parentheses must be closed in reverse order of opening (LIFO), which is exactly what a stack provides.
    }

    //IMPLEMENT MIN STACK
    class MinStack{
        Stack<Integer> sNum = new Stack<>();
        Stack<Integer> sMin = new Stack<>();

        boolean isEmpty(){
            return sNum.isEmpty();
        }

        void push(int data){
            if(sNum.isEmpty()){
                sNum.push(data);
                sMin.push(data);
                return;
            }
            sNum.push(data);

            if( Math.min(sMin.peek(),data) == data) sMin.push(data);
            return;
        }
        int pop(){
            if(sNum.isEmpty()) throw new NoSuchElementException();
            int result = sNum.pop();
            if(result == sMin.peek()) sMin.pop();
            return result;
        }

        int peek(){
            if(sNum.isEmpty()) throw new NoSuchElementException();
            return sNum.peek();
        }
        int getMin(){
            if(sMin.isEmpty()) throw new NoSuchElementException();
            return sMin.peek();
        }

    }



}



