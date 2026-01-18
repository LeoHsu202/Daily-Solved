/*
 * @lc app=leetcode.cn id=1895 lang=java
 * @lcpr version=30204
 *
 * [1895] 最大的幻方
 *
 * https://leetcode.cn/problems/largest-magic-square/description/
 *
 * algorithms
 * Medium (58.90%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    10.6K
 * Total Submissions: 15.5K
 * Testcase Example:  '[[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]'
 *
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。幻方中的整数 不需要互不相同
 * 。显然，每个 1 x 1 的方格都是一个幻方。
 * 
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 * 解释：最大幻方尺寸为 3 。
 * 每一行，每一列以及两条对角线的和都等于 12 。
 * - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 * - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 * - 对角线的和：5+4+3 = 6+4+2 = 12
 * 
 * 
 * 示例 2：
 * 
 * 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^6
 * 
 * 
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start
class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 从最大可能的边长开始往下尝试
        for (int k = Math.min(m, n); k >= 1; k--) {
            // 枚举所有可能的左上角起点
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (isMagicSquare(grid, i, j, k)) {
                        return k;
                    }
                }
            }
        }
        return 1;  // 最差情况，1×1 一定成立
    }
    
    // 判断以 (r,c) 为左上角，边长为 k 的子矩阵是否是幻方
    private boolean isMagicSquare(int[][] grid, int r, int c, int k) {
        // 先算第一行的和，作为魔法常数
        int magicSum = 0;
        for (int j = 0; j < k; j++) {
            magicSum += grid[r][c + j];
        }
        
        // 1. 检查所有行
        for (int i = 0; i < k; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += grid[r + i][c + j];
            }
            if (sum != magicSum) return false;
        }
        
        // 2. 检查所有列
        for (int j = 0; j < k; j++) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += grid[r + i][c + j];
            }
            if (sum != magicSum) return false;
        }
        
        // 3. 检查主对角线（左上→右下）
        int diag1 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[r + i][c + i];
        }
        if (diag1 != magicSum) return false;
        
        // 4. 检查副对角线（右上→左下）
        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag2 += grid[r + i][c + k - 1 - i];
        }
        if (diag2 != magicSum) return false;
        
        return true;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]\n
// @lcpr case=end

// @lcpr case=start
// [[5,1,3,1],[9,3,3,1],[1,3,3,8]]\n
// @lcpr case=end

 */

