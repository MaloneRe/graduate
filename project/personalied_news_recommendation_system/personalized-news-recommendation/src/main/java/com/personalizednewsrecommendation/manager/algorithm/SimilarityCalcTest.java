package com.personalizednewsrecommendation.manager.algorithm;

public class SimilarityCalcTest {
	private double[] vectorX;
	private double[] vectorY;
	private int rank;
	public double eucledianDistance(){
		double sum=0;
		for (int i = 0; i < this.rank; i++) {
			sum+=Math.pow((vectorX[i]-vectorY[i]),2);
		}
		return Math.sqrt(sum);
	}
	
	public double manhattanDistance() {
		double sum=0;
		for (int i = 0; i <this.rank; i++) {
			sum+=Math.abs(vectorX[i]-vectorY[i]);
		}
		return sum;
	}
	/**
	 * 
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
		for (int i = 0; i < this.rank; i++) {
			sum+=Math.pow(Math.abs(vectorX[i]-vectorY[i]), p);
		}
		return Math.round(Math.pow(sum, 1/p));
	}
	
	public double cosineDistance() {
		double sumX=0;
		double sumY=0;
		double XY=0;
		for (int i = 0; i < this.rank; i++) {
			sumX+=Math.pow(vectorX[i], 2);
			sumY+=Math.pow(vectorY[i], 2);
			XY+=vectorX[i]*vectorY[i];
		}
		
		return XY/(Math.sqrt(sumX)*Math.sqrt(sumY));
	}
	
	public double pearsonDistance() {
		double sumX=0;
		double sumY=0;
		double sumXSqrt=0;
		double sumYSqrt=0;
		double product=0;
		for (int i = 0; i < this.rank; i++) {
			sumX+=vectorX[i];
			sumY+=vectorY[i];
			sumXSqrt+=Math.pow(vectorX[i], 2);
			sumYSqrt+=Math.pow(vectorY[i], 2);
			product+=vectorX[i]*vectorY[i];
		}
		double numerator= product-sumX*sumY/this.rank;
		double denominator =Math.sqrt((sumXSqrt-Math.pow(sumX, 2)/this.rank)*(sumYSqrt-Math.pow(sumY, 2)/this.rank));
		return numerator/denominator;
	}
}
