package E_StackAndQueue;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

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

    //QUEUE IMPLEMENTATION USING ARRAY (circular queue)
    class QueueUsingArray {
        int arr[];
        int size;
        int start = -1, rear = -1;

        //constructor
        QueueUsingArray(int size) {
            arr = new int[size];
            this.size = size;
        }

        //isEmpty
        boolean isEmpty() {
            return start == -1;
        }

        //add
        void add(int data) {
            //if queue is full
            if ((rear + 1) % size == start) return;
            //if first element is being added
            if (start == -1) start = 0;

            rear = (rear + 1) % size;
            arr[rear] = data;
        }

        //remove
        int remove() {
            if (isEmpty()) return -1;

            int data = arr[start];
            //if last element is being removed
            if (start == rear) {
                start = rear = -1;
                return data;
            }
            start = (start + 1) % size;
            return data;
        }

        //peek
        int peek() {
            if (isEmpty()) return -1;

            return arr[start];
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


        /// ////////////// QUESTIONS /////////////////////
        //NEXT GREATER ELEMENT
        //(APPROACH: MONOTONIC DECREASING STACK :ELEMENTS IN THE STACK (BOTTOM → TOP) ARE ALWAYS IN DECREASING ORDER)
        public int[] nextGreaterElement(int nums[]) {
            //edge case
            if (nums == null || nums.length == 0) return new int[0];

            //main concept
            Stack<Integer> s = new Stack<>();  //in this we will push indx of elements
            int result[] = new int[nums.length];
            for (int i = nums.length - 1; i >= 0; i--) {
                //removing indx of all elements which are smaller than curr ele
                while (!s.isEmpty() && nums[i] >= nums[s.peek()]) {
                    s.pop();
                }
                result[i] = s.isEmpty() ? -1 : nums[s.peek()];
                //adding current ele to stack
                s.push(i);
            }
            // result[i] will store the next greater element of ele at nums[i]

            return result;

        }


    }
