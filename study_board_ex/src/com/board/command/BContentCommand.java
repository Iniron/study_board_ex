package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.Dao;
import com.board.dto.Dto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bid = request.getParameter("bid");
		
		Dao dao = Dao.getInstance();
		
		//�۳��� ��������
		Dto dto = dao.getContent(bid);
		
		request.setAttribute("content", dto);
	}

}
