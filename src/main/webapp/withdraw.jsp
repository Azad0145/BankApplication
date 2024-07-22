<!DOCTYPE html>
<html>

<body>
    <h2>Withdraw Money</h2>
    <form action="WithdrawServlet" method="post">
        <label for="withdrawAmount">Amount:</label>
        <input type="number" id="withdrawAmount" placeholder = "Enter withdraw amount" name="withdrawAmount" required>
        <br>
        <label for = "pin">Pin:</label>
        <input type = "password" id = "pin" name = "pin" required> <br> <br>
        <input type="submit" value="Withdraw">
    </form>
    <a href="homepage.jsp">Home</a>
</body>
</html>
