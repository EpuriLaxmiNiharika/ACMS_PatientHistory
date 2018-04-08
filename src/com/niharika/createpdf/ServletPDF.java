package com.niharika.createpdf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException; 
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
/**
 * Servlet implementation class ServletPDF
 */
@WebServlet("/ServletPDF")
public class ServletPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		  PDDocument document = new PDDocument();    
		  for (int i=0; i<2; i++) {
	          //Creating a blank page 
	          PDPage blankPage = new PDPage();

	          //Adding the blank page to the document
	          document.addPage( blankPage );
	       } 
	      //Saving the document
	      document.save("/home/niharika/my_doc.pdf");
	         
	      System.out.println("PDF created");  
	    
	      //Closing the document  
	      document.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		  
		
		       
		      //Creating PDF document object 
		      PDDocument document = new PDDocument();    
		       
		      //Saving the document
		      document.save("my_doc.pdf");
		      for (int i=0; i<2; i++) {
		          //Creating a blank page 
		          PDPage blankPage = new PDPage();

		          //Adding the blank page to the document
		          document.addPage( blankPage );
		       } 
		        
		      document.save("/home/niharika/my_doc.pdf");
		    
		      //Closing the document  
		      document.close();

		  
	}

}
