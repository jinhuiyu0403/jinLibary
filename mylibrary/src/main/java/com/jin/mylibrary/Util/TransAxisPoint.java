package com.jin.mylibrary.Util;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;

public class TransAxisPoint {



    public static TransPoint3D[] Transformation(TransPoint3D[] pointsA, TransPoint3D[] pointsB,TransPoint3D[] srcDatas){
        TransPoint3D[] results = new TransPoint3D[srcDatas.length];
        double[][] ptsA = new double[3][3];
        double[][] ptsB = new double[3][3];
        ptsA[0][0] = pointsA[0].x;ptsA[0][1] = pointsA[0].y;ptsA[0][2] = pointsA[0].z;
        ptsA[1][0] = pointsA[1].x;ptsA[1][1] = pointsA[1].y;ptsA[1][2] = pointsA[1].z;
        ptsA[2][0] = pointsA[2].x;ptsA[2][1] = pointsA[2].y;ptsA[2][2] = pointsA[2].z;

        ptsB[0][0] = pointsB[0].x;ptsB[0][1] = pointsB[0].y;ptsB[0][2] = pointsB[0].z;
        ptsB[1][0] = pointsB[1].x;ptsB[1][1] = pointsB[1].y;ptsB[1][2] = pointsB[1].z;
        ptsB[2][0] = pointsB[2].x;ptsB[2][1] = pointsB[2].y;ptsB[2][2] = pointsB[2].z;
        //计算质心
        double[] centroidA = meanEachRow(ptsA);
        double[] centroidB = meanEachRow(ptsB);
        //中心化点（将点移动到原点周围）
        double[][] ptsA_centered = subtractCentroid(ptsA, centroidA);
        double[][] ptsB_centered = subtractCentroid(ptsB, centroidB);

        //使用奇异值分解（SVD）的变体来计算旋转矩阵
        // 将二维数组转换为 RealMatrix 对象
        RealMatrix matrixA = MatrixUtils.createRealMatrix(ptsA_centered);
        RealMatrix matrixB = MatrixUtils.createRealMatrix(ptsB_centered);

        // 计算 ptsA_centered * ptsB_centered'
        RealMatrix product = matrixA.multiply(matrixB.transpose());

        // 执行奇异值分解
        SingularValueDecomposition svd = new SingularValueDecomposition(product);

        // 提取 U 和 V 矩阵
        RealMatrix U = svd.getU();
        RealMatrix V = svd.getV();
        // 计算 U 的转置
        RealMatrix UTranspose = U.transpose();
        // 计算 R = V * U'
        RealMatrix R = V.multiply(UTranspose);

        // 创建一个新的 3x3 矩阵来存储交换行后的结果
        double[][] swappedRData = new double[3][3];

        // 复制 R 的元素到新的矩阵，同时交换最后两行
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1) {
                    swappedRData[i][j] = R.getEntry(2, j); // 将第三行的元素复制到第二行
                } else if (i == 2) {
                    swappedRData[i][j] = R.getEntry(1, j); // 将第二行的元素复制到第三行
                } else {
                    swappedRData[i][j] = R.getEntry(i, j); // 其他行保持不变
                }
            }
        }

        // 将交换行后的数组转换为 RealMatrix 对象
        R = MatrixUtils.createRealMatrix(swappedRData);
        //开始转换
        for(int n = 0;n < srcDatas.length;n++) {
            double[] ptNewA = {srcDatas[n].x,srcDatas[n].y,srcDatas[n].z};
            //ptNewB = R * (ptNewA - centroidA') + centroidB'; % 转换到坐标系B
            double[][] ptnew = new double[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ptnew[i][j] = ptNewA[i] - centroidA[j];
                }
            }
            RealMatrix ptNewMatrix = R.multiply(MatrixUtils.createRealMatrix(ptnew));
            ptnew = ptNewMatrix.getData();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ptnew[i][j] = ptnew[i][j] + centroidB[j];
                }
            }
            results[n] = new TransPoint3D(ptnew[0][0], ptnew[1][0], ptnew[2][0]);
        }
        return results;
    }

    public static double[] meanEachRow(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length; // Assuming all rows have the same number of columns
        double[] rowMeans = new double[numRows];

        for (int i = 0; i < numRows; i++) {
            double sum = 0.0;
            for (int j = 0; j < numCols; j++) {
                sum += matrix[i][j];
            }
            rowMeans[i] = sum / numCols;
        }

        return rowMeans;
    }

    public static double[][] subtractCentroid(double[][] matrix, double[] centroid) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        double[][] centeredMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // Since centroid only has one value per row, we subtract the same value for each column in that row
                centeredMatrix[i][j] = matrix[i][j] - centroid[i];
            }
        }

        return centeredMatrix;
    }

}
