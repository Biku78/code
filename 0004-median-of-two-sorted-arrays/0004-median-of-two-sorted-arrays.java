class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int half = total / 2;

        int left = 0;
        int right = m;

        while (left <= right) {
            int mid1 = (left + right) / 2; // Partition for nums1
            int mid2 = half - mid1;       // Partition for nums2

            // Edge cases where partitions fall out of bounds
            int left1 = (mid1 > 0) ? nums1[mid1 - 1] : Integer.MIN_VALUE;
            int right1 = (mid1 < m) ? nums1[mid1] : Integer.MAX_VALUE;
            int left2 = (mid2 > 0) ? nums2[mid2 - 1] : Integer.MIN_VALUE;
            int right2 = (mid2 < n) ? nums2[mid2] : Integer.MAX_VALUE;

            // Check if the partitions are correct
            if (left1 <= right2 && left2 <= right1) {
                // Correct partition found
                if (total % 2 == 0) {
                    // Even total: average of max(left) and min(right)
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    // Odd total: middle element
                    return Math.min(right1, right2);
                }
            } else if (left1 > right2) {
                // Move left in nums1
                right = mid1 - 1;
            } else {
                // Move right in nums1
                left = mid1 + 1;
            }
        }

        throw new IllegalArgumentException("No median found");
    }
}