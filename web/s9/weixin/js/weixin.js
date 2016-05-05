var grid = null;//表格对象
var m; 	//弹出窗口对象
// 查询用户信息列表数据
function findVipInfoList(url){
	var pageSize=200;
	grid = $("#maingrid").ligerGrid({
		width:'100%',
		height:'100%',
		onAfterShowData:function()
		{
			$(".l-grid-row-cell-inner").css("height","auto"); //单元格高度自动化，撑开
			var i=0;
			$("tr",".l-grid2","#maingrid").each(function ()
			{
				$($("tr",".l-grid1","#maingrid")).css("height",$(this).height()); //2个表格的tr高度一致
				i++;
			});
		},
		headerRowHeight:28,
		rowHeight:26,
		checkbox: true,
		columns: [

			{ display: '昵称', name: 'nickname', width: '8%',minWidth: 100 },
			{ display: '头像', name: 'headimgurl', width: '15%',minWidth: 200,render:function(r){

				return '<img src='+r.headimgurl+' width="200" height="100">';
			}},
			{ display: '性别', name: 'sex',  width: '3%',minWidth: 30 },
			{ display: 'openid', name: 'openid', width: '20%',minWidth: 160},
			{ display: '国家', name: 'country', width: '4%',minWidth: 60 },
			{ display: '省份', name: 'province',  width: '5%' ,minWidth: 60},
			{ display: '城市', name: 'city', width: '5%' ,minWidth: 60},
			{ display: '关注时间', name: 'subscribe_time', width: '10%' ,minWidth: 160}


		], url:url, pageSize:20,enabledEdit: true,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize",onSelectRow:onClickRow
//          , onDblClickRow : function (data, rowindex, rowobj)
//                {
//    	  					 var obj=$(".l-dialog");
//    	  					 if(obj.length!=0){
//    	  						 $(obj).close();
//    	  					 }
//    	   			  $.ligerDialog.prompt('编辑备注',data.remark, function (yes,value) {
//    	   				if(yes){
//    	   					updateUserRemark(data.userId,value);
//    	   				}
//
//    	   			});
//    	   			   $(".l-dialog-close").live("click",function(){
//    	   				 var obj=$(".l-dialog");
//    	   				 obj.remove();
//    	   			 })
//                    //$.ligerDialog.alert('选择的是' + data.userId);
//                }

	});
	$("#pageloading").hide();


}
/**
 *重新加载数据
 */
function addUserInit(){
	var url="../wx/loadData.shtml";
	var secret=$("#secret").val();
	var appid=$("#appid").val();
	if(appid==''||null==appid){
		alert("请输入appid");
		return ;
	}else if(null==secret||''==secret){
		alert("请输入秘钥");
		return ;
	}else{
		$.ajax( {
			type :"post",
			url :url,
			data:{"appid":appid,"secret":secret},
			success : function(data) {
				alert("加载数据完成");
				grid.loadData();
			},
			error : function() {
				$.ligerDialog.error("error");
			}
		});
	}


}
/**
 * 拓展行点击事件radio选中
 **/
function onClickRow(rowdata, rowindex, rowDomElement) {
	var $v = $(rowDomElement);
	$v.find(":checkbox").attr("checked","checked")
}
//群发站内信
function sendMessage(){
	var openid='';
	var rows = grid.getCheckedRows();
	var length=rows.length;
	if(length<1){
		$.ligerDialog.error("请至少选择一条会员信息");
		return false;
	}
	var rows = grid.getCheckedRows();
	$(rows).each(function (index)
	{
		if(index==rows.length-1){
			openid += this.openid;
		}else{
			openid += this.openid + ",";
		}

	});
	var appid=$("#appid").val();
	var secret=$("#secret").val();
	var templateId=$("#templateId").val();
	if(''==appid||null==appid){
		$.ligerDialog.error("微信AppId不能为空");
		return false;
	}
	if(''==secret||null==secret){
		$.ligerDialog.error("秘钥不能为空");
		return false;
	}
	if(''==templateId||null==templateId){
		$.ligerDialog.error("模板消息ID不能为空");
		return false;
	}
	var url="../sendTemplateMessage/send.shtml";
	$.ajax( {
		type :"post",
		url :url,
		data:{"appid":appid,"appsecret":secret,"openids":openid,"templateId":templateId},
		success : function(data) {
			if(data.success){
				$.ligerDialog.success("发送成功");
			}else{
				$.ligerDialog.error("模板消息ID无效,请联系管理员");
			}

			//grid.loadData();
		},
		error : function() {
			$.ligerDialog.error("error");
		}
	});
	//var url="../vipManage/initPage.shtml?userId="+userId;
	//m = $.ligerDialog.open({ height: 400,width:600, title:'群发站内信', url: url, showMax: true, showToggle: true, showMin: true, isResize: true, slide: false });
}
