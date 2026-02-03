package E_StackAndQueue;

import java.util.Stack;

public class StackAndQueue {

    //NEXT GREATER ELEMENT
    //(APPROACH: MONOTONIC DECREASING STACK :ELEMENTS IN THE STACK (BOTTOM → TOP) ARE ALWAYS IN DECREASING ORDER)
    public int[] nextGreaterElement(int nums[]){
        //edge case
        if(nums==null || nums.length==0) return new int[0];

        //main concept
        Stack<Integer> s = new Stack<>();  //in this we will push indx of elements
        int result[] = new int[nums.length];
        for(int i=nums.length-1;i>=0;i--){
            //removing indx of all elements which are smaller than curr ele
            while(!s.isEmpty()&&nums[i]>=nums[s.peek()]){
                s.pop();
            }
            result[i]=s.isEmpty()?-1:nums[s.peek()];
            //adding current ele to stack
            s.push(i);
        }
        // result[i] will store the next greater element of ele at nums[i]

        return result;

    }



}
