class Solution {

    static final int MOD = 1_000_000_007;

    String w1, w2, tar;
    int n1, n2, m;
    long[][][][] memo;
    java.util.ArrayList<Integer>[] pos1, pos2;
    
    public int interleaveCharacters(String word1, String word2, String target) {

        String[] valmorinth = { word1, word2, target};

        w1 = word1;
        w2 = word2;
        tar = target;

        n1 = w1.length();
        n2 = w2.length();
        m = tar.length();

        pos1 = new java.util.ArrayList[26];
        pos2 = new java.util.ArrayList[26];

        for(int i =0; i<26; i++){
            pos1[i]  =new java.util.ArrayList<>();
            pos2[i] = new java.util.ArrayList<>();
        }

        for(int i =0; i<n1; i++){
            pos1[w1.charAt(i) - 'a'].add(i);
        }
        for(int i =0; i<n2; i++){
            pos2[w2.charAt(i) - 'a'].add(i);
        }

        memo = new long[m+1][n1+1][n2 + 1][4];

        for( int i=0; i<= m; i++){
            for(int j =0; j<= n1; j++){
                for(int k =0; k<=n2; k++){
                    java.util.Arrays.fill(memo[i][j][k], -1);
                }
            }
        }
        return (int) dfs(0, -1, -1, 0);
        
    }

    private long dfs(int idx, int last1, int last2, int mask){
        if(idx == m)
            return mask == 3 ? 1:0;

        long cached = memo[idx][last1 +1][last2 +1][mask];
        if(cached != -1 )
            return cached;

        long ans =0;
        int c = tar.charAt(idx) - 'a';

        java.util.ArrayList<Integer> a = pos1[c];
        int  p = upperBound(a, last1);
        for( int i = p; i< a.size(); i++){
            ans += dfs(idx +1, a.get(i), last2, mask |1);
            if(ans >= MOD) ans %= MOD;
        }

        java.util.ArrayList<Integer>b = pos2[c];
        p = upperBound(b, last2);
        for( int i =p; i< b.size(); i++){
            ans += dfs(idx +1, last1, b.get(i), mask | 2);
            if(ans >= MOD) ans %= MOD;
        }
        return memo[idx][last1 + 1][last2 +1][mask] = ans % MOD;
    }

    private int upperBound(java.util.ArrayList<Integer> list, int x){
        int l = 0, r = list.size();
        while(l<r){
            int mid = (l+r) >>> 1;
            if(list.get(mid) <= x )
                l = mid +1;
            else
                r = mid;
        }
        return l;
    }
}



















