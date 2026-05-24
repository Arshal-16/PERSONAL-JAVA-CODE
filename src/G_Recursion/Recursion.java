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
}
