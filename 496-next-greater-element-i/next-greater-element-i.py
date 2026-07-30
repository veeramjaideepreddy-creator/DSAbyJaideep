class Solution(object):
    def nextGreaterElement(self, nums1, nums2):
        stack = []
        nextgreater = {}
        result = []

        for i in nums2:
            while stack and i > stack[-1]:
                prev = stack.pop()
                nextgreater[prev] = i
            stack.append(i)

        while stack:
            nextgreater[stack.pop()] = -1

        for i in nums1:
            if i in nextgreater:
                result.append(nextgreater[i])

        return result


        

            

        

    

