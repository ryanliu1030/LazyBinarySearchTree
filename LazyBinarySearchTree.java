public class LazyBinarySearchTree {
    private class TreeNode{
        int key;
        TreeNode leftChild, rightChild;
        boolean deleted;
        public TreeNode(int key, TreeNode leftChild, TreeNode rightChild, boolean deleted){
            this.key = key;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.deleted = deleted;
        }
    }

    TreeNode root;
    public LazyBinarySearchTree(){
        root.key = 0;
        root.leftChild = null;
        root.rightChild = null;
        root.deleted = false;
    }
    private TreeNode find(int key){
        TreeNode temp = root;
        while (temp.leftChild != null || temp.rightChild != null){
            if (temp.key > key)
                temp = temp.leftChild;

        }
    }

    public boolean insert(int key){
        if (key < 1 || key > 99)
            throw new IllegalArgumentException("Key value needs to be between 1 and 99");
        TreeNode temp = find(key, root);
        if (temp.key == key)
            return false;
        else if (temp.key > key){
            temp.leftChild = new TreeNode(key, null, null, false);
        }
        return false;
    }

    public int contains(int key){

        return 0;
    }



}
