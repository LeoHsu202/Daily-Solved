/*
 * @lc app=leetcode.cn id=1161 lang=java
 * @lcpr version=30204
 *
 * [1161] 最大层内元素和
 *
 * https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (66.17%)
 * Likes:    166
 * Dislikes: 0
 * Total Accepted:    72K
 * Total Submissions: 106.4K
 * Testcase Example:  '[1,7,0,7,-8,null,null]'
 *
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 * 
 * 返回总和 最大 的那一层的层号 x。如果有多层的总和一样大，返回其中 最小 的层号 x。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：root = [1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 * 
 * 
 * 示例 2：
 * 
 * 输入：root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中的节点数在 [1, 10^4]范围内
 * -10^5 <= Node.val <= 10^5
 * 
 * 
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start

import java.util.ArrayList;
import java.util.List;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 

class Solution {
    public int maxLevelSum(TreeNode root) {
        int max_sum = Integer.MIN_VALUE;
        int ans = 0;

        List<TreeNode> q = new ArrayList<>();
        q.add(root);

        for (int level = 1; !q.isEmpty(); level++) {
            List<TreeNode> tmp = q;
            q = new ArrayList<>();
            int s = 0;

            for (TreeNode node : tmp) {
                s += node.val;
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }

            if (s > max_sum) {
                max_sum = s;
                ans = level;
            }
        }

        return ans;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,7,0,7,-8,null,null]\n
// @lcpr case=end

// @lcpr case=start
// [989,null,10250,98693,-89388,null,null,null,-32127]\n
// @lcpr case=end

 */

