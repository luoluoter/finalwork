package cn.edu.zhku.leo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import cn.edu.zhku.leo.Model.User;
import cn.edu.zhku.leo.Util.ConnectionManager;

public class UserDao {

	/**
	 * 
	 * �÷���Ϊ����ȡ����Ա���ݿ������
	 * ������ص�userΪ�գ���ʾ���ñ�������
	 * ������ص�user��Ϊ�գ���ʾ��ȡ�ɹ�
	 * 
	 * @param 
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList<User> getAll() throws Exception {
	
		ArrayList<User> a=new ArrayList<User>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// �������ݿ�
		conn = ConnectionManager.getConnection();

		if (conn == null) {
			throw new Exception("���ݿ����Ӳ��ɹ���");
		}

		String sqlQuery = "Select * from user";
		
		ps = conn.prepareStatement(sqlQuery);

		rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String password = rs.getString("password");
			String name = rs.getString("name");
			
			User m = new User();
			m.setId(id);
			m.setPassword(password);
			m.setName(name);
			
			a.add(m);
		}
		return a;

	}
	
	/**
	 * ���ݴ����idɾ�����ݿ����Ӧ������
	 * 
	 * @param String id
	 * @return �ɹ�����true��ʧ�ܷ���false
	 * @throws Exception
	 * 
	 */
	public boolean del(String id) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// �������ݿ�
		conn = ConnectionManager.getConnection();

		if (conn == null) {
			throw new Exception("���ݿ����Ӳ��ɹ���");
		}

		String sqlQuery = "Select * from user where id=?";
		
		ps = conn.prepareStatement(sqlQuery);
		ps.setString(1, id);
		rs = ps.executeQuery();
		
		if(rs.next()){
			sqlQuery = "delete from user where id=?";
			
			ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, id);
			int r= ps.executeUpdate();
			if(r!=0){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}
	}
	
}
