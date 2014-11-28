<div class="panel panel-default">
	<div class="panel-body " style="height: 800px;">
			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" class="form-control" id="business"> 
						<span  class="input-group-btn">
							<button class="btn btn-default" type="button" onclick="addBusiness()">Add</button>
						</span>
					</div>
					<div id=allBusiness style="width: 100%; height: 800px;"></div>
				</div>
			</div>
	</div>
</div>
<script>
$(document).ready(function(){
	showAllBusiness();
});
function showAllBusiness(){
	var url="/businessShow/findAllNodes.do";
	var  nodeData="a",relationData="b";
	$.ajax(
		{
			type: "POST",
			url: url,
			cache: false,
			dataType: "json",
			async :false,
			success: 
				function(nodes)
				{   
					nodeData=nodes;
				},
			error: 
				function(jqXHR, textStatus, errorThrown )
				{
					alert(errorThrown); 
				}
		}
	);
	var getRela="/businessShow/findAllRelationship.do";
	$.ajax(
		{
			type: "POST",
			url: getRela,
			cache: false,
			dataType: "json",
			async :false,
			success: 
				function(relations)
				{   
					relationData=relations;
				},
			error: 
				function(jqXHR, textStatus, errorThrown )
				{
					alert(errorThrown); 
				}
		}
	);
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
		}
	};
	var network = new vis.Network(container, data, options);
	
}

	function addBusiness(){
		var bus=$("#business").val();
		var url="/businessShow/addBusiness.do";
		$.ajax
		(
			{
				type: "POST",
				url: url+"?business="+bus,
				cache: false,
				dataType: "text",
				success: 
					function(result)
					{   
						if(result=="ok"){
							showAllBusiness();
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
</script>