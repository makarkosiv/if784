<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>

    <head>
        <title>MY TASK</title>
<style>
table {
    border: 1px solid black;
    font-family: arial, sans-serif;
    width: 50%;
}

td, th {
    border: 1px solid black;
    text-align: center;
    padding: 8px;
}

</style>
</head>
<body>

<table>
<caption>Salary List</caption>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Salary</th>
    <th>Action</th>
  </tr>
  <c:forEach var = "list" items = "${lists}">
  <tr>
    <td>${list.id}</td>
    <td>${list.name}</td>
    <td>${list.salary}</td>
    <td>
        <a href="/view/${list.id}">View</a>
        <a href="/delete/${list.id}">Delete</a>
        <a href="/update/${list.id}">Update</a>
    </td>
  </tr>
  </c:forEach>
</table>
<hr>
<form method="post" action="/save">
  <input type="hidden" name="id" value="">
  Name:<br>
  <input type="text" name="name">
  <br>
  Salary:<br>
  <input type="number" name="salary">
  <br><br>
  <input type="submit" value="Add">
</form>


</body>
</html>