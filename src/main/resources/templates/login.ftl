<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <title>Login</title>
</head>
<body>
<#if error??>
    <div class="alert alert-danger" role="alert">
        Login or password is incorrect!!

    </div>
</#if>
<br/>
<div>
    Please login!
</div>
<br/>
<form method="post" action="/login">
<p>
    <label for="login">User login
        <input type="text" id="login" name="login">
    </label>
</p>
    <p>
    <label for="password">User password
        <input type="password" id="password" name="password">
    </label>
    </p>
    <p>
    <label for="remember-me"> Remember me
        <input type="checkbox" id="remember-me" name="remember-me">
    </label>
    </p>
    <input type="submit" value="Log in">

</form>

</body>
</html>