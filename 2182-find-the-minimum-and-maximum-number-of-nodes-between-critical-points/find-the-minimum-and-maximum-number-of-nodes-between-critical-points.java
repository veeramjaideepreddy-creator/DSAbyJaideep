class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next) values.add(node.val);

        int n = values.size();
        int firstIdx = -1, lastIdx = -1, prevIdx = -1;
        int minDist = Integer.MAX_VALUE;

        for (int i = 1; i < n - 1; i++) {
            boolean isMax = values.get(i) > values.get(i - 1) && values.get(i) > values.get(i + 1);
            boolean isMin = values.get(i) < values.get(i - 1) && values.get(i) < values.get(i + 1);
            if (isMax || isMin) {
                if (firstIdx == -1) firstIdx = i;
                if (prevIdx != -1) minDist = Math.min(minDist, i - prevIdx);
                prevIdx = i;
                lastIdx = i;
            }
        }

        if (firstIdx == -1 || firstIdx == lastIdx) return new int[]{-1, -1};
        return new int[]{minDist, lastIdx - firstIdx};
    }
}