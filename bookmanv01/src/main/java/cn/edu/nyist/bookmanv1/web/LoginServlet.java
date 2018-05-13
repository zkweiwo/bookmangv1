package cn.edu.nyist.bookmanv1.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.nyist.bookmanv1.biz.AdminBiz;
import cn.edu.nyist.bookmanv1.biz.impl.AdminBizImpl;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 ��ȡ����
				String name = request.getParameter("name");
				String pwd = request.getParameter("pwd");
				String vcode = request.getParameter("vcode");
				// ִ����֤��
				HttpSession session = request.getSession();
				String serverVcode = (String) session.getAttribute("validateCode");
				// ��֤�벻��
				if (!serverVcode.equalsIgnoreCase(vcode)) {
					// ��¼������Ϣ
					request.setAttribute("msg", "��֤�����");
					request.setAttribute("name", name);
					request.getRequestDispatcher("login.jsp").forward(request, response);
					return;
				}
				// 2 ����ҵ���
				AdminBiz adminBiz = new AdminBizImpl();
				boolean ret = adminBiz.findAdminByNameAndPwd(name, pwd);
				// 3 ���û�һ����Ӧ
				if (ret) {
					response.sendRedirect("main.jsp");
				} else {
					// ��½ʧ��
					request.setAttribute("msg", "�û��������������");
					request.setAttribute("name", name);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}


	}

}
