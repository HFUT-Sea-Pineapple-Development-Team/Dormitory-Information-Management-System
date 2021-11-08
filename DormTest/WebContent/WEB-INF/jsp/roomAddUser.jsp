<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wdlPager" uri="wdl-page-tag"%>

<script type="text/javascript">

$(document).ready(function(){
	$("#room").addClass("active");
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
			寝室管理
		</div>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<!-- <th>编号</th> -->
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>专业</th>
					<th>班级</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach  varStatus="i" var="student" items="${studentsNotRoom }">
						<tr>
							<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
							<td>${student.stu_code}</td>
							<td>${student.name}</td>
							<td>${student.sex eq 0 ? '女':'男'}</td>
							<td>${student.tel}</td>
							<td>${student.major}</td>
							<td>${student.className}</td>
							<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='room.action?action=addUser&buildId=${build_id }&roomId=${room_id }&studentId=${student.id }'">添加</button>&nbsp;
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center">
			<button class="btn btn-primary" type="button" onclick="javascript:window.location='room.action?action=detail&buildId=${build_id }&roomId=${room_id }'">返回</button>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<%-- <div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div> --%>
</div>

