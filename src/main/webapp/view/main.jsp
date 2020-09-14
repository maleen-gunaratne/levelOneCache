<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
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

    <title>Cache configuration</title>
</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />

<div align="center">
    <c:if test="${not empty message}">
        Messages : ${message}
    </c:if>
    <h2>Cache Configuration</h2>
    <form:form action="${base}initCache" method="post" modelAttribute="cacheAttribute">
        <form:label path="maxSize">Max Size:</form:label>
        <form:input path="maxSize" type="number"/><br/>


        <form:label path="cacheType">Cache Type:</form:label>
        <form:radiobutton path="cacheType" value="lfu"/>LFU
        <form:radiobutton path="cacheType" value="lru"/>LRU<br/>


        <form:button>Start</form:button>
    </form:form>
</div>
</body>
</html>