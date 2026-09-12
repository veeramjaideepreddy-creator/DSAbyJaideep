class Solution:
    def rotateRight(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        if head == None or head.next == None:
            return head
        
        l = 1
        last = head
        while last.next:
            last = last.next
            l+=1
        

        k = k%l
        if k == 0:
            return head

        curr = head
        
        for i in range(l-k-1):
            curr = curr.next

        last.next = head
        head = curr.next
        curr.next = None

        return head


        