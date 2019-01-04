

<html>
<head>
    <style>
        <%@include file='../css/usersCss.css' %>
    </style>
    <title>Registration page</title>
</head>
<body>
<div>Registration</div>

<form method="post" action="/registration">
    <table>
        <thead>
        <th></th>
        <th></th>
        </thead>
        <tbody >
        <tr >
            <td> <input class="radius" type="text"  id="lastName" name="lastName">    </td>
            <td >  <label class="lastName">Last Name</label>   </td>
        </tr>
        <tr >
            <td>  <input class="radius" type="text" id="name" name="name">  </td>
            <td >  <label class="name"> Name</label> </td>
        </tr>
        <tr >
            <td> <input class="radius" type="password" id="password" name="password">   </td>
            <td > <label class="password">Password</label>  </td>
        </tr>
        <tr >
            <td>   <input class="radius input_date"  type="date" id="date" name="date">   </td>
            <td >  <label class="date">Date of birth</label>  </td>

        </tr>
        <tr >
            <td>   <input class="radius input_date"  type="text" id="login" name="login">   </td>
            <td >  <label class="date">Login</label>  </td>

        </tr>
        </tbody>
    </table>
    <p>
        <label>
            <input type="checkbox" name="terms">
            I agree with privacy policy
        </label>
    </p>
    <p>
        <input TYPE="submit" value="confirm">
    </p>

</form>




</body>
</html>
