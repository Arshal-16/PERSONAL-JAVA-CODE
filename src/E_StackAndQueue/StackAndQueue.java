package E_StackAndQueue;

import java.util.*;

public class StackAndQueue {

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
    class StackUsingLL {
        LinkedList<Integer> ll = new LinkedList<>();

        boolean isEmpty() {
            return ll.isEmpty();
        }

        void push(int data) {
            ll.addFirst(data);
        }

        int pop() {
            if (isEmpty()) throw new NoSuchElementException();
            return ll.removeFirst();
        }

        int peek() {
            if (isEmpty()) throw new NoSuchElementException();
            return ll.getFirst();
        }


    }

    //IMPLEMENT QUEUE USING LINKED LIST
    class QueueUsingLL {
        LinkedList<Integer> ll = new LinkedList<>();

        boolean isEmpty() {
            return ll.isEmpty();
        }

        void add(int data) {
            ll.addLast(data);
        }

        int remove() {
            if (isEmpty()) throw new NoSuchElementException();
            return ll.removeFirst();
        }

        int peek() {
            if (isEmpty()) throw new NoSuchElementException();
            return ll.getFirst();
        }

    }

    //CHECK FOR BALANCED PARENTHESES
    boolean checkForBalancedParenthesis(String str) {
        if (str == null || str.length() == 0) return true;
        Stack<Character> s = new Stack<>();
        for (int itr = 0; itr < str.length(); itr++) {
            char ch = str.charAt(itr);
            if (ch == '(' || ch == '{' || ch == '[') {
                s.push(ch);
            } else if (ch == ')') {
                if (s.isEmpty() || s.peek() != '(') return false;
                s.pop();
            } else if (ch == '}') {
                if (s.isEmpty() || s.peek() != '{') return false;
                s.pop();
            } else if (ch == ']') {
                if (s.isEmpty() || s.peek() != '[') return false;
                s.pop();
            }
        }
        //at last stack should be empty if its a valid parenthesis
        return s.isEmpty();

        // why used stack?
        // Because parentheses must be closed in reverseStr order of opening (LIFO), which is exactly what a stack provides.
    }

    //IMPLEMENT MIN STACK
    class MinStack {
        Stack<Integer> sNum = new Stack<>();
        Stack<Integer> sMin = new Stack<>();

        boolean isEmpty() {
            return sNum.isEmpty();
        }

        void push(int data) {
            if (sNum.isEmpty()) {
                sNum.push(data);
                sMin.push(data);
                return;
            }
            sNum.push(data);

            if (Math.min(sMin.peek(), data) == data) sMin.push(data);
            return;
        }

        int pop() {
            if (sNum.isEmpty()) throw new NoSuchElementException();
            int result = sNum.pop();
            if (result == sMin.peek()) sMin.pop();
            return result;
        }

        int peek() {
            if (sNum.isEmpty()) throw new NoSuchElementException();
            return sNum.peek();
        }

        int getMin() {
            if (sMin.isEmpty()) throw new NoSuchElementException();
            return sMin.peek();
        }

    }

    /// /////////////// PREFIX, INFIX, POSTFIX /////////////////

    //NOTE: ALL THE CODE WE WROTE IS VALID WHEN INPUT CONTAINS CHAR AND NOT DIGITS, TO MAKE IT VALID FOR DIGITS
    //USE str.split(" ") TO EXTRACT THE DIGITS AND THEN ITERATE ON THE ARRAY AND SOLVE

    //INFIX TO POSTFIX
    String infixToPostfix(String infix) {
        if (infix == null || infix.length() == 0) return infix;

        Stack<Character> operator = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            // If operand
            if (Character.isLetterOrDigit(ch)) {
                ans.append(ch);
            }

            // If '('
            else if (ch == '(') {
                operator.push(ch);
            }

            // If ')'
            else if (ch == ')') {
                while (!operator.isEmpty() && operator.peek() != '(') {
                    ans.append(operator.pop());
                }
                if (!operator.isEmpty()) {
                    operator.pop();
                }
            }

            // If operator
            else {
                while (!operator.isEmpty() && operator.peek() != '(' && (priority(ch) < priority(operator.peek()) || (priority(ch) == priority(operator.peek()) && ch != '^'))) { //^ is right associative

                    ans.append(operator.pop());
                }
                operator.push(ch);
            }
        }

