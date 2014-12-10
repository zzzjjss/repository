<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
	<div class="panel-body">
		<select id="business" onchange="showNodeGraph()">
			<c:forEach items="${nodes}" var="element">
				<option value="${element.id}">${element.name}</option>
			</c:forEach>
		</select>
		<div id="businessGraph" style="width: 100%; height: 800px;"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#business").select2();
	showNodeGraph();
});

function showNodeGraph() {
	var  nodeId=$("#business").val();
	var url="/businessShow/getNodeGraph.do";
	var nodes,edges;
	$.ajax
	(
		{
			type: "POST",
			url: url+"?nodeId="+nodeId,
			cache: false,
			dataType: "json",
			async :false,
			success: 
				function(result)
				{   
					nodes=result.nodes;
					edges=result.edges;
				},
			error: 
				function(jqXHR, textStatus, errorThrown )
				{
					alert(errorThrown); 
				}
		}
	);

	// create a network
	var container = document.getElementById('businessGraph');
	var data = {
		nodes : nodes,
		edges : edges,
	};
	var options = {
		width : '100%',
		height : '100%',
		edges : {
			style : "arrow-center"
		},
		hierarchicalLayout: {
			 enabled:false,
		      levelSeparation: 150,
		      nodeSpacing: 100,
		      direction: "UD",
		      layout: "hubsize"
		}
	};
	var network = new vis.Network(container, data, options);

}
</script>