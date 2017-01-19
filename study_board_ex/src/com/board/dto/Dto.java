package com.board.dto;

import java.sql.Date;

public class Dto {

	int bid;
	String bname;
	String btitle;
	String bcontent;
	Date bdate;
	int bhit;
	
	public Dto() {
		// TODO Auto-generated constructor stub
	}
	
	public Dto(int bid, String bname, String btitle, String bcontent, Date bdate, int hit) {
		this.bid = bid;
		this.bname = bname;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bhit = hit;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int hit) {
		this.bhit = hit;
	}

}
