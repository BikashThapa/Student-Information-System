package com.Bikash.studentinfosys.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bikash.studentinfosys.dao.*;

@WebServlet("/ImageDisplay")
public class ImageDisplayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	studentDao studentDao = new studenDaoImpl();
	private static final int DEFAULT_BUFFER_SIZE = 1024;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String studentId = request.getParameter("studentId");
		String imagePath = studentDao.getStudentById(Integer.parseInt(studentId)).getImage_url();

		File file = new File(imagePath);
		String contentType = getServletContext().getMimeType(file.getName());
		if (contentType == null) {

			contentType = "application/octet-stream";

		}
		response.setContentType(contentType);
		response.setBufferSize(DEFAULT_BUFFER_SIZE);

		response.setHeader("content-length", String.valueOf(file.length()));
		response.setHeader("contact-Deposition", "attachment; filename=\"" + file.getName() + "\"");

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));

		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

		int len;
		byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];

		while ((len = in.read(bytes)) != -1) {
			out.write(bytes, 0, len);

		}
		in.close();
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
