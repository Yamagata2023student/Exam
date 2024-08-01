package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Test;

public class TestDao extends Dao {

    // 指定された条件に基づいてTestを取得
    public Test get(int entYear, String classNum, String subject, int no, String name, int point) throws Exception {

        Test test = null;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM subject WHERE entYear=? AND classNum=? AND subject=? AND isAttend=? AND name=? AND point=?")) {

            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject);
            statement.setInt(4, no);
            statement.setString(5, name);
            statement.setInt(6, point);

            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    test = new Test();
                    test.setEntYear(rSet.getInt("entYear"));
                    test.setClassNum(rSet.getString("classNum"));
                    test.setSubject(rSet.getString("subject"));
                    test.setNo(rSet.getInt("no"));
                    test.setName(rSet.getString("name"));
                    test.setPoint(rSet.getInt("point"));
                }
            }
        }

        return test;
    }


    // subjectのプルダウンリストを取得
    public List<String> getSubjects(String subject) throws Exception {
        List<String> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT NAME FROM SUBJECT")) {

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    list.add(rSet.getString("name"));
                }
            }
        }

        return list;
    }

    // noのプルダウンリストを取得
    public List<Integer> getNo(int no) throws Exception {
        List<Integer> list = new ArrayList<>();

       try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT DISTINCT NO FROM TEST ")) {

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                   list.add(rSet.getInt("no"));
               }
            }
        }

        return list;
    }

    // classNumのプルダウンリストを取得
    public List<String> getClassNums(String classNum) throws Exception {
        List<String> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT CLASS_NUM FROM CLASS_NUM ")) {

            try (ResultSet rSet = statement.executeQuery()) {
                while (rSet.next()) {
                    list.add(rSet.getString("class_Num"));
                }
            }
        }

        return list;
    }
}


//ここ足すと画面がホワイトアウトする
//	/**
//	 * baseSql:String 共通SQL文 プライベート
//	 */
//	private String baseSql = "select * from student where school_cd=? ";
//
//	/**
//	* filterメソッド 学校、入学年度、クラス番号、在学フラグを指定して学生の一覧を取得する
//	*
//	* @param school:School
//	*            学校
//	* @param entYear:int
//	*            入学年度
//	* @param classNum:String
//	*            クラス番号
//	* @param isAttend:boolean
//	*            在学フラグ
//	* @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
//	* @throws Exception
//	*/
//	public List<Test> filter(int entYear, String classNum, String subject, String name, int point) throws Exception {
//	// リストを初期化
//	List<Test> list = new ArrayList<>();
//	// コネクションを確立
//	Connection connection = getConnection();
//	// プリペアードステートメント
//	PreparedStatement statement = null;
//
//	ResultSet rSet = null;
//
//	// SQL文の条件
//	String condition = "and ent_year=? and class_num=? and name=? and no=?";
//	String order = " order by no asc";
//
//
//	try {
//		// プリペアードステートメントにSQL文をセット
//		statement = connection.prepareStatement(baseSql + condition + order);
//
//	            statement.setInt(1, entYear);
//	            statement.setString(2, classNum);
//	            statement.setString(3, subject);
//	            statement.setString(4, name);
//	            statement.setInt(5, point);
//	            rSet = statement.executeQuery();
//
//	                while (rSet.next()) {
//	                    Test test = new Test();
//	                    test.setEntYear(rSet.getInt("entYear"));
//	                    test.setClassNum(rSet.getString("classNum"));
//	                    test.setSubject(rSet.getString("subject"));
//	                    test.setName(rSet.getString("name"));
//	                    test.setPoint(rSet.getInt("point"));
//
//	                    list.add(test);
//
//
//	            }
//	} catch (Exception e) {
//		throw e;
//	} finally {
//		// プリペアードステートメントを閉じる
//		if (statement != null) {
//			try {
//				statement.close();
//			} catch (SQLException sqle) {
//				throw sqle;
//			}
//		}
//		// コネクションを閉じる
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException sqle) {
//				throw sqle;
//			}
//		}
//	}
//
//	return list;
//	}
//}