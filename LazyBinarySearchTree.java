import java.util.*;
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
            first = false;
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
        if (!checkRange(key))
            throw new IllegalArgumentException("Key value needs to be between 1 and 99");
        TreeNode temp = find(key);
        if (temp.key == key && !temp.deleted)
            return true;
        return false;
    }

    public int findMin(){
        int min = root.key;
        TreeNode temp = root;
        while (temp != null){
            if (temp != null && !temp.deleted)
                min = temp.key;
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
        int height = traverseHeight(root, -1 );
        return height;
    }

    private int traverseHeight(TreeNode root, int count){
        if (root == null)
            return count;
        int left = traverseHeight(root.leftChild, count + 1);
        int right = traverseHeight(root.rightChild, count + 1);
        count = Math.max(left, right);
        return count;
    }


    public int size(){
        int size = traverseSize(root);
        return size;
    }
    private int traverseSize(TreeNode root){
        if (root == null)
            return 0;
        int left = traverseSize(root.leftChild);
        int right = traverseSize(root.rightChild);
        return 1 + left + right;
    }

    public String toString(){

        if(root == null)
            return "";
        String result = "";
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while(!stack.empty()){

            TreeNode n = stack.pop();
            if (n.deleted)
                result += "*" + n.key + " ";
            else
                result +=  n.key + " ";

            if(n.rightChild != null){
                stack.push(n.rightChild);
            }
            if(n.leftChild != null){
                stack.push(n.leftChild);
            }

        }
        return result;
    }


}
