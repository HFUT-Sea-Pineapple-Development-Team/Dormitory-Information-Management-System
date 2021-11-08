<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	function checkForm(){
		//通过id获取输入框中用户输入的信息
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		var rPassword=document.getElementById("rPassword").value;
		var name=document.getElementById("name").value;
		var sex=document.getElementById("sex").value;
		var tel=document.getElementById("tel").value;
		
		var dormBuildIdCheckBox = document.getElementsByName("dormBuildId");
		var checkBoxValue = new Array();
		for (var i = 0; i < dormBuildIdCheckBox.length;i++){
			if (dormBuildIdCheckBox[i].checked){
				//复选框被选中，返回值为true，将复选框中的值添加到数组中
				checkBoxValue.push(dormBuildIdCheckBox[i].value);
			}
		}
		if(userName==""||password==""||rPassword==""||name==""||sex==""||tel==""|| checkBoxValue.length < 1){
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
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${dormManager.dormManagerId!=null }">
				修改管理员
			</c:when>
			<c:otherwise>
				添加管理员
			</c:otherwise>
		</c:choose>
		</div>
		<form action="dormManager?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="dormManagerId" name="dormManagerId" value="${dormManager.dormManagerId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>用户名：</td>
							<td><input type="text" id="userName"  name="userName" value="${dormManager.userName }"  style="margin-top:5px;height:30px;" /></td>
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
									<option value="男" ${dormManager.sex eq "男"?'selected':'' }>男</option>
									<option value="女" ${dormManager.sex eq "女"?'selected':'' }>女</option>
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
								<input type="checkbox" name="dormBuildId" value="1"  style="vertical-align:top;height:14px" >1号宿舍楼 &nbsp;
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