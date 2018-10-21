
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