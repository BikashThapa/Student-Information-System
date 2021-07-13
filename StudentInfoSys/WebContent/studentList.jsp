<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student List</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<a href="StudentController?actions=student_new">New Students</a>
	<c:if test="${!empty students}">


		<table class="table table-striped">
			<thead>

				<tr>
					<th>SN</th>
					<th>ID</th>
					<th>Student Name</th>
					<th>College Name</th>
					<th>Roll</th>
					<th>Gender</th>
					<th>Email</th>
					<th>Subject</th>
					<th>DOB</th>
					<th>Departments</th>
					<th>Photo</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students }" var="s" varStatus="d">

					<tr>

						<td><c:out value="${d.count}" /></td>
						<td><c:out value="${s.id}"></c:out></td>
						<td><c:out value="${s.studentName}"></c:out></td>
						<td><c:out value="${s.collegeName}"></c:out></td>
						<td><c:out value="${s.roll}"></c:out></td>
						<td><c:out value="${s.gender}"></c:out></td>
						<td><c:out value="${s.email}"></c:out></td>
						<td><c:out value="${s.subject}"></c:out></td>
						<td><c:out value="${s.dob}"></c:out></td>
						<td><c:out value="${s.departments}"></c:out></td>
						<td><img alt="pic" src="ImageDisplay?studentId=${s.id}" width="50px" height="50px"></td>

						<td><a
							href="StudentController?actions=student_edit&id=${s.id }">Edit/</a>
							<a href="StudentController?actions=student_delete&id=${s.id }">Delete</a>
						</td>
					</tr>

				</c:forEach>

			</tbody>

		</table>

	</c:if>

</body>
</html>