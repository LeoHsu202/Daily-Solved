/*
 * @lc app=leetcode.cn id=1458 lang=java
 * @lcpr version=30204
 *
 * [1458] 两个子序列的最大点积
 *
 * https://leetcode.cn/problems/max-dot-product-of-two-subsequences/description/
 *
 * algorithms
 * Hard (53.45%)
 * Likes:    140
 * Dislikes: 0
 * Total Accepted:    21.7K
 * Total Submissions: 37.2K
 * Testcase Example:  '[2,1,-2,5]\n[3,0,-6]'
 *
 * 给你两个数组 nums1 和 nums2 。
 * 
 * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
 * 
 * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。比方说，[2,3,5] 是
 * [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
 * 输出：18
 * 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
 * 它们的点积为 (2*3 + (-2)*(-6)) = 18 。
 * 
 * 示例 2：
 * 
 * 输入：nums1 = [3,-2], nums2 = [2,-6,7]
 * 输出：21
 * 解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
 * 它们的点积为 (3*7) = 21 。
 * 
 * 示例 3：
 * 
 * 输入：nums1 = [-1,-1], nums2 = [1,1]
 * 输出：-1
 * 解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
 * 它们的点积为 -1 。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 500
 * -1000 <= nums1[i], nums2[i] <= 1000
 * 
 * 
 * 
 * 
 * 点积：
 * 
 * 定义 a = [a1, a2,…, an] 和 b = [b1, b2,…, bn] 的点积为：
 * 
 * 
 * 
 * 这里的 Σ 指示总和符号。
 * 
 * 
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start

import java.util.Arrays;

class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return dfs(n - 1, m - 1, nums1, nums2, memo);
    }

    // 从 nums1[0,i] 和 nums2[0,j] 中选两个长度相同的【非空】子序列的最大点积
    private int dfs(int i, int j, int[] nums1, int[] nums2, int[][] memo) {
        if (i < 0 || j < 0) {
            // 其中一个数组没有元素，无法选出非空子序列
            return Integer.MIN_VALUE; // 下面计算 max 不会取到无解情况
        }

        if (memo[i][j] != Integer.MAX_VALUE) { // 之前计算过
            return memo[i][j];
        }

        // 选 nums1[i] 和 nums2[j]
        // 和前面的子序列拼起来，或者不拼（作为子序列的第一个数）
        int res1 = Math.max(dfs(i - 1, j - 1, nums1, nums2, memo), 0) + nums1[i] * nums2[j];

        // 不选 nums1[i]
        int res2 = dfs(i - 1, j, nums1, nums2, memo);

        // 不选 nums2[j]
        int res3 = dfs(i, j - 1, nums1, nums2, memo);

        memo[i][j] = Math.max(res1, Math.max(res2, res3)); // 记忆化
        return memo[i][j];
    }
}

// @lc code=end



/*
// @lcpr case=start
// [2,1,-2,5]\n[3,0,-6]\n
// @lcpr case=end

// @lcpr case=start
// [3,-2]\n[2,-6,7]\n
// @lcpr case=end

// @lcpr case=start
// [-1,-1]\n[1,1]\n
// @lcpr case=end

 */

