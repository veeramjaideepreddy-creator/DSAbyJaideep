
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        curr = head
        prev = None
        nxt = None

        while curr!= None:
            nxt = curr.next
            curr.next = prev
            prev = curr
            curr = nxt

        return prev
        