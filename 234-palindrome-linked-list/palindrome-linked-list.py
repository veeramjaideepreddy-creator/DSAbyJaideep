class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        list1 = []
        list2 = []
        curr1 = head

        while curr1!= None:
            list1.append(curr1.val)
            curr1 = curr1.next

        curr2 = head
        prev = None
        nxt = None

        while curr2!= None:
            nxt = curr2.next
            curr2.next = prev
            prev = curr2
            curr2 = nxt

        curr3 = prev

        while curr3!= None:
            list2.append(curr3.val)
            curr3 = curr3.next

        return list1 == list2
            
        
        
        
        