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
        维修报告
    </div>
    <form name="myForm" class="form-search" method="post" action="keeperRepair.action?action=list">
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th>维修时间</th>
                <th>维修是否完成</th>
                <th>维修情况</th>
                <th>维修满意情况</th>
                <th>维修意见</th>
            </tr>
            </thead>
            <tbody>
            <!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
                    <td>${reports.repair_time }</td>
                    <td>${reports.is_finished }</td>
                    <td>${reports.repair_report }</td>
                    <td>${reports.is_satisfied }</td>
                    <td>${reports.idea }</td>
                        <%--                    <td>${repair.idea==null||repair.idea==""?"无":repair.idea }</td>--%>

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