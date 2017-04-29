package org.muks.wimd.utils.kdTree;

/**
 * Created by 300000511 on 29/04/17
 *   KDTree traversals and other logic
 */
public class KD2DTree {
    KD2DNode Root;
    int TimeStart, TimeFinish;
    int CounterFreq;
    double d_min;
    KD2DNode nearest_neighbour;

    int KD_id;
    int nList;

    KD2DNode CheckedNodes[];
    int checkedNodesCount;
    KD2DNode List[];

    double xMin[], xMax[];
    boolean maxBoundary[], minBoundary[];
    int nBoundary;


    public KD2DTree(int size) {
        Root = null;
        KD_id = 1;
        nList = 0;
        List = new KD2DNode[size];
        CheckedNodes = new KD2DNode[size];
        maxBoundary = new boolean[2];
        minBoundary = new boolean[2];
        xMin = new double[2];
        xMax = new double[2];
    }


    public boolean add(double[] x) {
        if (nList >= 2000000 - 1)
            return false; // can't add more points

        if (Root == null) {
            Root = new KD2DNode(x, 0);
            Root.id = KD_id++;
            List[nList++] = Root;

        } else {
            KD2DNode pNode;
            if ((pNode = Root.Insert(x)) != null) {
                pNode.id = KD_id++;
                List[nList++] = pNode;

            }
        }

        return true;
    }

    public boolean add(double[] x, int driverId) {
        if (nList >= 2000000 - 1)
            return false; // can't add more points

        if (Root == null) {
            Root = new KD2DNode(x, 0, driverId);
            Root.id = KD_id++;
            List[nList++] = Root;

        } else {
            KD2DNode pNode;
            if ((pNode = Root.Insert(x, driverId)) != null) {
                pNode.id = KD_id++;
                List[nList++] = pNode;

            }
        }

        return true;
    }

    public KD2DNode find_nearest(double[] x) {

        if (Root == null)
            return null;

        checkedNodesCount = 0;
        KD2DNode parent = Root.FindParent(x);
        nearest_neighbour = parent;
        d_min = Root.distance2(x, parent.x, 2);

        if (parent.equal(x, parent.x, 2) == true)
            return nearest_neighbour;

        search_parent(parent, x);
        uncheck();

        return nearest_neighbour;
    }


    public void checkSubtree(KD2DNode node, double[] x) {
        if ((node == null) || node.checked)
            return;

        CheckedNodes[checkedNodesCount++] = node;
        node.checked = true;
        set_bounding_cube(node, x);

        int dim = node.axis;
        double d = node.x[dim] - x[dim];

        if (d * d > d_min) {
            if (node.x[dim] > x[dim])
                checkSubtree(node.Left, x);
            else
                checkSubtree(node.Right, x);
        } else {
            checkSubtree(node.Left, x);
            checkSubtree(node.Right, x);
        }
    }


    public void set_bounding_cube(KD2DNode node, double[] x) {
        if (node == null)
            return;

        int d = 0;
        double dx;
        for (int k = 0; k < 2; k++) {
            dx = node.x[k] - x[k];
            if (dx > 0) {
                dx *= dx;
                if (!maxBoundary[k]) {
                    if (dx > xMax[k])

                        xMax[k] = dx;

                    if (xMax[k] > d_min) {
                        maxBoundary[k] = true;
                        nBoundary++;
                    }

                }

            } else {
                dx *= dx;
                if (!minBoundary[k]) {
                    if (dx > xMin[k])
                        xMin[k] = dx;

                    if (xMin[k] > d_min) {
                        minBoundary[k] = true;
                        nBoundary++;
                    }
                }

            }

            d += dx;
            if (d > d_min)
                return;

        }

        if (d < d_min) {
            d_min = d;
            nearest_neighbour = node;
        }

    }


    public KD2DNode search_parent(KD2DNode parent, double[] x) {
        for (int k = 0; k < 2; k++) {
            xMin[k] = xMax[k] = 0;
            maxBoundary[k] = minBoundary[k] = false; //
        }

        nBoundary = 0;


        KD2DNode searchRoot = parent;

        while (parent != null && (nBoundary != 2 * 2)) {
            checkSubtree(parent, x);
            searchRoot = parent;
            parent = parent.Parent;
        }


        return searchRoot;

    }


    public void uncheck() {
        for (int n = 0; n < checkedNodesCount; n++)
            CheckedNodes[n].checked = false;
    }


    public void inorder() {
        inorder(Root);
    }


    private void inorder(KD2DNode root) {
        if (root != null) {
            inorder(root.Left);
            System.out.print("(" + root.x[0] + ", " + root.x[1] + ")  ");
            inorder(root.Right);
        }
    }


    public void preorder() {
        preorder(Root);
    }


    private void preorder(KD2DNode root) {
        if (root != null) {
            System.out.print("(" + root.x[0] + ", " + root.x[1] + ")  ");
            inorder(root.Left);
            inorder(root.Right);
        }

    }


    public void postorder() {
        postorder(Root);

    }


    private void postorder(KD2DNode root) {
        if (root != null) {
            inorder(root.Left);
            inorder(root.Right);
            System.out.print("(" + root.x[0] + ", " + root.x[1] + ")  ");
        }

    }


}
