# The guess API is already defined for you.
# @param num, your guess
# @return -1 if num is higher than the picked number
#          1 if num is lower than the picked number
#          otherwise return 0
# def guess(num):

class Solution(object):
    def guessNumber(self, n):
        left = 0
        right = n 


        while left <= right:
            mid = (left + right) // 2
            res = guess(mid)
            
            if res == 0 :
                return mid
            elif guess(mid) ==  -1:
                right = mid - 1
            elif guess(mid) == 1:
                left = mid + 1
           


        

        