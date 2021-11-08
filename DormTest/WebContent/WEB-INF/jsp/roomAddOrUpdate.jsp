<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function checkForm(){
		var room_id=document.getElementById("room_id").value;
		if((room_id < 140 && room_id >= 100) || (room_id < 240 && room_id >= 200) || (room_id < 340 && room_id >= 300) || (room_id < 440 && room_id >= 400) || (room_id < 540 && room_id >= 500)){
			return true;
		}else{
			document.getElementById("error").innerHTML="没有这么多寝室！";
			return false;
		}
	}
	
	$(document).ready(function(){
		$("#room").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			寝室添加
		</div>
		<form action="room.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="buildId" name="buildId" value="${buildId}"/>
					<table align="center">
						<tr>
							<td>寝室号：</td>
							<td><input type="text" id="room_id"  name="room_id" value="${roomUpate.room_id }"  style="margin-top:5px;height:30px;" /></td>
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