class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < nums.length; right++) {
            int x = nums[right];
            freq.merge(x, 1, Integer::sum);
            while (freq.get(x) > k) {
                int y = nums[left++];
                freq.put(y, freq.get(y) - 1);
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}