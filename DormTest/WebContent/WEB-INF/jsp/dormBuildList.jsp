<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function dormBuildDelete(dormBuildId) {
		if(confirm("您确定要删除这个宿舍楼吗？")) {
			window.location="dormBuild?action=delete&dormBuildId="+dormBuildId;
		}
	}
	$(document).ready(function(){
		$("ul li:eq(4)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			宿舍楼管理
		</div>
		<form name="myForm" class="form-search" method="post" action="dormBuild.action?action=list">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='dormBuild.action?action=preAdd'">添加</button>
				<span class="data_search">
				<select id="id" name="id" style="width:120px ;">
					<c:forEach items="${buildSelects}" var="build" varStatus="stat" >
							<option value="${build.id }" ${id eq build.id ? 'selected' : "" }>${build.dormBuildName}</option>
					</c:forEach>
	
				</select>
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
						<th width="15%">编号</th>
						<th>名称</th>
						<th>简介</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
					<c:forEach items="${builds}" var="build" varStatus="stat" >
					<tr>
						<td>${stat.index}</td>
						<td>${build.dormBuildName }</td>
						<td>${build.detail==null||build.detail==""?"无":build.detail }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild?action=manager&dormBuildId=${dormBuild.dormBuildId }'">管理员</button>&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild.action?action=preUpdate&id=${build.id }'">修改</button>&nbsp;
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div align="center"><font color="red">${error }</font></div>
		<div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div>

</div>
