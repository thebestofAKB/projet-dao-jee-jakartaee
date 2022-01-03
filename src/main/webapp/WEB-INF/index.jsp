<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${ sessionScope.title }</title>
</head>
<body>
	
	<c:if test="${ !empty erreur }">
		<p style="color:red"> <c:out value="${ erreur }" /> </p>
	</c:if>
	
	<form method="post" action="index">
		<p>
			<label for="prenom">Prenom:</label>
			<input type="text" name="prenom" id="prenom" />
		</p>
		
		<p>
			<label for="nom">Nom:</label>
			<input type="text" name="nom" id="nom" />
		</p>
		
		<button type="submit" name="submit">Envoyer</button>
	</form>
	
	<ul>
		<c:forEach var="utilisateur" items="${ utilisateurs }">
			<li> <c:out value="${ utilisateur.nom }" /> <c:out value="${ utilisateur.prenom }" /> </li>
		</c:forEach>
	</ul>
</body>
</html>