        while (!operator.isEmpty()) {
            ans.append(operator.pop());
        }

        return ans.toString();
    }

    int priority(char optr) {
        if (optr == '^') return 3;
        else if (optr == '*' || optr == '/') return 2;
        else if (optr == '+' || optr == '-') return 1;
        return -1;
    }

    //INFIX TO PREFIX
    String infixToPrefix(String infix) {
        if (infix == null || infix.length() == 0) return infix;
        //reverseStr the infix
        infix = reverseStr(infix);
        //swap the ( and ) brackets
        infix = swapBrackets(infix);
        //apply infix to postfix logic but handle ^ right associativity
        Stack<Character> operator = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            // If operand
            if (Character.isLetterOrDigit(ch)) {
                ans.append(ch);
            }

            // If '('
            else if (ch == '(') {
                operator.push(ch);
            }

            // If ')'
            else if (ch == ')') {
                while (!operator.isEmpty() && operator.peek() != '(') {
                    ans.append(operator.pop());
                }
                if (!operator.isEmpty()) {
                    operator.pop();
                }
            }

            // If operator
            else {
                while (!operator.isEmpty() && operator.peek() != '(' && (priority(ch) < priority(operator.peek()) || (priority(ch) == priority(operator.peek()) && ch == '^'))) { //^ is right associative

                    ans.append(operator.pop());
                }
                operator.push(ch);
            }
        }

        while (!operator.isEmpty()) {
            ans.append(operator.pop());
        }

        return reverseString(ans.toString());
    }

    String reverseString(String str) {
        StringBuilder sb = new StringBuilder(str);
        int start = 0, end = sb.length() - 1;
        while (start < end) {
            char temp = sb.charAt(end);
            sb.setCharAt(end, sb.charAt(start));
            sb.setCharAt(start, temp);
            start++;
            end--;
        }
        return sb.toString();
    }

    String swapBrackets(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                sb.setCharAt(i, ')');
            } else if (sb.charAt(i) == ')') {
                sb.setCharAt(i, '(');
            }
        }
        return sb.toString();
    }

    //POSTFIX TO INFIX
    String postfixToInfix(String str) {
        if (str == null || str.length() == 0) return str;

        Stack<String> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            //if char is operand
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                s.push(String.valueOf(ch));
            } else { //ch will be operator
                String top2 = s.pop();
                String top1 = s.pop();
                String ans = "(" + top1 + ch + top2 + ")";
                s.push(ans);
            }
        }
        return s.pop();
    }

    //PREFIX TO INFIX
    String prefixToInfix(String str) {
        if (str == null || str.length() == 0) return str;

        Stack<String> s = new Stack<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            //if ch is operand
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                s.push(String.valueOf(ch));
            } else { // it will be an operator
                String top1 = s.pop();
                String top2 = s.pop();
                String ans = "(" + top1 + ch + top2 + ")";
                s.push(ans);
            }
        }
        return s.pop();
    }

    //POSTFIX TO PREFIX
    String postfixToPrefix(String str) {
        if (str == null || str.length() == 0) return str;

        Stack<String> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                s.push(String.valueOf(ch));
            } else { //it will be an operator
                String top2 = s.pop();
                String top1 = s.pop();
                s.push(ch+top1  + top2);
            }
        }
        return s.pop();
    }

    //PREFIX TO POSTFIX
    String prefixToPostfix(String str) {
        if (str == null || str.length() == 0) return str;

        Stack<String> s = new Stack<>();
        for (int i = str.length()-1; i >=0; i--) {
            char ch = str.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                s.push(String.valueOf(ch));
            } else { //it will be an operator
                String top1 = s.pop();
                String top2 = s.pop();
                s.push(top1  + top2+ch);
            }
        }
        return s.pop();
    }

    ////////////////// MONOTONIC STACK/QUEUE PROBLEMS //////////////////////////
    /*
 A stack is monotonic if its elements are always arranged in a consistent order:
Monotonic increasing stack: elements from bottom → top are increasing
Monotonic decreasing stack: elements from bottom → top are decreasing
Whenever you try to push a new value that would break the order, you pop elements until the order is restored.

A queue is monotonic if its elements are always ordered:
Monotonic increasing queue: front → back is increasing
Monotonic decreasing queue: front → back is decreasing
*/

    //NORMAL NEXT GREATER ELEMENT
    public int[] nextGreaterElement(int A[]){
        int soln[] = new int[A.length];
        Stack<Integer> s = new Stack<>();
        for(int i=A.length-1;i>=0;i--){
            int curr = A[i];
            while(!s.isEmpty() && s.peek()<=curr) s.pop();
            if(s.isEmpty()){
                soln[i] = -1;
            }else {
                soln[i] = s.peek();
            }
            s.push(curr);
        }
        return soln;
    }

    //CLOCKWISE NEXT GREATER ELEMENT (NGE-2)

    //brute
