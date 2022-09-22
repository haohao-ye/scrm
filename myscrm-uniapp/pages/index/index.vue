<template>
	<view>
		<view class="content">
			<image class="logo" src="/static/logo1.png"></image>
			<text>欢迎使用MyScrm</text>
		</view>
		<view style="padding-top: 40px;">
			<navigator url="/pages/login/login" hover-class="navigator-hover">
				<button type="default" size="default" style="width: 120px;">登录使用</button>
			</navigator>
			<view style="padding-top: 5%;">
				<button type="default" size="default" style="width: 120px;" @click="getUserInfo">微信登录</button>
			</view>
			
		</view>
	</view>


</template>

<script>
	export default {
		data() {
			return {
				title: '欢迎使用MyScrm'
			}
		},
		onLoad() {

		},
		methods: {
			login() {
				
			},
			wxlogin(openid) {
				console.log(openid)
				wx.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/employee/employee/wxlogin',
					method: "post",
					header: {
						"Content-Type": "application/x-www-form-urlencoded"
					},
					data: {
						openid: openid
					},
					dataType: 'json',
					success: (response) => {
						let id = response.data.data;
						console.log("idok?"+id)
						if (response.data.code == 20001) {
							console.log("登陆成功！");
							wx.showToast({
								title: '登陆成功！',
								icon: 'none',
								duration: 2000
							})
							console.log("idok?"+id)
							wx.reLaunch({
								//跳转到首页
								url: '/pages/home/home?id='+JSON.stringify(id)
							})
						}else{
							wx.showToast({
								title:'微信未绑定员工号，请先使用员工号和密码登录',
								icon:'none',
								duration: 5000
							})
						}
					},
				})
			},
			/**
			 *
			 * 获取用户信息
			 */
			getUserInfo() {
				let _this = this
				uni.getUserProfile({
					desc: '登录后可同步数据',
					success: async (obj) => {
						// this.userInfo = obj.userInfo;
						// 调用 action ，请求登录接口
						uni.login({
							success(res) {
								if (res.code) {
									//向后端发起网络请求
									console.log("ok1");
									wx.request({
										url: 'http://admin.dkhaohao.shop/prod-api/testopenid',
										data: {
											code: res.code
										},
										success: (response) => {
											console.log(response.data);
											_this.wxlogin(response.data);
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
			},

		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 300rpx;
		width: 300rpx;
		margin-top: 300rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #e2e2e2;
	}
</style>
