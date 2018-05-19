package com.personalizednewsrecommendation.manager.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;

public class Recommender {
	/**
	 * ------------item------------
	 * |
	 * |
	 * user
	 * |
	 * |
	 * useItem矩阵
	 */
	private Matrix userItem;
	/**
	 * 奇异值矩阵
	 */
	private Matrix sigular;
	/**
	 * 左奇异值矩阵
	 */
	private Matrix leftSigular;
	/**
	 * 右奇异值矩阵
	 */
	private Matrix rightSigular;
	
	public void sigularValueDecompostion() {
		Matrix[] matrixs= userItem.svd();
		this.leftSigular=matrixs[0];
		this.sigular=matrixs[1];
		this.rightSigular=matrixs[2];
		for (int i = 0; i < userItem.getRowCount(); i++) {
			leftSigular.setRowLabel(i, userItem.getRowLabel(i));
		}
		for (int i = 0; i < userItem.getColumnCount(); i++) {
			rightSigular.setColumnLabel(i, userItem.getColumnLabel(i));
		}
		
	}
	
	/*public void approximation() {
		double sum=0;
		for (int i = 0; i < sigular.getRowCount(); i++) {
			if(i>sigular.getColumnCount()){
				sum+=sigular.getAsDouble(i,sigular.getColumnCount()-1);
			}else{
				sum+=sigular.getAsDouble(i,i);
			}
			
		}
	}*/
	public Matrix getCoordinateMatrix(){
		Matrix coordinate = null;
		Matrix leftSigularInverse=leftSigular.inv();
		Matrix sigularInverse=sigular.inv();
		coordinate=sigularInverse.mtimes(leftSigularInverse).mtimes(userItem);
		return coordinate;
	}
	/**
	 * 
	 * @Method: getColumnByLable 
	 * @Description: 得到matrix 标签为id的一个列向量
	 * @param matrix
	 * @param id
	 * @return
	 * @throws
	 */
	public Matrix getColumnByLable(Matrix matrix, Object id) {
		int rowCount=(int) matrix.getRowCount();
		int columCount=(int) matrix.getColumnCount();
	    Matrix item = SparseMatrix.Factory.zeros(rowCount,1); 
		long column=matrix.getColumnForLabel(id);
		item.setColumnLabel(0, id);
		for (int i = 0; i < rowCount; i++) {
			item.setAsInt((int)matrix.getAsDouble(i,column), i, 0);
			item.setRowLabel(i, matrix.getRowLabel(i));
		}
		return item;
	}
	
	/**
	 * 
	 * @Method: calcCoordinate 
	 * @Description: 將一个矩阵切片成列向量
	 * @param item
	 * @return
	 * @throws
	 */
	public List<Matrix> sliceMatricColumn(Matrix matrix){
		List<Matrix> matrixsList=new ArrayList<Matrix>();
		int row = (int) matrix.getRowCount();
		int column = (int) matrix.getColumnCount();
		for (int i = 0; i < column; i++) {
			Matrix temp=SparseMatrix.Factory.zeros(row, 1);
			temp.setColumnLabel(0, matrix.getColumnLabel(i));
			for (int j = 0; j < row; j++) {
				temp.setAsDouble(matrix.getAsDouble(j,i), j, 0);
				temp.setRowLabel(j, matrix.getRowLabel(j));
			}
			matrixsList.add(temp);
		}
		return matrixsList;
	}
	
	/**
	 * 
	 * @Method: calcCoordinate 
	 * @Description: 计算矩阵item 变换后的item坐标
	 * @param item
	 * @return
	 * @throws
	 */
	public Matrix calcCoordinate(Matrix item){
		
		Matrix leftSigularInverse=leftSigular.inv();
		Matrix sigularInverse=sigular.inv();
		/**
		 * 矩阵相乘U*Σ*item得到坐标
		 */
		Matrix coord=sigularInverse.mtimes(leftSigularInverse).mtimes(item);
		coord.setColumnLabel(0, item.getColumnLabel(0));
		/*for (int i = 0; i < item.getRowCount(); i++) {
			coord.setRowLabel(i, item.getRowLabel(i));
		}*/
		return coord;
	}
	
	/**
	 * 
	 * @Method: getSimilarityCoordinates 
	 * @param lable 物品为lable的 
	 * @return
	 * @throws
	 */
	public List<Double> getSimilarityCoordinates(Matrix vector
			, List<Matrix> matrixs
			, Map<Double, Matrix> map) {
	    List<Double> simList = new LinkedList<Double>();
	    Matrix coord = leftSigular.inv().mtimes(sigular.inv()).mtimes(vector);
	    Similarity similarity= new Similarity();
	    similarity.setRank((int) coord.getRowCount());
	    similarity.setVectorX(coord);
	    Iterator<Matrix> it = matrixs.iterator();
	    while (it.hasNext()) {
			Matrix temp = it.next();
			similarity.setVectorY(temp);
			double sim = similarity.pearsonDistance();
			simList.add(sim);
			map.put(sim, temp);
		}
	    Collections.sort(simList);
		return simList; 
	}
	
