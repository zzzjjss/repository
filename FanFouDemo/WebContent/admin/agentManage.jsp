<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<link href="../flexigrid/css/flexigrid.css" rel="stylesheet">
<link href="../flexigrid/css/style.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../flexigrid/js/flexigrid.js"></script>
</head>	
<style>
body {
	padding-top: 25px;
	
}
.flexigrid div.fbutton .add {
	background: url(images/add.png) no-repeat center left;
}

.flexigrid div.fbutton .delete {
	background: url(images/close.png) no-repeat center left;
}
</style>
<body>


<table class="flexme" style="display: none"></table>

</body>


<script type="text/javascript">




$(".flexme").flexigrid({
    url : 'GetAllAgent',
    dataType : 'json',
    colModel : [ {
        display : '代理名称',
        name : 'agentName',
        width : 100,
        sortable : true,
        align : 'left'
        }, {
            display : '配送地址',
            name : 'address',
            width : 280,
            sortable : true,
            align : 'left'
        }, {
            display : '账户余额',
            name : 'balance',
            width : 100,
            sortable : true,
            align : 'left'
        },{
            display : '提成金额',
            name : 'backMoney',
            width : 100,
            sortable : true,
            align : 'left'
        }, {
            display : '配送员',
            name : 'deliverymanName',
            width : 100,
            sortable : true,
            align : 'left'
        }, {
            display : '客户数',
            name : 'customerNum',
            width : 80,
            sortable : true,
            align : 'right'
    } ],
    buttons : [ {
        name : '新增代理',
        bclass : 'add',
        onpress : test
        },{
            separator : true
        }, {
            name : '删除代理',
            bclass : 'delete',
            onpress : test
        }, {
            separator : true
    } ],
    searchitems : [ {
        display : '代理名称',
        name : 'agentName',
        isdefault : true
        }, {
            display : '配送地址',
            name : 'address'
    } ],
    sortname : "agentName",
    sortorder : "asc",
    usepager : true,
    title : '代理管理',
    useRp : true,
    rp : 15,
    showTableToggleBtn : true,
    height : 350,
});      

function test(com, grid) {
	alert($('.trSelected', grid).length);
}
function rowDbClick(tr){
	alert(tr.id);
}
</script>


</html>