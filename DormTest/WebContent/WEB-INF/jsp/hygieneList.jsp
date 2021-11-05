<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wdlPager" uri="wdl-page-tag"%>

<script type="text/javascript">

$(document).ready(function(){
	$("#hygiene").addClass("active");
	$('.datatable').dataTable( {        				
		 "oLanguage": {
				"sUrl": "/DormManage/media/zh_CN.json"
		 },
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"aoColumns": [
			null,
			null,
			null,
			null,
			null,
			{ "asSorting": [ ] },
			{ "asSorting": [ ] }
		]
	});
});

//文档加载完成后
window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
	function studentDelete(studentId) {
		if(confirm("您确定要删除这个学生吗？")) {
			window.location="student?action=delete&studentId="+studentId;
		}
	}
</script>
<style type="text/css">
	.span6 {
		width:0px;
		height: 0px;
		padding-top: 0px;
		padding-bottom: 0px;
		margin-top: 0px;
		margin-bottom: 0px;
	}


</style>
<div class="data_list">
		<div class="data_list_title">
			卫生统计
		</div>
		<form name="myForm" class="form-search" method="post" action="hygiene.action?action=list" style="padding-bottom: 0px">
				<span class="data_search">
					<c:if test="${session_user.roleId == 0}">
					<select id="buildToSelect" name="buildToSelect" style="width: 110px;">
						<option value="">全部宿舍楼</option>
						<c:forEach var="build" items="${builds}">
							<option value="${build.id}" ${buildToSelect eq build.id?'selected':'' }>${build.dormBuildName}号楼</option>
						</c:forEach>
					</c:if>
					</select>
					<select id="id" name="id" style="width: 80px;">
						<option value="name">寝室号</option>
					</select>
					&nbsp;<input id="keyword" name="keyword" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${keyword}">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<!-- <th>编号</th> -->
					<th>寝室号</th>
					<th>18年卫生</th>
					<th>19年卫生</th>
					<th>20年卫生</th>
					<th>21年卫生</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach  varStatus="i" var="hygiene" items="${hygienes}">
						<tr>
							<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
							<td>${hygiene.room_id}</td>
							<td>${hygiene.grade_18}</td>
							<td>${hygiene.grade_19}</td>
							<td>${hygiene.grade_20}</td>
							<td>${hygiene.grade_21}</td>
							<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='hygiene.action?action=preUpdate&roomId=${hygiene.room_id}'">录入分数</button>&nbsp;
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<%-- <div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div> --%>
</div>
