class Solution {

    private static final long INF = (long) 1e18;
    public int minOperations(String s1, String s2) {
        int n = s1.length();

        String[] melorvanti = { s1, s2};

        int[] a = new int[n];
        int[] b = new int[n];

        for(int i =0; i<n; i++){
            a[i] = s1.charAt(i) - '0';
            b[i] = s2.charAt(i) - '0';
            
        }
        long[] dp = new long[]{0, INF};
        

        for(int i =0; i<n-1; i++){
            long[] ndp = new long[]{INF, INF};
            
            for(int prev =0; prev <= 1; prev++){
                if(dp[prev] >= INF) continue;

                for(int cur =0; cur<=1; cur++){
                    int deg = prev+cur;
                    long c = cost(a[i], b[i], deg);
                    if(c>= INF) continue;

                    ndp[cur] = Math.min(ndp[cur], dp[prev] + c+ cur);
                }
            }
            dp = ndp;
        }

        long ans = INF;

        for(int prev =0; prev<= 1; prev++){
            long c = cost(a[n-1], b[n-1], prev);
            if(c<INF) ans = Math.min(ans, dp[prev] + c);
        }
        return ans >= INF ? -1:(int) ans;
        
    }

    private long cost(int a, int b, int deg){
        if(deg == 0){
            if(a ==1 && b ==0) return INF;
            return(a ==0 && b == 1) ? 1 : 0;
        }

        long res = Math.max(0, deg -a);

        if(b == 1) res++;

        return res;
    }
}