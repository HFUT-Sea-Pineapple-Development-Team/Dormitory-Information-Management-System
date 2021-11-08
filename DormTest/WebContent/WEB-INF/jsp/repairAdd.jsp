<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    function checkForm(){
        //通过id获取输入框中用户输入的信息
        var name=document.getElementById("name").value;
        var tel=document.getElementById("tel").value;
        var reason=document.getElementById("reason").value;

        if(name==""||tel==""||reason==""){
            document.getElementById("error").innerHTML="信息填写不完整！";
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
        申请维修
    </div>
    <form action="repair.action?action=save" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <table align="center">
                <tr>
                    <td><font color="red">*</font>联系人：</td>
                    <td><input type="text" id="name"  name="name" value="${repair.name }"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>电话：</td>
                    <td><input type="text" id="tel"  name="tel" value="${repair.tel }"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>报修原因：</td>
                    <td><textarea id="reason" name="reason" rows="10">${repair.reason }</textarea></td>

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