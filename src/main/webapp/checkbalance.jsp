<!DOCTYPE html>
<html>
<head>
    <title>Check Balance</title>
</head>
<body>
    <h2>Check Balance</h2>
    <form action="CheckBalanceServlet" method="post">
        <label for="accountNumber">Account Number:</label>
        <input type="number" id="accountNumber" placeholder = "Enter account number" name="accountNumber" required>
        <br>
        <label for = "pin">Pin:</label>
        <input type = "password" id = "pin" name = "pin" required> <br> <br>
        <input type="submit" value="CheckBalance">
    </form>
    <a href="homepage.jsp">Home</a>
</body>
</html>