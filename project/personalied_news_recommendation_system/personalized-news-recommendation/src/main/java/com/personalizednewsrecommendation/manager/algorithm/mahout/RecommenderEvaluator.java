package com.personalizednewsrecommendation.manager.algorithm.mahout;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.recommender.svd.ALSWRFactorizer;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class RecommenderEvaluator {

	final static int NEIGHBORHOOD_NUM = 2;
	final static int RECOMMENDER_NUM = 3;
	public static RecommenderBuilder userLoglikelihood(DataModel dataModel) throws TasteException, IOException {
        System.out.println("userLoglikelihood");
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.LOGLIKELIHOOD, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, NEIGHBORHOOD_NUM);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, false);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
        return recommenderBuilder;
    }

    public static RecommenderBuilder userCityBlock(DataModel dataModel) throws TasteException, IOException {
        System.out.println("userCityBlock");
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.CITYBLOCK, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, NEIGHBORHOOD_NUM);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, false);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
        return recommenderBuilder;
    }

    public static RecommenderBuilder userTanimoto(DataModel dataModel) throws TasteException, IOException {
        System.out.println("userTanimoto");
        UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.TANIMOTO, dataModel);
        UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, NEIGHBORHOOD_NUM);
        RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, false);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
        return recommenderBuilder;
    }

    public static RecommenderBuilder itemLoglikelihood(DataModel dataModel) throws TasteException, IOException {
        System.out.println("itemLoglikelihood");
        ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.LOGLIKELIHOOD, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, false);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
        return recommenderBuilder;
    }

    public static RecommenderBuilder itemCityBlock(DataModel dataModel) throws TasteException, IOException {
        System.out.println("itemCityBlock");
        ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.CITYBLOCK, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, false);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
        return recommenderBuilder;
    }

    public static RecommenderBuilder itemTanimoto(DataModel dataModel) throws TasteException, IOException {
        System.out.println("itemTanimoto");
        ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.TANIMOTO, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, false);

        RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, 2);
        return recommenderBuilder;
    }

	
	
	public static RecommenderBuilder svd(DataModel dataModel, int at, Map<String, Object> evaluatorResult) throws TasteException {
        System.out.println("svd");
        RecommenderBuilder recommenderBuilder = RecommendFactory.svdRecommender(new ALSWRFactorizer(dataModel, 7, 0.05, 20));
        
        Map<String, Object> evaluate = RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        Map<String, Object> statsEvaluate = RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, at);
        evaluatorResult.put("diff", evaluate);
        evaluatorResult.put("IR", statsEvaluate);
        return recommenderBuilder;
    }
	
	
	/**
	 * 自己的
	 * @Method: userCF 
	 * @param dataModel
	 * @param at 用户要求推荐数目
	 * @return
	 * @throws TasteException
	 * @throws
	 */
	public static RecommenderBuilder userCF(DataModel dataModel, int at, Map<String, Object> evaluatorResult) throws TasteException{
		 System.out.println("userCF");
		 UserSimilarity userSimilarity = RecommendFactory.userSimilarity(RecommendFactory.SIMILARITY.PEARSON, dataModel);
	     UserNeighborhood userNeighborhood = RecommendFactory.userNeighborhood(RecommendFactory.NEIGHBORHOOD.NEAREST, userSimilarity, dataModel, NEIGHBORHOOD_NUM);
	     RecommenderBuilder recommenderBuilder = RecommendFactory.userRecommender(userSimilarity, userNeighborhood, true);

	     Map<String, Object> evaluate = RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
	     Map<String, Object> statsEvaluate = RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, at);
		
	     evaluatorResult.put("diff", evaluate);
	     evaluatorResult.put("IR", statsEvaluate);
	     return recommenderBuilder;
	}
	public static RecommenderBuilder itemCF(DataModel dataModel, int at, Map<String, Object> evaluatorResult) throws TasteException{
		ItemSimilarity itemSimilarity = RecommendFactory.itemSimilarity(RecommendFactory.SIMILARITY.PEARSON, dataModel);
        RecommenderBuilder recommenderBuilder = RecommendFactory.itemRecommender(itemSimilarity, true);

        Map<String, Object> evaluate = RecommendFactory.evaluate(RecommendFactory.EVALUATOR.AVERAGE_ABSOLUTE_DIFFERENCE, recommenderBuilder, null, dataModel, 0.7);
        Map<String, Object> statsEvaluate = RecommendFactory.statsEvaluator(recommenderBuilder, null, dataModel, at);
        evaluatorResult.put("diff", evaluate);
	    evaluatorResult.put("IR", statsEvaluate);
        /**
         * test的
         */
       /* LongPrimitiveIterator iter = dataModel.getUserIDs();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            List<RecommendedItem> list = recommenderBuilder.buildRecommender(dataModel).recommend(uid, RECOMMENDER_NUM);
            RecommendFactory.showItems(uid, list, true);
        }*/
        
		return recommenderBuilder;
	}
}
