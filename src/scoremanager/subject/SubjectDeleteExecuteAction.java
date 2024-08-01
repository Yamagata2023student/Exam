package scoremanager.subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ローカル変数の宣言
		SubjectDao sDao = new SubjectDao(); // 科目Dao
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザーを取得
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// リクエストパラメータの取得
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		String classNum = req.getParameter("class_num");

		// DBからデータ取得
		Subject subject = sDao.get(cd, null);
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (subject != null) {
			// 科目が存在していた場合
			// 科目を削除
			sDao.delete(subject);
		} else {
			errors.put("cd", "科目が存在していません");
		}

		req.setAttribute("class_num_set", list);

		if (!errors.isEmpty()) { // エラーがあった場合、削除画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.setAttribute("class_num", classNum);
			req.getRequestDispatcher("/scoremanager/subject/subject_delete.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("/scoremanager/subject/subject_delete_done.jsp").forward(req, res);
	}
}