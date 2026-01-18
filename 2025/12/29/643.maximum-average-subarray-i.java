/*
 * @lc app=leetcode.cn id=643 lang=java
 * @lcpr version=30204
 *
 * [643] 子数组最大平均数 I
 *
 * https://leetcode.cn/problems/maximum-average-subarray-i/description/
 *
 * algorithms
 * Easy (44.04%)
 * Likes:    418
 * Dislikes: 0
 * Total Accepted:    204.4K
 * Total Submissions: 463.4K
 * Testcase Example:  '[1,12,-5,-6,50,3]\n4'
 *
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 
 * 任何误差小于 10^-5 的答案都将被视为正确答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0;i < nums.length;i++){
            sum += nums[i];
            int left = i - k + 1;
            if(left < 0){
                continue;
            }
            max = Math.max(max,sum);
            sum -= nums[left];
        }
        return (double)max / k;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,12,-5,-6,50,3]\n4\n
// @lcpr case=end

// @lcpr case=start
// [5]\n1\n
// @lcpr case=end

 */

