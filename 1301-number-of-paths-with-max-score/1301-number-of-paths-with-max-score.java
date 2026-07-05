class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1_000_000_007;
        int n = board.size();
        int[][] dp = new int[n][n];
        int[][] ways = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        dp[n-1][n-1] = 0;
        ways[n-1][n-1] = 1;

        for(int i = n-1; i>=0; i-- ){
            for(int j = n-1; j>=0; j--){
                char ch = board.get(i).charAt(j);

                if(ch == 'X' || (i==n-1 && j==n-1))
                    continue;
                
                int best =-1;
                long cnt = 0;

                int[][] dirs = {{1,0}, {0,1}, {1,1}};

                for(int[] d : dirs){
                    int ni = i+d[0];
                    int nj = j+d[1];

                    if(ni >= n|| nj >= n|| dp[ni][nj] == -1)
                        continue;

                    if(dp[ni][nj] > best){
                        best = dp[ni][nj];
                        cnt = ways[ni][nj];

                    }
                    else if(dp[ni][nj] == best){
                        cnt = (cnt+ ways[ni][nj]) %MOD;
                    }
                }

                if(best == -1)
                    continue;
                
                int val = 0;
                if(ch != 'E')
                    val = ch - '0';

                dp[i][j] = best +val;
                ways[i][j] = (int)(cnt %MOD);
            }

        }

        if(ways[0][0] == 0)
            return new int[] {0,0};

        return new int[] {dp[0][0], ways[0][0]};
    }
}