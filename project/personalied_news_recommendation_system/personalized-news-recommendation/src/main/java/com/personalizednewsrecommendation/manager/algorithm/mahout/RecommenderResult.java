package com.personalizednewsrecommendation.manager.algorithm.mahout;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import org.apache.mahout.cf.taste.recommender.*;

public class RecommenderResult {
	
	private static int recommenderNUM;
	final static int NEIGHBORHOOD_NUM = 2;
	final static int RECOMMENDER_NUM = 3;
	private static DataModel dataModel ;//= RecommendFactory.buildDataModelByDB();
	private static Recommender recommender;
	 
	public static Recommender startRecommend() throws TasteException{
		//DataModel dataModel = RecommendFactory.buildDataModelByDB();
		//NewsDataModel newsDataModel =new NewsDataModel();
		//ReloadFromJDBCDataModel dataModel = new ReloadFromJDBCDataModel(newsDataModel);
		/**
		 * 问题待解决
		 * RecommendFactory.buildDataModelByDB();
		 * 是空的
		 * 
		 * 已解决RecommendFactory引用到了test中的并非该目录的
		 */
		RecommenderResult.dataModel = RecommendFactory.buildDataModelByDB();
		if (dataModel == null) {
			NewsDataModel newsDataModel =new NewsDataModel();
			dataModel = new ReloadFromJDBCDataModel(newsDataModel);
		}
		System.out.println(dataModel.toString());
		RecommenderBuilder recommenderBuilder =
				 RecommenderEvaluator.svd(dataModel);
		 //RecommenderResult.recommender = recommenderBuilder.buildRecommender(dataModel);
		 return  RecommenderResult.recommender = recommenderBuilder.buildRecommender(dataModel);
		
		 //RecommenderResult.svd(dataModel);
		//return recommender;
	}
	
	public static List<RecommendedItem> getRecommendResult(Long userID) throws TasteException{
		int howMany = RecommenderResult.dataModel.getNumItems();
		return recommender.recommend(userID, howMany);
	}
	
	public static  List<RecommendedItem> result(long uid, RecommenderBuilder recommenderBuilder, DataModel dataModel) throws TasteException {
        List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
        RecommendFactory.showItems(uid, list, false);
        return  list;
    }
	
	public static void svd(DataModel dataModel) throws TasteException {
		/**
		 * ALSWRFactorizer 
		 * 第二参数 评分依赖因子 如以用户点击、阅读、收藏、转载、评论、其他为基础所以是6
		 * 第三个待研究
		 * 第四个迭代次数
		 */
        RecommenderBuilder recommenderBuilder = RecommendFactory.svdRecommender(new ALSWRFactorizer(dataModel, 6, 0.05, 20));

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);

        LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
            RecommendFactory.showItems(uid, list, true);
        }
    }
	
	public static void userCF(DataModel dataModel) throws TasteException {
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, NEIGHBORHOOD_NUM);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);

        LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
            RecommendFactory.showItems(uid, list, true);
        }
    }

    public static void itemCF(DataModel dataModel) throws TasteException {
        ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.EUCLIDEAN, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);

        LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
            RecommendFactory.showItems(uid, list, true);
        }
    }

}
