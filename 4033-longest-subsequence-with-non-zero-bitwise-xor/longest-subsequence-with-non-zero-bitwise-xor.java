class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xorTotal = 0, zeroCount = 0;
        for (int x : nums) {
            xorTotal ^= x;
            if (x == 0) zeroCount++;
        }
        if (xorTotal != 0) return n;
        if (zeroCount == n) return 0;
        return n - 1;
    }
}