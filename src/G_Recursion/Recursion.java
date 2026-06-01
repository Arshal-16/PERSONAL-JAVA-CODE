package G_Recursion;

public class Recursion {
    /*
     * Key idea for problems that ask to generate ALL sequences/subsequences:
     *
     * Think in terms of two choices at every index:
     *   1. PICK the current element
     *   2. NOT PICK the current element
     *
     * This "pick / not-pick" pattern is the foundation of most
     * recursion and backtracking problems involving:
     *   - Subsequences
     *   - Subsets
     *   - Combination generation
     *   - Power set problems
     *   - Include/exclude style recursion
     *
     * Recursive Flow:
     *   - Pick:
     *       Add the current element to the current answer/path
     *       and move to the next index.
     *
     *   - Not Pick:
     *       Skip the current element
     *       and move to the next index.
     *
     * Base Case:
     *   When the index reaches the end of the array/string,
     *   store or print the current sequence/path.
     *
     * Time Complexity:
     *   Usually O(2^n) because every element has 2 choices.
     */

    /// /////////// GETTING A STRONG HOLD ///////////////////////

    // Recursive Implementation of atoi()

    /* Iterative Solution (think why ans is long and not int, also see how overflow is managed)
    class Solution {
        public int myAtoi(String s) {
            long ans = 0;
            boolean isNegative = false;
            int idx = 0;

            String num = s.trim();

            if (num.length() == 0)
                return 0;

            if (num.charAt(idx) == '-') {
                isNegative = true;
                idx++;
            } else if (num.charAt(idx) == '+') {
                idx++;
            }

            while (idx < num.length() && Character.isDigit(num.charAt(idx))) {
                int digit = num.charAt(idx) - '0';

                long limit = isNegative
                        ? (long) Integer.MAX_VALUE + 1
                        : Integer.MAX_VALUE;

                if (ans > limit / 10 ||
                        (ans == limit / 10 && digit > limit % 10)) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }

                ans = ans * 10 + digit;
                idx++;
            }

            ans = isNegative ? -ans : ans;
            return (int) ans;
        }
    }
    ans is long because if ans is int then it will overflow and wrap around to Integer.MIN_VALUE
    when input is Integer.MIN_VALUE because in mag min value is max val + 1
    */

    /* Recursive solution

     class Solution {
    public int myAtoi(String s) {
        String num = s.trim();
        boolean isNegative = false;
        int idx = 0, len = num.length();

        if (idx < len && num.charAt(idx) == '-') {
            isNegative = true;
            idx++;
        } else if (idx < len && num.charAt(idx) == '+') {
            idx++;
        }

        return helper(num, idx, 0, isNegative);

    }

    int helper(String num, int idx, long ans, boolean isNegative) {
        if (idx >= num.length() || !Character.isDigit(num.charAt(idx))) {
            if (isNegative)
                ans = -ans;
            return (int) ans;
        }

        int digit = num.charAt(idx)-'0';
        // overflow
        long limit = isNegative ? (long) Integer.MAX_VALUE + 1 : Integer.MAX_VALUE;
        if (ans > limit / 10 || (ans == limit / 10 && digit > limit % 10)) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        ans = ans * 10 + digit;

        return helper(num, idx + 1, ans, isNegative);

    }
}
    */

    // Pow (x,n)

    /* Recursive Solution

    class Solution {
        public double myPow(double x, int n) {

            long pow = (long) n;
            if (pow < 0) {
                pow = -pow;
                x = 1 / x;
            }

            return helper(x, pow);
        }

        double helper(double x, long n) {
            if (n == 1)
                return x;
            if (n == 0)
                return 1;

            // n=2k+1 or  n=2k

            if (n % 2 == 0) {
                return helper(x * x, n / 2);
            } else {
                return helper(x * x, n / 2) * x;
            }

        }
    }

    */

    /* Iterative Solution

    class Solution {
    public double myPow(double x, int n) {
        long pow = n;

        if (pow < 0) {
            x = 1 / x;
            pow = -pow;
        }

        double ans = 1.0;

    Think of the power in terms of binary representation. For example, (3^{13}) can be written as (3^(1101) base 2),
    which corresponds to (3^{8 + 4 + 1}). Using exponent rules, this becomes (3^{13} = 3^8 * 3^4 * 3^1).
    Now each of these terms can be built efficiently using repeated squaring: (3^1 = 3), (3^2 = 3 times 3), (3^4 = (3^2)^2), and (3^8 = (3^4)^2).
    This shows a clear pattern where each next term is obtained by squaring the previous one, i.e., (x  x^2  x^4  x^8), and so on.
    The binary representation of the exponent tells us which of these powers we actually need to include in the final answer—whenever a bit is 1,
    we multiply the corresponding power of (x) into the result, and when it is 0, we skip it.
    In this way, we efficiently compute (x^n) by both generating powers through squaring and selecting
    only the required ones using the binary form of (n), reducing the complexity from (O(n)) to (O(\log n)).


        while (pow > 0) {
            if ((pow & 1) == 1) {
                ans *= x;
            }

            x *= x;
            pow >>= 1;
        }

        return ans;
    }
}

    */

