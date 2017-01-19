package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.Dao;
import com.board.dto.Dto;

public class BUpdateCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int bid = Integer.parseInt(request.getParameter("bid"));
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		Dto dto = new Dto();
		dto.setBid(bid);
		dto.setBtitle(btitle);
		dto.setBcontent(bcontent);
		
		Dao dao = Dao.getInstance();
		dao.updateList(dto);		

	}

}
