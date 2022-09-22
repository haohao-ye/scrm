<template>
 	<view >
 		<!-- <u-tabs :list="list1" lineWidth="50" 
			  itemStyle="padding-left: 17px; padding-right: 15px; height: 34px;" 
			   @click="click()"></u-tabs> -->
 		<view class="inv-h-w">
 			<view :class="['inv-h',Inv==0?'inv-h-se':'']" @click="changeTab(0)">全部</view>
 			<view :class="['inv-h',Inv==1?'inv-h-se':'']" @click="changeTab(1)">待付款</view>
 			<view :class="['inv-h',Inv==2?'inv-h-se':'']" @click="changeTab(2)">待配送</view>
 			<view :class="['inv-h',Inv==3?'inv-h-se':'']" @click="changeTab(3)">配送中</view>
 			<view :class="['inv-h',Inv==4?'inv-h-se':'']" @click="changeTab(4)">已完成</view>
 		</view>
 		<!-- 内容 -->
 		<view class="swiper-content">
 			<view v-if="Inv==Inv"  class="content style-default">
 				<goods-order :list="orderlist"  :types="Inv" class="item"></goods-order>
				
 			</view>
 		</view>
		
		<!-- 通用的底部导航栏 -->
		<u-tabbar
			:value="value6"
			@change="name => value6 = name"
			:fixed="true"
			:placeholder="true"
			:safeAreaInsetBottom="true"
		>
			<u-tabbar-item text="首页" icon="home" name="home" @click="goToHome()"></u-tabbar-item>
			<u-tabbar-item text="客户" icon="chat" name="client" @click="goToClient()"></u-tabbar-item>
			<u-tabbar-item text="订单" icon="order" name="orders" ></u-tabbar-item>
			<u-tabbar-item text="我的" icon="account" name="account" @click="goToPersonalCenter()"></u-tabbar-item>
		</u-tabbar>


 	</view>

 </template>

 <script>
 	export default {
 		data() {
 			return {
 				Inv: 0,
 				orderlist: [],
 				pageindex: 1,
 				flag: false,
 				list: [], //真正的列表数据
				value6:'2',
				id:'',

 			}


 		},
 		onLoad(options) {
			if(JSON.stringify(options)!='{}'){
				this.id = JSON.parse(options.id);
				 console.log(options.id);
			}
 			this.getorderlist();
 			// this.list=this.orderlist;
 			// console.log("获取yes");
 			// console.log(this.orderlist.data);
 		},
 		methods: {
 			getorderlist() {
 				let that = this;
 				uni.request({
 					url: 'http://admin.dkhaohao.shop/prod-api/api/orders/orders/lists',
 					data: {
						salesmanId: this.id,
					},
 					success: (res) => {
 						if (res.data.code == 20000) {
 							// console.log(res.data.data instanceof Array);
 							console.log(JSON.stringify( res.data.data),'--------------------')
 							that.orderlist = res.data.data;
 							that.list = res.data.data;
							
 							 // console.log(that.orderlist+"获取数据成功");
 						} else {
 							return uni.$u.toast("连接失败")
 						}
 					}
 				})
 			},
			goToPersonalCenter(){
				uni.redirectTo({
					url:'/pages/person/personalCenter?id='+this.id
				});
			},
			goToHome(){
				uni.redirectTo({
					url:'/pages/home/home?id='+this.id
				});
			},
			goToOrders(){
				uni.redirectTo({
					url:'/pages/orders/orders?id='+this.id
				});
			},
			goToClient(){
				uni.redirectTo({
					url:'/pages/client/client?id='+this.id
				});
			},
			detail(){
				console.log("kkkkkkkkkk");
				uni.navigateTo({
					// url:"/pages/orderdetail/orderdetail?param="+encodeURIComponent(JSON.stringify(e))
					url:"/pages/orderdetail/orderdetail",
					
				})
			},

 			changeTab(Inv) {
 				var that = this;
 				that.Inv = Inv;
 		
 				this.orderlist = this.list.filter(item => {
					// console.log(item.types+'----------------'+Inv)
 					if (item.types == Inv) {
 						return item;
 					}
 				});
				// console.log(this.orderlist);
				if(Inv == 0){
					this.orderlist = this.list;
				}
 				//滚动到顶部
 				uni.pageScrollTo({
 					duration: 0, //过渡时间必须为0，否则运行到手机会报错
 					scrollTop: 0 //滚动到实际距离是元素距离顶部的距离减去最外层盒子的滚动距离（如res.top - data.top）
 				})
 			},	
 		},
 		onPullDownRefresh() {
 			this.pageindex = 1;
 			this.orderlist = [];
 			this.flag = false;
 			setTimeout(() => {
 				this.getorderlist();
 				uni.stopPullDownRefresh();
 			}, 1000)
 		},
 		// 监听触底
 		onReachBottom() {
 			if (this.orderlist.length < this.pageindex * 10) return this.flag = true
 			this.pageindex++;
 			this.getorderlist();
 		},
 	}
 </script>

 <style lang="scss">
 	.inv-h-w {
 		top: 0;
 		background-color: #FFFFFF;
 		height: 100upx;
 		display: flex;
 		// position: fixed;

 		width: 100%;
 	}

 	.inv-h {
 		font-size: 30upx;
 		flex: 1;
 		text-align: center;
 		height: 100upx;
 		line-height: 100upx;
 	}

 	.inv-h-se {
 		color: #FF9014;
 		font-weight: bold;
 		border-bottom: 6rpx solid #FF9014;
 	}

 	.swiper-content {
 		margin-top: 30rpx;

 		.content {
 			width: 900rpx;
 			// margin: 0 auto 60rpx;
 			// margin-top: 40rpx;
 		}
 	}
 </style>

 