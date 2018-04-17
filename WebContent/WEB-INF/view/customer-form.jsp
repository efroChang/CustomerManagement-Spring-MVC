<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<header>
	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />

	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/add-customer-style.css" />
</header>
<title>Customer Form</title>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<h3>Save Customer</h3>

         <form:form action="saveCustomer" modelAttribute="customer" method="POST">
         
		      <!-- IMPORTANT! Add a hidden "id" field -->
		      <!-- So it will NOT create a new Customer every time it saves -->
		      <form:hidden path="id" />         
         
				<table>
					<tbody>
						
						<tr>
							<td><label>First Name:</label>
							<td><form:input path="firstName" /></td>
						</tr>
                  <tr>
                     <td><label>Last Name:</label>
                     <td><form:input path="lastName" /></td>
                  </tr>
                  <tr>
                     <td><label>Email:</label>
                     <td><form:input path="email" /></td>
                  </tr>  

                  <tr>
                     <td><label></label>
                     <td><input type="submit" value="Save Customer" class="save" /></td>
                  </tr>                    
                      
					</tbody>
				</table>
				
				<div style="clear; both;"></div>
				
				<p>
				  <a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
				</p>
				
			</form:form>
			
		</div>
	</div>

</body>

</html>