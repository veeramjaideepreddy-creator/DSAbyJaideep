class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if (k == 1) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int x : nums) cnt.merge(x, 1, Integer::sum);
            int ans = -1;
            for (var e : cnt.entrySet()) {
                if (e.getValue() == 1) ans = Math.max(ans, e.getKey());
            }
            return ans;
        }
        if (k == n) {
            return Arrays.stream(nums).max().getAsInt();
        }
        return Math.max(uniqueEndpoint(nums, 0), uniqueEndpoint(nums, n - 1));
    }

    private int uniqueEndpoint(int[] nums, int idx) {
        int v = nums[idx];
        for (int i = 0; i < nums.length; i++) {
            if (i != idx && nums[i] == v) return -1;
        }
        return v;
    }
}