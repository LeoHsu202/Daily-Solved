/*
 * @lc app=leetcode.cn id=2147 lang=java
 * @lcpr version=30204
 *
 * [2147] 分隔长廊的方案数
 *
 * https://leetcode.cn/problems/number-of-ways-to-divide-a-long-corridor/description/
 *
 * algorithms
 * Hard (43.46%)
 * Likes:    47
 * Dislikes: 0
 * Total Accepted:    13.6K
 * Total Submissions: 26.1K
 * Testcase Example:  '"SSPPSPS"'
 *
 * 在一个图书馆的长廊里，有一些座位和装饰植物排成一列。给你一个下标从 0 开始，长度为 n 的字符串 corridor ，它包含字母 'S' 和 'P'
 * ，其中每个 'S' 表示一个座位，每个 'P' 表示一株植物。
 * 
 * 在下标 0 的左边和下标 n - 1 的右边 已经 分别各放了一个屏风。你还需要额外放置一些屏风。每一个位置 i - 1 和 i 之间（1 <= i
 * <= n - 1），至多能放一个屏风。
 * 
 * 请你将走廊用屏风划分为若干段，且每一段内都 恰好有两个座位
 * ，而每一段内植物的数目没有要求。可能有多种划分方案，如果两个方案中有任何一个屏风的位置不同，那么它们被视为 不同 方案。
 * 
 * 请你返回划分走廊的方案数。由于答案可能很大，请你返回它对 10^9 + 7 取余 的结果。如果没有任何方案，请返回 0 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：corridor = "SSPPSPS"
 * 输出：3
 * 解释：总共有 3 种不同分隔走廊的方案。
 * 上图中黑色的竖线表示已经放置好的屏风。
 * 上图每种方案中，每一段都恰好有 两个 座位。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 
 * 输入：corridor = "PPSPSP"
 * 输出：1
 * 解释：只有 1 种分隔走廊的方案，就是不放置任何屏风。
 * 放置任何的屏风都会导致有一段无法恰好有 2 个座位。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 
 * 输入：corridor = "S"
 * 输出：0
 * 解释：没有任何方案，因为总是有一段无法恰好有 2 个座位。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == corridor.length
 * 1 <= n <= 10^5
 * corridor[i] 要么是 'S' ，要么是 'P' 。
 * 
 * 
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start
class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = 1_000_000_007;
        long ans = 1;
        int cntS = 0;
        int lastS = 0;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                cntS++;
                if (cntS >= 3 && cntS % 2 > 0) {
                    ans = ans * (i - lastS) % MOD;
                }
                lastS = i; // 记录上一个座位的位置
            }
        }
        if (cntS == 0 || cntS % 2 > 0) {
            return 0;
        }
        return (int) ans;
    }
}

// @lc code=end



/*
// @lcpr case=start
// "SSPPSPS"\n
// @lcpr case=end

// @lcpr case=start
// "PPSPSP"\n
// @lcpr case=end

// @lcpr case=start
// "S"\n
// @lcpr case=end

 */

