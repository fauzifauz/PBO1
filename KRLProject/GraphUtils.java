import java.util.*;

public class GraphUtils {
    public static class PathNode {
        public final String node;
        public PathNode(String node) { this.node = node; }
    }

    private static Map<String, List<Edge>> buildAdjacency(DataStore store) {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (Station s : store.getStations()) adj.put(s.getName(), new ArrayList<>());
        for (Route r : store.getRoutes()) {
            adj.computeIfAbsent(r.getAsal(), k -> new ArrayList<>()).add(new Edge(r.getTujuan(), r.getHarga()));
            adj.computeIfAbsent(r.getTujuan(), k -> new ArrayList<>()).add(new Edge(r.getAsal(), r.getHarga())); // undirected
        }
        return adj;
    }

    private static class Edge {
        String to; double cost;
        Edge(String to, double cost) { this.to = to; this.cost = cost; }
    }

    public static List<PathNode> findShortestPath(DataStore store, String start, String goal) {
        if (start == null || goal == null) throw new IllegalArgumentException("Start/Goal null");
        Map<String, List<Edge>> adj = buildAdjacency(store);
        if (!adj.containsKey(start) || !adj.containsKey(goal)) return null;

        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        for (String node : adj.keySet()) dist.put(node, Double.POSITIVE_INFINITY);
        dist.put(start, 0.0);

        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingDouble(dist::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (u.equals(goal)) break;
            for (Edge e : adj.getOrDefault(u, new ArrayList<>())) {
                double alt = dist.get(u) + e.cost;
                if (alt < dist.getOrDefault(e.to, Double.POSITIVE_INFINITY)) {
                    dist.put(e.to, alt);
                    prev.put(e.to, u);
                    pq.remove(e.to);
                    pq.add(e.to);
                }
            }
        }

        if (!prev.containsKey(goal) && !start.equals(goal)) {
            if (start.equals(goal)) return Collections.singletonList(new PathNode(start));
            return null;
        }

        // reconstruct
        LinkedList<PathNode> path = new LinkedList<>();
        String cur = goal;
        path.addFirst(new PathNode(cur));
        while (prev.containsKey(cur)) {
            cur = prev.get(cur);
            path.addFirst(new PathNode(cur));
        }
        return path;
    }

    public static double getEdgeCost(DataStore store, String a, String b) {
        for (Route r : store.getRoutes()) {
            if ((r.getAsal().equalsIgnoreCase(a) && r.getTujuan().equalsIgnoreCase(b))
             || (r.getAsal().equalsIgnoreCase(b) && r.getTujuan().equalsIgnoreCase(a))) {
                return r.getHarga();
            }
        }
        return Double.POSITIVE_INFINITY;
    }
}
