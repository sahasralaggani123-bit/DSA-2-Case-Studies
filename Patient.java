// Patient Record Management using AVL Tree

public class Patient {

    int id;
    String name;
    int height;
    Patient left, right;

    Patient(int id, String name) {
        this.id = id;
        this.name = name;
        height = 1;
    }

    static int getHeight(Patient n) {
        if (n == null)
            return 0;
        return n.height;
    }

    static int getBalance(Patient n) {
        if (n == null)
            return 0;
        return getHeight(n.left) - getHeight(n.right);
    }

    static Patient rightRotate(Patient y) {
        Patient x = y.left;
        Patient t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(getHeight(y.left),
                getHeight(y.right)) + 1;

        x.height = Math.max(getHeight(x.left),
                getHeight(x.right)) + 1;

        return x;
    }

    static Patient leftRotate(Patient x) {
        Patient y = x.right;
        Patient t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(getHeight(x.left),
                getHeight(x.right)) + 1;

        y.height = Math.max(getHeight(y.left),
                getHeight(y.right)) + 1;

        return y;
    }

    static Patient insert(Patient root, int id, String name) {

        if (root == null)
            return new Patient(id, name);

        if (id < root.id)
            root.left = insert(root.left, id, name);

        else if (id > root.id)
            root.right = insert(root.right, id, name);

        else
            return root;

        root.height = Math.max(getHeight(root.left),
                getHeight(root.right)) + 1;

        int balance = getBalance(root);

        // LL
        if (balance > 1 && id < root.left.id)
            return rightRotate(root);

        // RR
        if (balance < -1 && id > root.right.id)
            return leftRotate(root);

        // LR
        if (balance > 1 && id > root.left.id) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL
        if (balance < -1 && id < root.right.id) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    static void display(Patient root) {
        if (root != null) {
            display(root.left);

            System.out.println(
                    "ID : " + root.id +
                    " Name : " + root.name);

            display(root.right);
        }
    }

    static void search(Patient root, int id) {

        if (root == null) {
            System.out.println("Patient Not Found");
            return;
        }

        if (root.id == id) {
            System.out.println("Patient Found");
            System.out.println("ID : " + root.id);
            System.out.println("Name : " + root.name);
            return;
        }

        if (id < root.id)
            search(root.left, id);
        else
            search(root.right, id);
    }

    public static void main(String[] args) {

        Patient root = null;

        root = insert(root, 201, "Ravi");
        root = insert(root, 203, "Sneha");
        root = insert(root, 205, "Arjun");
        root = insert(root, 202, "Kiran");
        root = insert(root, 204, "Anjali");

        System.out.println("Patient Records:");

        display(root);

        System.out.println("\nSearching Patient ID 202");

        search(root, 202);
    }
}