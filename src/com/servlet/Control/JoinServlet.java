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

@WebServlet("/JS")
public class JoinServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			String ID = request.getParameter("userID");
			String Password = request.getParameter("userPassword");
			String Name = request.getParameter("userName");
			String Gender = request.getParameter("userGender");
			String Email = request.getParameter("userEmail");
			UserDTO userDTO = new UserDTO();
			if(userDTO.getUserID() == null || userDTO.getUserPassword() == null || userDTO.getUserName() == null ||
					userDTO.getUserGender() == null || userDTO.getUserEmail() == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Please Fill out your Form!')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				UserDAO userDAO = new UserDAO();
				int result = userDAO.join(userDTO);
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('Already Sign Up!')");
					script.println("history.back()");
					script.println("</script>");
				} else if (result == 0) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'main.jsp'");
					script.println("</script>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
