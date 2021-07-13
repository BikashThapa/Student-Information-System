<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Form</title>
<%@ include file="header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="row col-md-1o">
			<div class="col-md-2"></div>
			<div class="col-md-8 jumbotron">
				<fieldset>
					<legend>Student Registration Form</legend>
					<form action="StudentController" method="post"
						enctype="multipart/form-data" class="form-horizontal">
						<span><input type="hidden" name="id" value="${student.id}"></span>
						<div class="form-group">
							<label>Student Name</label> <input type="text" name="sName"
								value="${student.studentName}" class="form-control">
						</div>
						<div class="form-group">
							<label>College Name</label> <input type="text" name="cName"
								value="${student.collegeName}" class="form-control">
						</div>
						<div class="form-group">
							<label>Email</label> <input type="email" name="email"
								value="${student.email}" class="form-control">
						</div>
						<div class="form-group">
							<label>Roll</label> <input type="number" name="roll"
								value="${student.roll}" class="form-control">
						</div>
						<div>
							<label>DOB</label> <input type="date" name="dob"
								value="${student.dob}">
						</div>
						<div class="form-group">
							<label>Gender</label> <input type="radio" name="gender"
								value="Male" ${student.gender=='Male'?'checked':''}>Male
							<input type="radio" name="gender" value="Female"
								${student.gender=='Female'?'checked':''}>Female
						</div>
						<div class="form-group">
							<label>Department</label> <select name="department"
								class="form-control">
								<option value="IT" ${student.departments=='IT'?'selected':''}>IT</option>
								<option value="Computer"
									${student.departments=='Computer'?'selected':''}>Computer</option>
								<option value="Software"
									${student.departments=='Software'?'selected':''}>Software</option>
								<option value="Electronics"
									${student.departments=='Electronics'?'selected':''}>Electronics</option>
							</select>
						</div>
						<div class="form-group">
							<label>Subjects</label> <input type="checkbox" name="subject"
								value="Java"
								<c:if  test="${fn:contains(student.subject,'Java')}">checked</c:if>>Java
							<input type="checkbox" name="subject" value="PHP"
								test="${fn:contains(student.subject,'PHP')}">PHP
						</div>
						<div class="form-group">
							<label>Upload Photo</label> <input type="file" name="photo"
								class="form-control">
						</div>
						<div class="form-group">

							<input type="submit" class="btn btn-success" value="Submit">
						</div>
					</form>
				</fieldset>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>