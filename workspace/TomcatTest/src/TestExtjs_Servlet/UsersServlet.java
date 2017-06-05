package TestExtjs_Servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		printLog("#### /UsersServlet, doGet() start");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String str = "[{\"name\":\"Ed_33\",\"email\":\"ed@sencha.com\",\"id\":1}]";
		response.getWriter().print(str);
		printLog(String.format("#### /UsersServlet, doGet(), response: %s", str));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		printLog("#### /UsersServlet, doPost() start");

		String name = "";
		String email = "";
		printLog("#### " + request.getContentType());
		if (request.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")) {
			name = request.getParameter("name");
			email = request.getParameter("email");
		}
		else if (request.getContentType().equals("application/json")) {
			String strJson = readJsonString(request);
			printLog("#### strJson = " + strJson);

			JSON_User j_user = JSON.parseObject(strJson, JSON_User.class);
			name = j_user.getName();
			email = j_user.getEmail();
		}
		printLog(String.format("#### /UsersServlet, doPost(), (%s, %s)", name, email));
	}
	
	public String readJsonString(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();

		try {
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			printLog("#### Exception");
			printLog(e.toString());
		}
		return json.toString();
	}

	private void printLog(final String slog) {
    	System.out.println(slog);
    }
}
