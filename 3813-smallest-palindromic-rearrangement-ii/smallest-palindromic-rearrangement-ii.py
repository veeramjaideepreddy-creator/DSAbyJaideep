class Solution(object):
    def smallestPalindrome(self, s, k):
        n = len(s)
        freq = Counter(s)

        # Half-counts: every char's total frequency is even in a palindrome,
        # except possibly one (the middle character).
        half = [0] * 26
        mid_char = ''
        for ch, f in freq.items():
            half[ord(ch) - ord('a')] = f // 2
            if f % 2:
                mid_char = ch

        half_len = n // 2
        CAP = k  # we never need a count beyond this — just "is it >= k?"

        def arrangements(counts, total):
            """# of distinct permutations of multiset `counts` (summing to
            `total`), capped at CAP + 1 to avoid huge numbers."""
            result = 1
            remaining = total
            for c in range(26):
                cnt = counts[c]
                if cnt == 0:
                    continue
                # multiply result by C(remaining, cnt)
                val = 1
                for i in range(1, cnt + 1):
                    val = val * (remaining - cnt + i) // i
                    if val > CAP:
                        val = CAP + 1
                        break
                result *= val
                if result > CAP:
                    return CAP + 1
                remaining -= cnt
            return result

        # Does a k-th arrangement even exist?
        if arrangements(half, half_len) < k:
            return ""

        # Greedily build the k-th smallest half, one char at a time.
        counts = half[:]
        remaining = half_len
        half_chars = []

        for _ in range(half_len):
            for c in range(26):
                if counts[c] == 0:
                    continue
                counts[c] -= 1
                cnt = arrangements(counts, remaining - 1)
                if cnt >= k:
                    half_chars.append(chr(ord('a') + c))
                    remaining -= 1
                    break
                k -= cnt
                counts[c] += 1  # undo, try next character

        half_str = ''.join(half_chars)
        if n % 2:
            return half_str + mid_char + half_str[::-1]
        return half_str + half_str[::-1]