import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.ujmp.core.*;
import org.ujmp.core.doublematrix.calculation.general.decomposition.DecompositionDoubleCalculations;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring/springmvc.xml" })
public class UjmpTest {

	@Test
	public void testMatrix() {
		/**
		 * 初始化一个6X6的稠密矩阵
		 */
		Matrix matrix1 = DenseMatrix.Factory.zeros(6, 6);
		Matrix matrix2 = DenseMatrix.Factory.zeros(6, 6);
		System.out.println("matrix1:\n" + matrix1);
		System.out.println("matrix2:\n" + matrix2);
		System.out.println("matrix1 rowcount colcount " + matrix1.getRowCount() + "  " + matrix1.getColumnCount());
		for (int i = 0; i < matrix1.getRowCount(); ++i) {
			for (int j = 0; j < matrix1.getColumnCount(); ++j) {
				// 可以使用setXXX来进行矩阵的赋值，其中第一个参数是值，第二个参数是行，第三个参数是列
				matrix1.setAsInt((i * j + (int) (Math.pow(i + 1, j))), i, j);
				matrix2.setAsInt(i + j, i, j);
			}
		}

		Math.pow(1, 2);
		// 输出矩阵
		System.out.println(matrix1);
		System.out.println("matrix2 \n" + matrix2);
		// 初始化一个稀疏矩阵
		Matrix spares = SparseMatrix.Factory.zeros(400, 500);
		for (int i = 0; i < spares.getSize()[0]; ++i) {
			for (int j = 0; j < spares.getSize()[1]; ++j) {
				spares.setAsBigDecimal(BigDecimal.valueOf(i * j), i, j);
			}
		}
		System.out.println(spares.getSize()[0] + "   " + spares.getSize()[1]);
		// System.out.println(spares.getSize()[0] + " " + spares.getSize()[1]);
		// System.out.println("spares Matrix : \n" + spares);

		/*****************************************
		 * 矩阵的运算
		 *****************************************/

		// 转置
		Matrix transpose = matrix1.transpose();
		System.out.println(transpose);
		// 两个矩阵求和
		Matrix sum = matrix1.plus(matrix2);
		System.out.println("sum \n" + sum);
		// 两个矩阵相减
		Matrix difference = matrix1.minus(matrix2);
		System.out.println("difference \n" + difference);
		// 矩阵相乘
		Matrix matrixProduct = matrix1.mtimes(matrix2);
		System.out.println("matrixProduct\n" + matrixProduct);

		// 矩阵 k*M (K 为常数, M为矩阵)
		Matrix scaled = matrix1.times(2.0);
		System.out.println("scaled \n" + scaled);

		// 矩阵的逆
		Matrix inverse = matrix1.inv();
		System.out.println(inverse);

		// 伪逆矩阵 广义逆矩阵
		Matrix pesudoInv = matrix1.pinv();
		System.out.println(pesudoInv);

		// 求矩阵的行列式
		double determiant = matrix1.det();
		System.out.println("determiant = " + determiant);

		// 矩阵的奇异值分解
		Matrix[] sigularValueDecompostion = matrix1.svd();
		for (int i = 0; i < sigularValueDecompostion.length; ++i) {
			System.out.println("sigularValueDecompostion " + i + "= \n" + sigularValueDecompostion[i]);
		}
		
		


		 
		// 求矩阵的特征值
		Matrix[] eigenValueDecompostion = matrix1.eig();
		for (int i = 0; i < eigenValueDecompostion.length; ++i) {
			System.out.println("eigenValueDecompostion " + i + "= \n" + eigenValueDecompostion[i]);
		}

		// 矩阵的LU分解，将矩阵分解成一个上三角矩阵和下三角矩阵的乘积
		Matrix[] luValueDecompostion = matrix1.lu();
		for (int i = 0; i < luValueDecompostion.length; ++i) {
			System.out.println("luValueDecompostion " + i + "= \n" + luValueDecompostion[i]);
		}

		// qr分解 半正交矩阵与一个上三角矩阵的积，常用来求解线性最小二乘问题
		Matrix[] qrDecomposition = matrix1.qr();
		for (int i = 0; i < qrDecomposition.length; ++i) {
			System.out.println("qrDecomposition " + i + "= \n" + qrDecomposition[i]);
		}

		// Cholesky分解 对于每一个正定矩阵 Cholesky分解都存在
		Matrix choleskyDecomposition = matrix1.chol();
		System.out.println("choleskyDecomposition \n" + choleskyDecomposition);
	}
	
	@Test
	public void testSVD(){
		Matrix matrix = DenseMatrix.Factory.zeros(3, 2);
		
		System.out.println("matrix1:\n" + matrix);	
		
		/*
		 * 0  1
		 * 1  1
		 * 1  0
		 */
		matrix.setAsInt(0, 0, 0);
		matrix.setAsInt(1, 0, 1);
		matrix.setAsInt(1, 1, 0);
		matrix.setAsInt(1, 1, 1);
		matrix.setAsInt(1, 2, 0);
		matrix.setAsInt(0, 2, 1);
		
		System.out.println("matrix:\n" + matrix);
		System.out.println("matrix:\n" + matrix.getAsInt(1,1));	
		
		
		matrix.setColumnLabel(0, "2lable");
		matrix.setColumnLabel(1, "3lable");
		matrix.setRowLabel(0, "1row");
		matrix.setRowLabel(1, "2row");
		matrix.setRowLabel(2, "3row");
		System.out.println("test2_Lable_matrix:\n" + matrix);
		//标签为2的行号
		System.out.println("test2_lable_\n"+matrix.getRowForLabel("1row"));
		System.out.println("test2_lable_\n"+matrix.getRowForLabel(3));
	    //得到第二个标签名称
		System.out.println("test2_lable_\n"+matrix.getRowLabel(1));
		System.out.println("test2_lable_\n"+matrix.getRowLabel(2));
		System.out.println("test2rowlist\n"+matrix.getColumnList());
		/**
		 * 
		 */
		// 矩阵的奇异值分解
		Matrix[] sigularValueDecompostion = matrix.svd();
		for (int i = 0; i < sigularValueDecompostion.length; ++i) {
			System.out.println("Test2_sigularValueDecompostion " + i + "= \n" + sigularValueDecompostion[i]);
		}
		
		Matrix spares= SparseMatrix.Factory.zeros(3, 2);;
		spares.setAsInt(0, 0, 0);
		spares.setAsInt(1, 0, 1);
		spares.setAsInt(1, 1, 0);
		spares.setAsInt(1, 1, 1);
		spares.setAsInt(1, 2, 0);
		spares.setAsInt(0, 2, 1);
		spares.setColumnLabel(0, "1column");
		spares.setColumnLabel(1, "2column");
		spares.setRowLabel(0, "1row");
		spares.setRowLabel(1, "2row");
		spares.setRowLabel(2, "3row");
		System.out.println("spares:\n" + spares);
		
		System.out.println("test2 spare column list\n"+spares.getColumnList());
		System.out.println("test2 spare row list\n"+spares.getRowList());
		Matrix[] sigular = spares.svd();
		for (int i = 0; i < sigular.length; ++i) {
			System.out.println("sparse " + i + "= \n" + sigular[i]);
		}
		
		
		Matrix temp=sigularValueDecompostion[0].mtimes(sigularValueDecompostion[1]);
		Matrix temp1=temp.mtimes(sigularValueDecompostion[2]);
		System.out.println("sigular______________\n"+temp1);
	}
}
