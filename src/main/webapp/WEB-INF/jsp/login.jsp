<hr/>
<html>
<head>
<title>MOVIES API</title>

</head>
<body>
<h1>LOGIN</h1>
${SPRING_SECURITY_LAST_EXCEPTION.message}
<form action="login" method='POST'>
<table>
<tr>
<td> USER: </td>
<td><input type='text' name='username' value=''></td>
</tr>
<tr>
<td>Password:</td>
<td><input type='password' name='password'/></td>
</tr>
<tr>
<td><input name="submit" type="submit" value="submit" /></td>
<tr>
<table>
</form>
</body>
</html>
