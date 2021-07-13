package com.Bikash.studentinfosys.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Bikash.studentinfosys.dao.*;
import com.Bikash.studentinfosys.dto.Student;
import com.Bikash.studentinfosys.util.ImageUtil;

@WebServlet("/StudentController")
@MultipartConfig
public class StudentController extends HttpServlet {
	studentDao dao = new studenDaoImpl();
	private static final long serialVersionUID = 1L;
	private static final String STUDENT_FORM = "Form.jsp";
	private static final String STUDENT_LIST = "studentList.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("actions");
		String forward = "";
		if (action.equals("student_new")) {

			forward = STUDENT_FORM;
		} else if (action.equals("student_edit")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("student", dao.getStudentById(id));
			forward = STUDENT_FORM;

		} else if (action.equals("student_delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			dao.deleteStudentInfo(id);
			request.setAttribute("students", dao.getAllstudentInfo());
			forward = STUDENT_LIST;

		} else if (action.equals("student_list")) {
			request.setAttribute("students", dao.getAllstudentInfo());
			forward = STUDENT_LIST;
		}
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Student student = new Student();
		student.setStudentName(request.getParameter("sName"));
		student.setCollegeName(request.getParameter("cName"));
		student.setRoll(Integer.parseInt(request.getParameter("roll")));
		student.setEmail(request.getParameter("email"));
		student.setDepartments(request.getParameter("department"));
		student.setGender(request.getParameter("gender"));

		// For date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(request.getParameter("dob"));
			student.setDob(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// For Subjects
		String[] subjects = request.getParameterValues("subject");
		String subject = "";
		for (String sub : subjects) {

			subject = subject + sub + "/";
		}
		student.setSubject(subject);

		String studentId = request.getParameter("id");

		// for imageUrl
		Part part = request.getPart("photo");
		String fileName = ImageUtil.getFileName(part);

		String imageUrl="";

		if (!fileName.isEmpty()) {
			imageUrl = ImageUtil.writeImageToFile(part, ImageUtil.Image_Upload_PAth+fileName);

		} else {
			int id = Integer.parseInt(studentId);
			imageUrl = dao.getStudentById(id).getImage_url();

		}

		student.setImage_url(imageUrl);

		if (studentId == null || studentId.isEmpty()) {

			dao.savestudent(student);
		} else {
			student.setId(Integer.parseInt(studentId));
			dao.updatestudent(student);
		}

		response.sendRedirect("StudentController?actions=student_list");
	}

}