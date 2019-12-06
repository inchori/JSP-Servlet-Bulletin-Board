package com.servlet.Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.DAO.BBSDAO;
import com.servlet.DTO.BBSDTO;

public class ModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			BBSDAO bbsDAO = new BBSDAO();
			int bbsID = Integer.parseInt(request.getParameter("bbsID"));
			String Title = request.getParameter("bbsTitle");
			String Content = request.getParameter("bbsContent");

			bbsDAO.update(bbsID, Title, Content);
			
			response.sendRedirect("./modify?bbsID=" + bbsID);
		} catch (Exception e) {
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
