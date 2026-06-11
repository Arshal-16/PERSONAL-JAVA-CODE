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

    // Power Set

    /* Solved 78. Subsets from leetcode, same concept of pick/don't pick

    class Solution {

        // TC = (2^n)*n AND SC = n

        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();

            helper(0, new ArrayList<>(), nums, ans);

            return ans;
        }

        void helper(int idx, ArrayList<Integer> current, int nums[], List<List<Integer>> ans) {

            // base case
            if (idx == nums.length) {
                // note: don't add current directly
                ans.add(new ArrayList<>(current));
                return;
            }

            // pick the current element
            current.add(nums[idx]);
            helper(idx + 1, current, nums, ans);

            // don't pick the current element
            current.remove(current.size() - 1);
            helper(idx + 1, current, nums, ans);

        }
    }
    */

    // Count all subsequences with sum K

    /* this solution if it is allowed to consider empty subsequence

    class Solution {
    public int countSubsequenceWithTargetSum(int[] nums, int target) {
        return helper(0, nums, target);
    }

     int helper(int index, int[] nums, int remainingSum) {
        if (index == nums.length) {
            return remainingSum == 0 ? 1 : 0;
        }

        int include = helper(index + 1, nums, remainingSum - nums[index]);
        int exclude = helper(index + 1, nums, remainingSum);

        return include + exclude;
    }
}

     */

    /* This soln if empty subsequence is not allowed

    class Solution {
    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        return dfs(0, nums, k, false);
    }

    private int dfs(int index, int[] nums, int remainingSum, boolean picked) {
        if (index == nums.length) {
            return (remainingSum == 0 && picked) ? 1 : 0;
        }

        int include = dfs(index + 1, nums, remainingSum - nums[index], true);
        int exclude = dfs(index + 1, nums, remainingSum, picked);

        return include + exclude;
    }
}

     */

    // Check if there exists a subsequence with sum K

    /*

    class Solution {
    public boolean checkSubsequenceSum(int[] nums, int k) {
        return helper(0, nums, k);
    }

    private boolean helper(int index, int[] nums, int remainingSum) {
        if (index == nums.length) {
            return remainingSum == 0;
        }

        if (helper(index + 1, nums, remainingSum - nums[index])) {
            return true;
        }

        if (helper(index + 1, nums, remainingSum)) {
            return true;
        }

        return false;
    }
}

     */

    // Combination Sum

    /*

    class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        helper(0, candidates, target, new ArrayList<>(), ans);
        return ans;
    }

    void helper(int idx, int[] candidates, int remainingSum,
                ArrayList<Integer> curr, List<List<Integer>> ans) {

        // base case: if we have processed all elements
        if (idx == candidates.length) {
            if (remainingSum == 0) {
                ans.add(new ArrayList<>(curr)); // store valid combination
            }
            return;
        }

        // pick current element (if it does not exceed remaining sum)
        if (candidates[idx] <= remainingSum) {
            curr.add(candidates[idx]); // choose element
            helper(idx, candidates, remainingSum - candidates[idx], curr, ans);
            curr.remove(curr.size() - 1); // backtrack
        }

        // skip current element
        helper(idx + 1, candidates, remainingSum, curr, ans);
    }
}

     */

    // Combination Sum II

    /*

    class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);

        List<List<Integer>> ans = new ArrayList<>();

        helper(0, candidates, target, new ArrayList<>(), ans);

        return ans;

    }

    void helper(int start, int[] candidates, int remaining,
            List<Integer> path, List<List<Integer>> ans) {

    // start tells "From which index am I allowed to pick the next number?"

    // We found a valid combination whose sum equals the target
    if (remaining == 0) {
        ans.add(new ArrayList<>(path));
        return;
    }

    // Try every candidate starting from 'start'.
    // Using 'start' ensures each element is used at most once.
    for (int i = start; i < candidates.length; i++) {

        // Skip duplicates at the same recursion level.
        if (i > start && candidates[i] == candidates[i - 1]) {
            continue;
        }

        // Since the array is sorted, all elements after candidates[i]
        // will also be greater than 'remaining', so no valid combination
        // can be formed beyond this point.
        if (candidates[i] > remaining) {
            break;
        }

        // Choose the current candidate.
        path.add(candidates[i]);

        helper(i + 1, candidates, remaining - candidates[i], path, ans);

        // Backtrack: remove the last chosen element and try the next candidate.
        path.remove(path.size() - 1);
    }
}
}

     */

    // Subset I ( Subset Sum : Sum of all Subsets )

    /*

    class Solution {
    public List<Integer> subsetSums(int[] nums) {

        List<Integer> result = new ArrayList<>();
        helper(0, 0, nums, result);

        Collections.sort(result);

        return result;
    }

    void helper(int idx, int sum, int nums[], List<Integer> result){

        //base case
        if(idx==nums.length){
            result.add(sum);
            return;
        }

        // pick the curr ele
        helper(idx+1,sum+nums[idx],nums,result);

        // don't pick the curr ele
        helper(idx+1,sum,nums,result);

    }
}

     */

    // Subsets II

    /*

    Every recursion state represents a valid subset,
    so add the current subset to the answer immediately.
    We use a loop to try every possible next element.
    Choosing nums[i] is the "pick" step.
    The "not-pick" case is implicit: after exploring a choice,
    we backtrack and continue the loop, effectively skipping
    that element and moving on to other possibilities.
    Duplicate values are skipped at the same recursion level
    to avoid generating duplicate subsets.

    class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        // Sort
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        helper(0, nums, new ArrayList<>(), ans);

        return ans;
    }

    void helper(int start, int nums[], List<Integer> path, List<List<Integer>> ans) {

        ans.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1])
                continue;

            // select the current element
            path.add(nums[i]);
            helper(i + 1, nums, path, ans);
            //backtrack
            path.remove(path.size() - 1);
        }

    }
}

     */

    // Combination Sum III

    /*

    class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {

        int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        List<List<Integer>> ans = new ArrayList<>();

        helper(0, nums, k , n, new ArrayList<>(), ans);

        return ans;

    }

    void helper(int idx, int nums[], int k, int remaining, ArrayList<Integer> curr, List<List<Integer>> ans) {

        // base case
        if (idx == nums.length || k == 0 || remaining == 0) {
            if (remaining == 0 && k == 0) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        // pick
        if (nums[idx] <= remaining) {
            curr.add(nums[idx]);
            helper(idx + 1, nums, k - 1, remaining - nums[idx], curr, ans);
            curr.remove(curr.size() - 1);
        }

        // don't pick
        helper(idx + 1, nums, k, remaining, curr, ans);

    }
}

     */

    /* Slightly optimised for memory ( runtime beats 100%, memory beats 81% )

    class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> ans = new ArrayList<>();

        helper(1, k, n, new ArrayList<>(), ans);

        return ans;

    }

    void helper(int num, int k, int remaining, ArrayList<Integer> curr, List<List<Integer>> ans) {

        // base case
        if (num > 9 || k == 0 || remaining == 0) {
            if (remaining == 0 && k == 0) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        // pick
        if (num <= remaining) {
            curr.add(num);
            helper(num + 1, k - 1, remaining - num, curr, ans);
            curr.remove(curr.size() - 1);
        }

        // don't pick
        helper(num + 1, k, remaining, curr, ans);

    }
}

     */

    /* Most preferred solution, above 2 solutions were done by self

    class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(n, 1, new ArrayList<>(), k, result);

        return result;
    }

    void backtrack(int remainingSum, int startNum, List<Integer> currentCombination, int targetSize, List<List<Integer>> result) {

        // base case
        if (remainingSum == 0 && currentCombination.size() == targetSize) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        // base case
        if (remainingSum <= 0 || currentCombination.size() > targetSize) {
            return;
        }

        for (int num = startNum; num <= 9; num++) {

            if (num > remainingSum) {
                break;
            }

            currentCombination.add(num);

            backtrack(remainingSum - num, num + 1, currentCombination, targetSize, result);

            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}

     */

    // Letter Combinations of a Phone Number

    /*

    class Solution {

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();

        buildCombinations(0, digits, new StringBuilder(), combinations);

        return combinations;
    }

    void buildCombinations(int digitIndex, String digits, StringBuilder currentCombination, List<String> combinations) {

        if (digits.length() == 0) {
            return;
        }

        if (digitIndex == digits.length()) {
            combinations.add(currentCombination.toString());
            return;
        }

        char startChar, endChar;

        int currentDigit = digits.charAt(digitIndex) - '0';

        if (currentDigit < 8) {
            startChar = (char) ('a' + (currentDigit - 2) * 3);

            if (currentDigit != 7) {
                endChar = (char) (startChar + 2);
            } else {
                endChar = (char) (startChar + 3);
            }
        } else if (currentDigit == 8) {
            startChar = 't';
            endChar = (char) (startChar + 2);
        } else {
            startChar = 'w';
            endChar = (char) (startChar + 3);
        }

        for (char letter = startChar; letter <= endChar; letter++) {
            currentCombination.append(letter);

            buildCombinations(digitIndex + 1,digits,currentCombination,combinations);

            currentCombination.deleteCharAt(digitIndex);
        }
    }
}

     */

    ////////////// TRYING OUT ALL COMBOS/HARD  ///////////////////////

    // Palindrome Partitioning ( LC 131 )

    /*

    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

    A palindrome string is a string that reads the same backward as forward.

    Example 1:

    Input:  s = "aab"
    Output: [["a","a","b"],["aa","b"]]

    Example 2:

    Input:  s = "a"
    Output: [["a"]]

    Constraints:

    • 1 <= s.length <= 16
    • s contains only lowercase English letters.

    */ // question

    /*
        class Solution {
        public List<List<String>> partition(String s) {

            List<List<String>> res = new ArrayList<>();
            backtrack(0, s, new ArrayList<>(), res);

            return res;
        }

        void backtrack(int idx, String s, List<String> path, List<List<String>> res) {

        // Base case:
        // We have processed the entire string, so the current
        // partition stored in 'path' is a valid answer.
        if (idx == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // Try every possible substring starting from idx
        for (int i = idx; i < s.length(); i++) {

            // Skip if the current substring is not a palindrome
            if (!isPalindrome(s, idx, i))
                continue;

            // Choose:
            // Add the palindrome substring to the current partition
            path.add(s.substring(idx, i + 1));

            // Explore:
            // Recursively partition the remaining part of the string
            backtrack(i + 1, s, path, res);

            // Unchoose (Backtrack):
            // Remove the last added substring so that we can
            // try another partition choice
            path.remove(path.size() - 1);
        }
    }

        boolean isPalindrome(String s, int start, int end) {

            while (start < end) {
                if (s.charAt(start) != s.charAt(end))
                return false;
                start++;
                end--;
            }
            return true;
        }
    }

     */

    // Word Search

    /* ( done by self ) here i used visited array, but it canbe done without it, optimizing SC from mn to 1

            class Solution {
            public boolean exist(char[][] board, String word) {

                int rows = board.length;
                int cols = board[0].length;

                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < cols; col++) {
                        if (backtrack(0, row, col, word, board, new boolean[rows][cols])) {
                            return true;
                        }
                    }
                }
                return false;
            }

            boolean backtrack(int currIdx, int row, int col, String word,
                              char[][] board, boolean[][] visited) {

                // base case
                if (currIdx == word.length()) {
                    return true;
                }

                if (row >= board.length || col >= board[0].length || row < 0 || col < 0) {
                    return false;
                }

                if (board[row][col] == word.charAt(currIdx) && !visited[row][col]) {
                    // mark position as visited
                    visited[row][col] = true;

                    // check for next letter upward
                    if (backtrack(currIdx + 1, row - 1, col, word, board, visited))
                        return true;

                    // check for next letter downward
                    if (backtrack(currIdx + 1, row + 1, col, word, board, visited))
                        return true;

                    // check for next letter left
                    if (backtrack(currIdx + 1, row, col - 1, word, board, visited))
                        return true;

                    // check for next letter right
                    if (backtrack(currIdx + 1, row, col + 1, word, board, visited))
                        return true;

                    // backtrack
                    visited[row][col]=false;
                }

                return false;
            }
        }


    */

    /*

    The time complexity is **O(m × n × 4^L)** because we may start a DFS from every cell in the board (`m × n` starting positions).
    For each starting position, the recursion can explore up to **4 directions** (up, down, left, right) at every character of the word.
    If the word length is `L`, the recursion tree can have at most `4^L` paths in the worst case.
    Therefore, combining the `m × n` starting cells with the worst-case DFS exploration gives a total time complexity of **O(m × n × 4^L)**.
    This is a conservative upper bound; a tighter analysis is **O(m × n × 4 × 3^(L−1))** because after the first move,
    we cannot immediately revisit the previous cell.

        class Solution {

        public boolean exist(char[][] board, String word) {

            int rows = board.length;
            int cols = board[0].length;

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (backtrack(0, row, col, word, board)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean backtrack(int currIdx, int row, int col, String word,
                char[][] board) {

            // matched entire word
            if (currIdx == word.length()) {
                return true;
            }

            // out of bounds
            if (row < 0 || row >= board.length ||
                    col < 0 || col >= board[0].length) {
                return false;
            }

            // character mismatch or already visited
            if (board[row][col] != word.charAt(currIdx)) {
                return false;
            }

            // mark as visited
            char temp = board[row][col];
            board[row][col] = '#';

            boolean found = backtrack(currIdx + 1, row - 1, col, word, board) ||
                    backtrack(currIdx + 1, row + 1, col, word, board) ||
                    backtrack(currIdx + 1, row, col - 1, word, board) ||
                    backtrack(currIdx + 1, row, col + 1, word, board);

            // restore cell (backtrack)
            board[row][col] = temp;

            return found;
        }
    }


    */ // without using visited array

    // N-Queens

    /* See how hashing is used to check if queen canbe placed
            class Solution {
            public List<List<String>> solveNQueens(int n) {

                char board[][] = new char[n][n];

                // fill the board
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        board[row][col] = '.';
                    }
                }

                List<List<String>> res = new ArrayList<>();

                int[] leftRow = new int[n];
                int[] lowerDiagonal = new int[2 * n - 1];
                int[] upperDiagonal = new int[2 * n - 1];

                backtrack(0, board, res, n, leftRow, lowerDiagonal, upperDiagonal);

                return res;
            }

            void backtrack(int col, char board[][], List<List<String>> res, int n,
                           int[] leftRow, int[] lowerDiagonal, int[] upperDiagonal) {

                // base case
                if (col == n) {
                    List<String> temp = new ArrayList<>();
                    for (int row = 0; row < n; row++) {
                        temp.add(new String(board[row]));
                    }
                    res.add(temp);
                    return;
                }

                for (int row = 0; row < n; row++) {

                    // O(1) safety check using hashing
                    if (leftRow[row] == 0 &&
                        lowerDiagonal[row + col] == 0 &&
                        upperDiagonal[(n - 1 )+ (col - row)] == 0) {

                        // place queen
                        board[row][col] = 'Q';
                        leftRow[row] = 1;
                        lowerDiagonal[row + col] = 1;
                        upperDiagonal[(n - 1 )+ (col - row)] = 1;

                        backtrack(col + 1, board, res, n,
                                  leftRow, lowerDiagonal, upperDiagonal);

                        // backtrack
                        board[row][col] = '.';
                        leftRow[row] = 0;
                        lowerDiagonal[row + col] = 0;
                        upperDiagonal[(n - 1 )+ (col - row)] = 0;
                    }
                }
            }
        }

        For the diagonals that run from the top-right to the bottom-left (`/` diagonals),
        all cells on the same diagonal have the same value of `col - row` (equivalently,
        you can use `row - col`; both work). For example, in a 4×4 board, the cells `(0,3)`, `(1,2)`, `(2,1)`, and `(3,0)` all have `col - row = 3`,
        so they belong to the same diagonal. The problem is that `col - row` can be negative. On an `n × n` board, its range is from `-(n - 1)` to `+(n - 1)`.
        Since array indices cannot be negative, we shift the entire range by adding `n - 1`,
        giving the formula `n - 1 + col - row`. This converts the range from `[-(n-1), +(n-1)]` to `[0, 2n-2]`,
        which fits perfectly into an array of size `2*n - 1`.

     */

    // Rat in a Maze

    /*

        class Solution {
        public List<String> findPath(int[][] grid) {

            List<String> res = new ArrayList<>();

            if (grid[0][0] == 0) {
                return res;
            }

            backtrack(0, 0, "", grid, res);

            return res;
        }

        void backtrack(int row, int col, String currPath, int grid[][], List<String> res) {

            int n = grid.length;

            // out of bounds
            if (row < 0 || col < 0 || row >= n || col >= n) {
                return;
            }

            // blocked or already visited
            if (grid[row][col] == 0) {
                return;
            }

            // destination reached
            if (row == n - 1 && col == n - 1) {
                res.add(currPath);
                return;
            }

            // mark visited
            grid[row][col] = 0;

            backtrack(row - 1, col, currPath + "U", grid, res);
            backtrack(row + 1, col, currPath + "D", grid, res);
            backtrack(row, col - 1, currPath + "L", grid, res);
            backtrack(row, col + 1, currPath + "R", grid, res);

            // backtrack
            grid[row][col] = 1;
        }
    }

     */

}
