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
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		try {
			BBSDAO bbsDAO = new BBSDAO();
			BBSDTO bbsDTO = bbsDAO.getBBS(bbsID);
			request.setAttribute("BBS", bbsDTO);
			request.setAttribute("bbsID", bbsID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bbsID = Integer.parseInt(request.getParameter("bbsID"));
		String Title = request.getParameter("bbsTitle");
		String Content = request.getParameter("bbsContent");
		//System.out.println(Title);
		//System.out.println(Content);
		try {
			BBSDAO bbsDAO = new BBSDAO();
			bbsDAO.update(bbsID, Title, Content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("./list");
	}

}
