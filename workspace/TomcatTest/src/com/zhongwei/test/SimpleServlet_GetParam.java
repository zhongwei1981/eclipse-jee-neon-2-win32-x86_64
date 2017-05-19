package com.zhongwei.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SimpleServlet_GetParam", urlPatterns = { "/SimpleServlet_GetParam" }, initParams = {
		@WebInitParam(name = "name1", value = "v1") })
public class SimpleServlet_GetParam extends HttpServlet {

	private static final long serialVersionUID = -1915463532411657451L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("usr");
		String password = request.getParameter("pwd");

		String value1 = getServletConfig().getInitParameter("name1");

		try {
			// Write some content
			out.println("<html>");
			out.println("<head>");
			out.println("<title>LoginServlet</title>");
			out.println("</head>");
			out.println("<body>");

			out.println("<h2>#### user: " + username + "</h2>");
			out.println("<h2>#### pwd: " + password + "</h2>");
			out.println("<h2>#### value1: " + value1 + "</h2>");

			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("usr");
		String password = request.getParameter("pwd");

		String value1 = getServletConfig().getInitParameter("name1");

		Connection conn = null;

		try {
			// Connection to DB
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=testDB", "sa",
					"IloveChina1981");
// 			PreparedStatement prep = conn.prepareStatement("select * from account where user_name = " + username);
// 			PreparedStatement prep = conn.prepareStatement("select * from account");
//			PreparedStatement prep = conn.prepareStatement(strSQL);
//			ResultSet rs = prep.executeQuery();
			String strSQL = String.format("INSERT INTO account (user_name, password) VALUES ('%s', '%s')", username,
					password);
			System.out.println("#### strSQL = " + strSQL);
			PreparedStatement prep = conn.prepareStatement(strSQL);
			prep.executeUpdate();

			// Write some content
			out.println("<html>");
			out.println("<head>");
			out.println("<title>LoginServlet</title>");
			out.println("</head>");
			out.println("<body>");
			// while (rs.next()) {
			// out.format("<h2>#### %d: (%s, %s)</h2>", rs.getRow(),
			// rs.getString("user_name"),
			// rs.getString("password"));
			// }
			// out.println();
			out.format("<h2>#### done (%s, %s)</h2>", username, password);
			out.println();
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("#### Error: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			out.println("#### Error: " + e.getMessage());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					out.println("#### Error: can't close conn");
				}
			}
		} // finally

		out.close();
	}

	@Override
	public String getServletInfo() {
		return "SimpleServlet_GetParam";
	}
}
