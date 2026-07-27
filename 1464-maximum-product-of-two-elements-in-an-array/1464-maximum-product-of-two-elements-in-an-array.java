class Solution {
    public int maxProduct(int[] nums) {
        int max =0, max1 = 0;
        for(int num : nums){
            if(num >= max){
                max1 = max;
                max = num;
            }
            else if(num > max1){
                max1 = num;
            }
        }
        return (max - 1) *(max1 -1);
    }
}