<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    function repairInfoDelete(repairId) {
        if(confirm("您确定要删除这条报修记录吗？")) {
            window.location="repair.action?action=delete&repairId="+repairId;
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
    <form name="myForm" class="form-search" method="post" action="repair.action?action=list">
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th width="15%">编号</th>
                <th>报修学生</th>
                <th>报修时间</th>
                <th>报修原因</th>
                <th>维修时间</th>
                <th>维修意见</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
            <c:forEach items="${repairs}" var="repair" varStatus="stat" >
                <tr>
                    <td>${stat.index}</td>
                    <td>${repair.name }</td>
                    <td>${repair.report_time }</td>
                    <td>${repair.reason }</td>
                    <td>${repair.repair_time }</td>
                    <td>${repair.idea==null||repair.idea==""?"无":repair.idea }</td>
                    <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='repair.action?action=preUpdate&id=${repair.id}'">提出意见</button>&nbsp;
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
<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='repair.action?action=preAdd'">申请维修</button>