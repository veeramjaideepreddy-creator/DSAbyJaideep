class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] indexed = new Integer[n];
        for (int i = 0; i < n; i++) indexed[i] = i;
        Arrays.sort(indexed, (a, b) -> nums[a] - nums[b]);

        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            while (j + 1 < n && nums[indexed[j + 1]] - nums[indexed[j]] <= limit) j++;

            int[] groupIndices = new int[j - i + 1];
            int[] groupValues = new int[j - i + 1];
            for (int k = i; k <= j; k++) {
                groupIndices[k - i] = indexed[k];
                groupValues[k - i] = nums[indexed[k]];
            }
            Arrays.sort(groupIndices);
            for (int k = 0; k < groupIndices.length; k++) {
                result[groupIndices[k]] = groupValues[k];
            }
            i = j + 1;
        }
        return result;
    }
}