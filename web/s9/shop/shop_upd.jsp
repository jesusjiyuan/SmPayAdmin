<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=utf-8"	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>修改用户管理</title>
		<!-- 引用本页面JS、CSS样式静态资源 -->
		<%@include file="/s9/common/common.jsp"%>
		<!-- 用户管理操作JS静态资源的引用 -->
		<script type="text/javascript" src="<%=basePath %>/s9/shop/js/shopList.js"></script>
		<script type="text/javascript" src="<%=basePath %>/s9/res/js/util/md5.js"></script>
		<script type="text/javascript">
		 $(function (){
				var flag="${backInfo}";
				if(flag=='1'){
					window.parent.reload("操作成功");
				}else if(flag=='0'){
					$.ligerDialog.error("操作失败");
					return;
				}
			});
		</script>
	</head>
	<body class="dialogBody">
		<form id="form1" action="../Shop/update.shtml" name="form1" method="post" onsubmit="return check();">
			<input type="hidden" id="id" name="id" value="${bo.id}">
			<div style="height: 5px;"></div>
			<table width="770" height="227" align="center" cellpadding="2" cellspacing="0">
				<tbody>
				<tr>
					<td width="400">
						<div class="z-legend"><b>基本信息</b></div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tbody>
							<tr>
								<td width="39%" height="30" align="right">
									店铺名称：
								</td>
								<td width="61%">
									<input type="text" id="name" name="name"  style="width: 300px;" value="${bo.name}"/>
								</td>
							</tr>
							<tr>
								<td width="39%" height="30" align="right">
									店铺简介：
								</td>
								<td width="61%" >
									<textarea rows="" cols="" id="remark" name="remark" style="width: 300px;">${bo.remark}</textarea>
								</td>
							</tr>
							<tr>
								<td width="39%" height="30" align="right">
									微信appId：
								</td>
								<td width="61%" id="tdUserName">
									<input type="text" id="appId" name="appId" value="${bo.appId}"  style="width: 300px;"/>
									<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">
									微信秘钥：
								</td>
								<td>
									<input type="text" value="${bo.appSecret}" id="appSecret" name="appSecret"  style="width: 300px;"/>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">
									商户号：
								</td>
								<td>
									<input type="text" id="partenKey" name="partenKey" value="${bo.partenKey}" style="width: 300px;"/>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">
									商户秘钥：
								</td>
								<td>
									<input type="text" id="partenValue" name="partenValue" value="${bo.partenValue}" style="width: 300px;"/>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">
									支付主体：
								</td>
								<td>
									<input type="text" id="preBody" name="preBody" value="${bo.preBody}"  style="width: 300px;"/>
								</td>
							</tr>
							<tr id="tr_Password2">
								<td height="30" align="right">
									密码：
								</td>
								<td>
									<input type="password" id="password" name="password" value="${bo.password}" style="width: 300px;"/>
									<span style="color: red; padding-left: 2px; padding-top: 13px;">*</span>
								</td>
							</tr>
							<tr>
								<td height="30" align="right">
									移动电话：
								</td>
								<td>
									<input type="text" id="mobile" name="mobile" value="${bo.mobile}"  style="width: 300px;"/>
								</td>
							</tr>

							</tbody>
						</table>
					</td>

				</tr>
				</tbody>
			</table>
			<div class="aui_buttons">
				<button value="确定" id="z-dialog-2-OKButton" onclick="forSave();"
						class="z-dlg-button z-dialog-okbutton aui_state_highlight">
					确定
				</button>
			</div>
		</form>
	</body>
</html>
