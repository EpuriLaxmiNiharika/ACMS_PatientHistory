
<%@ page import="java.io.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%int[] africa_jsp = (int[])request.getAttribute("array_val"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
</head>
<body>


<div class="wrapper">
      <h2>Variations of Sugar levels of person over period</h2>

      <canvas id="myChart" width="1600" height="900"></canvas>
    </div>

    <script>
    
    var africa = new Array();
    	
    	<%
    	for(int j = 0; j< africa_jsp.length;j++)
    	{%>
    	
    		africa[<%=j%>] = <%=africa_jsp[j] %>;
    	<%}%>
    	
    var years = ["visit1","visit2","visit3","visit4","visit5","visit6"];
	// For drawing the lines
	var asia = [282,350,411,502,635,809,947,1402,3700,5267];
	var europe = [168,170,178,190,203,276,408,547,675,734];
	var latinAmerica = [40,20,10,16,24,38,74,167,508,784];
	var northAmerica = [6,3,2,2,7,26,82,172,312,433];

	var ctx = document.getElementById("myChart");
	var myChart = new Chart(ctx, {
	  type: 'line',
	  data: {
	    labels: years,
	    datasets: [
	      { label: "sugar levels",
	        data: africa
	      }
	    ]
	  }
	});
   
  
   
    </script>

</body>

</html>
