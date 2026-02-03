class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 1;
        while(i < n && nums[i-1] < nums[i]){
            i++;
        }
        if(i < 2) return false;
        int j = i;
        while(i < n && nums[i-1] > nums[i]){
            i++;
        }
        if(i == j || i == n)return false;
        while(i < n && nums[i-1] < nums[i]){
            i++;
        }
        return i == n;
    }
}