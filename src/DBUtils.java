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
			conn = DriverManager.getConnection(url, user, password);// ��ȡ����
		} catch (ClassNotFoundException e) {
			System.out.println("�Ҳ������������� ����������ʧ�ܣ�");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	String sql;
	String colomnName;
	String value;

	public void save(String tableName, Map<String, String> data) {
		try {
			if (conn == null)
				conn = DriverManager.getConnection(url, user, password);// ��ȡ����
			sql = "";
			sql += "insert into " + tableName;
			colomnName = "";
			value = "";
			for (String key : data.keySet()) {
				colomnName += key + ",";
				value += "'" + data.get(key) + "',";
			}
			// �������һ������
			colomnName = colomnName.substring(0, colomnName.length() - 1);
			value = value.substring(0, value.length() - 1);
			sql += " ( " + colomnName + " ) values ( " + value + " )";
			conn.createStatement().execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	 * ���������˵����ƶ�
	 * 
	 * @param id1
	 * @param id2
	 */
	public double getSimilarity(int id1, int id2) {

		List<Integer> tmp = getSameMovieIds(id1, id2);
		// id1��rate����
		int[] sameMovieRate1 = new int[tmp.size()];
		// id2��rate����
		int[] sameMovieRate2 = new int[tmp.size()];
		// ��ȡ��ͬ��Ӱ������
		for (int i = 0; i < tmp.size(); i++) {
			int[] tmp2 = getSameMovieRate(id1, id2, tmp.get(i));
			sameMovieRate1[i] = tmp2[0];
			sameMovieRate2[i] = tmp2[1];
		}
		return Utils.culSimilarity(sameMovieRate1, sameMovieRate2);
	}

	/**
	 * ��ȡ�����˶���һ����Ӱ������
	 * 
	 * @param id1
	 * @param id2
	 * @param sameMovieId
	 * @return ����������intֵ����һ����id1��rate���ڶ�����id2��rate
	 */
	public int[] getSameMovieRate(int id1, int id2, int sameMovieId) {
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

}
