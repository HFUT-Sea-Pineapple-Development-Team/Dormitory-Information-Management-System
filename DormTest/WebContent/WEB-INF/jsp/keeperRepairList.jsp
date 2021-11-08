<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    function repairInfoDelete(repairId) {
        if(confirm("您确定要删除这条报修记录吗？")) {
            window.location="keeperRepair.action?action=delete&id="+repairId;
        }
    }
    $(document).ready(function(){
        $("ul li:eq(3)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title" >
        报修维修记录
    </div>
    <form name="myForm" class="form-search" method="post" action="keeperRepair.action?action=list">
        <span class="data_search">
            <tr>
                <td><font color="red">*</font>寝室号：</td>
                <td><input type="text" id="room_id"  name="room_id" value="${repair.room_id }"  style="margin-top:5px;height:30px;" /></td>
                <button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
            </tr>

        </span>
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th width="15%">编号</th>
                <th>报修寝室</th>
                <th>联系人</th>
                <th>联系方式</th>
                <th>报修时间</th>
                <th>报修原因</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
            <c:forEach items="${repairs}" var="repair" varStatus="stat" >
                <tr>
                    <td>${stat.index}</td>
                    <td>${repair.room_id }</td>
                    <td>${repair.name }</td>
                    <td>${repair.tel }</td>
                    <td>${repair.report_time }</td>
                    <td>${repair.reason }</td>
<%--                    <td>${repair.idea==null||repair.idea==""?"无":repair.idea }</td>--%>
                    <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='keeperRepair.action?action=preCheck&id=${repair.id}'">维修报告</button>&nbsp;
                    <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='keeperRepair.action?action=preUpdate&id=${repair.id}'">维修情况登记</button>&nbsp;
                    <td> <button class="btn btn-mini btn-danger" type="button" onclick="repairInfoDelete(${repair.id})">删除</button></td>
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
<div> <br></div>