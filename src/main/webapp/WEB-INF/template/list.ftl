<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通讯簿</title>
    <script>
    	var cpath = '${cpath}';
    </script>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <meta name="language" content="zh_CN" />
    <link rel="stylesheet" type="text/css" href="${cpath}/jquery_ui_layout/css/layout-default-latest.css" />
    <link rel="stylesheet" type="text/css" href="${cpath}/jquery_ui/themes/start/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="${cpath}/jqgrid/css/ui.jqgrid.css" />
	<script type="text/javascript" src="${cpath}/jquery/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="${cpath}/jquery_ui/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${cpath}/jquery_ui/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
	<script type="text/javascript" src="${cpath}/jquery_ui_layout/jquery.layout-latest.min.js"></script>
	<script src="${cpath}/jqgrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script src="${cpath}/jqgrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script type="text/javascript">
      $(function(){
			jQuery("#infoContent").jqGrid({
				url:"${cpath}/listJson",
				datatype: "json",
				height:$(window).height()-70,
				width:$(window).width()-20,
				//search中构建select下拉框必须要加这个属性，可能是bug。
				ajaxSelectOptions: {
			    },
			    colNames:['序号','姓名','电话','邮箱','部门'],
			    //下方的name属性必须和后台生成的json对象的key值相同才可以
			   	colModel:[
	   				{name:'id',index:'id',sortable:true,editable: true,editoptions: { readonly: 'readonly' },search:true,searchoptions:{sopt:['eq','ne']}},
	   				{name:'username',index:'username',sortable:true,editable: true,editrules:{minValue:2,maxvalue:5},search:true},
	   				{name:'mobile',index:'mobile',sortable:true,editable: true,search:true},
	   				{name:'email',index:'email',sortable:true,editable: true,editrules:{email:true},search:true},
	   				{name:'deptid',index:'deptid',stype:'select',sortable:true,search:true,searchoptions:{
	   					sopt:['eq','ne'],
	   					dataUrl: '${cpath}/deptManager/listAll/getJson',
			            buildSelect: function (data) {
				            var retValue = $.parseJSON(data);
				            var s = '<select id="searchid" name="searchid">';
				            if (retValue && retValue.length) {
				                for (var i = 0, l = retValue.length; i < l; i++) {
				                s += '<option value="' + retValue[i]["id"] + '">' + retValue[i]["name"] + '</option>';
				                }
				            }
				            return s + "</select>";
			            }
	   				},
	   				edittype: 'select',editable: true,
	   				editoptions: {
			            dataUrl: '${cpath}/deptManager/listAll/getJson',
			            buildSelect: function (data) {
				            var retValue = $.parseJSON(data);
				            var s = '<select id="myid" name="myid">';
				            if (retValue && retValue.length) {
				                for (var i = 0, l = retValue.length; i < l; i++) {
				                s += '<option value="' + retValue[i]["id"] + '">' + retValue[i]["name"] + '</option>';
				                }
				            }
				            return s + "</select>";
			            }
			        }}
			   	],
			   	//默认每页显示的数据
			   rowNum:20,
			   //控制每页显示数据
			   rowList:[10,20,30],
			   //底部分页栏显示的块
			   pager: '#pInfoContent',
			   //默认按照id字段排序
			   sortname: 'id',
			   viewrecords: true,
			   sortorder: "asc",
			   //编辑请求的方法
			   editurl:"${cpath}/editData",
			   jsonReader : {
			        root: "rows",
			        page: "page",
			        total: "total",
			        records: "records",
			        repeatitems: false,
			        cell: "cell",
			        id: "id"
		    	}
		    	,
		    	//双击数据弹出编辑框
		    	ondblClickRow: function(rowid) {
			   		 jQuery(this).jqGrid('editGridRow', rowid,editOptions);
				}
			});
			var grid = jQuery("#infoContent");
			var addOptions={
				beforeShowForm: function(form) {
	                 // "editmodlist"
	                 //令新增框初始化的时候居中显示
	                 var dlgDiv = $("#editmod" + grid[0].id);
	                 var parentDiv = dlgDiv.parent();
	                 var dlgWidth = dlgDiv.width();
	                 var parentWidth = parentDiv.width();
	                 var dlgHeight = dlgDiv.height();
	                 var parentHeight = parentDiv.height();
	                 // TODO: change parentWidth and parentHeight in case of the grid
	                 //       is larger as the browser window
	                 dlgDiv[0].style.top = Math.round((parentHeight-dlgHeight)/2) + "px";
	                 dlgDiv[0].style.left = Math.round((parentWidth-dlgWidth)/2) + "px";
	             },
				 serializeEditData: function(data){ 
				 	//新增数据的时候把默认的id='_empty'设置为id='0'
				    return $.param($.extend({},data,{id:0}));
			     },
				    reloadAfterSubmit:true, 
				    //提交前验证的方法
				    beforeSubmit:validate_edit
			}
			var editOptions={
				beforeShowForm: function(form) {
	                 // "editmodlist"
	                 //令编辑框初始化的时候居中显示
	                 var dlgDiv = $("#editmod" + grid[0].id);
	                 var parentDiv = dlgDiv.parent();
	                 var dlgWidth = dlgDiv.width();
	                 var parentWidth = parentDiv.width();
	                 var dlgHeight = dlgDiv.height();
	                 var parentHeight = parentDiv.height();
	                 // TODO: change parentWidth and parentHeight in case of the grid
	                 //       is larger as the browser window
	                 dlgDiv[0].style.top = Math.round((parentHeight-dlgHeight)/2) + "px";
	                 dlgDiv[0].style.left = Math.round((parentWidth-dlgWidth)/2) + "px";
	             },
				    reloadAfterSubmit:true, beforeSubmit:validate_edit
	          }
			grid.jqGrid('navGrid',"#pInfoContent",{},editOptions,addOptions,{},{multipleSearch:true});
			re_pos();
      });
      	function re_pos()
		{
			if($('#infoContent')[0])
			{
				$("#infoContent").setGridHeight($(window).height()-70);
				$("#infoContent").setGridWidth($(window).width()-20);
				
			}
		}
		var timer = null;		
		$(window).resize(function(){
			timer && clearTimeout(timer);
			timer = setTimeout(function()
			{
				re_pos();
			},200);
		})
		function validate_edit(postdata,obj)
		{
			  //在提交数据之前检查数据库是否已经存在相同的手机号码和邮箱
			  var mobile = postdata.mobile;
			  var email = postdata.email;
			  var id = postdata.id;
	      	  var result=[false , '未知错误！'];
	      	  $.ajax({
	      	  	async : false,
	      	  	type:'POST',
	      	  	url:'${cpath}/checkExists',
	      	  	data:{"id":id,"mobile":mobile},
	      	  	error:function(){return [false, "请求错误"];},
	      	  	success:function(msg)
	      	  	{
	      	  		if(msg=='1')
	      	  		{
	      	  			result =  [true , ''];
	      	  			$.ajax({
				      	  	async : false,
				      	  	type:'POST',
				      	  	url:'${cpath}/checkEmailExists',
				      	  	data:{"id":id,"email":email},
				      	  	error:function(){return [false, "请求错误"];},
				      	  	success:function(msg)
				      	  	{
				      	  		if(msg=='1')
				      	  		{
				      	  			result =  [true , ''];
				      	  		}
				      	  		else if(msg=='2')
				      	  		{
				      	  			result =  [false , '您输入的邮箱已经存在，请校对！'];
				      	  			return result;
				      	  		}
				      	  		else
				      	  		{
				      	  			result =  [false , '未知错误！'];
				      	  		}
				      	  	}
				      	  });
	      	  		}
	      	  		else if(msg=='2')
	      	  		{
	      	  			result =  [false , '您输入的手机号已经存在，请校对！'];
	      	  			return result;
	      	  		}
	      	  		else
	      	  		{
	      	  			result =  [false , '未知错误！'];
	      	  		}
	      	  	}
	      	  });
	      	  return result;
		}
	</script>
	<body>
			<table id="infoContent">
	    	 </table>
	    	 <div id="pInfoContent"></div>
	</body>
</html>