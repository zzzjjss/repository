<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<link href="../flexigrid/css/flexigrid.css" rel="stylesheet">
<script src="../js/jquery.js"></script>
<script src="../flexigrid/js/flexigrid.js"></script>
</head>	
<style>
body {
	padding-top: 10px;
	
}
.flexigrid div.fbutton .add {
	background: url(images/add.png) no-repeat center left;
}

.flexigrid div.fbutton .delete {
	background: url(images/close.png) no-repeat center left;
}
</style>
<body>


<table class="flexme3" style="display: none"></table>


<script type="text/javascript">




$(".flexme3").flexigrid({
    url : 'post-xml.php',
    dataType : 'xml',
    colModel : [ {
        display : 'ISO',
        name : 'iso',
        width : 40,
        sortable : true,
        align : 'center'
        }, {
            display : 'Name',
            name : 'name',
            width : 180,
            sortable : true,
            align : 'left'
        }, {
            display : 'Printable Name',
            name : 'printable_name',
            width : 120,
            sortable : true,
            align : 'left'
        }, {
            display : 'ISO3',
            name : 'iso3',
            width : 130,
            sortable : true,
            align : 'left',
            hide : true
        }, {
            display : 'Number Code',
            name : 'numcode',
            width : 80,
            sortable : true,
            align : 'right'
    } ],
    buttons : [ {
        name : 'Add',
        bclass : 'add',
        onpress : test
        }, {
            name : 'Delete',
            bclass : 'delete',
            onpress : test
        }, {
            separator : true
    } ],
    searchitems : [ {
        display : 'ISO',
        name : 'iso'
        }, {
            display : 'Name',
            name : 'name',
            isdefault : true
    } ],
    sortname : "iso",
    sortorder : "asc",
    usepager : true,
    title : 'Countries',
    useRp : true,
    rp : 15,
    showTableToggleBtn : true,
   
    height : 300,
    maxHeight:400
});      

function test(com, grid) {
    if (com == 'Delete') {
        confirm('Delete ' + $('.trSelected', grid).length + ' items?')
    } else if (com == 'Add') {
        alert('Add New Item');
    }
}
</script>

<%@ include file="bottom.jsp"%>