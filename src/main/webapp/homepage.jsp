<!DOCTYPE html>
<html>
<style>
h2 {text-align: center;}
</style>
<body>
    <h2><%= session.getAttribute("username") %>, Welcome to Hello Bank</h2>
    <p>Account Number: <%= session.getAttribute("accountNumber") %></p>

         <h3> Check Balance </h3>
         <a href="checkbalance.jsp"> Check Balance </a>
   
        <h3>Transfer Money</h3>
        <a href="transfer.jsp">Go to Transfer Page</a>
   

  
        <h3>Withdraw Money</h3>
        <a href="withdraw.jsp">Go to Withdraw Page</a>
    
</body>
</html>

