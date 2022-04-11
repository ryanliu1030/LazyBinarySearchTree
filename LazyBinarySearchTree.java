import java.util.*;
public class LazyBinarySearchTree {

    //Creates primary TreeNode class
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

    //Initalize it as null to check if it's time add a root
    public LazyBinarySearchTree(){
        root = null;
    }


    //Find the targeted node or the node prior to it if its unfound.
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

    //check the range if it's [1,99], else throw exception
    public void checkRange(int key){
        if (key < 1 || key > 99)
            throw new IllegalArgumentException("Key value needs to be between 1 and 99");;
    }



    //Insert the key inside of the bst tree.
    //Return true if a node is actually added.
    //False if a node has the target value is marked deleted(will unmark the deletion)
    // or if a node is already there.
    public boolean insert(int key){
        checkRange(key);
        if (root == null){
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

    //mark a node to be deleted if it's present.
    //return true if such action was done, else return false.
    public boolean delete(int key){
        checkRange(key);
        if(root == null)
            return false;
        TreeNode temp = find(key);
        if (temp.key == key){
            temp.deleted = true;
            return true;
        }
        return false;
    }

    //Check if the tree has a node that has the targeted value
    public boolean contains(int key){
        checkRange(key);
        TreeNode temp = find(key);
        if (temp.key == key && !temp.deleted)
            return true;
        return false;
    }

    //find the minimum value in the tree
    public int findMin(){
        if (root == null)
            return 0;
        int min = root.key;
        TreeNode temp = root;
        while (temp != null){
            if (temp != null && !temp.deleted)
                min = temp.key;
            temp = temp.leftChild;
        }

        return min;
    }

    //find the maximum value in the tree
    public int findMax(){
        if (root == null)
            return 100;
        int max = root.key;
        TreeNode temp = root;
        while (temp != null){
            if (temp != null && !temp.deleted)
                max = Math.max(temp.key, max);
            temp = temp.leftChild;
        }

        return max;
    }

    //find the height of the tree, if it's a empty tree will return -1.
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

    //return the number of the nodes in the tree
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

    //return the tree in pre-order.
    public String toString(){
        if(root == null)
            return "No nodes found.";
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
