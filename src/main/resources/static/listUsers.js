$(document).ready(
    function() {
        $.ajax({
            type: "GET",
            url: "rest/usersRest",
            dataType: "json",
            success: function (result) {
                $('#getResultDiv').empty();
                $('#getResultDiv').append(
                    "<th>ID</th>\n" +
                    "<th>Name</th>\n" +
                    "<th>Age</th>" +
                    "<th>Edit</th>"
                )
                $.each(result,
                    function (i, user) {
                        var userItem = "<tr id=row"+ i + ">" +
                                        "<td id=idLabel" + i + ">" + user.id + "</td>" +
                                        "<td id=nameLabel" + i + ">" + user.name + "</td>" +
                                        "<td id=ageLabel" + i + ">" + user.age + "</td>" +
                                        "<td> <button type='button' class='btn btn-primary' data-toggle='modal' data-target=#model" + i + ">" +
                                                "Edit" +
                                            " </button>" +
                                        "<form id=userEdit"+ i + ">" +
                                                "<div class='modal fade' id=model"+ i + " tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>\n" +
                                                    "<div class='modal-dialog modal-dialog-centered'>\n" +
                                                        "<div class='modal-content'>\n" +
                                                            "<div class='modal-header'>\n" +
                                                                "<h5 class='modal-title' id='exampleModalLongTitle'>Edit</h5>\n" +
                                                                "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n" +
                                                                    "<span aria-hidden='true'>&times;</span>\n" +
                                                                "</button>\n" +
                                                            "</div>\n" +
                                                            "<div class='modal-body'>\n" +
                                                                "<div class='container h-100 text-center font-weight-bold'>\n" +
                                                                    "<div class='row h-100 justify-content-center align-items-center'>\n" +
                                                                        "<div class='col-6'>\n" +
                                                                            "<input type='hidden' name='password' id=userPassword"+i+" value=" + user.password + ">\n" +
                                                                            "<div class='form-group'>\n" +
                                                                                "<label for='id-label'>ID</label>\n" +
                                                                                "<input type='text' name='id'  id=userId"+i+" value=" + user.id +" class='form-control' placeholder='id' readonly>\n" +
                                                                            "</div>\n" +
                                                                            "\n" +
                                                                            "<div class='form-group'>\n" +
                                                                                "<label for='username-label'>Login</label>\n" +
                                                                                "<input type='text'id=userName"+i+" name='name' value=" + user.name + " class='form-control' placeholder='User Name' required>\n" +
                                                                            "</div>\n" +
                                                                            "\n" +
                                                                            "<div class='form-group'>\n" +
                                                                                "<label for='age-label'>Age</label>\n" +
                                                                                "<input type='number' id=userAge"+i+" name='age' value=" + user.age  + " class='form-control' placeholder='Age' required>\n" +
                                                                            "</div>\n" +
                                                                        "</div>\n" +
                                                                    "</div>\n" +
                                                                "</div>\n" +
                                                            "</div>\n" +
                                                        "<div class='modal-footer'>\n" +
                                                        "<button class='btn btn-secondary' data-dismiss='modal'>Закрыть</button>\n" +
                                                        "<button type='submit' class='btn btn-primary'>Edit user</button>\n" +
                                                    "</div>\n" +
                                                "</div>\n" +
                                            "</form>" +
                                         "</td>";
                        $('#getResultDiv').append(
                            userItem);
                        $("#userEdit"+i).submit(function(event) {
                            event.preventDefault();
                            var formData = {
                                id : $("#userId"+i).val(),
                                name : $("#userName"+i).val(),
                                password : $("#userPassword"+i).val(),
                                age : $("#userAge"+i).val()
                            }

                            // DO POST
                            $.ajax({
                                type : "POST",
                                contentType : "application/json",
                                url : "rest/editRest",
                                data : JSON.stringify(formData),
                                dataType : 'json',
                                success : function (user) {
                                    $("#model"+i).modal('hide');
                                    $('#nameLabel'+i).html(user.name);
                                    $('#ageLabel'+i).html(user.age);
                                }
                            });
                        });
                    });
                console.log("Success: ", result);
            }
        });
    })
