class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
        Set<Integer> present = new HashSet<>();
        for (int x : nums) {
            lo = Math.min(lo, x);
            hi = Math.max(hi, x);
            present.add(x);
        }
        List<Integer> result = new ArrayList<>();
        for (int x = lo; x <= hi; x++) {
            if (!present.contains(x)) result.add(x);
        }
        return result;
    }
}