class Solution {
    static final int[] PRIMES = {2, 3, 5, 7};
    static final int[][] DIGIT = {
        {0,0,0,0},{0,0,0,0},{1,0,0,0},{0,1,0,0},{2,0,0,0},
        {0,0,1,0},{1,1,0,0},{0,0,0,1},{3,0,0,0},{0,2,0,0}
    };

    public String smallestNumber(String num, long t) {
        int[] need = new int[4];
        for (int i = 0; i < 4; i++) while (t % PRIMES[i] == 0) { t /= PRIMES[i]; need[i]++; }
        if (t != 1) return "-1";

        int[] pack = packDigits(need);
        int minLen = sum(pack), n = num.length();
        if (minLen > n) return build(pack);

        int[] prefix = new int[4];
        for (char ch : num.toCharArray())
            for (int k = 0; k < 4; k++) prefix[k] += DIGIT[ch - '0'][k];

        int zeroIdx = num.indexOf('0');
        if (zeroIdx == -1) {
            zeroIdx = n;
            boolean subset = true;
            for (int k = 0; k < 4; k++) if (prefix[k] < need[k]) subset = false;
            if (subset) return num;
        }

        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            for (int k = 0; k < 4; k++) prefix[k] -= DIGIT[d][k];
            if (i > zeroIdx) continue;
            int space = n - 1 - i;
            for (int bigger = d + 1; bigger <= 9; bigger++) {
                int[] remaining = new int[4];
                for (int k = 0; k < 4; k++)
                    remaining[k] = Math.max(0, need[k] - prefix[k] - DIGIT[bigger][k]);
                int[] fill = packDigits(remaining);
                int filled = sum(fill);
                if (filled <= space) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append((char) ('0' + bigger));
                    for (int o = 0; o < space - filled; o++) sb.append('1');
                    sb.append(build(fill));
                    return sb.toString();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int o = 0; o < n + 1 - minLen; o++) sb.append('1');
        sb.append(build(pack));
        return sb.toString();
    }

    private int[] packDigits(int[] c) {
        int n8 = c[0]/3, r2 = c[0]%3, n9 = c[1]/2, n3 = c[1]%2, n4 = r2/2, n2 = r2%2, n6 = 0;
        if (n2==1 && n3==1) { n2=0; n3=0; n6=1; }
        if (n3==1 && n4==1) { n2=1; n6=1; n3=0; n4=0; }
        return new int[]{n2, n3, n4, c[2], n6, c[3], n8, n9}; // digits 2..9
    }
    private int sum(int[] a) { int s=0; for (int v: a) s+=v; return s; }
    private String build(int[] dc) {
        StringBuilder sb = new StringBuilder();
        for (int d = 0; d < 8; d++)
            for (int k = 0; k < dc[d]; k++) sb.append((char) ('0' + d + 2));
        return sb.toString();
    }
}