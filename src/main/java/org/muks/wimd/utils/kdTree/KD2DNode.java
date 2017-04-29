package org.muks.wimd.utils.kdTree;

/**
 * Created by 300000511 on 29/04/17.
 */
public class KD2DNode {
    public int driverId;
    int axis;
    double[] x;
    int id;
    boolean checked;
    boolean orientation;

    KD2DNode Parent;
    KD2DNode Left;
    KD2DNode Right;

    public String toString() {
        return x[0]  + ", " + x[1];
    }

    public KD2DNode(double[] x0, int axis0, int driver)  {
        x = new double[2];
        axis = axis0;
        for (int k = 0; k < 2; k++)
            x[k] = x0[k];


        Left = Right = Parent = null;
        checked = false;
        id = 0;

        driverId = driver;
    }

    public KD2DNode(double[] x0, int axis0)  {
        x = new double[2];
        axis = axis0;
        for (int k = 0; k < 2; k++)
            x[k] = x0[k];


        Left = Right = Parent = null;
        checked = false;
        id = 0;
    }


    public KD2DNode FindParent(double[] x0) {
        KD2DNode parent = null;
        KD2DNode next = this;
        int split;
        while (next != null) {
            split = next.axis;
            parent = next;
            if (x0[split] > next.x[split])
                next = next.Right;
            else
                next = next.Left;
        }

        return parent;
    }


    public KD2DNode Insert(double[] p, int driver) {
        x = new double[2];
        KD2DNode parent = FindParent(p);
        if (equal(p, parent.x, 2) == true)
            return null;

        KD2DNode newNode = new KD2DNode(p,
                parent.axis + 1 < 2 ? parent.axis + 1 : 0, driver);

        newNode.Parent = parent;

        if (p[parent.axis] > parent.x[parent.axis]) {
            parent.Right = newNode;
            newNode.orientation = true; //

        } else {
            parent.Left = newNode;
            newNode.orientation = false; //

        }

        return newNode;
    }

    public KD2DNode Insert(double[] p) {
        x = new double[2];
        KD2DNode parent = FindParent(p);
        if (equal(p, parent.x, 2) == true)
            return null;

        KD2DNode newNode = new KD2DNode(p,
                parent.axis + 1 < 2 ? parent.axis + 1 : 0);

        newNode.Parent = parent;

        if (p[parent.axis] > parent.x[parent.axis]) {
            parent.Right = newNode;
            newNode.orientation = true; //

        } else {
            parent.Left = newNode;
            newNode.orientation = false; //

        }

        return newNode;
    }



    boolean equal(double[] x1, double[] x2, int dim) {
        for (int k = 0; k < dim; k++) {
            if (x1[k] != x2[k])
                return false;
        }

        return true;
    }



    public double distance2(double[] x1, double[] x2, int dim) {
        double S = 0;
        for (int k = 0; k < dim; k++)
            S += (x1[k] - x2[k]) * (x1[k] - x2[k]);

        return S;
    }
}
