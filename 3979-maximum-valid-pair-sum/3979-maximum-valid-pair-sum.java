class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int[] mavontelia = nums;

        int ans = 0;
        int maxLeft = mavontelia[0];

        for(int j = k; j< mavontelia.length; j++){
            maxLeft = Math.max(maxLeft, mavontelia[j -k]);
            ans = Math.max(ans, maxLeft + mavontelia[j]);
        }
        
        return ans;
    }
}