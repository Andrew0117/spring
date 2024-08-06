<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        .logo {
            margin-bottom: 20px;
        }
        .search-bar {
            display: flex;
            width: 100%;
            max-width: 600px;
            margin-bottom: 20px;
        }
        .search-bar input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 24px 0 0 24px;
            outline: none;
            font-size: 16px;
        }
        .search-bar button {
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-left: none;
            border-radius: 0 24px 24px 0;
            background-color: #4285f4;
            color: white;
            font-size: 16px;
            cursor: pointer;
            outline: none;
        }
        .search-bar button:hover {
            background-color: #357ae8;
        }
        .result {
            display: flex;
            gap: 10px;
        }
        .result span {
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            cursor: pointer;
            font-size: 14px;
            outline: none;
        }
        .result span:hover {
            background-color: #e8e8e8;
        }
    </style>
</head>
<body>
    <img class="logo" src="https://modernmarketingtoday.com/wp-content/uploads/2013/02/search-marketing.jpg" width="272" height="92" alt="Search Logo">
    <f:form class="search-bar" method="post" action="/search" modelAttribute="search">
        <f:input path="search" placeholder="Search..."/>
        <button type="submit">Search</button>
    </f:form>
    <div class="result">
        <span>GOOGLE: ${googleTotal}</span>
        <span>YAHOO: ${yahooTotal}</span>
    </div>
</body>
</html>