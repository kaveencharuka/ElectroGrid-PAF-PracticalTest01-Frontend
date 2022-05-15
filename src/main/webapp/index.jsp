<%-- @author IT19990750_K.C.A.Weerarathna
	@email it19990750@my.sliit.lk --%>

<%@ page import="com.example.demo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
          crossorigin="anonymous">
    <script src="Components/jquery-3.6.0.js" type="text/javascript"></script>
    <script src="Components/user.js" type="text/javascript"></script>
    <title>Generate Users</title>
</head>
<body>
<div class="container" >
    <h1 >Generate Users</h1>
    <hr>
    <div class="row">
        <div class="col-3 frm">
            <form id="formUser" name="formUser" action="" method="post">

                Account Number:
                    <input id="accountNo" name="accountNo" type="text" class="form-control form-control-sm"> <br>

                User Name:
                    <input id="userName" name="userName" type="text" class="form-control form-control-sm"> <br>

                Email:
                    <input id="email" name="email" type="text" class="form-control form-control-sm"> <br>

                Mobile Number:
                    <input id="mobileNo" name="mobileNo" type="text" class="form-control form-control-sm"> <br>

                Address:
                    <input id="address" name="address" type="text" class="form-control form-control-sm"> <br>

                Units:
                    <input id="units" name="units" type="text" class="form-control form-control-sm"> <br>


                    <input style="width: 50%;" id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">

                    <input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
            </form>
            <div id="alertSuccess" class="alert alert-success"></div>
            <div id="alertError" class="alert alert-danger"></div>
            <br>
        </div>
        <div class="col-9" id="divUsersGrid">
            <%
                User userObj = new User();
                out.print(userObj.readUsers());
            %>
        </div>
    </div>
</div>
</body>
</html>