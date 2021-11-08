<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function checkForm(){
		var grade_18=document.getElementById("grade_18").value;
		var grade_19=document.getElementById("grade_19").value;
		var grade_20=document.getElementById("grade_20").value;
		var grade_21=document.getElementById("grade_21").value;
		if(grade_18 < 0 || grade_18 > 100 || grade_19 < 0 || grade_19 > 100 || grade_20 < 0 || grade_20 > 100 || grade_21 < 0 || grade_21 > 100){
			document.getElementById("error").innerHTML="录入分数不合规范！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("#hygiene").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			录入卫生分数
		</div>
		<form action="hygiene.action?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="room_id" name="room_id" value="${hygiene.room_id }"/>
					<table align="center">
						<tr>
							<td>18年卫生分数：</td>
							<td><input type="text" id="grade_18"  name="grade_18" value="${hygiene.grade_18 }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>19年卫生分数：</td>
							<td><input type="text" id="grade_19"  name="grade_19" value="${hygiene.grade_19 }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>20年卫生分数：</td>
							<td><input type="text" id="grade_20"  name="grade_20" value="${hygiene.grade_20 }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>21年卫生分数：</td>
							<td><input type="text" id="grade_21"  name="grade_21" value="${hygiene.grade_21 }"  style="margin-top:5px;height:30px;" /></td>
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