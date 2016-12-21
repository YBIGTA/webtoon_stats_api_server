package ybigta.webtoon_recommendation.analysis.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ybigta.webtoon_recommendation.analysis.domain.Morpheme;
import ybigta.webtoon_recommendation.analysis.domain.Similarity;
import ybigta.webtoon_recommendation.analysis.domain.Webtoon;

public class WebtoonDAO {

	private final int BATCH_SIZE = 1000;

	// TODO webtoon Feature Vector 가져와서 추가하는 것 추가되어야 함
	public List<Webtoon> getWebtoonList() {
		Connection connection = DBManager.connectDB();
		ResultSet result = null;
		List<Webtoon> resultList = new ArrayList<>();

		String sql = "{CALL get_webtoon_list()}";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			result = preparedStatement.executeQuery();

			while (result.next()) {

				Webtoon webtoon = new Webtoon();
				webtoon.setTitleId(result.getInt("titleId"));
				webtoon.setTitle(result.getString("title").replaceAll("\"", ""));
				webtoon.setAuthor(result.getString("author").replaceAll("\"", ""));
				webtoon.setGenre(result.getString("genre").replaceAll("\"", ""));
				webtoon.setHeartsAverage(result.getDouble("heartsAverage"));
				webtoon.setScoreParticipantAverage(result.getDouble("scoreParticipantAverage"));
				
				// TODO feature vector setting
				// TODO cosine 유사도 구하는 부분 double로
				Map<String, Double> featureVector = new HashMap<>();
				featureVector.put("kikikiRate", result.getDouble("kikikiRate") * 10);
				featureVector.put("uuuRate", result.getDouble("uuuRate") * 10);
				webtoon.setFeatureVector(featureVector);
				
				resultList.add(webtoon);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.disconnectDB();
		}

		return resultList;
	}

	public List<Morpheme> getMorphemeCountByMorpheme(String targetMorpheme) {
		Connection connection = DBManager.connectDB();
		ResultSet result = null;
		List<Morpheme> resultList = new ArrayList<>();

		String sql = "{CALL get_morpheme_count_by_morpheme(?)}";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setString(1, targetMorpheme);

			result = preparedStatement.executeQuery();

			while (result.next()) {

				Morpheme morpheme = new Morpheme();
				morpheme.setTitleId(result.getInt("title_id"));
				morpheme.setMorpheme(result.getString("morpheme").replaceAll("\"", ""));
				morpheme.setMorphemeCount(result.getInt("morpheme_count"));

				resultList.add(morpheme);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.disconnectDB();
		}

		return resultList;
	}

	public List<Morpheme> getMorphemeCountByWebtoon(int titleId) {
		Connection connection = DBManager.connectDB();
		ResultSet result = null;
		List<Morpheme> resultList = new ArrayList<>();

		String sql = "{CALL get_morpheme_count_by_webtoon(?)}";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, titleId);

			result = preparedStatement.executeQuery();

			while (result.next()) {
				Morpheme morpheme = new Morpheme();
				morpheme.setMorpheme(result.getString("morpheme").replaceAll("\"", ""));
				morpheme.setMorphemeCount(result.getInt("morpheme_count"));

				resultList.add(morpheme);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.disconnectDB();
		}

		return resultList;
	}

	public void insertSimilarity(List<Similarity> similarityList) {
		Connection connection = DBManager.connectDB();

		String sql = "INSERT INTO similarity VALUES (?, ?, ?)";

		int batchCount = 0;

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			for (Similarity similarity : similarityList) {

				preparedStatement.setInt(1, similarity.getWebtoon1Id());
				preparedStatement.setInt(2, similarity.getWebtoon2Id());
				preparedStatement.setDouble(3, similarity.getSimilarity());
				preparedStatement.addBatch();

				if (++batchCount % BATCH_SIZE == 0) {
					System.out.println("BATCH COUNT: " + batchCount);
					preparedStatement.executeBatch();
				}
			}

			preparedStatement.executeBatch();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.disconnectDB();
		}
	}

	public void callProcedure1(int input) {
		Connection connection = DBManager.connectDB();

		String sql = "{CALL calc_and_insert_morpheme_rate_by_webtoon('ㅋㅋㅋ', ?)}";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, input);

			preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.disconnectDB();
		}
	}

	public void callProcedure2(int input) {
		Connection connection = DBManager.connectDB();

		String sql = "{CALL calc_and_insert_morpheme_rate_by_webtoon('ㅠㅠㅠ', ?)}";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, input);

			preparedStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.disconnectDB();
		}
	}

}