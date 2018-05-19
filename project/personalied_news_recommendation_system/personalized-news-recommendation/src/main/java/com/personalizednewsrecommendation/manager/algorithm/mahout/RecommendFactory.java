package com.personalizednewsrecommendation.manager.algorithm.mahout;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.ClusterSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.FarthestNeighborClusterSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.NearestNeighborClusterSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CityBlockSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.UncenteredCosineSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.druid.pool.DruidDataSource;

import mahoutTest.RecommendFactory.NEIGHBORHOOD;
import mahoutTest.RecommendFactory.SIMILARITY;

public class RecommendFactory {
	
	/*@Autowired
	@Qualifier("dataSource")
	public void setDataSource(DataSource dataSource1){
		dataSource = dataSource1;
	}
	private static DataSource dataSource;*/
	
	/**
     * build Data model from file
     */
	public static DataModel buildDataModel(String file) throws TasteException, IOException {
        return new FileDataModel(new File(file));
    }
	public static DataModel buildDataModelByDB() throws TasteException {
		NewsDataModel newsDataModel =new NewsDataModel();
		 /**
		  * 利用ReloadFromJDBCDataModel包裹jdbcDataModel,可以把输入加入内存计算，加快计算速度。  
		  */
		
		  ReloadFromJDBCDataModel model = new ReloadFromJDBCDataModel(newsDataModel);
		  
		/**
		 * 这里的refresh是刷新model，一般情况下我觉得用不上，因为像我这个程序，
		 * 每次都会新建立datamodel，都是最新的，所以不用刷新  
		 * 当然，如果你需要在内存中存储下model，
		 * 然后自己的taste_preferences时刻在变化，此时是需要刷新的。  
		 * model.refresh(null);
		 */
		 
	     return model; 
	}
	
