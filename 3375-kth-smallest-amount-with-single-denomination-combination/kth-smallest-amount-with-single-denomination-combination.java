class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long lo = 1, hi = (long) Arrays.stream(coins).min().getAsInt() * k;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countLE(coins, mid) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private long countLE(int[] coins, long x) {
        int n = coins.length;
        long total = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long l = 1;
            int bits = 0;
            boolean overflow = false;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    l = l / gcd(l, coins[i]) * coins[i];
                    bits++;
                    if (l > x) { overflow = true; break; }
                }
            }
            if (overflow) continue;
            long cnt = x / l;
            total += (bits % 2 == 1) ? cnt : -cnt;
        }
        return total;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}