package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;


public class TestListStudentDao extends Dao {

	private String baseSql = "select * from student where school_cd=? ";

	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		try {
			//リザルトセットを全権走査
			while (rSet.next()) {
				TestListStudent testListStu = new TestListStudent();
				testListStu.setSubjectName(rSet.getString("subjectName"));
				testListStu.setSubjectCd(rSet.getString("subjectCd"));
				testListStu.setNum(rSet.getInt("num"));
				testListStu.setPoint(rSet.getInt("point"));

				list.add(testListStu);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}


	public List<TestListStudent> filter(Student student) throws Exception {

		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "SELECT NO FROM STUDENT where = ?";
		// SQL文のソート
		String order = " order by CLASS_NUM asc, NO asc ";

		try {
			statement = connection.prepareStatement(baseSql + condition + order);
			statement.setString(1, "NO");
		}catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}