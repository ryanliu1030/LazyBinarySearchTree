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
    Boolean first;
    public LazyBinarySearchTree(){
        first = true;
    }

    private TreeNode find(int key){
        TreeNode temp = root;
        while (temp.leftChild != null || temp.rightChild != null){
            if (temp.key == key)
                break;
            else if (temp.key > key)
                if (temp.leftChild != null)
                    temp = temp.leftChild;
                else
                    break;
            else if (temp.key < key)
                if (temp.rightChild != null)
                    temp = temp.rightChild;
                else
                    break;
        }
        return temp;
    }
    public boolean checkRange(int key){
        if (key < 1 || key > 99)
            return false;
        return true;
    }
    public boolean insert(int key){
        if (!checkRange(key))
            throw new IllegalArgumentException("Key value needs to be between 1 and 99");
        if (first){
            root = new TreeNode(key, null, null, false);
            return true;
        }
        TreeNode temp = find(key);

        if (temp.key == key){
            if (temp.deleted)
                temp.deleted = false;
            return false;
        }
        else if (temp.key > key){
            temp.leftChild = new TreeNode(key, null, null, false);
            return true;
        }
        else {
            temp.rightChild = new TreeNode(key, null, null, false);
            return true;
        }
    }

    public boolean delete(int key){
        TreeNode temp = find(key);
        if (temp.key == key){
            temp.deleted = true;
            return true;
        }
        return false;
    }

    public boolean contains(int key){
        if (checkRange(key))
            throw new IllegalArgumentException("Key value needs to be between 1 and 99");
        TreeNode temp = find(key);
        if (temp != null && temp.key == key)
            return true;
        return false;
    }

    public int findMin(){
        int min = root.key;
        TreeNode temp = root;
        while (temp != null){
            if (temp != null && !temp.deleted)
                min = Math.min(temp.key, min);
            temp = temp.leftChild;
        }

        return min;
    }

    public int findMax(){
        int max = root.key;
        TreeNode temp = root;
        while (temp != null){
            if (temp != null && !temp.deleted)
                max = Math.max(temp.key, max);
            temp = temp.leftChild;
        }

        return max;
    }

    public int height(){
        int height = traverseHeight(root, 0);
        return height;
    }

    private int traverseHeight(TreeNode root, int count){
        if (root != null)
            return Math.max(traverseHeight(root.leftChild, count + 1),
                    traverseHeight(root.rightChild, count + 1));

        return count;
    }


    public int size(){
        int size = traverseSize(root, 0);
        return size;
    }
    private int traverseSize(TreeNode root, int count){
        if (root != null)
            return traverseSize(root.leftChild, count + 1) + traverseSize(root.rightChild, count + 1);
        return count;
    }

    public String toString(){
        String result = traverseString(root, "");
        return result;
    }
    private String traverseString(TreeNode root, String result){
        if (root != null)
            if (root.deleted)
                return traverseString(root.leftChild, result +  "*" + root.key + " ") +
                        traverseString(root.rightChild, result + "*" + root.key + " " );
            else
                return traverseString(root.leftChild, result + root.key + " ") +
                        traverseString(root.rightChild, result + root.key + " ");
        return result;
    }

}
