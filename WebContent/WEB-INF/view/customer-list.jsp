<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<header>

   <link type="text/css" 
         rel="stylesheet" 
         href="${pageContext.request.contextPath}/resources/css/style.css" />

</header>
<title>List of Customers</title>

<body>

   <div id="wrapper">
      <div id="header">
         <h2>CRM - Customer Relationship Manager</h2>
      </div>
   </div>
   
   <div id="container">
      <div id="content">
         
         <!-- Search Contact Form -->
         <form:form action="search" method="GET">
            
            <input type="text" name="searchCriteria" />
            
            <input type="submit" value="Search" class="add-button" />
         
         </form:form>
         
         
         <table>
            <tr>
               <th>First Name</th>
               <th>Last Name</th>
               <th>Email</th>
               <th>Action</th>
            </tr>
            
            <!-- Loop through the Customer list -->
            <c:forEach var="tempCustomer" items="${ customers }">
            
               <!-- Constructing the UPDATE URL -->
               <c:url var="updateLink" value="/customer/showFormForUpdate">
                  <c:param name="customerId" value="${ tempCustomer.id }"></c:param>
               </c:url>
               
               <!-- Constructing the DELETE URL -->
               <c:url var="deleteLink" value="/customer/delete">
                  <c:param name="customerId" value="${ tempCustomer.id }"></c:param>
               </c:url>               
               
               <tr>
                  <td>${ tempCustomer.firstName }</td>
                  <td>${ tempCustomer.lastName }</td>
                  <td>${ tempCustomer.email }</td>
                  
                  <!-- The Update Link -->
                  <td>
                     <a href="${ updateLink }">Update</a>
                     |
                     <a href="${ deleteLink }" 
                        onClick="if (!(confirm('Are you sure you want to remove this customer?'))) return false;">Delete</a>
                  </td>
                  
               </tr>
            
            </c:forEach>         
         </table>
         
         <br>
         
         <!-- Add customer button -->
         <input type="submit" value="Add Customer" class="add-button" 
                onclick="window.location.href='showFormForAdd'; return false;" />
      
      </div>   
   </div>
   
</body>

</html>