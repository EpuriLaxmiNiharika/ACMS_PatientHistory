package upload_pdf;

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
 * Servlet implementation class ServletPDF_MultipleText
 */
@WebServlet("/ServletPDF_MultipleText")
public class ServletPDF_MultipleText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPDF_MultipleText() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	      contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
	       
	      //Setting the leading
	      contentStream.setLeading(14.5f);

	      //Setting the position for the line
	      contentStream.newLineAtOffset(25, 725);

	      String text1 = "This is an example of adding text to a page in the pdf document.we can add as many lines";
	      String text2 = "as we want like this using the ShowText()  method of the  ContentStream class";

	      //Adding text in the form of string
	      contentStream.showText(text1);
	      contentStream.newLine();
	      contentStream.showText(text2);
	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();

	      //Saving the document
	      //doc.save(new File("C:/PdfBox_Examples/new.pdf"));
	       
	      //Closing the document
	     // doc.save(new File("/home/niharika/mypdf.pdf"));
	      document.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		  PDDocument document = new PDDocument();    
	       
	      //Saving the document
	      document.save("/home/niharika/mypdf.pdf");
	      
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
	      contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
	       
	      //Setting the leading
	      contentStream.setLeading(14.5f);

	      //Setting the position for the line
	      contentStream.newLineAtOffset(25, 725);

	      String text1 = "This is an example of adding text to a page in the pdf document.we can add as many lines";
	      String text2 = "as we want like this using the ShowText()  method of the  ContentStream class";

	      //Adding text in the form of string
	      contentStream.showText(text1);
	      contentStream.newLine();
	      contentStream.showText(text2);
	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();

	      //Saving the document
	      //doc.save(new File("C:/PdfBox_Examples/new.pdf"));
	       
	      //Closing the document
	      document.save(new File("/home/niharika/mypdf.pdf"));
	      
	      document.close();
	      System.out.println("doc closed");
	}

}
