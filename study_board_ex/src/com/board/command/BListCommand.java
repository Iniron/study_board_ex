package com.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.Dao;
import com.board.dto.Dto;

public class BListCommand implements BCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		//������ ���̽� ����
		//������� �޾Ƽ�
//		String bname = request.getParameter("bname");
//		String btitle = request.getParameter("btitle");
//		String bcontent = request.getParameter("bcontent");
		
		Dao dao = Dao.getInstance();
		ArrayList<Dto> dtos = dao.getList();
		
		// -> ��Ʈ�ѷ� ����
		request.setAttribute("list", dtos);
	}
}
