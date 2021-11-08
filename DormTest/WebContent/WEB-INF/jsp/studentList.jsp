<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wdlPager" uri="wdl-page-tag"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(2)").addClass("active");
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
<c:if test="${session_user.roleId < 2}">
<div class="data_list">
		<div class="data_list_title">
			学生管理
		</div>
		<form name="myForm" class="form-search" method="post" action="student.action?action=list" style="padding-bottom: 0px">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='student.action?action=preAdd'">添加</button>
			    <button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='student.action?action=addList'">批量导入</button>
				<span class="data_search">
					<c:if test="${session_user.roleId == 0}">
					<select id="buildToSelect" name="buildToSelect" style="width: 110px;">
						<option value="">全部宿舍楼</option>
						<c:forEach var="build" items="${builds}">
							<option value="${build.id}" ${buildToSelect eq build.id?'selected':'' }>${build.dormBuildName}号楼</option>
						</c:forEach>
					</c:if>
					</select>
					<select id="searchType" name="searchType" style="width: 80px;">
					<option value="name">姓名</option>
					<option value="stuCode" ${searchType eq "stuCode"?'selected':'' }>学号</option>
					<option value="dorm" ${searchType eq "dorm"?'selected':'' }>寝室</option>
					<option value="sex" ${searchType eq "sex"?'selected':'' }>性别</option>
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
					<th>学号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>寝室楼</th>
					<th>寝室</th>
					<th>专业</th>
					<th>班级</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach  varStatus="i" var="student" items="${students}">
						<tr>
							<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
							<td>${student.stu_code}</td>
							<td>${student.name}</td>
							<td>${student.sex eq 0 ? '女':'男'}</td>
							<td>${student.tel}</td>
							<td>${student.build.dormBuildName}号楼</td>
							<td>${student.room.room_id}</td>
							<td>${student.major}</td>
							<td>${student.className}</td>
							<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='student.action?action=preUpdate&id=${student.id }'">修改</button>&nbsp;
								<button class="btn btn-mini btn-danger" type="button" onclick="javascript:window.location='student.action?action=delete&id=${student.id }'">删除</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<div style="text-align: center;">
		<wdlPager:pager
			totalNum="${totalNum }"
			pageSize="10"
			pageIndex="${pageIndex }"
			submitUrl="${pageContext.request.contextPath}/student.action?action=list&searchType=${searchType }&keyword=${keyword }&buildToSelect=${buildToSelect }"></wdlPager:pager>
		</div>
		<%-- <div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div> --%>
</div>
</c:if>

<c:if test="${session_user.roleId == 2}">
<div class="data_list">
	<div class="data_list_title">我的寝室情况 </div>
	<div>
		<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>寝室楼</th>
					<th>寝室房间号</th>
					<th>卫生情况</th>
					<th>剩余水费</th>
					<th>剩余电费</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>${studentDormBuild.dormBuildName}</td>
					<td>${roomId}</td>
					<td>${userAndRoom.is_good eq 0 ? '优秀':'良好'}</td>
					<td>${userAndRoom.remain_water_charge }</td>
					<td>${userAndRoom.remain_elec_charge }</td>
					<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormManager.action?action=preUpdate&dormManagerId=${dormManager.id }&dormBuildId=${dormManager.dormBuildId }'">缴纳水费</button>&nbsp;
					<button class="btn btn-mini btn-danger" type="button" onclick="dormManagerDelete(${dormManager.id})">缴纳电费</button></td>
				</tr>
				
				
			</table>
	</div>
	</div>
	<th>寝室楼:</th>
	<td>${studentDormBuild.dormBuildName}</td>
	<th>寝室房间号:</th>
	<td>${roomId}</td>
	<th>寝室卫生情况:</th>
	<td>${userAndRoom.is_good eq 0 ? '优秀':'良好'}</td>
	<th>剩余水费:</th>
	<td>${userAndRoom.remain_water_charge }</td>
	<th>剩余电费:</th>
	<td>${userAndRoom.remain_elec_charge }</td>
	<th>缴纳水费</th>
	<th>缴纳电费</th>
</c:if>