package com.personalizednewsrecommendation.manager.algorithm;

import org.ujmp.core.Matrix;

public  class Similarity {
	/**
	 * 假定都是m*1矩阵
	 */
	private Matrix vectorX;
	
	private Matrix vectorY;
	
	private int rank;
	
	public Similarity() {
		super();
	}

	public Similarity(Matrix vectorX, Matrix vectorY, int rank) {
		super();
		this.vectorX = vectorX;
		this.vectorY = vectorY;
		this.rank = rank;
	}
    
	public Matrix getVectorX() {
		return vectorX;
	}

	public void setVectorX(Matrix vectorX) {
		this.vectorX = vectorX;
	}

	public Matrix getVectorY() {
		return vectorY;
	}

	public void setVectorY(Matrix vectorY) {
		this.vectorY = vectorY;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * 
	 * @Method: eucledianDistance 
	 * @Description: 欧几里得距离
	 * @return
	 * @throws
	 */
	public double eucledianDistance(){
		Matrix vector=vectorX.minus(vectorY);
		double sum=0;
		for (int i = 0; i <this.rank; i++) {
			sum+=Math.pow(vector.getAsDouble(i,0), 2);
		}
		return Math.sqrt(sum);
	}
	
	/**
	 * 
	 * @Method: manhattanDistance 
	 * @Description: 曼哈顿距离（Manhattan Distance）
	 * @return
	 * @throws
	 */
	public double manhattanDistance() {
		double sum=0;
		Matrix vector=vectorX.minus(vectorY);
		for (int i = 0; i < this.rank; i++) {
			sum+=Math.abs(vector.getAsDouble(1,0));
		}
		return sum;
		
	}
	
	/**
	 * 明可夫斯基距离（Minkowski distance）
	 * @Method: minkowskiDistance 
	 * @Description: 	当p==1,“明可夫斯基距离”变成“曼哈顿距离”
						当p==2,“明可夫斯基距离”变成“欧几里得距离”
						当p==∞,“明可夫斯基距离”变成“切比雪夫距离”
	 * @param p
	 * @return
	 * @throws
	 */
	public double minkowskiDistance(int p) {
		double sum=0;
		Matrix vector=vectorX.minus(vectorY);
		for (int i = 0; i < this.rank; i++) {
			sum+=Math.pow(Math.abs(vector.getAsDouble(i,0)), p);
		}
		return Math.pow(sum, 1/p);
		
	}
	
	/**
	 * 
	 * @Method: cosineDistance 
	 * @Description: 余弦相似度
	 * @return
	 * @throws
	 */
	public double cosineDistance() {
		double sumX=0;
		double sumY=0;
		double XY=0;
		for (int i = 0; i < this.rank; i++) {
			sumX+=Math.pow(vectorX.getAsDouble(i,0), 2);
			sumY+=Math.pow(vectorY.getAsDouble(i,0), 2);
			XY+=vectorX.getAsDouble(i,0)*vectorY.getAsDouble(i,0);
		}
		
		return XY/(Math.sqrt(sumX)*Math.sqrt(sumY));
	}
	
	/**
	 * 
	 * @Method: pearsonDistance 
	 * @Description: 
	 * 皮尔森相关系数(Pearson Correlation Coefficient)
     * 又称相关相似性，通过Peason相关系数来度量两个用户的相似性。
     * 计算时，首先找到两个用户共同评分过的项目集，然后计算这两个向量的相关系数
	 * @return
	 * @throws
	 */
	public double pearsonDistance() {
		double sumX=0;
		double sumY=0;
		double sumXSqrt=0;
		double sumYSqrt=0;
		double product=0;
		for (int i = 0; i < this.rank; i++) {
			sumX+=vectorX.getAsDouble(i,0);
			sumY+=vectorY.getAsDouble(i,0);
			sumXSqrt+=Math.pow(vectorX.getAsDouble(i,0), 2);
			sumYSqrt+=Math.pow(vectorY.getAsDouble(i,0), 2);
			product+=vectorX.getAsDouble(i,0)*vectorY.getAsDouble(i,0);
		}
		double numerator= product-sumX*sumY/this.rank;
		double denominator =Math.sqrt((sumXSqrt-Math.pow(sumX, 2)/this.rank)*(sumYSqrt-Math.pow(sumY, 2)/this.rank));
		return numerator/denominator;
	}
	
	public double adjustedCosine() {
		
		return 0;
	}
	
}
