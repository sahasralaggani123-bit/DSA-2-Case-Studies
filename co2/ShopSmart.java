// E-Commerce Sales Analytics using Segment Tree

public class ShopSmart {

    // Array to store segment tree
    static int[] tree = new int[100];

    // Build Segment Tree
    static void buildTree(int[] sales, int node, int start, int end) {

        // If there is only one element
        if (start == end) {
            tree[node] = sales[start];
        } else {

            int mid = (start + end) / 2;

            // Build left subtree
            buildTree(sales, 2 * node, start, mid);

            // Build right subtree
            buildTree(sales, 2 * node + 1, mid + 1, end);

            // Store sum of left and right child
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    // Range Sum Query
    static int getSalesSum(int node, int start, int end,
                           int left, int right) {

        // No overlap
        if (right < start || left > end)
            return 0;

        // Complete overlap
        if (left <= start && end <= right)
            return tree[node];

        // Partial overlap
        int mid = (start + end) / 2;

        int sum1 = getSalesSum(2 * node, start, mid, left, right);
        int sum2 = getSalesSum(2 * node + 1, mid + 1, end, left, right);

        return sum1 + sum2;
    }

    // Update sales value
    static void updateSales(int node, int start, int end,
                            int index, int value) {

        if (start == end) {
            tree[node] = value;
        } else {

            int mid = (start + end) / 2;

            if (index <= mid)
                updateSales(2 * node, start, mid, index, value);
            else
                updateSales(2 * node + 1, mid + 1, end, index, value);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    public static void main(String[] args) {

        // Daily sales data
        int[] sales = {100, 200, 150, 300, 250};

        int n = sales.length;

        // Build Segment Tree
        buildTree(sales, 1, 0, n - 1);

        System.out.println("Original Sales Data:");

        for (int i = 0; i < n; i++) {
            System.out.print(sales[i] + " ");
        }

        System.out.println();

        // Query total sales from Day 2 to Day 4
        int totalSales = getSalesSum(1, 0, n - 1, 1, 3);

        System.out.println("\nSales from Day 2 to Day 4 = "
                           + totalSales);

        // Update Day 3 sales
        updateSales(1, 0, n - 1, 2, 400);

        System.out.println("\nDay 3 Sales Updated to 400");

        // Query again after update
        totalSales = getSalesSum(1, 0, n - 1, 1, 3);

        System.out.println("Updated Sales from Day 2 to Day 4 = "
                           + totalSales);
    }
}