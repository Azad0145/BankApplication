
<!DOCTYPE html>
<html>
<style>
h2 {text-align: center;}
</style>
<body>
    <h2> Welcome to Hello Bank</h2>
    <br>
    <h3>Login</h3>
    <form action="LoginServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>
        <label for="pin">PIN:</label>
        <input type="password" id="pin" name="pin" required><br>
        <input type="submit" value="Login">
    </form>
    <br>
     
     <p>Don't have Account? <a href = "signup.jsp" > Create Account </a></p>
</body>
</html>