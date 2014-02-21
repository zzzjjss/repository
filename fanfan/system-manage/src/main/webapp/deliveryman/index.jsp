<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="navTop.jsp"%>

<div class="container">
	<table class="table table-hover ">
              <thead>
                <tr >
                  <th colspan="4"  style="text-align: left;">
      					<form class="navbar-search pull-left">
  <input type="text" class="search-query" placeholder="代理名称" style="height: 30;">
</form><button class="btn" style=";margin-top:5">查询</button>
                  </th>
                  <th colspan="2" style="text-align: right;">
                  
                  </th>
                </tr>
                <tr >
                  <th class="span1">#</th>
                  <th class="span2">
                  代理名称<i class=" icon-arrow-up"></i>
                  
                  </th>
                  <th class="span2">  账户余额    </th>
                   <th class="span2">应收金额</th>
                 <th class="span2">地址</th>
                   <th class="span8">
             操作
                   </th>
                </tr>
              
              </thead>
              <tbody>
                <tr>
                  <td>
                  	1
                  </td>
                  <td align="left">
                  	zhangjinsheng
                  </td>
                  <td valign="middle">￥1200</td>
                  <td>￥0</td>
                  <td>金蝶大厦8楼</td>
                   <td>
        				<button class="btn " > 充值</button>
        				<button class="btn" > 退款</button>
                   </td>
                </tr>
               <tr class="warning" >
                  <td>
                  	1
                  </td>
                  <td align="left">
                  	zhangjinsheng
                  </td>
                  <td valign="middle">￥-1200.00</td>
                  <td>￥1200</td>
                  <td>金蝶大厦8楼</td>
                   <td>

        				<button class="btn " > 充值</button>
        				<button class="btn" > 退款</button>
                   </td>
                </tr>
               
                
               
                
              </tbody>
            </table>
</div>



<%@ include file="bottom.jsp"%>