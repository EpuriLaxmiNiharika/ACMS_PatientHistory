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
import java.util.Random;
/**
 * Servlet implementation class ServletPDF_HTML_PAGE
 */
@WebServlet("/ServletPDF_HTML_PAGE")
public class ServletPDF_HTML_PAGE extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPDF_HTML_PAGE() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println("hey there");
		String patient_id = request.getParameter("patient_id");
		String medicines = request.getParameter("Medication");
		String alergies = request.getParameter("alergies");
		String condition=request.getParameter("select_condition");
		String tobacco = request.getParameter("tobacco");
		String alchohol = request.getParameter("Alchohol");
		//String condition=request.getParameter("symptom1");
		String[] check_box_symp = request.getParameterValues("symp");

		String[] symptoms = {"symptom1","symptom2","symptom3","symptom4","symptom5"};

		String [] symptom1_conditions = {"Lower back pain","Inability to stand","Unable to bend","Unable to walk","Difficulty to sit"};
		String [] symptom2_conditions = {"Muscle pain","Rashes","Fever","Nausea","Loss of appetite"};
		String [] symptom3_conditions =  {"Sudden weightloss","Always thirsty","Heavy breathing","Slow healing cuts","Nausea"};
		String [] symptom4_conditions =  {"TenderScalp","Mild Headache","Depressed","Dizziness","Nasal congestion"};
		String [] symptom5_conditions =  {"Head ache","Fever","Cold","Cough","Body pains"};

		String selectedSymps [] = {};
		//String labels[] = {request.getParameter("symptom1"),request.getParameter("symptom2"),request.getParameter("symptom3"),request.getParameter("symptom4"),request.getParameter("symptom5")};
		if(condition.equals("Backpain")) {
			selectedSymps = symptom1_conditions.clone();
		}
		
		else if(condition.equals("Thyphoid")) {
			selectedSymps = symptom2_conditions.clone();
		}
		else if(condition.equals("Diabetes")) {
			selectedSymps = symptom3_conditions.clone();
		}
		else if(condition.equals("Migrane")) {
			selectedSymps = symptom4_conditions.clone();
		}
		else if(condition.equals("General")){
			selectedSymps = symptom5_conditions.clone();
		}
		
		
		int checked_symps ;
		
		String symptoms_patient = "";
		
		for(int i = 0 ; i< check_box_symp.length -1;i++) {
			checked_symps = Integer.parseInt(check_box_symp[i]);
			//System.out.println(checked_symps);
			symptoms_patient = symptoms_patient + selectedSymps[checked_symps] +" , ";
		}
		if(check_box_symp.length>0)
			{
			checked_symps = Integer.parseInt(check_box_symp[check_box_symp.length-1]);
			symptoms_patient  = symptoms_patient + selectedSymps[checked_symps];
			}
		
		String medications_taken = "none";
		if(medicines.equals("Yes")){
			medications_taken = request.getParameter("text_medication");
		}
		
		String patient_alergies = "none";
		
		if(alergies.equals("Yes")){
			patient_alergies = request.getParameter("text_alergies");
		}
		
		String patient_alc = "none";
		
		if(alchohol.equals("Yes_alc")) {
			patient_alc = "Frequently";
		}
		else if(alchohol.equals("No_alc")) {
			patient_alc = "No";
		}
		else if(alchohol.equals("Week_alc")) {
			patient_alc = "Weekly";
		}
		else if(alchohol.equals("Month_alc")) {
			patient_alc = "Monthly";
		}
		
	/*	System.out.println(patient_id);
		
		System.out.println(condition);
		
		System.out.println(symptoms_patient);
		
		System.out.println(medicines);
		
		System.out.println(medications_taken);
		
		System.out.println(patient_alergies);
		
		System.out.println(tobacco);
		
		System.out.println(patient_alc);
		
		*/
		
		  PDDocument document = new PDDocument();    
	       
	      //Saving the document
		  String file_name = "/home/niharika/"+patient_id+getAlphaNumeric(10);
	      document.save(file_name);
	      for (int i=0; i<1; i++) {
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

	   //   String text1 = "This is an example of adding text to a page in the pdf document.we can add as many lines";
	    //  String text2 = "as we want like this using the ShowText()  method of the  ContentStream class";

	      //Adding text in the form of string
	      contentStream.showText("Patient Id:   " +patient_id);
	      contentStream.newLine();
	      contentStream.newLine();
	      contentStream.showText("Condition:   " + condition);
	      contentStream.newLine();
	      contentStream.newLine();
	      
	      contentStream.showText("Symptoms Observed:   " + symptoms_patient);
	      contentStream.newLine();
	      contentStream.newLine();
	      
	      contentStream.showText("Any Medication?  " + medications_taken);
	      contentStream.newLine();
	      contentStream.newLine();
	      
	      contentStream.showText("Any Alergies Found?  " + patient_alergies);
	      contentStream.newLine();
	      contentStream.newLine();
	      
	      contentStream.showText("Consumption of Alchohol?  " + patient_alc);
	      contentStream.newLine();
	      contentStream.newLine();
	      
	      contentStream.showText("Consumption of Tobacco? " + tobacco);
	      contentStream.newLine();
	      contentStream.newLine();
	      
	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();

	      //Saving the document
	      //doc.save(new File("C:/PdfBox_Examples/new.pdf"));
	       
	      //Closing the document
	      document.save(new File(file_name));
	      
	      document.close();
	      System.out.println("doc closed");
	      
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//String p=request.getParameter("login_pass");
	}
	
	  public String getAlphaNumeric(int len) {
		    char[] ch = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
		        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
		        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
		        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
		        'w', 'x', 'y', 'z' };
		    
		    char[] c=new char[len];
		    Random random=new Random();
		    for (int i = 0; i < len; i++) {
		      c[i]=ch[random.nextInt(ch.length)];
		    }
		    
		    return new String(c);
		  }


}
