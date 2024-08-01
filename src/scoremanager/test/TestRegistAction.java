package scoremanager.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.javafx.collections.MappingChange.Map;

import bean.Teacher;
import dao.TestDao;
import tool.Action;


public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー
		String entYearStr="";// 入力された入学年度
		String classNumStr = "";//入力された科目名
		String subjectStr="";//入力された科目
		String noStr="";//回数
		int entYear = 0;// 入学年度

		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		TestDao testDao = new TestDao();// testDaoを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ



		//リクエストパラメータ―の取得 2[[
			//リクエストから"f1"という名前のパラメーターの値を取得し、それをentYearStrに格納します。
			//リクエストから"f2"という名前のパラメーターの値を取得し、それをclassNumに格納します。
			//リクエストから"f3"という名前のパラメーターの値を取得し、それをisAttendStrに格納します。
		entYearStr = req.getParameter("f1");
		classNumStr = req.getParameter("f2");
		subjectStr = req.getParameter("f3");
		noStr= req.getParameter("f4");


		//ビジネスロジック 4
		if (entYearStr != null) {
			// 数値に変換
		entYear = Integer.parseInt(entYearStr);
	}
	// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}


        // TestDao からのデータ取得
        // 以下の filter メソッドが適切な引数を必要とするため、必要に応じて修正する必要があります
        List<String> classNumSet = null;
		try {
			classNumSet = testDao.getClassNums(teacher.getClassNum());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        List<String> subjectSet = null;
		try {
			subjectSet = testDao.getSubjects(teacher.getSubject());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		List<Integer> noSet = null;
		try {
			noSet = testDao.getNo(teacher.getNo());
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();


		}
		// 入学年度が送信されていた場合
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}


//		//検索結果の表示
//		List<Test> kennsakuitirannSet = null;
//
//		if (entYearStr != ("0") && classNumStr!=("0") && subjectStr != ("0") && noStr != ("0")) {
//		// 入学年度とクラス番号と科目と回数を指定
//			kennsakuitirannSet = testDao.filter(teacher.getEntYear(),teacher.getClassNum(),teacher.getSubject(),teacher.getName(),teacher.getPoint());
//		} else {
//			errors.put("f1","入学年度クラス科目を指定する場合は入学年度も指定してください");
//			req.setAttribute("errors", errors);
//




			// リクエスト属性にセット

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_set", subjectSet);
        req.setAttribute("no_set", noSet);
//		req.setAttribute("kennsakuitirannSet", kennsakuitirannSet);


		req.getRequestDispatcher("/scoremanager/test/test_regist.jsp").forward(req, res);
	}
}