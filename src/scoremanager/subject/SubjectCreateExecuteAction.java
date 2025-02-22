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

public class SubjectCreateExecuteAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao sDao = new SubjectDao();//学生Dao
		String cd = "";//科目コード
		String name = "";//科目名
		Subject subject = null;

		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得


		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");//科目コード
		name = req.getParameter("name");//科目名


		//DBからデータ取得 3
		subject = sDao.get(cd, null);
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (cd.length() <= 2 || 4 <= cd.length() ) {
			errors.put("cd", "科目コードは３文字で入力してください");
		} else {
			if (subject == null) {// 科目コードが未登録だった場合
			// 学生インスタンスを初期化
			subject = new Subject();
				// インスタンスに値をセット
			subject.setCd(cd);
			subject.setName(name);
			subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());
			// 学生を保存
			sDao.save(subject);
			}else {//入力された学番がDBに保存されていた場合
				errors.put("cd", "科目コードが重複しています");
			}



		req.setAttribute("class_num_set", list);//クラス番号のlistをセット
		}
		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("/scoremanager/subject/subject_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("/scoremanager/subject/subject_create_done.jsp").forward(req, res);
	}
}