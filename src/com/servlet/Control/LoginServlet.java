package com.servlet.Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet.DAO.UserDAO;
import com.servlet.DTO.UserDTO;


public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ID = request.getParameter("userID");
		String Password = request.getParameter("userPassword");
		
		try {
			UserDAO userDAO = new UserDAO();
			UserDTO userDTO = new UserDTO();
			userDTO.setUserID(ID);
			userDTO.setUserPassword(Password);
			int result = userDAO.login(userDTO.getUserID(), userDTO.getUserPassword());
			if (result == 1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("location.href= 'main.jsp'");
				script.println("</script>");
			}
			else if (result == 0) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Wrong Password!')");
				script.println("history.back()");
				script.println("</script>");
			}
			else if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Invalid ID!')");
				script.println("history.back()");
				script.println("</script>");
			}
			else if (result == -2) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Database Error!')");
				script.println("history.back()");
				script.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
