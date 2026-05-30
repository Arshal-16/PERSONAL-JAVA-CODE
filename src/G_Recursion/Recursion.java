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


}
