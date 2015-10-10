import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBUtils {
	private final int userCount = 943;
	private final int movieCount = 1682;

	private final String url = "jdbc:mysql://localhost:3306/movielens";
	private final String name = "com.mysql.jdbc.Driver";
	private final String user = "root";
	private final String password = "123456";
	private Connection conn = null;

	public DBUtils() {
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("数据库连接失败！");
			e.printStackTrace();
		}
	}

	String sql;
	String colomnName;
	String value;

	public void save(String tableName, Map<String, String> data) {
		try {
			if (conn == null)
				conn = DriverManager.getConnection(url, user, password);// 获取连接
			sql = "";
			sql += "insert into " + tableName;
			colomnName = "";
			value = "";
			for (String key : data.keySet()) {
				colomnName += key + ",";
				value += "'" + data.get(key) + "',";
			}
			// 过滤最后一个逗号
			colomnName = colomnName.substring(0, colomnName.length() - 1);
			value = value.substring(0, value.length() - 1);
			sql += " ( " + colomnName + " ) values ( " + value + " )";
			conn.createStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (!conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void culSimilarity(int targetUid) {

		for (int i = 1; i <= userCount; i++) {
			if (targetUid == i)
				continue;
			else {

			}
		}
	}

	/**
	 * 计算两个物品的相似度
	 * 
	 * @param id1
	 * @param id2
	 */
	public double getIBCFSimilarity(int movieId1, int movieId2) {
		// 对这两部电影都有评分的用户id列表
		List<Integer> tmp = getSameUserIds(movieId1, movieId2);
		// 如果不存在，则说明两部电影相似度为0
		if (tmp.size() == 0)
			return 0;
		// movieId1的rate数组
		int[] rate1 = new int[tmp.size()];
		// movieId2的rate数组
		int[] rate2 = new int[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			int[] tmp2 = getSameMovieRateByMids(movieId1, movieId2, tmp.get(i));
			rate1[i] = tmp2[0];
			rate2[i] = tmp2[1];
		}
		return Utils.culSimilarity(rate1, rate2);
	}

	/**
	 * 计算两个人的相似度
	 * 
	 * @param id1
	 * @param id2
	 */
	public double getUBCFSimilarity(int id1, int id2) {

		List<Integer> tmp = getSameMovieIds(id1, id2);
		if (tmp.size() == 0)
			return 0;
		// id1的rate数组
		int[] sameMovieRate1 = new int[tmp.size()];
		// id2的rate数组
		int[] sameMovieRate2 = new int[tmp.size()];
		// 获取相同电影的评分
		for (int i = 0; i < tmp.size(); i++) {
			int[] tmp2 = getSameMovieRateByUids(id1, id2, tmp.get(i));
			sameMovieRate1[i] = tmp2[0];
			sameMovieRate2[i] = tmp2[1];
		}
		return Utils.culSimilarity(sameMovieRate1, sameMovieRate2);
	}

	/**
	 * 获取一个人对两部电影的评价
	 * 
	 * @return 共返回两个int值，第一个是movieId1的rate，第二个是movieId2的rate
	 */
	public int[] getSameMovieRateByMids(int movieId1, int movieId2, int sameUid) {
		try {
			sql = "select * from u_data where movie_id in (" + movieId1 + " , " + movieId2
					+ ") and user_id = " + sameUid;
			ResultSet set = conn.createStatement().executeQuery(sql);
			int[] result = new int[2];
			while (set.next()) {
				int id = set.getInt(2);
				if (id == movieId1) {
					result[0] = set.getInt(3);
				} else if (id == movieId2) {
					result[1] = set.getInt(3);
				}
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取两个人对于一部电影的评价
	 * 
	 * @return 共返回两个int值，第一个是id1的rate，第二个是id2的rate
	 */
	public int[] getSameMovieRateByUids(int id1, int id2, int sameMovieId) {
		try {
			sql = "select * from u_data where user_id in (" + id1 + " , " + id2
					+ ") and movie_id = " + sameMovieId;
			ResultSet set = conn.createStatement().executeQuery(sql);
			int[] result = new int[2];
			while (set.next()) {
				int id = set.getInt(1);
				if (id == id1) {
					result[0] = set.getInt(3);
				} else if (id == id2) {
					result[1] = set.getInt(3);
				}
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取两个人都对同一部电影进行评分的电影id列表
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public List<Integer> getSameMovieIds(int id1, int id2) {
		try {
			sql = "SELECT movie_id FROM u_data where user_id in (" + id1 + "," + id2
					+ ") GROUP BY movie_id HAVING COUNT(movie_id)>1";
			ResultSet set = conn.createStatement().executeQuery(sql);
			List<Integer> result = new ArrayList<>();
			while (set.next()) {
				result.add(set.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取对两部电影都评分的用户id列表
	 * 
	 * @param movieId1
	 * @param movieId2
	 * @return
	 */
	public List<Integer> getSameUserIds(int movieId1, int movieId2) {
		try {
			sql = "SELECT user_id from u_data WHERE movie_id in(" + movieId1 + "," + movieId2
					+ ") GROUP BY user_id HAVING COUNT(user_id)>1";
			ResultSet set = conn.createStatement().executeQuery(sql);
			List<Integer> result = new ArrayList<>();
			while (set.next()) {
				result.add(set.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取一个人对于所有评分电影的id
	 * 
	 * @param uid
	 * @return
	 */
	public List<Integer> getNotNunMovies(int uid) {
		try {
			sql = "SELECT movie_id FROM u_data where user_id = " + uid;
			ResultSet set = conn.createStatement().executeQuery(sql);
			List<Integer> result = new ArrayList<>();
			while (set.next()) {
				result.add(set.getInt(1));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getWeight(int uid1, int uid2) {
		int sameMoivesCount = getSameMovieIds(uid1, uid2).size();
		int u1MoivesCount = getNotNunMovies(uid1).size();
		int u2MoivesCount = getNotNunMovies(uid2).size();

		if (sameMoivesCount == 0 || u1MoivesCount == 0 || u2MoivesCount == 0)
			return "0\t0";
		else {
			// 平方权重
			double weight1 = ((double) (sameMoivesCount * sameMoivesCount))
					/ ((double) (u1MoivesCount * u2MoivesCount));
			// 线性权重
			double weight2 = ((double) sameMoivesCount)
					/ ((double) (u1MoivesCount + u2MoivesCount));
			return weight1 + "\t" + weight2;
		}
	}
}
