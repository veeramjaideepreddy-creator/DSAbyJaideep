class Solution(object):
    def rightSideView(self, root):
        res = []

        if not root:
            return res

        queue = collections.deque()
        queue.append(root)

        while queue:
            level = []
            qlen = len(queue)

            for i in range(qlen):
                node = queue.popleft()
                level.append(node.val)
                
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            res.append(level[-1])

        return res

        