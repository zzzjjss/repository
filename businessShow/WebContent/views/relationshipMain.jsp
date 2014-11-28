<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-default">
   	<div class="panel-body " style="height: 800px;">
			<div class="row">
				<div class="col-lg-10">
					<div class="input-group">
						<select id="busFrom" style="width: 100px;" onchange="showNodeGraph()">
							<c:forEach items="${nodes}" var="element" >
								<option value="${element.id}">${element.name}</option>
							</c:forEach>
						</select>
						
						<select id="relationship" style="margin-left: 30px;width:100px" onchange="showNodeGraph()">
							<c:forEach items="${relations}" var="element">
								<option value="${element}">${element}</option>
							</c:forEach>
						</select>
						
						<select  multiple id="busTo" style="width: 500px; margin-left: 30px;" >
							<c:forEach items="${nodes}" var="element">
								<option value="${element.id}">${element.name}</option>
							</c:forEach>
						</select>
						<button class="btn btn-default" type="button" style="margin-left: 30px;" onclick="addRelation()">Sure</button>
					</div>
					<div id=allBusiness style="width: 100%; height: 800px;"></div>
				</div>
			</div>
   	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#busFrom").select2();
	$("#relationship").select2();
	$("#busTo").select2();
	showNodeGraph();
});

function addRelation(){
	var busFrom=$("#busFrom").val();
	var busTo=$("#busTo").val();
	if(busTo==null){
		busTo="";
	}
	var relation=$("#relationship").val();
	if(busFrom==""||relation==""){
		alert("please select  busFrom and  relationship");
		return;
	}
	console.log(busFrom+"-->"+busTo+"-->"+relation);
	var url="/businessShow/addRelation.do";
	$.ajax
	(
		{
			type: "POST",
			url: url+"?busFrom="+busFrom+"&busTo="+busTo+"&relationship="+relation,
			cache: false,
			dataType: "text",
			success: 
				function(result)
				{   
					if(result=="ok"){
						showNodeGraph();
					}else{
						alert(result);
					}
								
				},
			error: 
				function(jqXHR, textStatus, errorThrown )
				{
					alert(errorThrown); 
				}
		}
	);
}

function showNodeGraph(){
	var url="/businessShow/getNodeOneDepthRelationGraph.do";
	var busFrom=$("#busFrom").val();
	var relation=$("#relationship").val();
	var  nodeData,relationData;
	$.ajax(
		{
			type: "POST",
			url: url+"?nodeId="+busFrom+"&relation="+relation,
			cache: false,
			dataType: "json",
			async :false,
			success: 
				function(result)
				{   
					nodeData=result.nodes;
					relationData=result.edges;
				},
			error: 
				function(jqXHR, textStatus, errorThrown )
				{
					alert(errorThrown); 
				}
		}
	);
	var  toNodes=new Array();
	
	for(var i=0;i<nodeData.length;i++){
		var nodeId=nodeData[i].id;
		console.log(nodeId);
		if(nodeId!=busFrom){
			console.log(nodeId);
			toNodes.push(nodeId);
		}	
	}
	$("#busTo").select2("val",toNodes);
	
	var container = document.getElementById('allBusiness');
	var data = {
		nodes : nodeData,
		edges:relationData
	};
	var options = {
		width : '100%',
		height : '100%',
		edges : {
			style : "arrow-center"
		},
		hierarchicalLayout: true
	};
	var network = new vis.Network(container, data, options);
	
}
</script>