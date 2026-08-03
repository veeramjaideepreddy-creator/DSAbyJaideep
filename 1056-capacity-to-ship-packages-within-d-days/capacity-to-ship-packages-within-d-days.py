class Solution(object):
    def shipWithinDays(self, weights, days):
        left = max(weights)
        right = sum(weights)

        while left < right:
            mid = (left + right) // 2
            current = 0
            dayss = 1

            for weight in weights:
                if current + weight <= mid:
                    current += weight
                else:
                    dayss += 1
                    current = weight
            
            if dayss <= days:
                right = mid
            else:
                left = mid + 1
        return left
