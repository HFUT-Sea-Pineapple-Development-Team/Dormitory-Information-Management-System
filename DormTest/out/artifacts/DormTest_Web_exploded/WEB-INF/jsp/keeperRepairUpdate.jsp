<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>标题</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="本页描述或关键字描述" />
</head>
<body>

<div class="data_list">
    <div class="data_list_title">
        维修情况登记
    </div>
    <form action="keeperRepair.action?action=save" method="post" onsubmit="return checkForm()">
        <%--    <form action="repair.action?action=suggestion" method="post">--%>
        <div class="data_form" >
            <!-- 圣昌，这里做成一个单选框，选择是或者否，就是是否满意的意思 -->
            <table align="center">
                <tr>
                    <td><font color="red">*</font>维修是否完成：</td>
                    <td>
                        <select type="text" id="is_finished"  name="is_finished" value="${repair.is_finished }"  style="margin-top:5px;height:30px;">
                            <option value="">请选择...</option>
                            <option value="是" ${repair.is_satisfied eq "是"?'selected':'' }>是</option>
                            <option value="否" ${repair.is_satisfied eq "否"?'selected':'' }>否</option>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>&nbsp;维修情况：</td>
                    <td><textarea id="repair_report" name="repair_report" rows="10">${repair.repair_report }</textarea></td>
                </tr>

            </table>
            <div align="center">
                <input type="submit" class="btn btn-primary" value="提交"/>
                &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
            </div>
            <div align="center">
                <font id="error" color="red">${error }</font>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    function checkForm(){
        //检查用户是否输入宿舍楼名字
        var report=document.getElementById("repair_report").value;
        if(report==null||report==""){
            document.getElementById("error").innerHTML="维修情况不能为空！";
            return false;
        }
        return true;
    }

    $(document).ready(function(){
        $("ul li:eq(3)").addClass("active");
    });
</script>


</body>
</html>

