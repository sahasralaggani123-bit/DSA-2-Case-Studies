// Student Record Management using AVL Tree

public class Student {

    int id;
    String name;
    int height;

    Student left;
    Student right;

    // Constructor
    Student(int id, String name) {
        this.id = id;
        this.name = name;
        height = 1;
    }

    // Get height
    static int getHeight(Student n) {
        if (n == null)
            return 0;
        return n.height;
    }

    // Get balance factor
    static int getBalance(Student n) {
        if (n == null)
            return 0;
        return getHeight(n.left) - getHeight(n.right);
    }

    // Right Rotation
    static Student rightRotate(Student y) {

        Student x = y.left;
        Student temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // Left Rotation
    static Student leftRotate(Student x) {

        Student y = x.right;
        Student temp = y.left;

        y.left = x;
        x.right = temp;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    // Insert student
    static Student insert(Student root, int id, String name) {

        if (root == null)
            return new Student(id, name);

        if (id < root.id)
            root.left = insert(root.left, id, name);

        else if (id > root.id)
            root.right = insert(root.right, id, name);

        else
            return root;

        root.height = Math.max(getHeight(root.left),
                getHeight(root.right)) + 1;

        int balance = getBalance(root);

        // LL Case
        if (balance > 1 && id < root.left.id)
            return rightRotate(root);

        // RR Case
        if (balance < -1 && id > root.right.id)
            return leftRotate(root);

        // LR Case
        if (balance > 1 && id > root.left.id) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL Case
        if (balance < -1 && id < root.right.id) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Display students
    static void display(Student root) {

        if (root != null) {

            display(root.left);

            System.out.println("ID : " + root.id +
                    "  Name : " + root.name);

            display(root.right);
        }
    }

    // Search student
    static void search(Student root, int id) {

        if (root == null) {
            System.out.println("Student Not Found");
            return;
        }

        if (root.id == id) {
            System.out.println("Student Found");
            System.out.println("ID : " + root.id);
            System.out.println("Name : " + root.name);
            return;
        }

        if (id < root.id)
            search(root.left, id);
        else
            search(root.right, id);
    }

    // Main Method
    public static void main(String[] args) {

        Student root = null;

        // Adding student records
        root = insert(root, 103, "Rahul");
        root = insert(root, 101, "Priya");
        root = insert(root, 105, "Kiran");
        root = insert(root, 102, "Sneha");
        root = insert(root, 104, "Arjun");

        System.out.println("Student Records:");
        display(root);

        System.out.println("\nSearching Student ID 102");
        search(root, 102);
    }
}