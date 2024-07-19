package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // HttpServletRequestからセッションを取得する
        HttpSession session = req.getSession();

        if (session != null) {
            // セッションから"teacher"属性を削除する
            session.removeAttribute("teacher");

            // セッションを無効化する（オプション）
            session.invalidate();
        }

        // ログアウト成功時に遷移するページ名を設定する
        String logoutPage = "/scoremanager/main/logout.jsp"; // ログアウト後の遷移先ページ

        // リダイレクトする
        req.getRequestDispatcher(logoutPage).forward(req, res);
    }
}