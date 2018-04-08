package CMPE256;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class ItemBasedRecommendation {
        public static void main(String[] args) throws Exception {


            //Step 1:- Input CSV file (CSV file should be in userID, itemID, preference) format

            DataModel model = new FileDataModel(new File("/Users/sowmiteja/Desktop/HW4_256/ratings_preprocessed.csv"));


            RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
                public Recommender buildRecommender(DataModel model) throws TasteException {
                    ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
                    return new GenericItemBasedRecommender(model, similarity);
                }
            };

            List<RecommendedItem> recommendations = null;
            try {
                recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
            } catch (TasteException e) {
                e.printStackTrace();
            }
            System.out.println("*************************************");
            System.out.println("PearsonCorrelationSimilarity after preprocessing:\n Item based recommendation");
            for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

            RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
            double score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
            System.out.println("RMSE: " + score);

            RecommenderIRStatsEvaluator statsEvaluator = new GenericRecommenderIRStatsEvaluator();
            IRStatistics stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.5, 0.7); // evaluate precision recall at 10

            System.out.println("Precision: " + stats.getPrecision());
            System.out.println("Recall: " + stats.getRecall());
            System.out.println("F1 Score: " + stats.getF1Measure());


            recommenderBuilder = new RecommenderBuilder() {
                public Recommender buildRecommender(DataModel model) throws TasteException {
                    ItemSimilarity similarity = new EuclideanDistanceSimilarity(model);
                    return new GenericItemBasedRecommender(model, similarity);
                }
            };

            recommendations = null;
            try {
                recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
            } catch (TasteException e) {
                e.printStackTrace();
            }
            System.out.println("********************************");
            System.out.println("Euclidean similarity Results after preprocessing:\n Item based recommendation");
            for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

            evaluator = new RMSRecommenderEvaluator();
            score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
            System.out.println("RMSE: " + score);

            statsEvaluator = new GenericRecommenderIRStatsEvaluator();
            stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.5, 0.7); // evaluate precision recall at 10

            System.out.println("Precision: " + stats.getPrecision());
            System.out.println("Recall: " + stats.getRecall());
            System.out.println("F1 Score: " + stats.getF1Measure());

            recommenderBuilder = new RecommenderBuilder() {
                public Recommender buildRecommender(DataModel model) throws TasteException {
                    ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
                    return new GenericItemBasedRecommender(model, similarity);
                }
            };

            recommendations = null;
            try {
                recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
            } catch (TasteException e) {
                e.printStackTrace();
            }
            System.out.println("********************************");
            System.out.println("Log likelihood similarity Resultsafter preprocessing:\n Item based recommendation");
            for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

            evaluator = new RMSRecommenderEvaluator();
            score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
            System.out.println("RMSE: " + score);

            statsEvaluator = new GenericRecommenderIRStatsEvaluator();
            stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.5, 0.7); // evaluate precision recall at 10

            System.out.println("Precision: " + stats.getPrecision());
            System.out.println("Recall: " + stats.getRecall());
            System.out.println("F1 Score: " + stats.getF1Measure());

        }
}
