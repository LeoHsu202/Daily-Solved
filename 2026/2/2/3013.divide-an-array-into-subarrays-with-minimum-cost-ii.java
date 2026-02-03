/*
 * @lc app=leetcode.cn id=3013 lang=java
 * @lcpr version=30204
 *
 * [3013] 将数组分成最小总代价的子数组 II
 *
 * https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/description/
 *
 * algorithms
 * Hard (36.72%)
 * Likes:    38
 * Dislikes: 0
 * Total Accepted:    8.7K
 * Total Submissions: 16.5K
 * Testcase Example:  '[1,3,2,6,4,2]\n3\n3'
 *
 * 给你一个下标从 0 开始长度为 n 的整数数组 nums 和两个 正 整数 k 和 dist 。
 * 
 * 一个数组的 代价 是数组中的 第一个 元素。比方说，[1,2,3] 的代价为 1 ，[3,4,1] 的代价为 3 。
 * 
 * 你需要将 nums 分割成 k 个 连续且互不相交 的子数组，满足 第二 个子数组与第 k 个子数组中第一个元素的下标距离 不超过 dist
 * 。换句话说，如果你将 nums 分割成子数组 nums[0..(i1 - 1)], nums[i1..(i2 - 1)], ...,
 * nums[ik-1..(n - 1)] ，那么它需要满足 ik-1 - i1 <= dist 。
 * 
 * 请你返回这些子数组的 最小 总代价。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,3,2,6,4,2], k = 3, dist = 3
 * 输出：5
 * 解释：将数组分割成 3 个子数组的最优方案是：[1,3] ，[2,6,4] 和 [2] 。这是一个合法分割，因为 ik-1 - i1 等于 5 - 2
 * = 3 ，等于 dist 。总代价为 nums[0] + nums[2] + nums[5] ，也就是 1 + 2 + 2 = 5 。
 * 5 是分割成 3 个子数组的最小总代价。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [10,1,2,2,2,1], k = 4, dist = 3
 * 输出：15
 * 解释：将数组分割成 4 个子数组的最优方案是：[10] ，[1] ，[2] 和 [2,2,1] 。这是一个合法分割，因为 ik-1 - i1 等于 3
 * - 1 = 2 ，小于 dist 。总代价为 nums[0] + nums[1] + nums[2] + nums[3] ，也就是 10 + 1 + 2
 * + 2 = 15 。
 * 分割 [10] ，[1] ，[2,2,2] 和 [1] 不是一个合法分割，因为 ik-1 和 i1 的差为 5 - 1 = 4 ，大于 dist 。
 * 15 是分割成 4 个子数组的最小总代价。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [10,8,18,9], k = 3, dist = 1
 * 输出：36
 * 解释：将数组分割成 4 个子数组的最优方案是：[10] ，[8] 和 [18,9] 。这是一个合法分割，因为 ik-1 - i1 等于 2 - 1 =
 * 1 ，等于 dist 。总代价为 nums[0] + nums[1] + nums[2] ，也就是 10 + 8 + 18 = 36 。
 * 分割 [10] ，[8,18] 和 [9] 不是一个合法分割，因为 ik-1 和 i1 的差为 3 - 1 = 2 ，大于 dist 。
 * 36 是分割成 3 个子数组的最小总代价。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 3 <= k <= n
 * k - 2 <= dist <= n - 2
 * 
 * 
 */


// @lcpr-template-start

