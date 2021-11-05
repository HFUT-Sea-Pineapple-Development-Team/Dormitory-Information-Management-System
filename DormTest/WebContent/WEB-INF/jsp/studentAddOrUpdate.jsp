<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function checkForm(){
	var userName=document.getElementById("userName").value;
	var password=document.getElementById("password").value;
	var rPassword=document.getElementById("rPassword").value;
	var dormBuildId=document.getElementById("dormBuildId").value;
	var dormName=document.getElementById("dormName").value;
	var name=document.getElementById("name").value;
	var sex=document.getElementById("sex").value;
	var tel=document.getElementById("tel").value;
	var major=document.getElementById("major").value;
	var className=document.getElementById("className").value;
	if(userName==""||password==""||rPassword==""||name==""||sex==""||tel==""||dormBuildId==""||dormName==""||major==""||className==""){
		document.getElementById("error").innerHTML="信息填写不完整！";
		return false;
	} else if(password!=rPassword){
		document.getElementById("error").innerHTML="密码填写不一致！";
		return false;
	} else if(!/^1[34578]\d{9}$/.test(tel)){
		document.getElementById("error").innerHTML="手机号码格式错误！";
		return false;
	}
	return true;
}

window.onload = function(){
	var studentBuildId = "${userUpdate.dormBuildId}";
	
	var dormBuildIdSelect = document.getElementById("dormBuildId");
	var options = dormBuildIdSelect.options;
	
	$.each(options,function(i,option){
		$(option).attr("selected",option.value == studentBuildId);
	})
}

$(document).ready(function(){
	$("ul li:eq(2)").addClass("active");
});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${userUpdate.id != null }">
				修改学生信息
			</c:when>
			<c:otherwise>
				添加学生
			</c:otherwise>
		</c:choose>
		</div>
		<form action="student.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="id" name="id" value="${userUpdate.id}"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>学号：</td>
							<td><input type="text" id="userName"  name="userName" value="${userUpdate.stu_code}"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>密码：</td>
							<td><input type="password" id="password"  name="password" value="${userUpdate.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="rPassword"  name="rPassword" value="${userUpdate.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>姓名：</td>
							<td><input type="text" id="name"  name="name" value="${userUpdate.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="1" ${userUpdate.sex eq "1"?'selected':'' }>男</option>
									<option value="0" ${userUpdate.sex eq "0"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>专业：</td>
							<td>
								<select id="major" name="major" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="计算机" ${userUpdate.major eq "计算机"?'selected':'' }>计算机</option>
									<option value="数学" ${userUpdate.major eq "数学"?'selected':'' }>数学</option>
									<option value="语文" ${userUpdate.major eq "语文"?'selected':'' }>语文</option>
									<option value="英语" ${userUpdate.major eq "英语"?'selected':'' }>英语</option>
									<option value="物理" ${userUpdate.major eq "物理"?'selected':'' }>物理</option>
									<option value="生物" ${userUpdate.major eq "生物"?'selected':'' }>生物</option>
									<option value="化学" ${userUpdate.major eq "化学"?'selected':'' }>化学</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>班级：</td>
							<td>
								<select id="className" name="className" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="1" ${userUpdate.className eq "1"?'selected':'' }>1班</option>
									<option value="2" ${userUpdate.className eq "2"?'selected':'' }>2班</option>
									<option value="3" ${userUpdate.className eq "3"?'selected':'' }>3班</option>
									<option value="4" ${userUpdate.className eq "4"?'selected':'' }>4班</option>
									<option value="5" ${userUpdate.className eq "5"?'selected':'' }>5班</option>
								</select>
							</td>
						</tr>
						<c:if test="${session_user.roleId == 0}">
						<tr>
							<td><font color="red">*</font>宿舍楼：</td>
							<td>
								<select id="dormBuildId" name="dormBuildId" style="width: 90px;">
									<c:forEach var="build" items="${builds}">
										<option value="${build.id}">${build.dormBuildName}号楼</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						</c:if>
						<c:if test="${session_user.roleId == 1}">
						<tr>
							<input type="hidden" id="dormBuildId" name="dormBuildId" value="${builds.get(0).id}"/>
						</tr>
						</c:if>
						<tr>
							<td><font color="red">*</font>寝室：</td>
							<td><input type="text" id="dormName"  name="dormName" value="${userUpdate.roomId }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>联系电话：</td>
							<td><input type="text" id="tel"  name="tel" value="${userUpdate.tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<c:if test="${userUpdate.id != null }">
						<tr>
							<td><font color="red">*</font>是否离校：</td>
							<td><input type="text" id="leaveSchool"  name="leaveSchool" value="${userUpdate.leaveSchool }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						</c:if>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='javascript:history.back()'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>