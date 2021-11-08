<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
    function visiterDelete(visiter_id) {
        if(confirm("您确定要删除这条报修记录吗？")) {
            window.location="visiter.action?action=delete&id="+visiter_id;
        }
    }
    $(document).ready(function(){
        $("ul li:eq(3)").addClass("active");
    });
</script>
<div class="data_list">
    <div class="data_list_title" >
        访客记录
    </div>
    <form name="myForm" class="form-search" method="post" action="visiter.action?action=list">
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
                <th>姓名</th>
                <th>联系方式</th>
                <th>拜访时间</th>
                <th>拜访原因</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody>
            <!--items表示要循环遍历的元素 var：代表当前集合中每一个元素 varStaus :代表循环状态 的变量名-->
            <c:forEach items="${visiters}" var="visiter" varStatus="stat" >
                <tr>
                    <td>${stat.index}</td>
                    <td>${visiter.name }</td>
                    <td>${visiter.tel }</td>
                    <td>${visiter.showTime }</td>
                    <td>${visiter.reason }</td>
<%--                    <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='notice.action?action=Check&id=${visiter.id}'">修改</button>&nbsp;--%>
                    <td> <button class="btn btn-mini btn-danger" type="button" onclick="visiterDelete(${visiter.id})">删除</button></td>
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
<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='visiter.action?action=preAdd'">登记访客信息</button>