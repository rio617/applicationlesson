<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Product,java.util.*" %>
    <%
    //リクエストスコープから「メッセージ」「エラーメッセージ」を取得
    String msg=(String)request.getAttribute("msg");
    String err=(String)request.getAttribute("err");
    //アプリケーションスコープから「List」を取得
    List<Product> list=(List<Product>)application.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>練習問題７</title>
</head>
<body>
<p>商品を登録してください。</p>

<% 
//if文で「エラーメッセージ」がnullか確認
//nullじゃない場合、エラーメッセージを表示
if(err != null){ %>
   <p><%=err %></p>
 <% }%>
 <% //f文で「メッセージ」がnullか確認
    //nullじゃない場合、メッセージを表示
 if(msg != null){ %>
   <p><%=msg %></p>
 <% }%>
 
<form action="Main" method="post"> 
製品名：<br>
<input type="text" name="name"><br>
価格：<br>
<input type="number" name="price"><br>
<input type="submit" value="登録">
</form>
<%if(list != null){ %>
<table border="">
<tr><th>製品名</th><th>価格</th><th>登録日</th></tr>
<%for(Product p : list){ %>
<tr><td><%=p.getName() %></td><td><%=p.getPrice() %></td><td><%=p.getUpdated() %></td></tr>
<%} %>
</table>
<%} %>
</body>
</html>