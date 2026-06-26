<%-- 
    Document   : productslist
    Created on : Jun 19, 2026, 1:18:56 PM
    Author     : VU VAN HUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products list</title>
    </head>
    <body>
    <center><h1>PRODUCT MANAGEMENT</h1></center>
    <form action="displayproducts" method="post">
        <table border="0">
            <tr>
                <td>Search name</td>
                <td><input type="text" name="txtKw" id="txtKw" /></td>
                <td><input type="submit" id="btnSearch" value="Search" /></td>
            </tr>
            <tr>
                <td>Category</td>
                <td>
                    <select name="ddCat" id="ddCat">
                        <option value="all" id="all">--- All ---</option>
                        <c:forEach var="c" items="${catList}">
                            <option value="${c.catId}" id="${c.catId}">${c.catName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td></td>
            </tr>
        </table>
    </form>    
    <br>
    <!--Paging areas-->
    <div style="text-align: center">
        <c:forEach begin="${1}" end="${totalPages}" var="i">
            <a href="displayproducts?page=${i}">${i}</a>
        </c:forEach>
    </div> <br />
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Import date</th>
                <th>Category</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${prdList}"> 
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.qty}</td>
                <td>${p.price}</td>
                <td>${p.importDate}</td>
                <td>${p.catId}</td>
                <td>
                    <a href="edit?pid=${p.id}">Edit</a> |
                    <a href="delete?pid=${p.id}">Delete</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
