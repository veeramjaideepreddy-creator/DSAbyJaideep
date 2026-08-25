class Solution(object):
    def missingMultiple(self, nums, k):
        
        j = 0
        while j < 1000:
            if j + k in nums:
                j = j + k
            else:
                return j + k

        