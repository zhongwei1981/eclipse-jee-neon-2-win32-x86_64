package TestExtjs_Servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import JSON_Class.JSON_User;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Log log = new Log(UsersServlet.class.getSimpleName());
       
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
		log.d("#### doGet() start");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		JSON_User j_user = new JSON_User();
		j_user.setId(1L);
		j_user.setName("Ed_33");
		j_user.setEmail("ed@sencha.com");
		String str = String.format("[%s]", JSON.toJSONString(j_user));
		response.getWriter().print(str);
		log.d("#### doGet(), response: %s", str);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.d("#### doPost() start");

		String name = "";
		String email = "";
		log.d("#### " + request.getContentType());
		if (request.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")) {
			name = request.getParameter("name");
			email = request.getParameter("email");
		}
		else if (request.getContentType().equals("application/json")) {
			String strJson = readJsonString(request);
			log.d("#### strJson = " + strJson);

			JSON_User j_user = JSON.parseObject(strJson, JSON_User.class);
			name = j_user.getName();
			email = j_user.getEmail();
		}
		log.d("#### doPost(), (%s, %s)", name, email);
	}
	
	public String readJsonString(HttpServletRequest request) {
		StringBuffer strJson = new StringBuffer();

		try {
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				strJson.append(line);
			}
		} catch (Exception e) {
			log.e("#### Exception: " + e.toString());
		}
		return strJson.toString();
	}
}
