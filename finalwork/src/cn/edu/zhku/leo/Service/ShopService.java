package cn.edu.zhku.leo.Service;

import java.util.ArrayList;

import cn.edu.zhku.leo.Dao.ShopDao;
import cn.edu.zhku.leo.Model.Shop;

public class ShopService {
	
	/**
	 *  该方法检测数据库，并返回一个对象数组，
	 * @param
	 * @return Manager对象数组
	 * @throws Exception
	 *            
	 * 
	 */
	public ArrayList<Shop> get1() throws Exception {

		ShopDao ld = new ShopDao();
		ArrayList<Shop> a = ld.get();
		ArrayList<Shop> b = new ArrayList<Shop>();
		
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i).getStatus()!=0){
				b.add(a.get(i));
			}
		}
		return b;
	}


	/**
	 *  该方法检测数据库，并返回一个对象数组，
	 * @param
	 * @return Manager对象数组
	 * @throws Exception
	 *            
	 * 
	 */
	public ArrayList<Shop> get2() throws Exception {

		ShopDao ld = new ShopDao();
		ArrayList<Shop> a = ld.get();
		ArrayList<Shop> b = new ArrayList<Shop>();
		
		for (int i = 0; i < a.size(); i++) {
			if(a.get(i).getStatus()==0){
				b.add(a.get(i));
			}
		}
		return b;
	}
}
