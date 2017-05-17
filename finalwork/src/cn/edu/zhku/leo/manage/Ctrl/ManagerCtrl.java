package cn.edu.zhku.leo.manage.Ctrl;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.edu.zhku.leo.manage.Service.*;
import cn.edu.zhku.leo.manage.model.Manager;

public class ManagerCtrl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		if ("edit".equals(action)) {
			try {
				this.edit(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("del".equals(action)) {
			try {
				this.del(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("add".equals(action)) {
			try {
				this.add(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		} else if ("get".equals(action)) {
			try {
				this.getJson(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("login".equals(action)) {
			try {
				this.login(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if("getone".equals(action)){
			try {
				this.getone(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 将数据度读来的Manager对象数组整理成json文件-->暂时保留 输出数据库读来的数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * 
	 */
	private void getJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		// File file = new File(
		// "D:\\我的文档\\GitHub\\finalwork\\finalwork\\WebRoot\\html_manager\\tables\\managers.json");
		// if (!file.exists())
		// file.createNewFile();
		ManagerService g = new ManagerService();

		ArrayList<Manager> a = g.get();
		JSONObject resultJson = new JSONObject();// 创建最后结果的json
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < a.size(); i++) {
			Manager m = a.get(i);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", m.getId());
			jsonObject.put("password", m.getPassword());
			jsonObject.put("info", m.getInfo());
			jsonArray.add(jsonObject);
		}
		resultJson.put("managers", jsonArray);
		out.println(resultJson);
		out.flush();
		out.close();

		// PrintStream p = new PrintStream(file);
		// p.println(jsonArray);
		// p.close();
	}

	/**
	 * 
	 * 删除数据库的对应数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * 
	 */
	private void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		ManagerService g = new ManagerService();
		String id = (String) request.getParameter("id");

		if (g.del(id)) {
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html?r=1");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html?r=0");
		}

	}

	/**
	 * 
	 * 查询数据库的一条数据存不存在
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * 
	 */
	private void getone(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		ManagerService g = new ManagerService();
		int id =  Integer.parseInt(request.getParameter("id"));

		if (g.getone(id)!=null) {
			
			JSONObject resultJson = new JSONObject();// 创建最后结果的json
			JSONArray jsonArray = new JSONArray();


			Manager m = g.getone(id);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", m.getId());
			jsonObject.put("password", m.getPassword());
			jsonObject.put("info", m.getInfo());
			jsonArray.add(jsonObject);
			resultJson.put("managers", jsonArray);
			out.println(resultJson);
			out.flush();
			out.close();
		} else {
			
		}

	}
	
	/**
	 * 
	 * 修改数据库一条指定数据的info
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		ManagerService g = new ManagerService();
		int id =  Integer.parseInt(request.getParameter("id"));
		String info = request.getParameter("infoe");
		
		if(g.edit(id,info)){
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html?r=4");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html?r=5");
		}
	

	}
	
	/**
	 * 
	 * 读取id和password检索数据库
	 * 匹配成功-登录成功
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		ManagerService g = new ManagerService();
		if(request.getParameter("id")==""||request.getParameter("password")==""){
			response.sendRedirect(request.getContextPath()+ "/html_manager/login.html");
			return;
		}
		int id =  Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		
		Manager m = new Manager();
		m.setId(id);
		m.setPassword(password);
		
		if(g.login(m)){
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/login.html");
		}
	

	}
	
	/**
	 * 
	 * 添加一个Manager对象数据到数据库
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		if(request.getParameter("id")==""||request.getParameter("password")==""){
			response.sendRedirect(request.getContextPath()+ "/html_manager/manager.html?r=3");
			return;
		}
		int a =  Integer.parseInt(request.getParameter("id"));
		
		String b = request.getParameter("password");
		String c = request.getParameter("info");
		
		ManagerService g = new ManagerService();
		Manager m= new Manager();
		m.setId(a);
		m.setPassword(b);
		m.setInfo(c);

		if (g.add(m)) {
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html?r=2");
		} else {
			response.sendRedirect(request.getContextPath()
					+ "/html_manager/manager.html?r=3");
		}

	}

}
