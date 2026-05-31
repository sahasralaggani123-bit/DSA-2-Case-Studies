// HealthNet ICU Bed Occupancy Analytics using Segment Tree

public class HealthNet {

    static int[] tree = new int[100];

    // Build Segment Tree
    static void buildTree(int[] beds, int node,
                          int start, int end) {

        if (start == end) {
            tree[node] = beds[start];
        } else {

            int mid = (start + end) / 2;

            buildTree(beds, 2 * node,
                      start, mid);

            buildTree(beds, 2 * node + 1,
                      mid + 1, end);

            tree[node] =
                tree[2 * node] +
                tree[2 * node + 1];
        }
    }

    // Range Query
    static int getBedUsage(int node,
                           int start,
                           int end,
                           int left,
                           int right) {

        if (right < start || left > end)
            return 0;

        if (left <= start &&
            end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        int sum1 =
            getBedUsage(2 * node,
                        start,
                        mid,
                        left,
                        right);

        int sum2 =
            getBedUsage(2 * node + 1,
                        mid + 1,
                        end,
                        left,
                        right);

        return sum1 + sum2;
    }

    // Update Occupancy
    static void updateBeds(int node,
                           int start,
                           int end,
                           int index,
                           int value) {

        if (start == end) {

            tree[node] = value;

        } else {

            int mid = (start + end) / 2;

            if (index <= mid)
                updateBeds(2 * node,
                           start,
                           mid,
                           index,
                           value);
            else
                updateBeds(2 * node + 1,
                           mid + 1,
                           end,
                           index,
                           value);

            tree[node] =
                tree[2 * node] +
                tree[2 * node + 1];
        }
    }

    public static void main(String[] args) {

        int[] beds = {20, 25, 18, 30, 22};

        int n = beds.length;

        buildTree(beds, 1, 0, n - 1);

        System.out.println(
            "Original ICU Bed Usage:");

        for (int i = 0; i < n; i++) {
            System.out.print(beds[i] + " ");
        }

        System.out.println();

        int totalBeds =
            getBedUsage(1, 0,
                        n - 1,
                        1, 3);

        System.out.println(
            "\nBeds Occupied from Day 2 to Day 4 = "
            + totalBeds);

        updateBeds(1, 0,
                   n - 1,
                   2, 35);

        System.out.println(
            "\nDay 3 Occupancy Updated to 35");

        totalBeds =
            getBedUsage(1, 0,
                        n - 1,
                        1, 3);

        System.out.println(
            "Updated Beds Occupied from Day 2 to Day 4 = "
            + totalBeds);
    }
}