	public Matrix getSimilarityCoordinate() {
		int rowCount = (int) userItem.getRowCount();
		int columnCount = (int) userItem.getColumnCount();
		Matrix coord = SparseMatrix.Factory.zeros(rowCount, columnCount);
		List<Matrix> sliceMtrix = this.sliceMatricColumn(rightSigular);
		int listSize = sliceMtrix.size();
		double sim = 0;
		Similarity similarity = new Similarity();
		similarity.setRank(rowCount);
		for (int i = 0; i < listSize; i++) {
			coord.setColumnLabel(i, userItem.getColumnLabel(i));
			coord.setRowLabel(i, userItem.getColumnLabel(i));
			for (int j = i+1; j < listSize; j++) {
				similarity.setVectorX(sliceMtrix.get(i));
				similarity.setVectorY(sliceMtrix.get(j));
				sim = similarity.pearsonDistance();
				coord.setAsDouble(Math.abs(sim), i, j);
				coord.setAsDouble(Math.abs(sim), j, i);
			}
		}
		return coord;
	}
	
	public Matrix copyMatrix(Matrix matrix) {
		int rowCount = (int) matrix.getRowCount();
		int columnCount = (int) matrix.getColumnCount();
		Matrix duplicate = SparseMatrix.Factory.zeros(rowCount,columnCount);
		for (int i = 0; i < rowCount; i++) {
			duplicate.setRowLabel(i, matrix.getRowLabel(i));
			for (int j = 0; j < columnCount; j++) {
				duplicate.setColumnLabel(j, matrix.getColumnLabel(j));
				duplicate.setAsDouble(matrix.getAsDouble(i,j), i, j);
			}
		}
		return duplicate;
	}
	/**
	 * 机器学习的svd
	 * @Method: basicSVD 
	 * @param n 学习速率
	 * @param x 迭代次数
	 * @throws
	 */
	public Matrix basicSVD(Matrix matrix, double n, int x){
		int rowCount = (int) matrix.getRowCount();
		int columnCount = (int) matrix.getColumnCount();
		Matrix[] matrixs= matrix.svd();
		Matrix Puk=this.copyMatrix(matrixs[0].mtimes(matrixs[1]));
		Matrix Qki=this.copyMatrix(matrixs[2]);
		int kCount = (int) Puk.getColumnCount();
		for (int m = 0; m < x; m++) {
			/**
			 * 得到相似矩阵即相似的评分矩阵第u行第i列表示用户u对物品i的评分
			 * leftSigular.mtimes(sigular).mtimes(rightSigular);
			 */
			Matrix simMatric =  Puk.mtimes(Qki);
			
			System.out.println("similary--"+m+"\n"+simMatric);
			/**
			 * 计算评分误差
			 */
			Matrix scoreError = matrix.minus(simMatric);
			/**
			 * 方差矩阵
			 */
			Matrix variance = SparseMatrix.Factory.zeros(rowCount, columnCount);
			
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					variance.setAsInt((int)Math.pow(scoreError.getAsDouble(i,j), 2), i, j);
				}
			}
			
			/**
			 * 误差乘以速率n
			 */
			Matrix scoreErrorn=scoreError.times(n);
			
			Matrix tempuk=SparseMatrix.Factory.zeros(rowCount,kCount);
			Matrix tempki=SparseMatrix.Factory.zeros(kCount, columnCount);
			/*for (int u = 0; u < rowCount; u++) {
				for (int k = 0; k < kCount; k++) {
					for (int i = 0; i < columnCount; i++) {
						tempuk.setAsInt((int)(Puk.getAsDouble(u, k)
								+scoreErrorn.getAsInt(u, i)*Qki.getAsDouble(k, i)), u ,k);
						tempki.setAsInt((int)(Qki.getAsDouble(k,i)
								+scoreErrorn.getAsInt(u, i)*Puk.getAsDouble(u, k)), k, i);
					}
				}
			}*/
			double uk=0;
			double ki=0;
			for (int u = 0; u < rowCount; u++) {
				
				for (int k = 0; k < kCount; k++) {
					for (int i = 0; i < columnCount; i++) {
						uk+=scoreErrorn.getAsInt(u, i)*Qki.getAsDouble(k, i);
					}
					tempuk.setAsDouble(Puk.getAsDouble(u, k)+uk, u,k);
					uk=0;
				}
			}
			for (int k = 0; k < kCount; k++) {
				for (int i = 0; i < columnCount; i++) {
					for (int u = 0; u < rowCount; u++) {
						ki+=scoreErrorn.getAsInt(u, i)*Puk.getAsDouble(u, k);
					}
					tempki.setAsDouble(Qki.getAsDouble(k, i)+ki, k, i);
					ki=0;
				}
			}
			/**
			 * 更新Puk和Qki
			 */
			Puk = tempuk;
			Qki = tempki;
		}
		return Puk.mtimes(Qki);
	}
	
	/**
	 * 使用加权求和预测
	 * @Method: ratePrediction 
	 * @param i
	 * @param j
	 * @return
	 * @throws
	 */
	public double ratePrediction(int i, int j) {
		
		return 0;
	}
	
	public void recommender(String lable) {
		
		Matrix vector = null;
		List<Matrix> matrixs = null;
		Map<Double, Matrix> map = null;
		
		List<Double> list = this.getSimilarityCoordinates(vector, matrixs, map); 
	}
}
