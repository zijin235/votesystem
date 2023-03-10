<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>选项列表</title>
	<link rel="stylesheet" type="text/css" href="../../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../../easyui/css/demo.css">
	<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
	var subjectsJson = ${subjectsJson};
	$(function() {
		//datagrid初始化
	    $('#dataList').datagrid({
	        title:'投票选项列表',
	        iconCls:'icon-more',//图标
	        border: true,
	        collapsible: false,//是否可折叠的
	        // fit: true,//自动大小
	        method: "get",
	        url:"get_list",
	        idField:'id',
	        singleSelect: true,//是否单选
	        pagination: true,//分页控件
	        rownumbers: true,//行号
	        sortName: 'id',
	        sortOrder: 'DESC',
	        remoteSort: false,
	        columns: [[
				{field:'chk',checkbox: true,width:50},
 		        {field:'id',title:'ID',width:50, sortable: true},
 		        {field:'title',title:'选项标题',width:200},
 		        {field:'subjectId',title:'所属主题',width:200,formatter: function(value,row,index){
 		        	for(var index in subjectsJson){
 		        		if(subjectsJson[index].id == value){
 		        			return subjectsJson[index].name;
 		        		}
 		        	}
 		        	return value;
 		        }},
 		       {field:'voteNumber',title:'投票人数',width:60},
 		        {field:'createTime',title:'创建时间',width:150,formatter: function(value,row,index){
 		        	var date = new Date(value);
 		           var y = date.getFullYear();
 		           var m = date.getMonth() + 1;
 		           m = m < 10 ? ('0' + m) : m;
 		           var d = date.getDate();
 		           d = d < 10 ? ('0' + d) : d;
 		           var h = date.getHours();
 		           h = h < 10 ? ('0' + h) : h;
 		           var minute = date.getMinutes();
 		           var second = date.getSeconds();
 		           minute = minute < 10 ? ('0' + minute) : minute;
 		           second = second < 10 ? ('0' + second) : second;
 		           return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
 		        }},
	 		]],
	        toolbar: "#toolbar"
	    });
	    //设置分页控件
	    var p = $('#dataList').datagrid('getPager');
	    $(p).pagination({
	        pageSize: 10,//每页显示的记录条数，默认为10
	        pageList: [10,20,30,50,100],//可以设置每页记录条数的列表
	        beforePageText: '第',//页数文本框前显示的汉字
	        afterPageText: '页    共 {pages} 页',
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
	    });
	    //设置工具类按钮
	    $("#add").click(function(){
	    	$("#addDialog").dialog("open");
	    });
	    //删除
	    $("#delete").click(function(){
	    	var selectRow = $("#dataList").datagrid("getSelected");
	    	//console.log(selectRow);
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
            } else{
            	var clazzid = selectRow.id;
            	$.messager.confirm("消息提醒", "若在改选项下存在投票信息则无法删除，确认继续？", function(r){
            		if(r){
            			$.ajax({
							type: "post",
							url: "delete",
							dataType:"json",
							data: {id: clazzid},
							success: function(data){
								if(data.type == "success"){
									$.messager.alert("消息提醒","删除成功!","info");
									//刷新表格
									$("#dataList").datagrid("reload");
								} else{
									$.messager.alert("消息提醒",data.msg,"warning");
									return;
								}
							}
						});
            		}
            	});
            }
	    });



	  	//设置添加选项窗口
	    $("#addDialog").dialog({
	    	title: "添加投票选项",
	    	width: 500,
	    	height: 200,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'添加',
					plain: true,
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#addForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							//var gradeid = $("#add_gradeList").combobox("getValue");
							$.ajax({
								type: "post",
								url: "add",
								data: $("#addForm").serialize(),
								dataType:"json",
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","添加成功!","info");
										//关闭窗口
										$("#addDialog").dialog("close");
										//清空原表格数据
										$("#add_title").textbox('setValue', "");
										//$("#info").val("");
										//重新刷新页面数据
							  			//$('#gradeList').combobox("setValue", gradeid);
							  			$('#dataList').datagrid("reload");

									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
			]
	    });


	  	//搜索按钮监听事件
	  	$("#search-btn").click(function(){
	  		$('#dataList').datagrid('load',{
	  			name: $('#subjectName').val()
	  		});
	  	});

	  //修改按钮监听事件
	  	$("#edit-btn").click(function(){
	  		var selectRow = $("#dataList").datagrid("getSelected");
	    	//console.log(selectRow);
        	if(selectRow == null){
            	$.messager.alert("消息提醒", "请选择数据进行修改!", "warning");
            	return;
            }
        	$("#editDialog").dialog("open");
	  	});

	  //设置编辑选项窗口
	    $("#editDialog").dialog({
	    	title: "编辑投票选项",
	    	width: 500,
	    	height: 200,
	    	iconCls: "icon-add",
	    	modal: true,
	    	collapsible: false,
	    	minimizable: false,
	    	maximizable: false,
	    	draggable: true,
	    	closed: true,
	    	buttons: [
	    		{
					text:'确定修改',
					plain: true,
					iconCls:'icon-add',
					handler:function(){
						var validate = $("#editForm").form("validate");
						if(!validate){
							$.messager.alert("消息提醒","请检查你输入的数据!","warning");
							return;
						} else{
							//var gradeid = $("#add_gradeList").combobox("getValue");
							$.ajax({
								type: "post",
								url: "edit",
								dataType:"json",
								data: $("#editForm").serialize(),
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","修改成功!","info");
										//关闭窗口
										$("#editDialog").dialog("close");
										//清空原表格数据
										$("#edit_title").textbox('setValue', "");
										//$("#edit_info").val("");
										//重新刷新页面数据
							  			//$('#gradeList').combobox("setValue", gradeid);
							  			$('#dataList').datagrid("reload");

									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					}
				},
			],
			onBeforeOpen: function(){
				var selectRow = $("#dataList").datagrid("getSelected");
				//console.log(selectRow);
				//设置值
				$("#edit_title").textbox('setValue', selectRow.title);
				//$("#edit_info").val(selectRow.info);
				$("#edit-id").val(selectRow.id);
				$("#edit_subject_id").combobox('setValue', selectRow.subjectId);
			}
	    });

	});
	</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dataList" cellspacing="0" cellpadding="0">
	</table>

	<!-- 工具栏 -->
	<div id="toolbar">
		<div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
		<div style="float: left; margin-right: 10px;"><a id="edit-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
		<div style="float: left; margin-right: 10px;"><a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a></div>
<%--		<div style="margin-top: 3px;">主题名称：<input id="subjectName" class="easyui-textbox" name="subjectName" />--%>
<%--			<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>--%>
<%--		</div>--%>
	</div>

	<!-- 添加窗口 -->
	<div id="addDialog" style="padding: 10px">
    	<form id="addForm" method="post">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>选项名称:</td>
	    			<td><input id="add_title" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="title"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>所属主题:</td>
	    			<td>
		    			<select style="width: 200px; height: 30px;" class="easyui-combobox" name="subjectId"  data-options="required:true, missingMessage:'不能为空'" />
		    			<c:forEach items="${ subjects}" var="subject">
		    				<option value="${ subject.id}">${subject.name}</option>
		    			</c:forEach>
		    			</select>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>

	<!-- 编辑窗口 -->
	<div id="editDialog" style="padding: 10px">
    	<form id="editForm" method="post">
    	<input type="hidden" id="edit-id" name="id">
	    	<table cellpadding="8" >
	    		<tr>
	    			<td>主题名称:</td>
	    			<td><input id="edit_title" style="width: 200px; height: 30px;" class="easyui-textbox" type="text" name="title"  data-options="required:true, missingMessage:'不能为空'" /></td>
	    		</tr>
	    		<tr>
	    			<td>所属主题:</td>
	    			<td>
		    			<select id="edit_subject_id" style="width: 200px; height: 30px;" class="easyui-combobox" name="subjectId"  data-options="required:true, missingMessage:'不能为空'" />
		    			<c:forEach items="${ subjects}" var="subject">
		    				<option value="${ subject.id}">${subject.name}</option>
		    			</c:forEach>
		    			</select>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
	</div>

</body>
</html>
