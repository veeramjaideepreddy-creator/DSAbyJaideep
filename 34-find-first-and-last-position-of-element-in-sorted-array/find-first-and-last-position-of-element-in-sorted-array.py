class Solution(object):
    def searchRange(self, nums, target):
        def findFirst():
            left = 0
            right = len(nums)

            while left < right:
                mid = (left + right) // 2

                if nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid

            if left == len(nums) or nums[left] != target:
                return -1

            return left

        def findLast():
            left = 0
            right = len(nums)

            while left < right:
                mid = (left + right) // 2

                if nums[mid] <= target:
                    left = mid + 1
                else:
                    right = mid

            if left == 0 or nums[left - 1] != target:
                return -1

            return left - 1

        first = findFirst()

        if first == -1:
            return [-1, -1]

        last = findLast()

        return [first, last]