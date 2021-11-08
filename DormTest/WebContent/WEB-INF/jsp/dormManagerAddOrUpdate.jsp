<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function checkForm(){
		//通过id获取输入框中用户输入的信息
		var jobCode=document.getElementById("jobCode").value;
		var password=document.getElementById("password").value;
		var rPassword=document.getElementById("rPassword").value;
		var name=document.getElementById("name").value;
		var sex=document.getElementById("sex").value;
		var tel=document.getElementById("tel").value;
		
		var dormBuildId = document.getElementsByName("dormBuildId");
		if(jobCode==""||password==""||rPassword==""||name==""||sex==""||tel==""||dormBuildId==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} else if(password!=rPassword){
			document.getElementById("error").innerHTML="密码填写不一致！";
			return false;
		}else if(!/^1[34578]\d{9}$/.test(tel)){
			document.getElementById("error").innerHTML="手机号码格式错误！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("#dormManager").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${dormManager.dormBuildId!=null }">
				修改管理员
			</c:when>
			<c:otherwise>
				添加管理员
			</c:otherwise>
		</c:choose>
		</div>
		<form action="dormManager.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="dormManagerId" name="dormManagerId" value="${dormManager.id }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>工号：</td>
							<td><input type="text" id="jobCode"  name="jobCode" value="${dormManager.stu_code }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>密码：</td>
							<td><input type="password" id="password"  name="password" value="${dormManager.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="rPassword"  name="rPassword" value="${dormManager.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>姓名：</td>
							<td><input type="text" id="name"  name="name" value="${dormManager.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="1" ${dormManager.sex eq "1"?'selected':'' }>男</option>
									<option value="0" ${dormManager.sex eq "0"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>联系电话：</td>
							<td><input type="text" id="tel"  name="tel" value="${dormManager.tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>管理楼栋：</td>
							<td>
								<select id="dormBuildId" name="dormBuildId" style="width: 90px;">
									<c:forEach var="build" items="${builds}">
										<option value="${build.id }" ${dormBuildId eq build.id?'selected':'' }>${build.dormBuildName}号楼</option>
									</c:forEach>
								</select>
							</td>
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