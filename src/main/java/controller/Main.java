package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductsDAO;
import model.Product;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		String id=request.getParameter("id");
		
		ProductsDAO dao=new ProductsDAO();
		
		if(action !=null) {
			if(action.equals("delete")) {
				dao.deleteOne(Integer.parseInt(id));
			}
		}
		
		
		List<Product> list=dao.findAll();
		//dao.checkConnect();
		
		ServletContext appcation=this.getServletContext();
		appcation.setAttribute("list",list);
		
		RequestDispatcher rd=request.getRequestDispatcher("WEB-INF/view/main.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		ServletContext application=this.getServletContext();
//		List<Product> list=(List<Product>)application.getAttribute("list");
//		
//		if(list==null) {
//			list =new ArrayList<Product>();
//		}
		
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		
		//パラメータの値が空っぽか確認
		//空っぽの場合：リクエストスコープに「エラーメッセージ」を保存する
		if(name.isEmpty() || price.isEmpty()) {
			String err="未入力の項目があります。";
			request.setAttribute("err",err);
			//request.setAttribute("err", "未記入の項目があります");
			
		}else { //空っぽじゃない場合：
			//登録日の情報を取得
			Date now =new Date();
			//現在日時をフォーマットに合わせて文字列に変換
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:HH:ss");
			String updated=sdf.format(now);
			
			//Productインスタンスを１つ作成
			Product product = new Product(name,Integer.parseInt(price),updated);
			//Listに追加
//			list.add(product);
//			//アプリケーションスコープにListを保存する
//			application.setAttribute("list", list);
			ProductsDAO dao=new ProductsDAO();
			dao.insertOne(product);
			
			//リクエストスコープに「メッセージ」を登録する
			String msg="1件登録しました。";
			request.setAttribute("msg", msg);
			//request.setAttribute("msg", "1件登録しました。");
			
		}
		
		//doGet(request,response)を記述。
		//（フォワード先がdoGetと一緒なのでdoGetに任せる）
		doGet(request,response);
		
		
	}

}
