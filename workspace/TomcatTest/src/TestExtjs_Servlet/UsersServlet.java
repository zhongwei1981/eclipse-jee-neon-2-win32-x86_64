package TestExtjs_Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import CommonTools.DB_Connection;
import CommonTools.Log;
import JSON_Class.JSON_User;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/UsersServlet")
public class UsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Log log = new Log(UsersServlet.class.getSimpleName());
	private static DB_Connection conn = DB_Connection.getInstance();

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.d("#### doGet() start");

		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String strSQL = String.format("SELECT id, name, email from [user] ");
		log.d("#### strSQL = " + strSQL);
		try {
			ResultSet rs = conn.execQuery(strSQL);
			JSONArray jsonArray = new JSONArray();
			while (rs.next()) {
				JSON_User j_user = new JSON_User();
				log.d("#### (%d, %s, %s)",
						rs.getLong("id"), rs.getString("name"), rs.getString("email"));
				j_user.setId(rs.getLong("id"));
				j_user.setName(rs.getString("name"));
				j_user.setEmail(rs.getString("email"));
				jsonArray.add(j_user);
			}
			
			String str = jsonArray.toJSONString();
			log.d("#### response: %s", str);
			response.getWriter().print(str);
		} catch (SQLException e) {
			log.e(e.toString());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(e.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.d("#### doPost() start");

		String name = "";
		String email = "";
		log.d("#### " + req.getContentType());
		if (req.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")) {
			name = req.getParameter("name");
			email = req.getParameter("email");
		}
		else if (req.getContentType().equals("application/json")) {
			String strJson = readJsonString(req);
			log.d("#### strJson = " + strJson);

			JSON_User j_user = JSON.parseObject(strJson, JSON_User.class);
			name = j_user.getName();
			email = j_user.getEmail();
		}

		String strSQL = String.format(
							"UPDATE [user] SET email = '%s' where name = '%s' ",
							email, name);
		log.d("#### strSQL = " + strSQL);
		try {
			conn.execUpdate(strSQL);
		} catch (SQLException e) {
			log.e(e.toString());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(e.toString());
		}
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
	              throws ServletException, java.io.IOException {
		log.d("#### doPut() start");

		String name = "";
		String email = "";
		log.d("#### " + req.getContentType());
		if (req.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")) {
			name = req.getParameter("name");
			email = req.getParameter("email");
		}
		else if (req.getContentType().equals("application/json")) {
			String strJson = readJsonString(req);
			log.d("#### strJson = " + strJson);

			JSON_User j_user = JSON.parseObject(strJson, JSON_User.class);
			name = j_user.getName();
			email = j_user.getEmail();
		}

		String strSQL = String.format(
							"INSERT INTO [user] (name, email) VALUES ('%s', '%s') ",
							name, email);
		log.d("#### strSQL = " + strSQL);
		try {
			conn.execUpdate(strSQL);
		} catch (SQLException e) {
			log.e(e.toString());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(e.toString());
		}
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {
		log.d("#### doDelete() start");

		String name = "";
		String email = "";
		log.d("#### " + req.getContentType());
		if (req.getContentType().equals("application/x-www-form-urlencoded; charset=UTF-8")) {
			name = req.getParameter("name");
			email = req.getParameter("email");
		}
		else if (req.getContentType().equals("application/json")) {
			String strJson = readJsonString(req);
			log.d("#### strJson = " + strJson);

			JSON_User j_user = JSON.parseObject(strJson, JSON_User.class);
			name = j_user.getName();
			email = j_user.getEmail();
		}

		String strSQL = String.format(
							"DELETE [user] where name = '%s' ",
							name);
		log.d("#### strSQL = " + strSQL);
		try {
			conn.execUpdate(strSQL);
		} catch (SQLException e) {
			log.e(e.toString());
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(e.toString());
		}
	}
	
	private String readJsonString(HttpServletRequest request) {
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
