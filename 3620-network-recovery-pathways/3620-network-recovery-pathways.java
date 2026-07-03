class Solution {

    List<int[]>[] graph;
    int[] topo;
    boolean[] online;
    long k;
    int n; 
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.online = online;
        this.k = k;
        this.n = online.length;

        graph = new ArrayList[n];
        for(int i =0; i<n; i++)
            graph[i] = new ArrayList<>();

        int[] indeg = new int[n];
        int maxCost =0; 
        for(int[] e: edges){
            graph[e[0]].add(new int[]{e[1], e[2]});
            indeg[e[1]]++;
            maxCost = Math.max(maxCost, e[2]);
        }

        topo = new int[n];
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            if (indeg[i] == 0) q.offer(i);
        }

        int idx =0;
        while(!q.isEmpty()){
            int u = q.poll();
            topo[idx++] = u;

            for(int[] e : graph[u]){
                if(--indeg[e[0]] == 0){
                    q.offer(e[0]);
                }
            }
        }

        int lo =0, hi = maxCost, ans = -1;

        while(lo <= hi){
            int mid = lo +(hi -lo)/2;
            if(can(mid)){
                ans = mid;
                lo = mid +1;
            }
            else{
                hi = mid -1;
            }
        }
        return ans;
    }

    private boolean can(int minEdge){
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] =0;

        for(int u: topo){
            if(dist[u] == INF) continue;

            if(u != 0&& u!= n-1 && !online[u]) continue;

            for(int[] e:graph[u]){
                int v = e[0];
                int w = e[1];

                if(w < minEdge) continue;
                if(v != n-1 && v!= 0 && !online[v]) continue;

                if(dist[u] +w <dist[v]){
                    dist[v] = dist[u] + w;
                }
            }
        }

        return dist[n -1] <= k;
    }
}