import java.util.HashMap;
import java.util.NoSuchElementException;

public class Revision {
    //second largest element in an array
    int findSecLargest(int nums[]){
        if (nums== null || nums.length<2) throw new NoSuchElementException("No such element.");
        
        int largest = nums[0];
        int secLargest= Integer.MIN_VALUE;
        for(int num:nums){
            if(num>largest){
                secLargest=largest;
                largest =num;
            } else if (num>secLargest&& num<largest) {
                secLargest = num;
            }
        }
        return secLargest;
    }

    //remove duplicates in place from sorted array
    void removeDuplicatesInPlace(int nums[]){
        if(nums==null || nums.length==0) throw new NoSuchElementException("Array is empty.");
        int ptr=0;
        for(int i=1;i<nums.length;i++){
            if(nums[ptr]!=nums[i]){
                nums[++ptr]=nums[i];
            }
        }
    }

    //rotate arr by k places
    void rotateByK(int nums[], int k){
        //left rotate
            //rev first k ele, rev remaining ele, rev entire array
        //right rotate
            //rev the array, rev first k ele, rev remaining array
    }

    //move zeroes to end
    void moveZeroesToEnd(int nums[]){
        if(nums==null || nums.length==0) return;
        int zeroPointer=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                zeroPointer=i;
                break;
            }
        }
        if(zeroPointer==-1 || zeroPointer==nums.length-1) return;
        for(int j=zeroPointer+1;j<nums.length;j++){
            if(nums[j]!=0){
                nums[zeroPointer++]=nums[j];
                nums[j]=0;
            }
        }
    }

    //find the missing number XOR approach
    int findTheMissingNumber(int nums[]){
        if(nums==null || nums.length==0) return -1;
        int xor1= 0; //xor of numbers from 1 to n
        int xor2 = 0; //xor of all numbers in array

        for(int i=0;i<nums.length;i++){
            xor2^=nums[i];
            xor1^=i+1; //this will go from 1 to (n-1)
        }
        xor1^=nums.length+1; // (n-1) +1 = n
        // A^0 = A , A^A = 0
        return xor1^xor2; //xor is commutative and associative
    }

    //length of longest subarray that sums to k (prefix sum + hashmap) approach
    int lengthOfLongestSubarray(int nums[], int k){
        if (nums==null || nums.length==0) return 0;
        HashMap<Integer,Integer> hm = new HashMap<>();
        int length=0;
        int sum=0;
        hm.put(0,-1); // to handle cases when subarray starts from 0
        for(int i=0;i<nums.length;i++){
             sum+=nums[i];
             if(hm.containsKey(sum-k)){
                 int newLength= i- hm.get(sum-k);
                 length=Math.max(length,newLength);
             }
             if(!hm.containsKey(sum)){
             hm.put(sum,i);
             }

        }
        return length;
    }


}
