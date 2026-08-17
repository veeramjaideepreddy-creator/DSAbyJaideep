class Solution {
    private long[] acc;
    private int[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        acc = new long[n + 1];
        for (int i = 0; i < n; i++) acc[i + 1] = acc[i] + stoneValue[i];
        memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);
        return dp(0, n - 1);
    }

    private int dp(int i, int j) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        long total = acc[j + 1] - acc[i];
        long best = 0;
        for (int k = i; k < j; k++) {
            long left = acc[k + 1] - acc[i];
            long right = total - left;
            if (left <= right) best = Math.max(best, left + dp(i, k));
            if (right <= left) best = Math.max(best, right + dp(k + 1, j));
        }
        return memo[i][j] = (int) best;
    }
}