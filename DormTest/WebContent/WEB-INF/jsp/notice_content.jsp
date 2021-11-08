<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    function repairInfoDelete(repairId) {
        if(confirm("您确定要删除这条报修记录吗？")) {
            window.location="notice.action?action=delete&repairId="+repairId;
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
    <form name="myForm" class="form-search" method="post" action="notice.action?action=list">
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th>公告标题</th>
                <th>公告内容</th>
                <th>联系方式</th>
            </tr>
            </thead>
            <tbody>
            <!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
                <td>${notice.title }</td>
                <td>${notice.content }</td>
                <td>${notice.tel }</td>
            <%--                    <td>${repair.idea==null||repair.idea==""?"无":repair.idea }</td>--%>

            </tbody>
        </table>
        <div align="center">
            &nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
        </div>
    </div>
    <div align="center"><font color="red">${error }</font></div>
    <div class="pagination pagination-centered">
        <ul>
            ${pageCode }
        </ul>
    </div>
</div>
<div> <br></div>