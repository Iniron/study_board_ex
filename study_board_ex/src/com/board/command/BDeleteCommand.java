package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.Dao;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String bid = request.getParameter("bid");
		
		Dao dao = Dao.getInstance();
		dao.deleteList(bid);
		
		
	}

}
