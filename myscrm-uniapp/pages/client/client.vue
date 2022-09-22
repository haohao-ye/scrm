<template>

	<view>
		<view class="u-demo-block">
			<view class="u-demo-block__content">
				<view class="u-page__tag-item">
					<u-search v-model="searchName" shape="round" :show-action="false" @change="searchClientList()" height="50"  placeholder="请输入姓名"></u-search>
				</view>
			</view>
		</view>
		<view class="list" v-for="item in clientList" :key='item.id' @click="cclick(item)">
			<view class="center flex-1 ml-3 ">
				<view class="name">
					<view class="text-truncate">客户：{{item.clientName}}</view>
				</view>
				<view class="phoneNumber">
					联系电话：
					{{item.phoneNumber}}
				</view>
			</view>
			
			<view class="right mr-2">
					<view v-if="item.contactLabel==0"
						:class="['types d-flex align-items-center justify-content-center',item.contactLabel==0||item.contactLabel==1]">
						{{item.contactLabel==0?"本周待联系":item.contactLabel==1?"本周已联系":"无"}}
					</view>
					<view v-if="item.contactLabel==0" class="types types_dfk d-flex align-items-center justify-content-center">
						本周待联系
					</view>
					<view v-if="item.contactLabel==1" class="types types_ywc d-flex align-items-center justify-content-center">
						本周已联系
					</view>
				</view>
		</view>
		<view style="padding-top: 6%;">
			<u-button type="primary" class="add-btn" text="添加客户" @click="addbtnclick"></u-button>
		</view>
		

		<u-tabbar>
		</u-tabbar>
		<!-- 通用的底部导航栏 -->
		<u-tabbar
			:value="value6"
			@change="name => value6 = name"
			:fixed="true"
			:placeholder="true"
			:safeAreaInsetBottom="true"
		>
			<u-tabbar-item text="首页" icon="home" name="home" @click="goToHome()"></u-tabbar-item>
			<u-tabbar-item text="客户" icon="chat" name="client"></u-tabbar-item>
			<u-tabbar-item text="订单" icon="order" name="orders" @click="goToOrders()"></u-tabbar-item>
			<u-tabbar-item text="我的" icon="account" name="account" @click="goToPersonalCenter()"></u-tabbar-item>
		</u-tabbar>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				Inv: 0,
				clientList: [],
				pageindex: 1,
				flag: false,
				value6:'1',
				list: [], //真正的列表数据
				id:'',
				showList:[],
				searchName:'',
			}


		},
		props: {
			contactLabel: {
				type: Number,
				value: null
			}
		},
		onLoad(options) {
			if(JSON.stringify(options.id) != '{}'){
				this.id = options.id;
				console.log(this.id);
			}
		},
		onShow() {
			this.getClientList();
		},
		methods: {
			scustom() {
				console.log("llllll");

			},
			ssearch() {
				
			},
			cclick(item) {
				console.log(item);
				//跳转到信息页面
				uni.navigateTo({
					url: '/pages/client/detail?param='+ encodeURIComponent(JSON.stringify(item)),
				});

				//				this.$router.push('/pages/client/detail')
			},
			addbtnclick() {
				//跳转到添加页面
				uni.navigateTo({
					url: '/pages/client/add',
				});
				//				this.$router.push('/pages/client/add')
				this.getClientList();
			},
			getClientList() {
				let that = this;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/client/lists',
					method: 'get',
					data: {
						clientGroup: this.id
					},
					success: (res) => {
						if (res.data.code == 20000) {
							console.log(res.data, '--------------------')
							that.clientList = res.data.data.list;
						} else {
							return uni.$u.toast("连接失败")
						}
					}
				})

			},
			searchClientList() {
				let that = this;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/client/lists',
					method: 'get',
					data: {
						clientGroup: this.id,
						clientName: this.searchName,
					},
					success: (res) => {
						if (res.data.code == 20000) {
							console.log(res.data, '--------------------')
							that.clientList = res.data.data.list;
						} else {
							return uni.$u.toast("搜索失败")
						}
					}
				})
			
			},
			goToPersonalCenter(){
							uni.navigateTo({
								url:'/pages/person/personalCenter?id='+this.id
							});
						},
						goToHome(){
							uni.navigateTo({
								url:'/pages/home/home?id='+this.id
							});
						},
						goToOrders(){
							uni.navigateTo({
								url:'/pages/orders/orders?id='+this.id
							});
						},
		}
	};
</script>

<style lang="scss" scoped>
	.add-btn {
		color: #ffffff;
		width: 150rpx;
		iconColor: #1296db;
		plain: true;
		margin-top: 100px;
	}
	
	.list {
		margin-left: 25rpx;
		width: 700rpx;
		height: 208rpx;
		background: #fff;
		box-shadow: 0 8rpx 16rpx 0 rgba(83, 66, 49, 0.08);
		border-radius: 24rpx;
		margin-top: 32rpx;
		display: flex;
		justify-content: space-around;
		align-items: center;
		
		.center {
			width: 170rpx;
			display: flex;
			height: 136rpx;
			flex-direction: column;
			justify-content: space-between;
			
			.name {
				width:500rpx;
				font-size: 30rpx;
				font-weight: bolder;
				color: #3e3e3e;
				margin-top: 10rpx;
				white-space: nowrap;
				text-overflow: ellipsis;
				overflow: hidden;
			}
			
			.phoneNumber {
				font-size: 28rpx;
				color: #939393;
			}
		}
		
		.right {
			display: flex;
			height: 136rpx;
			flex-direction: column;
			justify-content: space-between;
			align-items: center;
		 
			.types {
				width: 180rpx;
				height: 50rpx;
				color: #FFFFFF;
				text-align:center;
				border-radius: 5px;
			}
		 
			.types_dfk {
				background-color: #2994FA;
			}
			
			.types_ywc {
				background-color: #0BC261;
			}
		 
		}
	}
</style>
