


import java.util.*;

class BNode{
    public char val;
    public BNode left;
    public BNode right;

    public BNode(char val){
        this.val = val;
    }
}


public class BinaryTree {

    //创建二叉树
    public BNode creatTree() {
        BNode A = new BNode('A');
        BNode B = new BNode('B');
        BNode C = new BNode('C');
        BNode D = new BNode('D');
        BNode E = new BNode('E');
        BNode F = new BNode('F');
        BNode G = new BNode('G');
        BNode H = new BNode('H');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        E.right = H;
        C.left = F;
        C.right = G;
        return A;

    }

    //前序遍历
    public void preTravel(BNode root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        preTravel(root.left);
        preTravel(root.right);
    }

    //中序遍历
    public void midTravel(BNode root) {
        if(root == null) return;
        midTravel(root.left);
        System.out.print(root.val + " ");
        midTravel(root.right);
    }

    //后序遍历
    public void posTravel(BNode root) {
        if(root == null) return;
        posTravel(root.left);
        posTravel(root.right);
        System.out.print(root.val + " ");
    }

    //求节点个数
    static int size = 0;
    public int getSize(BNode root) {
        if(root == null) return 0;
        size++;
        getSize(root.left);
        getSize(root.right);
        return size;
    }

    public int getSize1(BNode root) {
        if(root == null) return 0;
        return getSize1(root.left) + getSize1(root.right) + 1;
    }

    //求叶子节点个数
    static int leafSize = 0;
    public int getLeafSize1(BNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            leafSize++;
        }
        getLeafSize1(root.left);
        getLeafSize1(root.right);
        return leafSize;
    }
    public int getLeafSize2(BNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            return 1;
        }
        return getLeafSize2(root.left) + getLeafSize2(root.right);
    }

    //求k层节点个数
    public int getKLevelSize(BNode root,int k) {
        if(root == null) return 0;
        if(k == 1) return 1;
        return getKLevelSize(root.left,k - 1) + getKLevelSize(root.right,k - 1);
    }

    //获得高度
    public int getHeight(BNode root) {
        if(root == null) return 0;
        int highL = getHeight(root.left);
        int highR = getHeight(root.right);
        return highL > highR ? highL + 1 : highR + 1;
    }

    //找到val所在的节点
    public BNode find(BNode root, char val) {
        if(root == null) return null;
        if(root.val == val) return root;
        BNode left = find(root.left,val);
        if(left != null) return left;
        BNode right = find(root.right,val);
        if(right != null) return right;
        return null;

    }



//    Queue<BNode> queue = new LinkedList<>();
//    public void levelOrderTraversal(BNode root) {
//        if(root == null) return;
//        queue.offer(root);
//        if (!queue.isEmpty()) {
//            BNode cur = queue.poll();
//            System.out.print(cur.val + " ");
//        }
//        levelOrderTraversal(root.left);
//        levelOrderTraversal(root.right);
//
//    }

    //不用递归遍历树
    Queue<BNode> queue = new LinkedList<>();
    public void levelOrderTraversal(BNode root) {
        if(root == null) return;
        queue.offer(root);
        while (!queue.isEmpty()) {
            BNode cur = queue.poll();
            System.out.print(cur.val + " ");
            if(cur.left != null) {
                queue.offer(cur.left);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    Queue<BNode> que = new LinkedList<>();
    public boolean isCompleteTree(BNode root) {
        if(root == null) return true;
        que.offer(root);
        while(!que.isEmpty()) {
            BNode cur = que.poll();
            if(cur != null) {
                que.offer(cur.left);
                que.offer(cur.right);
            } else {
                break;
            }
        }
        while(!que.isEmpty()) {
            BNode tmp = que.poll();
            if(tmp != null) {
                return false;
            }
        }
        return true;
    }


    //非递归中序遍历
    public void middleTravel(BNode root) {
        if(root == null) return;
        Stack<BNode> s = new Stack<>();
        BNode cur = root;
        while(cur != null || !s.empty()) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            BNode tmp = s.pop();
            System.out.print(tmp.val + " ");
            cur = tmp.right;
        }
    }

    //非递归前序遍历
    public void preOrderTraversal1(BNode root) {
        if(root == null) return;
        Stack<BNode> s = new Stack<>();
        BNode cur = root;
        while(cur != null || !s.empty()) {
            while (cur != null) {
                s.push(cur);
                System.out.print(cur.val + " ");
                cur = cur.left;
            }
            BNode tmp = s.pop();
            cur = tmp.right;
        }
    }

    //非递归后序遍历
    public void postOrderTraversal(BNode root) {
        if(root == null) return;
        Stack<BNode> s = new Stack<>();
        BNode cur = root;
        BNode prev = null;
        while(cur != null || !s.empty()) {
            while(cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            BNode tmp = s.peek();
            if(tmp.right == null || tmp.right == prev) {
                s.pop();
                System.out.print(tmp.val + " ");
                prev = tmp;
            } else {
                cur = tmp.right;
            }
        }
    }

    //创建二叉树
    public static int i = 0;
    public static BNode creatBNode(String s) {
        if(s == null || s.length() <= 0) return null;
        BNode root = null;
        if(s.charAt(i) != '#') {
            root = new BNode(s.charAt(i));
            i++;
            root.left = creatBNode(s);
            root.right = creatBNode(s);
        } else {
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        BNode root = bt.creatTree();
        bt.preTravel(root);
        System.out.println();
        bt.midTravel(root);
        System.out.println();
        bt.posTravel(root);
        System.out.println();
//        System.out.println(bt.getSize(root));
//        System.out.println(bt.getSize1(root));
//        System.out.println(bt.getLeafSize1(root));
//        System.out.println(bt.getLeafSize2(root));
//        System.out.println(bt.getHeight(root));
//        System.out.println(bt.find(root,'C').val);
        bt.levelOrderTraversal(root);
        System.out.println(bt.isCompleteTree(root));
        bt.middleTravel(root);
        System.out.println();
        bt.preOrderTraversal1(root);
        System.out.println();
        bt.postOrderTraversal(root);
        String s = "abc##de#g##f###";
        BNode ret = creatBNode(s);
        bt.middleTravel(ret);

    }
}
