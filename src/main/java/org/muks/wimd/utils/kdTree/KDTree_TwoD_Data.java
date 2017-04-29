//package org.muks.wimd.utils.kdTree;
//
///**
// * Created by 300000511 on 29/04/17.
// */
//public class KDTree_TwoD_Data {
//    public static void main(String args[]) {
//
//        int numpoints = 5;
//        KD2DTree kdt = new KD2DTree(numpoints);
//        double x[] = new double[2];
//        x[0] = 0.0;
//        x[1] = 0.0;
//        kdt.add(x, 1);
//
//
//        x[0] = 3.3;
//        x[1] = 1.5;
//        kdt.add(x, 2);
//
//
//        x[0] = 4.7;
//        x[1] = 11.1;
//        kdt.add(x, 3);
//
//
//        x[0] = 5.0;
//        x[1] = 12.3;
//        kdt.add(x, 4);
//
//
//        x[0] = 5.1;
//        x[1] = 1.2;
//        kdt.add(x, 5);
//
//
//        System.out.println("Inorder of 2D Kd tree: ");
//        double a[] = new double[2];
//        a[0] = 3.0;
//        a[1] = 3.1;
//        KD2DNode node = kdt.find_nearest(a);
//
//
//        System.out.println("+++ " + node.toString() +", Driver: " + node.driverId);
//
////        kdt.inorder();
////
////
////        System.out.println("\nPreorder of 2D Kd tree: ");
////        kdt.preorder();
////
////        System.out.println("\nPostorder of 2D Kd tree: ");
////        kdt.postorder();
//
//    }
//}
