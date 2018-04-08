package com.niharika.createpdf;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream; 
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Servlet implementation class ServletPDF_Text
 */
@WebServlet("/ServletPDF_Text")
public class ServletPDF_Text extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPDF_Text() {
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
	       
	      //Saving the document
	      document.save("/home/niharika/my_doc.pdf");
	      for (int i=0; i<2; i++) {
	          //Creating a blank page 
	          PDPage blankPage = new PDPage();

	          //Adding the blank page to the document
	          document.addPage( blankPage );
	       } 
		
		  
		  PDPage page = document.getPage(0);
	      PDPageContentStream contentStream = new PDPageContentStream(document, page);
	      
	      //Begin the Content stream 
	      contentStream.beginText(); 
	       
	      //Setting the font to the Content stream  
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

	      //Setting the position for the line 
	      contentStream.newLineAtOffset(25, 500);

	      String text = "This is the sample document and we are adding content to it.";

	      //Adding text in the form of string 
	      contentStream.showText(text);      

	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();

	      
	      document.save("/home/niharika/my_doc.pdf");
	         
	      System.out.println("PDF created");  
	    
	      //Closing the document  
	      document.close();
	      System.out.println("doc closed");
	      
		
		//
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
		      document.save("/home/niharika/my_doc.pdf");
		      for (int i=0; i<2; i++) {
		          //Creating a blank page 
		          PDPage blankPage = new PDPage();

		          //Adding the blank page to the document
		          document.addPage( blankPage );
		       } 
		       
		      PDPage page = document.getPage(0);
		      PDPageContentStream contentStream = new PDPageContentStream(document, page);
		      
		      //Begin the Content stream 
		      contentStream.beginText(); 
		       
		      //Setting the font to the Content stream  
		      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

		      //Setting the position for the line 
		      contentStream.newLineAtOffset(25, 500);

		      String text = "This is the sample document and we are adding content to it.";

		      //Adding text in the form of string 
		      contentStream.showText(text);      

		      //Ending the content stream
		      contentStream.endText();

		      System.out.println("Content added");

		      //Closing the content stream
		      contentStream.close();

		      document.save("/home/niharika/my_doc.pdf");
		    
		      //Closing the document  
		      document.close();
		      
		      System.out.println("doc closed");

	}

}
