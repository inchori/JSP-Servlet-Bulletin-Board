package com.servlet.Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.DTO.BBSDTO;
import com.servlet.DAO.BBSDAO;

public class WriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			HttpSession session = request.getSession();
			String User = null;
			if(session.getAttribute("userID") != null) {
				User = (String) session.getAttribute("userID");
			}
			String Title = request.getParameter("bbsTitle");
			String Content = request.getParameter("bbsContent");
			if(Title == null || Content == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('Please Fill out your Form!')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				BBSDTO bbsDTO = new BBSDTO();
				BBSDAO bbsDAO = new BBSDAO();
				bbsDTO.setUserID(User);
				bbsDTO.setBbsTitle(Title);
				bbsDTO.setBbsContent(Content);
				int result = bbsDAO.write(Title, User, Content);
				//System.out.println(result);
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('Failure Write!')");
					script.println("history.back()");
					script.println("</script>");
				}
				else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = './list'");
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
