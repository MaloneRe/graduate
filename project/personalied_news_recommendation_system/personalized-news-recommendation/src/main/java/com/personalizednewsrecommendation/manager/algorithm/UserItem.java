package com.personalizednewsrecommendation.manager.algorithm;

public class UserItem {
	private double[] user;
	private double[] item;
	private int row;
	private int column;
	
	private double[][] score;
	public UserItem() {
		super();
	}
	public UserItem(int row, int column) {
		super();
		this.row = row;
		this.column = column;
		this.score=new double[row][column];
	}
	
	public double[][] getScore() {
		return score;
	}
	public void setScore(double[][] score) {
		this.score = score;
	}
	public void setScore(int rower, int columner, double value) {
		this.score[rower][columner] = value;
	}
	public double getScore(int rower, int columner) {
		return this.score[rower][columner];
	}
	
	public double[] getUser() {
		return user;
	}
	public void setUser(double[] user) {
		this.user = user;
	}
	public double[] getItem() {
		return item;
	}
	public void setItem(double[] item) {
		this.item = item;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) { 
		this.column = column;
	}	
}
