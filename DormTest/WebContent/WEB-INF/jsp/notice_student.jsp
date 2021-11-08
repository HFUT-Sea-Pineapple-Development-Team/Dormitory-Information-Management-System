<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    function noticeDelete(notice_id) {
        if(confirm("您确定要删除这条报修记录吗？")) {
            window.location="notice.action?action=delete&id="+notice_id;
        }
    }
    $(document).ready(function(){
        $("ul li:eq(3)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title" >
        公告通知
    </div>
    <form name="myForm" class="form-search" method="post" action="stuNotice.action?action=list">
        <span class="data_search">
            <tr>
                <td><font color="red">*</font>日期：</td>
                <span class="data_search">
				<select id="time" name="time" style="width:120px ;">
                    <option value="">请选择...</option>
					<c:forEach items="${dates}" var="date" varStatus="stat" >
                        <option value="${date }" ${time eq date ? 'selected' : "" }>${date}</option>
                    </c:forEach>

				</select>
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
            </tr>

        </span>
    </form>
    <div>
        <table class="table table-striped table-bordered table-hover datatable">
            <thead>
            <tr>
                <th width="15%">编号</th>
                <th>发布时间</th>
                <th>发布者</th>
                <th>公告标题</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
            <c:forEach items="${notices}" var="notice" varStatus="stat" >
                <tr>
                    <td>${stat.index}</td>
                    <td>${notice.time }</td>
                    <td>${notice.name }</td>
                    <td>${notice.title }</td>
                    <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='stuNotice.action?action=detail&id=${notice.id}'">查看具体内容</button>&nbsp;
                    <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='stuNotice.action?action=check&id=${notice.id}'">确认收到</button>
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
<%--<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='notice.action?action=preAdd'">发布新的公告</button>--%>