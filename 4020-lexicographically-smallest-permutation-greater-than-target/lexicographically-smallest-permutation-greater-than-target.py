class Solution(object):
    def lexGreaterPermutation(self, s, target):
        n = len(s)
        counts = Counter(s)
        best_breakout = None  # (position, char, snapshot of counts)

        for i in range(n):
            t = target[i]
            cand = None
            for c in range(ord(t) + 1, ord('z') + 1):
                ch = chr(c)
                if counts[ch] > 0:
                    cand = ch
                    break
            if cand is not None:
                best_breakout = (i, cand, counts.copy())

            if counts[t] > 0:
                counts[t] -= 1
            else:
                break

        if best_breakout is None:
            return ""

        pos, c, snap = best_breakout
        snap = snap.copy()
        snap[c] -= 1
        suffix = ''.join(ch * snap[ch] for ch in sorted(snap.keys()))
        return target[:pos] + c + suffix