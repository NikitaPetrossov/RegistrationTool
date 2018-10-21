
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <style>
        <%@include file='../css/usersCss.css' %>
    </style>
    <title>User page</title>
</head>
<body>
<div>User page</div>
<div class="form-style2-heading">${user.lastName}</div>
<div class="form-style2-heading">${user.name}</div>
<a href="/logout">Log out</a>
<div>

<input type="submit"
        onclick="location.href = '/crud'" value='Edit page'>


</div>
</body>
</html>