// @lcpr-template-end
// @lc code=start

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    // 延迟删除记录
    private Map<Integer, Integer> toRemove;
    // selectHeap 是大顶堆，维护最小的 target 个数
    private PriorityQueue<Integer> selectHeap;
    // candHeap 是小顶堆，维护剩余的数
    private PriorityQueue<Integer> candHeap;
    
    // select中的有效元素和
    private long totalValid;
    // 两个堆中当前的有效元素数量
    private int cntL;
    private int cntR;
    private int target;

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        // 从窗口中选出 k-2 个最小元素
        target = k - 2;
        
        selectHeap = new PriorityQueue<>(Collections.reverseOrder());
        candHeap = new PriorityQueue<>();
        toRemove = new HashMap<>();
        totalValid = 0;
        cntL = 0;
        cntR = 0;

        // 初始化窗口
        // i_1 初始为 1，候选窗口范围 nums[2 ... dist+1]
        int windowEnd = Math.min(n, dist + 2);
        for (int i = 2; i < windowEnd; i++) {
            add(nums[i]);
        }

        long ans = (long) nums[0] + nums[1] + totalValid;
        
        // 开始滑动，当前 i_1 变为 i+1
        for (int i = 1; i < n - 1; i++) {
            // 移除 nums[i+1]
            if (i + 1 < n) {
                remove(nums[i + 1]);
            } else {
                break; // 无法构成更靠后的组合
            }

            // 添加 nums[i + 1 + dist]
            if (i + 1 + dist < n) {
                add(nums[i + 1 + dist]);
            }

            // 如果有效元素不足 k-2 个，说明剩下的数组长度不够分
            if (cntL + cntR < target) {
                break;
            }

            long curCost = (long) nums[0] + nums[i + 1] + totalValid;
            if (curCost < ans) {
                ans = curCost;
            }
        }
        
        return ans;
    }

    // 将元素加入堆
    private void add(int val) {
        // 如果 selectHeap 未满，或者 val 比 selectHeap 中最大的小
        // 则加入 selectHeap，否则加入 candHeap
        if (selectHeap.isEmpty() || val < selectHeap.peek()) {
            selectHeap.offer(val);
            totalValid += val;
            cntL++;
        } else {
            candHeap.offer(val);
            cntR++;
        }
        balance();
    }

    // 标记删除
    private void remove(int val) {
        toRemove.put(val, toRemove.getOrDefault(val, 0) + 1);

        // 如果 val 比 selectHeap 的最大值还要小或相等，那它肯定在 selectHeap 里
        if (!selectHeap.isEmpty() && val <= selectHeap.peek()) {
            cntL--;
            totalValid -= val;
        } else {
            cntR--;
        }
        balance();
    }

    // 清理堆顶的废元素
    private void prune() {
        while (!selectHeap.isEmpty() && toRemove.getOrDefault(selectHeap.peek(), 0) > 0) {
            int val = selectHeap.poll();
            toRemove.put(val, toRemove.get(val) - 1);
        }
        while (!candHeap.isEmpty() && toRemove.getOrDefault(candHeap.peek(), 0) > 0) {
            int val = candHeap.poll();
            toRemove.put(val, toRemove.get(val) - 1);
        }
    }

    // 平衡两个堆的大小，使 selectHeap 恰好有 target 个有效元素
    private void balance() {
        prune();

        // 如果 selectHeap 不够，从 candHeap 拿
        while (cntL < target && !candHeap.isEmpty()) {
            int val = candHeap.poll();
            if (toRemove.getOrDefault(val, 0) > 0) {
                toRemove.put(val, toRemove.get(val) - 1);
                continue;
            } else {
                selectHeap.offer(val);
                totalValid += val;
                cntL++;
                cntR--;
                prune(); // 每次移动后清理一下
            }
        }

        // 如果 selectHeap 太多，移去 candHeap
        while (cntL > target && !selectHeap.isEmpty()) {
            int val = selectHeap.poll();
            if (toRemove.getOrDefault(val, 0) > 0) {
                toRemove.put(val, toRemove.get(val) - 1);
                continue;
            } else {
                candHeap.offer(val);
                totalValid -= val;
                cntL--;
                cntR++;
                prune(); // 同理清理
            }
        }
    }
}

// @lc code=end



/*
// @lcpr case=start
// [1,3,2,6,4,2]\n3\n3\n
// @lcpr case=end

// @lcpr case=start
// [10,1,2,2,2,1]\n4\n3\n
// @lcpr case=end

// @lcpr case=start
// [10,8,18,9]\n3\n1\n
// @lcpr case=end

 */

