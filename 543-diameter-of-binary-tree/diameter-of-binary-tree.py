# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def diameterOfBinaryTree(self, root):
        diameter = [0]

        def depth(root):
            if root is None:
                return 0
            
            left = depth(root.left)
            right = depth(root.right)
            current = left + right
            if current > diameter[0]:
                diameter[0] = current

            return 1 + max(left, right)
        
        depth(root)

        return diameter[0]
