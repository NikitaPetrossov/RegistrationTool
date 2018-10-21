
<html>
<head>

       <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript" charset="utf-8"></script>


        <link href="/static.css/usersCss.css" rel="stylesheet">

       <title>Title</title>

</head>
<body>
<noscript>Turn on JavaScript!</noscript>

<script>

function loadData() {
    $.ajax({
        url: '/crud',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function  (result) {
            var table = '<thead> <tr> <td> № </td> <td>  LastName </td> <td> Name </td> <td> Password </td>' +
                    ' <td> Date of Birth </td> <td>  Login </td> <td> Role </td> <td> State </td> </tr> </thead> <tbody>';

            $.each(result, function(idx, user){

//Outer .each loop is for traversing the JSON rows
                table += '<tr>';

                //Inner .each loop is for traversing JSON columns
                $.each(user, function(key, value){
                    var newValue =  value + "&emsp;";
                    table += '<td>' + newValue + '</td>';
                });
                table += '</tr>';
            });

            table += '</tbody>';

            $('#table').html(table);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('Hey, something went wrong because: ' + errorThrown);


        }
    });


}
function loadDataForOne() {
    $.get( "/findUser", {id : $("#userId").val()}, function( user ) {
        function userToString(user) {
            return user.lastName + " " + user.name;
        }

        // $( ".result" ).html( data );
      $("#userContent").html(userToString(user));
    });

}
function updateUser(){
    var id = $("#updateId").val();
    var login = $("#updateLogin").val();
    var password = $("#updatePassword").val();
    $.post("/updateUser",{id : id, login : login, password : password}, function (result) {
        alert(result);
    });
}
</script>



<iframe src="//wikiroutes.info/widget?t=4&c=313" frameborder="0" allowfullscreen="" scrolling="no" width="800" height="500">

</iframe>
    <div style="font-size: 14px; line-height: 18px; text-align: right;">
    <a title="Wikiroutes" href="https://wikiroutes.info/" style="color: #f86433;">Маршруты городского транспорта на сайте wikiroutes.info</a>
    </div>



<div>
<input type="submit"
       onclick="location.href = '/registration'" value='User registration'>
</div>
<div>
    <p>

    </p>
</div>
<div>
<input type="submit" value="show all users" onclick="loadData()">
</div>
<div>
    <p>

    </p>
</div>

<div id="table">
</div>


<form  method="post"  action="/delete">
    <p>
        <label for="deletingId">Id of deleting user
            <input type="text"   id="deletingId" name="deletingId" />
        </label>
    </p>

    <div>

        <input type="submit" value="delete">

    </div>

</form>


    <p>
        <label for="userId">Id of searching user
            <input  type="text"   id="userId" name="userId" />
        </label>
    </p>

<input type="submit" value="show one user" onclick="loadDataForOne()">

<div id="userContent">

</div>

    <p>
        <label for="updateId"> Id of updating user &emsp;&emsp;
            <input  type="text"   id="updateId" name="updateId" />
        </label>
    </p>

<p>
    <label for="updateLogin"> Login of updating user &emsp;
        <input  type="text"   id="updateLogin" name="updateLogin" />
    </label>
</p>

<p>
    <label for="updatePassword">Password of updating user
        <input  type="text"   id="updatePassword" name="updatePassword" />
    </label>
</p>


<input type="submit" value="update user" onclick="updateUser()">





<p>


</p>
<script src="/static.js/loadData.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