    // Count good numbers

    /* Optimal Solution
    class Solution {

    private static final long MOD = 1_000_000_007L;

    public int countGoodNumbers(long n) {

        long evenIdx = (n + 1) / 2;
        long oddIdx = n / 2;
        long ans = (powerHelper(5, evenIdx) * powerHelper(4, oddIdx)) % MOD;

        return (int) ans;
    }

    long powerHelper(long base, long pow) {

        if (pow == 0)
            return 1;

        long half = powerHelper(base, pow / 2);

        long result = (half * half) % MOD;

        if (pow % 2 == 1) {
            result = (result * base) % MOD;
        }

        return result;
    }
}

    */

    /* Purely using recursion (time complexity will be exponential)

// starting from position 'index' up to length 'n'.

public static int countGoodNumbers(int index, int n) {

    // Base case:
    // If we've filled all positions [0...n-1],
    // we've formed one valid good string.
    if (index == n) {
        return 1;
    }

    int result = 0;

    // Even index positions can contain:
    // {0, 2, 4, 6, 8} -> 5 choices
    if (index % 2 == 0) {

        int[] evenDigits = {0, 2, 4, 6, 8};

        // Try every valid digit at this position
        for (int digit : evenDigits) {
            result = (result + countGoodNumbers(index + 1, n)) % MOD;
        }
    }

    // Odd index positions can contain:
    // {2, 3, 5, 7} -> 4 choices
    else {

        int[] primeDigits = {2, 3, 5, 7};

        // Try every valid digit at this position
        for (int digit : primeDigits) {
            result = (result + countGoodNumbers(index + 1, n)) % MOD;
        }
    }
    return result;
}
*/

    // Sort a stack using recursion

    /*
    class Solution {

    public void sortStack(Stack<Integer> st) {

        // Base case
        if (st.isEmpty()) {
            return;
        }

        int top = st.pop();

        sortStack(st);

        insert(top, st);
    }

    void insert(int ele, Stack<Integer> st) {

        // Base case
        if (st.isEmpty() || st.peek() <= ele) {
            st.push(ele);
            return;
        }

        int top = st.pop();

        insert(ele, st);

        st.push(top);
    }
}
     */

    // Reverse stack using recursion

    /*

    class Solution {

    public void reverseStack(Stack<Integer> st) {

        //base case
        if(st.isEmpty()) return;

        int top  = st.pop();

        reverseStack(st);

        pushBottom(top,st);

    }

    void pushBottom(int ele, Stack<Integer> st){

        //base case
        if(st.isEmpty()){
            st.push(ele);
            return;
        }

        int top  = st.pop();

        pushBottom(ele,st);

        st.push(top);
    }
}

     */

    /// /////////// SUBSEQUENCE PATTERN ///////////////////////

    // Generate binary strings without consecutive 1's

    /* Solved 3211. Generate Binary Strings Without Adjacent Zeros from leetcode

    class Solution {
    public List<String> validStrings(int n) {
        // All substrings of length 2 should contain atleast one '1'
        // ie consecutive 0 should not be there

        ArrayList<String> ans = new ArrayList<>();

        return generateSequences(0, n, "", ans);
    }

    List<String> generateSequences(int idx, int n, String s, List<String> ans) {

        // base case
        if (idx == n) {
            ans.add(s);
            return ans;
        }
        if (idx == 0 || s.charAt(idx-1) == '1') {
            generateSequences(idx + 1, n, s + "0", ans);
            generateSequences(idx + 1, n, s + "1", ans);
        } else {
            generateSequences(idx + 1, n, s + "1", ans);
        }

        return ans;
    }
}
     */

    // Generate Parentheses

    /*

    class Solution {
        public List<String> generateParenthesis(int n) {

            ArrayList<String> ans = new ArrayList<>();
            helper(n, 0, 0, new StringBuilder(), ans);
            return ans;

        }

        void helper(int n, int openUsed, int closeUsed,
                    StringBuilder current, List<String> ans) {

            // base case
            if (current.length() == 2 * n) {
                ans.add(current.toString());
                return;
            }

            if (openUsed < n) {
                current.append('(');
                helper(n, openUsed + 1, closeUsed, current, ans);
                current.deleteCharAt(current.length() - 1);
            }

            if (closeUsed < openUsed) {
                current.append(')');
                helper(n, openUsed, closeUsed + 1, current, ans);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
    */




}
