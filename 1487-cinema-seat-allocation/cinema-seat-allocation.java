class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();
        for (int[] rs : reservedSeats) {
            int row = rs[0], seat = rs[1];
            if (seat >= 2 && seat <= 9) {
                int bit = seat - 2;
                rowMasks.merge(row, 1 << bit, (a, b) -> a | b);
            }
        }

        final int LEFT = 0b00001111, MID = 0b00111100, RIGHT = 0b11110000;
        long total = 2L * (n - rowMasks.size());
        for (int m : rowMasks.values()) {
            if ((m & LEFT) == 0 && (m & RIGHT) == 0) {
                total += 2;
            } else if ((m & LEFT) == 0 || (m & MID) == 0 || (m & RIGHT) == 0) {
                total += 1;
            }
        }
        return (int) total;
    }
}