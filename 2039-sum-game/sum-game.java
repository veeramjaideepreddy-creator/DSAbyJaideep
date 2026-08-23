class Solution {
    public boolean sumGame(String num) {
        int n = num.length(), half = n / 2;
        int sum1 = 0, sum2 = 0, remain1 = 0, remain2 = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);
            if (c == '?') remain1++; else sum1 += c - '0';
        }
        for (int i = half; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') remain2++; else sum2 += c - '0';
        }

        if ((remain1 + remain2) % 2 != 0) return true;

        int diff = sum1 - sum2;
        if (diff == 0) return remain1 != remain2;
        if (diff > 0 && remain1 >= remain2) return true;
        if (diff < 0 && remain1 <= remain2) return true;
        if (diff > 0) {
            if (diff < 9) return true;
            int maxDiff = (remain2 - remain1) / 2 * 9;
            return maxDiff != diff;
        } else {
            if (diff > -9) return true;
            int maxDiff = (remain1 - remain2) / 2 * 9;
            return maxDiff != -diff;
        }
    }
}