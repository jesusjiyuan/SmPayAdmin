
	var grid = null;//表格对象
	var m ; 	//弹出窗口对象
	var treeManger; //树对象
	/*
	 * 初始化加载
	 */
	$(function(){
		//查询用户信息列表
		var url = "../Shop/findAllList.shtml" ;
		findUserInfoList(url);
	});

	/*
	 * 查询用户信息列表数据
	 */
	function findUserInfoList(url){
		   var pageSize=20;
	       grid = $("#maingrid").ligerGrid({
	    	    width:'99.6%',
	    	    height:'100%',
				headerRowHeight:28,
				rowHeight:26,
				checkbox: true,
	    		columns: [
					{ display: '手机号码', name: 'mobile', width: '10%' },
					{ display: '店铺名', name: 'name', width: '12%' },
					{ display: '微信appId', name: 'appId', width: '14%' },
					{ display: '微信秘钥', name: 'appSecret', width: '13%' },
					{ display: '商户号', name: 'partenKey',  width: '13%' },
					{ display: '商户秘钥', name: 'partenValue',  width: '13%' },
					{ display: '创建时间', name: 'createTime', width: '10%' },
					/*{ display: '店铺简介', name: 'remark', width: '10%' },*/
					/*{ display: '用户状态', name: 'status', width: '6%', type: 'int', align: 'center' ,render: function (row){
						if(row.status == "1"){
							return "正常";
						}else if(row.status == "0"){
							return "<font color='red'>休息中</font>";
						}
			        }},*/

					{ display: '支付主体', name: 'preBody', width: '8%' }
					/*{ display: '新增日期', name: 'addDate', width: '9%' },
					{ display: '添加人员', name: 'addUser', width: '9%' },*/
					/*{ display: '失效提醒日期', name: 'expHintDate', width: '9%' },*/
					/*{ display: '用户状态', name: 'curStatusCode', width: '6%' }*/

	          ], url:url, pageSize:20,rownumbers:true,pageParmName:"curNo",pagesizeParmName:"curSize"
			 });
	      $("#pageloading").hide();
	}

	/*
	 * 查询用户信息
	 */
	function doSearch(){
		var name = $("#name").val() ;
		var mobile = $("#mobile").val() ;
		var url="../Shop/findAllList.shtml?name="+name+"&mobile="+mobile ;
		findUserInfoList(url);
	}

	/*
	 * 新增用户信息初始化
	 */
	function addUserInit() {
		var url= "../Shop/forAddInitPage.shtml" ;
		m = $.ligerDialog.open({ url: url, height: 618,width:800, title:'添加店铺',showMax: true, showToggle: true, showMin: true,isResize: true ,top:50});
	}

	/*
	 * 新增用户信息
	 */
	function forSave(){
		if(validData()){
			var pwd = hex_md5($("#password").val())
			$("#password").val(pwd);
			$("#form1").attr('onsubmit','');
			$("#form1").submit();
		}
	}

	/*
	 * 修改用户信息初始化
	 */
	function updUserInit() {
		//获取选中记录行
   		var rowid=grid.getSelecteds();
   		var length=rowid.length;
		if(length ==0){
			$.ligerDialog.error("请选择需要修改的信息！");
			return false;
		}
		if(length > 1){
			$.ligerDialog.error("只能选择一个条信息进行修改！");
			return false;
		}
		var id = rowid[0].id ;
		var url= "../Shop/forUpdateInitPage.shtml?id="+id;
		m = $.ligerDialog.open({ url: url, height: 680,width:800, title:'修改店铺',showMax: true, showToggle: true, showMin: true,isResize: true ,top:50});
	}

	/*
	*验证表单数据
	*/
	function validData(){
		var name = $("#name").val();
		if(name.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("店铺名称不能为空！");
			return false;
		}
		var appId = $("#appId").val();
		if(appId.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("appId不能不能为空！");
			return false;
		}
		var appSecret = $("#appSecret").val();
		if(appSecret.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("微信秘钥不能不能为空！");
			return false;
		}
		var partenKey = $("#partenKey").val();
		if(partenKey.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("商户号不能不能为空！");
			return false;
		}
		var partenValue = $("#partenValue").val();
		if(partenValue.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("商户秘钥不能不能为空！");
			return false;
		}
		var preBody = $("#preBody").val();
		if(preBody.replace(/(^\s*)|(\s*$)/,"").length==0){
			$.ligerDialog.error("支付主体不能不能为空！");
			return false;
		}

		return true;
	}
	function check(){
		return false;
	}

	/*
	 *删除选中的用户信息
	 */
	function delUser(){
		//用self参数来标识要删除的用户是否是登录账号本身
		var self = false;
   		var userId ='';
   		//获取选中记录行
   		var rowid=grid.getSelecteds();
   		var length=rowid.length;
		if (length ==0){
			$.ligerDialog.error("请选择要删除的记录！");
			return false;
		}else if(length > 0){
	   		//判断选择的记录中是否含有登录账号本身
			for(var i=0;i<length;i++){
				if(rowid[i].userId=="${userId}"){
					self = true;
				}
			}
			if(self==true){
				$.ligerDialog.error("所选记录中包含当前登录账号，不允许进行删除操作！");
				return;
			}else if($.ligerDialog.confirm('确实要删除选择的'+rowid.length+'条记录吗',
				function (yes) {
					if(yes){
						var url = "../userManage/delUser.shtml";
						//获取删除的ID
						for(var i=0;i<length;i++){
							if(i<length-1){
								userId+=rowid[i].userId+",";
							}else{
								userId+=rowid[i].userId;
							}
						}
						$.ajax({
							type : "post",
							url : url,
							data : {
								"userIds" : userId
							},
							success : function(data) {
								//如果删除成功，则刷新页面！
								if(data["message"]=="1") {
									$.ligerDialog.alert("删除用户成功！");
								}else if(data["mssage"]=="0")  {
									$.ligerDialog.error("删除用户失败！");
								}else{
									$.ligerDialog.error(data["mssage"]);
								}
								//删除用户信息后查询初始化
								findUserInfoList("../userManage/findUserInfoListAjax.shtml") ;
							},
							error : function() {
								$.ligerDialog.error("删除用户失败！");
							}
						});
					}
				}));
		}
	}



	/*
	 * 用户信息成功添加返回重新刷新列表
	 */
	function reload(backInfo){
		$.ligerDialog.success(backInfo);
		grid.loadData(); //重新加载不查询数据库
		m.close();
	}

