package cn.edu.zhku.leo.Service;

import java.util.ArrayList;


import cn.edu.zhku.leo.Dao.UserDao;
import cn.edu.zhku.leo.Model.User;

public class UserService {

	/**
	 *  �÷���������ݿ⣬������һ���������飬
	 * @param
	 * @return Manager��������
	 * @throws Exception
	 *            
	 * 
	 */
	public ArrayList<User> get() throws Exception {

		UserDao ld = new UserDao();

		return ld.getAll();
	}

	/**
	 * 
	 * del�������� �����ݿ������Ӧ���ݲ�ɾ��
	 * 
	 * @param
	 * @return �ɹ�����1��ʧ�ܷ���0
	 * @throws Exception
	 * 
	 */
	public boolean del(String id) throws Exception{
    	
    	UserDao ld = new UserDao();
    	if(ld.del(id)){
    		return true;
    	}else{
    		return false;
    	}
    }
}
