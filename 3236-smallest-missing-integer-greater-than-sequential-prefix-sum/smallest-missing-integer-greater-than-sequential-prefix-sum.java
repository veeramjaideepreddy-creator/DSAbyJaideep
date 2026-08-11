class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int total = nums[0];
        int i = 1;
        while (i < n && nums[i] == nums[i - 1] + 1) {
            total += nums[i];
            i++;
        }

        Set<Integer> present = new HashSet<>();
        for (int x : nums) present.add(x);

        while (present.contains(total)) total++;
        return total;
    }
}