<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    function checkForm(){
        //通过id获取输入框中用户输入的信息
        var title=document.getElementById("title").value;
        var tel=document.getElementById("tel").value;
        var content=document.getElementById("content").value;

        if(title==""||tel==""||content==""){
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
        公告发布
    </div>
    <form action="notice.action?action=save" method="post" onsubmit="return checkForm()">
        <div class="data_form" >
            <table align="center">
                <tr>
                    <td><font color="red">*</font>标题：</td>
                    <td><input type="text" id="title"  name="title" value="${notice.title }"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>联系方式：</td>
                    <td><input type="text" id="tel"  name="tel" value="${notice.tel }"  style="margin-top:5px;height:30px;" /></td>
                </tr>
                <tr>
                    <td><font color="red">*</font>内容：</td>
                    <td><textarea id="content" name="content" rows="10">${notice.content }</textarea></td>

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