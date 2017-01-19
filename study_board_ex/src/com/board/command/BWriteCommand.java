package com.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.Dao;
import com.board.dto.Dto;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		Dto dto = new Dto();
		dto.setBname(bname);
		dto.setBtitle(btitle);
		dto.setBcontent(bcontent);
		
		Dao dao = Dao.getInstance();
		dao.insertList(dto);
		/*if(dao.insertList(dto)==1){
			//성공
			//request.setAttribute("messgae", "success");
		} else{
			//실패
			//request.setAttribute("messgae", "error");
		}*/
		
		
	}
}
