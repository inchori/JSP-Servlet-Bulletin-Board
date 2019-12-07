package com.servlet.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.servlet.DTO.BBSDTO;

public class BBSDAO {
	DataSource datasource;
	
	private Connection con = null;
	private ResultSet rs = null;
	
	public BBSDAO() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle18c");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		try {
			con = datasource.getConnection();
			String SQL = "SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS') FROM DUAL";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public int getNext() {
		try {
			con = datasource.getConnection();
			String SQL = "SELECT BBSID FROM BBS ORDER BY BBSID DESC";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
	public int write(String bbsTitle, String userID, String bbsContent) {
		try {
			con = datasource.getConnection();
			String SQL = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, bbsTitle);
			pstmt.setString(3, userID);
			pstmt.setString(4, getDate());
			pstmt.setInt(5, 1);
			pstmt.setString(6, bbsContent);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<BBSDTO> getList(int pageNumber) {
		ArrayList<BBSDTO> list = new ArrayList<BBSDTO>();
		try {
			con = datasource.getConnection();
			String SQL = "SELECT * FROM (SELECT * FROM BBS WHERE BBSID < ? AND BBSAVAILABLE = 1 ORDER BY BBSID DESC) WHERE ROWNUM <= 10";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BBSDTO bbsDTO = new BBSDTO();
				bbsDTO.setBbsID(rs.getInt(1));
				bbsDTO.setBbsTitle(rs.getString(2));
				bbsDTO.setUserID(rs.getString(3));
				bbsDTO.setBbsDate(rs.getString(4));
				bbsDTO.setBbsAvailable(rs.getInt(5));
				bbsDTO.setBbsContent(rs.getString(6));
				
				list.add(bbsDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber) {
		ArrayList<BBSDTO> list = new ArrayList<BBSDTO>();
		try {
			con = datasource.getConnection();
			String SQL = "SELECT * FROM (SELECT * FROM BBS WHERE BBSID < ? AND BBSAVAILABLE = 1 ORDER BY BBSID DESC) WHERE ROWNUM <= 10";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public BBSDTO getBBS(int bbsID) {
		ArrayList<BBSDTO> list = new ArrayList<BBSDTO>();
		try {
			con = datasource.getConnection();
			String SQL = "SELECT * FROM BBS WHERE BBSID = ?";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BBSDTO bbsDTO = new BBSDTO();
				bbsDTO.setBbsID(rs.getInt(1));
				bbsDTO.setBbsTitle(rs.getString(2));
				bbsDTO.setUserID(rs.getString(3));
				bbsDTO.setBbsDate(rs.getString(4));
				bbsDTO.setBbsAvailable(rs.getInt(5));
				bbsDTO.setBbsContent(rs.getString(6));
				return bbsDTO;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int update(int bbsID, String bbsTitle, String bbsContent) {
		try {
			con = datasource.getConnection();
			String SQL = "UPDATE BBS SET BBSTITLE = ?, BBSCONTENT = ? WHERE BBSID =?";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, bbsTitle);
			pstmt.setString(2, bbsContent);
			pstmt.setInt(3, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int delete(int bbsID) {
		try {
			con = datasource.getConnection();
			String SQL = "DELETE FROM BBS WHERE BBSID =?";
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, bbsID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
