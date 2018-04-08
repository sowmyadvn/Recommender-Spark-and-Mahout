package CMPE256;

import java.io.*;
import java.util.*;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;

public class UserBasedRecommendation {
    public static void main(String[] args) throws Exception {


        //Step 1:- Input CSV file (CSV file should be in userID, itemID, preference) format

        DataModel model = new FileDataModel(new File("/Users/sowmiteja/Desktop/HW4_256/ratings-1.csv"));


        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        List<RecommendedItem> recommendations = null;
        try {
            recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        System.out.println("*************************************");
        System.out.println("PearsonCorrelationSimilarity Results after preprocessing:\nUser-based recommendation");
        for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

        RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        double score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
        System.out.println("RMSE: " + score);

        RecommenderIRStatsEvaluator statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        IRStatistics stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.75, 0.7); // evaluate precision recall at 10

        System.out.println("Precision: " + stats.getPrecision());
        System.out.println("Recall: " + stats.getRecall());
        System.out.println("F1 Score: " + stats.getF1Measure());

        recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
                UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        recommendations = null;
        try {
            recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        System.out.println("********************************");
        System.out.println("Pearson similarity Results after preprocessing:\nUser-based recommendation- threshold neighborhood");
        for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

        evaluator = new RMSRecommenderEvaluator();
        score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
        System.out.println("RMSE: " + score);

        statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 4, 0.7); // evaluate precision recall at 10

        System.out.println("Precision: " + stats.getPrecision());
        System.out.println("Recall: " + stats.getRecall());
        System.out.println("F1 Score: " + stats.getF1Measure());

        recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        recommendations = null;
        try {
            recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        System.out.println("********************************");
        System.out.println("Euclidean similarity Results after preprocessing:\nUser-based recommendation- nearest N user");
        for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

        evaluator = new RMSRecommenderEvaluator();
        score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
        System.out.println("RMSE: " + score);

        statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.75, 0.7); // evaluate precision recall at 10

        System.out.println("Precision: " + stats.getPrecision());
        System.out.println("Recall: " + stats.getRecall());
        System.out.println("F1 Score: " + stats.getF1Measure());

        recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
                UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        recommendations = null;
        try {
            recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        System.out.println("********************************");
        System.out.println("Euclidean similarity Results after preprocessing:\nUser-based recommendation-threshold neighborhood");
        for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

        evaluator = new RMSRecommenderEvaluator();
        score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
        System.out.println("RMSE: " + score);

        statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.75, 0.7); // evaluate precision recall at 10

        System.out.println("Precision: " + stats.getPrecision());
        System.out.println("Recall: " + stats.getRecall());
        System.out.println("F1 Score: " + stats.getF1Measure());

        recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new LogLikelihoodSimilarity(model);
                UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.3, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        recommendations = null;
        try {
            recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        System.out.println("********************************");
        System.out.println("Log likelihood similarity Results after preprocessing:\nUser-based recommendation");
        for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

        evaluator = new RMSRecommenderEvaluator();
        score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
        System.out.println("RMSE: " + score);

        statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.75, 0.7); // evaluate precision recall at 10

        System.out.println("Precision: " + stats.getPrecision());
        System.out.println("Recall: " + stats.getRecall());
        System.out.println("F1 Score: " + stats.getF1Measure());

        recommenderBuilder = new RecommenderBuilder() {
            public Recommender buildRecommender(DataModel model) throws TasteException {
                UserSimilarity similarity = new LogLikelihoodSimilarity(model);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
                return new GenericUserBasedRecommender(model, neighborhood, similarity);
            }
        };

        recommendations = null;
        try {
            recommendations = recommenderBuilder.buildRecommender(model).recommend(168, 3);
        } catch (TasteException e) {
            e.printStackTrace();
        }
        System.out.println("********************************");
        System.out.println("Log likelihood similarity Results after preprocessing:\nUser-based recommendation- nearest neighborhood");
        for (RecommendedItem recommendation : recommendations) System.out.println(recommendation);

        evaluator = new RMSRecommenderEvaluator();
        score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);
        System.out.println("RMSE: " + score);

        statsEvaluator = new GenericRecommenderIRStatsEvaluator();
        stats = statsEvaluator.evaluate(recommenderBuilder, null, model, null, 10, 0.75, 0.7); // evaluate precision recall at 10

        System.out.println("Precision: " + stats.getPrecision());
        System.out.println("Recall: " + stats.getRecall());
        System.out.println("F1 Score: " + stats.getF1Measure());

    }

}