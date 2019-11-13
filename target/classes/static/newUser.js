$(document).ready(
    function() {


        $("#newUserId").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });
        
        // $("#nav-profile-tab").active(function () {
        //
        // })


        function ajaxPost() {
            // PREPARE FORM DATA
            var formData = {
                name : $("#newUserName").val(),
                age : $("#newUserAge").val(),
                password : $("#newUserPassword").val()
            }

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "newRest",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(user) {
                    $('#newUserId')[0].reset();
                    $("#meassage-label").html(
                        "User was added successfully");
                    var userItem =
                        "<tr id=row"+ user.id + ">" +
                        "<td id=idLabel" + user.id + ">" + user.id + "</td>" +
                        "<td id=nameLabel" + user.id + ">" + user.name + "</td>" +
                        "<td id=ageLabel" + user.id + ">" + user.age + "</td>" +
                        "<td> <button type='button' class='btn btn-primary' data-toggle='modal' data-target=#model" + user.id + ">" + "Edit" + " </button>" +
                        "<form id=userEdit"+ user.id + ">" +
                            "<div class='modal fade' id=model"+ user.id + " tabindex='-1' role='dialog' aria-labelledby='exampleModalCenterTitle' aria-hidden='true'>\n" +
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
                                                    "<input type='hidden' name='password' id=userPassword"+user.id+" value=" + user.password + ">\n" +
                                                    "<div class='form-group'>\n" +
                                                        "<label for='id-label'>ID</label>\n" +
                                                        "<input type='text' name='id'  id=userId"+user.id+" value=" + user.id +" class='form-control' placeholder='id' readonly>\n" +
                                                    "</div>\n" +
                                                    "<div class='form-group'>\n" +
                                                        "<label for='username-label'>Login</label>\n" +
                                                        "<input type='text'id=userName"+user.id+" name='name' value=" + user.name + " class='form-control' placeholder='User Name' required>\n" +
                                                    "</div>\n" +
                                                    "<div class='form-group'>\n" +
                                                        "<label for='age-label'>Age</label>\n" +
                                                        "<input type='number' id=userAge"+user.id+" name='age' value=" + user.age  + " class='form-control' placeholder='Age' required>\n" +
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
                    $("#userEdit"+user.id).submit(function(event) {
                        event.preventDefault();
                        var formData = {
                            id : $("#userId"+user.id).val(),
                            name : $("#userName"+user.id).val(),
                            password : $("#userPassword"+user.id).val(),
                            age : $("#userAge"+user.id).val()
                        }

                        // DO POST
                        $.ajax({
                            type : "POST",
                            contentType : "application/json",
                            url : "editRest",
                            data : JSON.stringify(formData),
                            dataType : 'json',
                            success : function (user) {
                                $("#model"+user.id).modal('hide');
                                $('#nameLabel'+user.id).html(user.name);
                                $('#ageLabel'+user.id).html(user.age);
                            }
                        });
                    });
                },
                error : function () {
                    $("#meassage-label").html(
                        "This user exists!");
                }
            });
        }
    })
