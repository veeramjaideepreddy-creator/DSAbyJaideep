class Solution(object):
    def isBalanced(self, root):

        def height(root):
            if root is None:
                return 0

            left = height(root.left)
            right = height(root.right)

            # If either subtree is already unbalanced
            if left == -1 or right == -1:
                return -1

            # Current node is unbalanced
            if abs(left - right) > 1:
                return -1

            # Return height
            return 1 + max(left, right)

        return height(root) != -1