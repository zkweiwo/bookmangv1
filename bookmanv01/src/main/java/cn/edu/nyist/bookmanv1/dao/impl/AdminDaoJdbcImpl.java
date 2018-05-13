package cn.edu.nyist.bookmanv1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.nyist.bookmanv1.dao.AdminDao;
import cn.edu.nyist.bookmanv1.util.DsUtil;

public class AdminDaoJdbcImpl implements AdminDao {

	@Override
	public boolean get(String name, String pwd) {
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		boolean ret = false;
		try {
			conn = DsUtil.getConn();
			//�޸Ķ�������ƴ���ַ�������ռλ��д��
			//String sql = "select * from t_user where name='" + name + "' and pwd='" + pwd + "'";
			//?����ռλ������ʾ����Ҫ��һ�������������λ��
			String  sql="select * from t_admin where name=? and pwd=?";
			System.out.println(sql);
			//stmt = conn.createStatement();
			stmt=conn.prepareStatement(sql);
			//�������ַ�ת��
			stmt.setString(1, name);
			stmt.setString(2, pwd);
			//�޸���:��Ϊ�����Ѿ����벢��ռλ����ֵ�ˣ�������ٴ�
			//rs = stmt.executeQuery(sql);
			rs=stmt.executeQuery();
			if (rs.next()) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DsUtil.free(rs, stmt, conn);
		}
		return ret;
	}

}