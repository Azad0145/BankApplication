<!DOCTYPE html>
<html>
<head>
    <title>Transfer Money</title>
</head>
<body>
    <h2>Transfer Money</h2>
    <form action="TransferServlet" method="post">
        
        <label for="ReceiverAccountNumber">Receiver's Account Number:</label>
        <input type="number" id="ReceiverAccountNumber" name="ReceiverAccountNumber" required>
        <br>
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required>
        <br>
        <label for = "pin">Pin:</label>
        <input type = "password" id = "pin" name = "pin" required> <br> <br>
        <input type="submit" value="Transfer">
    </form>
    <a href="homepage.jsp">Home</a>
</body>
</html>
