class Solution {

    public long maxProfit(int[] prices, int[] strategy, int k) {
        long total = 0, maxSum = 0, sum = 0;
        for (int i = 0; i < prices.length; i++) {
            int p = prices[i], s = strategy[i];
            total += p * s;
            // 1. 入
            sum += p * (1 - s);
            if (i < k - 1) { // 窗口长度不足 k
                if (i >= k / 2 - 1) {
                    sum -= prices[i - k / 2 + 1];
                }
                continue;
            }
            // 2. 更新
            maxSum = Math.max(maxSum, sum);
            // 3. 出
            sum -= prices[i - k / 2 + 1] - prices[i - k + 1] * strategy[i - k + 1];
        }
        return total + maxSum;
    }
}