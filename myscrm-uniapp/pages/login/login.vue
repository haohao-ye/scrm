<template>
	<!--pages/dologin/dologin.wxml-->
	<view >
		<form @submit.prevent='doLogin'>
			<!--账号-->
			<view class="account" style="padding-top: 25%;">

				<label class="title">账号</label>
				<input name="username" value='20200902' class="inputText" placeholder="请输入账号" />
			</view>
			<view class="hr"></view>
			<!--密码-->
			<view class="account" style="padding-top: 10%;">
				<label class="title">密码</label>
				<input name="password" value='18830505' class="inputText" password="true" placeholder="请输入密码" />
			</view>
			<view class="hr"></view>
			<!--按钮-->
			<view class='backColor' style="padding-top: 12%;">
				<button class="btn" formType="submit" open-type='getUserInfo'>登录</button>
			</view>
		</form>
	</view>
</template>

<script>
	export default {
		methods: {
			
			
			
			doLogin(e) {
				console.log("进入登录事件" + e.detail.value.username); //输出表单的数据
				console.log("进入登录事件" + e.detail.value.password); //输出表单的数据
				let _this = this;
				let name = e.detail.value.username;
				let pass = e.detail.value.password;
				wx.request({
					url: "http://localhost:8080/api/employee/employee/doLogin",
					method: "post",
					header: {
						"Content-Type": "application/x-www-form-urlencoded"
					},
					data: {
						username: name,
						password: pass
					},
					dataType: 'json',
					success: function(res) {
						let id = name;
						console.log("res=:" + res.data.code + " " + res.data.msg);
						if (res.data.code == 20001) {
							console.log(id,"登陆成功！");
							wx.showToast({
								title: '登陆成功！',
								icon: 'none',
								duration: 2000
							})
							wx.reLaunch({
								//跳转到首页
								url: '/pages/home/home?id='+JSON.stringify(id)
							})
						} else if(res.data.code == 20009){
							console.log("询问是否绑定微信");
							_this.getUserInfo(name,pass);
						}
						else {
							wx.showToast({
								title: '用户名或密码有误',
								icon: 'none',
								duration: 2000
							})
							console.log("登陆失败！");
						}
					},
					fail: function(res) {
						console.log("登陆失败！");
					}
				})
			},
			wxbind(openid,name,pass) {
				console.log(openid)
				console.log(name)
				wx.request({
					url: 'http://localhost:8080/api/employee/employee/wxbind',
					method: "post",
					header: {
						"Content-Type": "application/x-www-form-urlencoded"
					},
					data: {
						openid: openid,
						id: name,
					},
					dataType: 'json',
					success: (response) => {
						let id = response.data.data;
						console.log("idok?"+id)
						if (response.data.code == 20001) {
							console.log("绑定成功！");
							wx.showToast({
								title: '绑定成功！',
								icon: 'none',
								duration: 2000
							})
							console.log("idok?"+id)
							wx.reLaunch({
								//跳转到首页
								url: '/pages/home/home?id='+JSON.stringify(id)
							})
						}else if(response.data.code == 25001){
							wx.showToast({
								title:'用户不存在',
								icon:'none',
								duration: 2000
							})
							wx.reLaunch({
								url: '/pages/index/index'
							})
						}
					},
				})
			},
			/**
			 *
			 * 获取用户信息
			 */
			getUserInfo(name,pass) {
				let _this = this
				let _name = name
				let _pass = pass
				uni.getUserProfile({
					desc: '申请绑定',
					
					success: async (obj) => {
						// this.userInfo = obj.userInfo;
						// 调用 action ，请求登录接口
						uni.login({
							success(res) {
								if (res.code) {
									//向后端发起网络请求
									console.log("ok1");
									wx.request({
										url: 'http://localhost:8080/testopenid',
										data: {
											code: res.code
										},
										success: (response) => {
											console.log(response.data);
											_this.wxbind(response.data,name,pass);
										}
									})
								} else {
									console.log('获取openid失败！' + res.errMsg)
								}
							}
						});
					},
					fail: () => {
						uni.showToast({
							title: '授权已取消',
							icon: 'error',
							mask: true,
						});
					},
					complete: () => {
						// 隐藏loading
						uni.hideLoading();
					},
				});
			}
		}
	}
</script>

<style>
	.content {
		margin-top: 40px;
	}

	.account {
		display: flex;
		flex-direction: row;
		padding-left: 20px;
		padding-top: 20px;
		padding-bottom: 10px;
		width: 90%;
	}

	.title {
		margin-right: 30px;
		font-weight: bold;
	}

	.hr {
		border: 1px solid #cccccc;
		opacity: 0.2;
		width: 90%;
		margin: 0 auto;
		background-color: red;
	}

	.btn {
		width: 90%;
		margin-top: 40px;
		background: #007aff !important;
		color: #fff;
	}
</style>
