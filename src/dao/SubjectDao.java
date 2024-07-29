package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;

public class SubjectDao extends Dao {
	public Subject get(String cd,School school) throws Exception {
		// 科目インスタンスを初期化
		Subject subject = new Subject();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

	try {
		// プリペアードステートメントにSQL文をセット
		statement = connection.prepareStatement("select * from subject where cd=?");
		// プリペアードステートメントに学生番号をバインド
		statement.setString(1, cd);
		// プリペアードステートメントを実行
		ResultSet rSet = statement.executeQuery();

		// 学校Daoを初期化
		SchoolDao schoolDao = new SchoolDao();

		if (rSet.next()) {
			// リザルトセットが存在する場合
			// 学生インスタンスに検索結果をセット
			subject.setCd(rSet.getString("cd"));
			subject.setName(rSet.getString("name"));
			// 学校フィールドには学校コードで検索した学校インスタンスをセット
			subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
		} else {
			// リザルトセットが存在しない場合
			// 学生インスタンスにnullをセット
			subject = null;
		}
	} catch (Exception e) {
		throw e;
	} finally {
		// プリペアードステートメントを閉じる
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
		// コネクションを閉じる
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
	}

	return subject;
	}

	private String baseSql = "select * from subject where school_cd=? ";


	public List<Student> filter(School school) throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		@SuppressWarnings("unused")
		ResultSet rSet = null;
		// SQL文の条件
		String order = " order by no asc";

		// SQL文の在学フラグ
		String conditionIsAttend = "";
		// 在学フラグがtrueの場合


		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}
	public boolean save(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースから学生を取得
			Subject old = get(subject.getCd(), null);
			if (old == null) {
				// 学生が存在しなかった場合
				// プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into subject(cd, name,) values(?, ?)");
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
			} else {
				// 学生が存在した場合
				// プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update subject set name=?");
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
			}

			// プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}
}
