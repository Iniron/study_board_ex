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
		//������ ���̽� (JDBC)
		//1. ����
		//2. query�� �ۼ�
		//statement��ü
		//����
		//��� �� ����
	
	private static Dao instance = new Dao();
	
	private Dao() {	}	//�̱���
	
	public static Dao getInstance(){
		return instance;
	}
	
	//Ŀ�ؼ�Ǯ �����ͺ��̽� ����
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
	
	//�����ͺ��̽����� �۸�� ��������
	public ArrayList<Dto> getList(){
		//�����ͺ��̽� ����
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
		
	
	//�����ͺ��̽��� ������
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
	
	//�����ͺ��̽����� ���밡������
	public Dto getContent(String bid){
		
		
		//��ȸ�� �ø���
		upHit(bid);
		
		//�����ͺ��̽� ����
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
	
	//��ȸ�� ����
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
	
	//�ۻ���
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
	
	//������ ����
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