	public static DataModel buildDataModelNoPref(String file) throws TasteException, IOException {
        return new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(new FileDataModel(new File(file))));
    }

    public static DataModelBuilder buildDataModelNoPrefBuilder() {
        return new DataModelBuilder() {
            @Override
            public DataModel buildDataModel(FastByIDMap<PreferenceArray> trainingData) {
                return new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(trainingData));
            }
        };
    }
	
    
    /**
     * similarity
     */
    public enum SIMILARITY {
        PEARSON, EUCLIDEAN, COSINE, TANIMOTO, LOGLIKELIHOOD, SPEARMAN, CITYBLOCK, FARTHEST_NEIGHBOR_CLUSTER, NEAREST_NEIGHBOR_CLUSTER
    }

    public static UserSimilarity userSimilarity(SIMILARITY type, DataModel m) throws TasteException {
        switch (type) {
        case PEARSON:
            return new PearsonCorrelationSimilarity(m);
        case COSINE:
            return new UncenteredCosineSimilarity(m);
        case TANIMOTO:
            return new TanimotoCoefficientSimilarity(m);
        case LOGLIKELIHOOD:
            return new LogLikelihoodSimilarity(m);
        case SPEARMAN:
            return new SpearmanCorrelationSimilarity(m);
        case CITYBLOCK:
            return new CityBlockSimilarity(m);
        case EUCLIDEAN:
        default:
            return new EuclideanDistanceSimilarity(m);
        }
    }

    public static ItemSimilarity itemSimilarity(SIMILARITY type, DataModel m) throws TasteException {
        switch (type) {
        case PEARSON:
            return new PearsonCorrelationSimilarity(m);
        case COSINE:
            return new UncenteredCosineSimilarity(m);
        case TANIMOTO:
            return new TanimotoCoefficientSimilarity(m);
        case LOGLIKELIHOOD:
            return new LogLikelihoodSimilarity(m);
        case CITYBLOCK:
            return new CityBlockSimilarity(m);
        case EUCLIDEAN:
        default:
            return new EuclideanDistanceSimilarity(m);
        }
    }

    /**
     * neighborhood
     */
    public enum NEIGHBORHOOD {
        NEAREST, THRESHOLD
    }

    public static UserNeighborhood userNeighborhood(NEIGHBORHOOD type, UserSimilarity s, DataModel m, double num) throws TasteException {
        switch (type) {
        case NEAREST:
            return new NearestNUserNeighborhood((int) num, s, m);
        case THRESHOLD:
        default:
            return new ThresholdUserNeighborhood(num, s, m);
        }
    }

    /**
     * recommendation
     */
    public enum RECOMMENDER {
        USER, ITEM
    }

    public static RecommenderBuilder userRecommender(final UserSimilarity us, final UserNeighborhood un, boolean pref) throws TasteException {
        return pref ? new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
                return new GenericUserBasedRecommender(model, un, us);
            }
        } : new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
                return new GenericBooleanPrefUserBasedRecommender(model, un, us);
            }
        };
    }

    public static RecommenderBuilder itemRecommender(final ItemSimilarity is, boolean pref) throws TasteException {
        return pref ? new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
                return new GenericItemBasedRecommender(model, is);
            }
        } : new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel model) throws TasteException {
                return new GenericBooleanPrefItemBasedRecommender(model, is);
            }
        };
    }
    
    
    
	/**
	 * builder recommend
	 */
	
	/**
	 * build svdrecommder 
	 * @Method: svdRecommender 
	 * @param factorizer
	 * @return
	 * @throws TasteException
	 * @throws
	 */
	public static RecommenderBuilder svdRecommender(final Factorizer factorizer) throws TasteException {
        return new RecommenderBuilder() {

        	@Override
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                return new SVDRecommender(dataModel, factorizer);
            }
            
        };
    }
	
	public static void showItems(long uid, List<RecommendedItem> recommendations, boolean skip) {
        if (!skip || recommendations.size() > 0) {
            System.out.printf("uid:%s,", uid);
            for (RecommendedItem recommendation : recommendations) {
                System.out.printf("(%s,%f)", recommendation.getItemID(), recommendation.getValue());
            }
            System.out.println();
        }
    }

    /**
     * evaluator
     */
	
	public enum EVALUATOR {
        AVERAGE_ABSOLUTE_DIFFERENCE, RMS
    }

    public static RecommenderEvaluator buildEvaluator(EVALUATOR type) {
        switch (type) {
        case RMS:
            return new RMSRecommenderEvaluator();
        case AVERAGE_ABSOLUTE_DIFFERENCE:
        default:
            return new AverageAbsoluteDifferenceRecommenderEvaluator();
        }
        
    }

    public static Map<String, Object> evaluate(EVALUATOR type, RecommenderBuilder rb, DataModelBuilder mb, DataModel dm, double trainPt) throws TasteException {
    	 Map<String, Object> map = new HashMap<String, Object>(); 
    	 double score = buildEvaluator(type).evaluate(rb, mb, dm, trainPt, 1.0);
    	 map.put("type", type.toString());
    	 map.put("score", score);
    	 System.out.printf("%s Evaluater Score:%s\n", type.toString(), score);
    	 return map;
    }
    
    /**
     * 传入的是已经构造好的evaluatebuilder
     */
    public static Map<String, Object> evaluate(RecommenderEvaluator re, RecommenderBuilder rb, DataModelBuilder mb, DataModel dm, double trainPt) throws TasteException {
    	 Map<String, Object> map = new HashMap<String, Object>(); 
    	 double score = re.evaluate(rb, mb, dm, trainPt, 1.0);
    	 map.put("score", score);
    	System.out.printf("Evaluater Score:%s\n", score);
    	return map;
    }

    /**
     * statsEvaluator
     * @return 
     */
    public static Map<String, Object> statsEvaluator(RecommenderBuilder rb, DataModelBuilder mb, DataModel m, int topn) throws TasteException {
        RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
        IRStatistics stats = evaluator.evaluate(rb, mb, m, null, topn, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
        // System.out.printf("Recommender IR Evaluator: %s\n", stats);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "Recommender IR Evaluator");
        map.put("precision", stats.getPrecision());
        map.put("recall", stats.getRecall());
        System.out.printf("Recommender IR Evaluator: [Precision:%s,Recall:%s]\n", stats.getPrecision(), stats.getRecall());
        return map;
    }
	
}
