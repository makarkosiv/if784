<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

    <head>
        <title>MY TASK</title>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: center;
    padding: 8px;
}

table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>
<form method="post" action="/save">
  <input type="hidden" name="id" value="${lists.id}"><br/>
  Name:<br>
  <input type="text" name="name" value="${lists.name}">
  <br>
  Salary:<br>
  <input type="number" name="salary" value="${lists.salary}">
  <br><br>
  <input type="submit" value="Save">
</form>


</body>
</html>