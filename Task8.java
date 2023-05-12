import java.util.*;

public class ShortestPathRouting 
{
    private Map<String, Map<String, Integer>> graph;
    private Map<String, Map<String, String>> routingTables;

    public ShortestPathRouting(List<String> connectivityInfo) 
    {
        graph = new HashMap<>();
        routingTables = new HashMap<>();

        for (String conn : connectivityInfo) 
        {
            String[] parts = conn.split(":");

            String node1 = parts[0].trim();
            String node2 = parts[1].trim();

            addEdge(node1, node2);
            addEdge(node2, node1);
        }

        for (String node : graph.keySet()) 
        {
            routingTables.put(node, buildRoutingTable(node));
        }
    }

    private void addEdge(String node1, String node2) 
    {
        if (!graph.containsKey(node1)) 
        {
            graph.put(node1, new HashMap<>());
        }
        graph.get(node1).put(node2, 1);
    }

    private Map<String, String> buildRoutingTable(String source) 
    {
        Map<String, String> routingTable = new HashMap<>();

        Set<String> visited = new HashSet<>();

        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> routingTable.get(a).length() - routingTable.get(b).length());

        routingTable.put(source, source);
        visited.add(source);
        queue.offer(source);

        while (!queue.isEmpty()) 
        {
            String currNode = queue.poll();
            for (String neighbor : graph.get(currNode).keySet()) 
            {
                if (!visited.contains(neighbor)) 
                {
                    visited.add(neighbor);
                    routingTable.put(neighbor, routingTable.get(currNode) + neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return routingTable;
    }

    public void printRoutingTables() 
    {
        for (String node : routingTables.keySet()) 
        {
            System.out.println("Routing table for node " + node + ":");
            Map<String, String> table = routingTables.get(node);
            for (String dest : table.keySet()) 
            {
                System.out.println(dest + " -> " + table.get(dest));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) 
    {
        List<String> connectivityInfo = new ArrayList<>();
        connectivityInfo.add("A: B");
        connectivityInfo.add("A: C");
        connectivityInfo.add("A: D");
        connectivityInfo.add("B: C");
        connectivityInfo.add("B: E");
        connectivityInfo.add("C: D");
        connectivityInfo.add("C: E");
        connectivityInfo.add("D: E");

        ShortestPathRouting sp = new ShortestPathRouting(connectivityInfo);
        sp.printRoutingTables();
    }
}