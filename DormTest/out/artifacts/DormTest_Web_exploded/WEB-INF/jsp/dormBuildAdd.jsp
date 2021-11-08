<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function checkForm(){
		//检查用户是否输入宿舍楼名字
		var dormBuildName=document.getElementById("dormBuildName").value;
		if(dormBuildName==null||dormBuildName==""){
			document.getElementById("error").innerHTML="名称不能为空！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			<c:if test="${build.id==null }">
				添加宿舍楼
			</c:if>
			<c:if test="${build.id!=null }">
				修改宿舍楼
			</c:if>
		</div>
		<form action="dormBuild.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${build.id }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>名称：</td>
							<td><input type="text" id="dormBuildName"  name="dormBuildName" value="${build.dormBuildName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>&nbsp;简介：</td>
							<td><textarea id="detail" name="detail" rows="10">${build.detail }</textarea></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>