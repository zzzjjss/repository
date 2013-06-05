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
    url : 'PM_getPageShopProducts.action',
    dataType : 'json',
    colModel : [ {
        display : '菜名',
        name : 'productName',
        width : 300,
        sortable : true,
        align : 'left'
        }, {
            display : '价格',
            name : 'price',
            width : 100,
            sortable : true,
            align : 'left'
        }, {
            display : '销售份额',
            name : 'saleSum',
            width : 100,
            sortable : true,
            align : 'left'
        },{
            display : '添加时间',
            name : 'createTime',
            width : 100,
            sortable : true,
            align : 'left'
        }],
    buttons : [ {
        name : '添加新菜',
        bclass : 'add',
        onpress : addProduct
        },{
            separator : true
        }, {
            name : '删除菜谱',
            bclass : 'delete',
            onpress : deleteProduct
        }, {
            separator : true
    } ],
    searchitems : [ {
        display : '菜名',
        name : 'productName',
        isdefault : true
        } ],
    sortname : "productName",
    sortorder : "asc",
    usepager : true,
    title : '菜品管理',
    useRp : true,
    rp : 15,
    showTableToggleBtn : true,
    height : 350,
});      

function addProduct(com, grid){
	window.showModalDialog("addProduct.jsp",null,"dialogWidth=700px;dialogHeight=400px;center:yes");
	
}

function deleteProduct(){
	
}

function test(com, grid) {
	//alert($('.trSelected', grid).length);
	alert("ddd");
	
}
function rowDbClick(tr){
	alert(tr.id);
}
</script>


</html>