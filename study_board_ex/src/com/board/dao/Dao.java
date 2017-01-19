package com.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.dto.Dto;

public class Dao {
		//데이터 베이스 (JDBC)
		//1. 접속
		//2. query문 작성
		//statement객체
		//질의
		//결과 값 전송
	
	private static Dao instance = new Dao();
	
	private Dao() {	}	//싱글톤
	
	public static Dao getInstance(){
		return instance;
	}
	
	//커넥션풀 데이터베이스 연결
	private Connection getConnect(){
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//데이터베이스에서 글목록 가져오기
	public ArrayList<Dto> getList(){
		//데이터베이스 연결
		Connection connection = getConnect();
		PreparedStatement pStatement =null;
		ResultSet resultSet = null;
		Dto dto = null;
		ArrayList<Dto> dtos = null;
		
		String query = "select * from board_table";
		
		try {
			pStatement = connection.prepareStatement(query);
			resultSet = pStatement.executeQuery();
			dtos = new ArrayList<>();
			
			while(resultSet.next()){
				dto = new Dto();
				dto.setBid(resultSet.getInt("bid"));
				dto.setBname(resultSet.getString("bname"));
				dto.setBtitle(resultSet.getString("btitle"));
				dto.setBcontent(resultSet.getString("bcontent"));
				dto.setBdate(resultSet.getDate("bdate"));
				dto.setBhit(resultSet.getInt("bhit"));
				
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(pStatement!=null) pStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
		
	
	//데이터베이스에 글저장
	public int insertList(Dto dto){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "insert into board_table values(bid_seq.nextVal, ?, ?, ?, default, default)";
		int check=0;
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getBname());
			preparedStatement.setString(2, dto.getBtitle());
			preparedStatement.setString(3, dto.getBcontent());
			check = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	//데이터베이스에서 내용가져오기
	public Dto getContent(String bid){
		
		
		//조회수 올리기
		upHit(bid);
		
		//데이터베이스 연결
		Connection connection = getConnect();
		PreparedStatement pStatement =null;
		ResultSet resultSet = null;
		Dto dto = null;
		
		String query = "select * from board_table where bid=?";
		
		try {
			pStatement = connection.prepareStatement(query);
			pStatement.setString(1, bid);
			resultSet = pStatement.executeQuery();
			
			while(resultSet.next()){
				dto = new Dto();
				dto.setBid(resultSet.getInt("bid"));
				dto.setBname(resultSet.getString("bname"));
				dto.setBtitle(resultSet.getString("btitle"));
				dto.setBcontent(resultSet.getString("bcontent"));
				dto.setBdate(resultSet.getDate("bdate"));
				dto.setBhit(resultSet.getInt("bhit"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(pStatement!=null) pStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	//조회수 증가
	private int upHit(String bid){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update board_table set bhit=bhit+1 where bid=?";
		int check=0;
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bid);
			check = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	//글삭제
	public int deleteList(String bid){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from board_table where bid=?";
		int check=0;
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, bid);
			check = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	//글정보 수정
	public int updateList(Dto dto){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update board_table set btitle=?, bcontent=? where bid=?";
		int check=0;
		
		try {
			connection = getConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dto.getBtitle());
			preparedStatement.setString(2, dto.getBcontent());
			preparedStatement.setInt(3, dto.getBid());
			check = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return check;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
