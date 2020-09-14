<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sample Order Cache</title>
    <style type="text/css">
        label {
            display: inline-block;
            width: 200px;
            margin: 5px;
            text-align: left;
        }
        input[type=text], input[type=password], select {
            width: 200px;
        }
        input[type=radio] {
            display: inline-block;
            margin-left: 45px;
        }
        input[type=checkbox] {
            display: inline-block;
            margin-right: 190px;
        }

        button {
            padding: 10px;
            margin: 10px;
        }
    </style>

</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />


<div align="center">
    <h2>Order Registration</h2>
    <form:form action="${base}/add" method="post" modelAttribute="order">
        <form:label path="orderNo">order No:</form:label>
        <form:input path="orderNo"/><br/>

        <form:label path="name">name:</form:label>
        <form:input path="name"/><br/>

        <form:label path="cardType">cardType:</form:label>
        <form:input path="cardType"/><br/>

        <form:hidden path="accessCount" /><br/>


        <form:button>Register</form:button>
    </form:form>
</div>

<div align="center">
<h1>Cached Items</h1>
<table border="1" >
    <thead>
    <th>OrderNo</th>
    <th>Name</th>
    <th>CardType</th>
    <th>Access Count</th>
    <th>access item</th>
    </td>
    </thead>
    <tbody>
    <c:forEach items="${orderlist}" var="order">
        <tr>
            <td>${order.orderNo}</td>
            <td>${order.name}</td>
            <td>${order.cardType}</td>
            <td>${order.accessCount}</td>
            <td><a href="${base}/update/${order.orderNo}">get</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>