//    public int[] nextGreaterElement2(int arr[]){
//        if(arr.length==0) return new int[]{};
//        int nge[] = new int[arr.length];
//        for(int i=0;i<nge.length;i++) nge[i]= -1;
//        // double the arr mentally like [1,2,3,4,5] will become [1,2,3,4,5,1,2,3,4,5]
//       for(int  i = 0;i<arr.length;i++){
//           for(int j=i+1;j<=i+arr.length-1;j++){
//               int idx = j%arr.length;
//               if(arr[idx]>arr[i]){
//                   nge[i] = arr[idx];
//                   break;
//               }
//           }
//       }
//       return nge;
//    }

    //OPTIMAL
    public int[] nextGreaterElement2(int arr[]){
        if(arr==null || arr.length==0) return new int[]{};
        Stack<Integer> s = new Stack<>();
        int nge[]= new int[arr.length];
        int n = arr.length;
        // double the arr hypothetically like [1,2,3,4,5] will become [1,2,3,4,5,1,2,3,4,5], index of last ele is 2n-1
        for(int i = 2*n-1;i>=0;i--){
            int curr = arr[i%n];
            while(!s.isEmpty()&& s.peek()<=curr) s.pop();
            if(i<n){
                if(s.isEmpty()){
                    nge[i]=-1;
                }else{
                    nge[i]=s.peek();
                }

            }
            s.push(curr);
        }
        return nge;
    }

    //NEXT SMALLER ELEMENT
    public int[] nextSmallerElement(int arr[]){
        if(arr==null || arr.length==0) return arr;
        int nse[]  = new int[arr.length];
        Stack<Integer> s = new Stack<>();
        for(int i = arr.length-1;i>=0;i--){
            int curr = arr[i];
            while(!s.isEmpty() && s.peek()>=curr) s.pop();

            if(s.isEmpty()) nse[i]=-1;
            else {
                nse[i]=s.peek();
            }
            s.push(curr);
        }
        return nse;
    }

    //NUMBER OF NGEs TO THE RIGHT
    public int[] numberOfNGEs(int arr[]){
        if(arr==null || arr.length==0) return arr;

        int numOfnge[] = new int[arr.length];

        return numOfnge;
    }

    //TRAPPING RAINWATER USING STACK

        //using array (prefix-suffix maximum)
        public int waterTrapped(int arr[]){
        if(arr==null || arr.length==0) return 0;
        int largestBefore[]=new int[arr.length];
        int largestAfter[]= new int[arr.length];
        largestBefore[0]=largestAfter[arr.length-1]=0;
        //find largest behind
        for(int i=1;i<arr.length;i++){
            largestBefore[i]=Math.max(largestBefore[i-1],arr[i-1]);
        }
        //find largest after
        for(int i=arr.length-2;i>=0;i--){
            largestAfter[i]=Math.max(largestAfter[i+1],arr[i+1]);
        }

        //find total water trapped
        int TotalTrapped=0;
        for(int i=0;i<arr.length;i++){
            int min = Math.min(largestAfter[i],largestBefore[i]);
            int trapped = min-arr[i];
            TotalTrapped+= trapped>0?trapped:0;
        }
        return TotalTrapped;
    }
        //here S.C. canbe taken to n from 2n, but most optimal is O(1)

        //using stack


}



