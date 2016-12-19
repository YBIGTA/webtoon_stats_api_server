package ybigta.webtoon_recommendation.analysis.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ybigta.webtoon_recommendation.analysis.domain.Morpheme;
import ybigta.webtoon_recommendation.analysis.domain.Webtoon;
import ybigta.webtoon_recommendation.analysis.domain.Similarity;

public class WebtoonDAO {

	private final int BATCH_SIZE = 1000;

	//TODO webtoon Feature Vector 가져와서 추가하는 것 추가되어야 함
	public List<Webtoon> getWebtoonList() {
		Connection connection = DBManager.connectDB();
		ResultSet result = null;
		List<Webtoon> resultList = new ArrayList<>();

		String sql = "SELECT * FROM webtoon";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			result = preparedStatement.executeQuery();

			while (result.next()) {

				Webtoon webtoon = new Webtoon();
				webtoon.setTitleId(result.getInt("title_id"));
				webtoon.setTitle(result.getString("title").replaceAll("\"", ""));
				webtoon.setAuthor(result.getString("author").replaceAll("\"", ""));
				webtoon.setGenre(result.getString("genre").replaceAll("\"", ""));

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

				preparedStatement.setString(1, similarity.getWebtoon1Name());
				preparedStatement.setString(1, similarity.getWebtoon2Name());
				preparedStatement.setDouble(3, similarity.getSimilarity());
				preparedStatement.addBatch();

				if (++batchCount % BATCH_SIZE == 0) {
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
}