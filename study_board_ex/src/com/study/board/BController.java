package com.study.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.command.BCommand;
import com.board.command.BContentCommand;
import com.board.command.BDeleteCommand;
import com.board.command.BListCommand;
import com.board.command.BUpdateCommand;
import com.board.command.BWriteCommand;

/**
 * Servlet implementation class BController
 */
@WebServlet("*.do")
public class BController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR");
		
		BCommand bCommand = null;
		String viewPage = null;
		
		
		//http://localhost:8282/study_board_ex/board_list_view.do
		
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String comStr = uri.substring(contextPath.length());
		
		if(comStr.equals("/board_list_view.do")){
			bCommand = new BListCommand();
			bCommand.execute(request, response);
			viewPage = "/board_list_view.jsp";
		} else if(comStr.equals("/board_write_view.do")){
			viewPage = "/board_write_view.jsp";
		}
		else if(comStr.equals("/board_write.do")){
			bCommand = new BWriteCommand();
			bCommand.execute(request, response);
			viewPage = "/board_list_view.do";			
		}
		else if(comStr.equals("/board_content_view.do")){
			bCommand = new BContentCommand();
			bCommand.execute(request, response);
			viewPage = "/board_content_view.jsp";
		}else if(comStr.equals("/board_delete.do")){
			bCommand = new BDeleteCommand();
			bCommand.execute(request, response);
			viewPage = "/board_list_view.do";
		}else if(comStr.equals("/board_modify_view.do")){
			bCommand = new BContentCommand();
			bCommand.execute(request, response);			
			viewPage = "/board_modify_view.jsp";
		}else if(comStr.equals("/board_update.do")){
			bCommand = new BUpdateCommand();
			bCommand.execute(request, response);			
			viewPage = "/board_content_view.do";
		}
		
		
		
		
		
		//클라이언트로부터 받은 request, response를 포워딩
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
