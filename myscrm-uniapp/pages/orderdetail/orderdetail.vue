<template>
	<view >
		<image :src="order.img" mode="scaleToFill" style="width: 100%;" ></image>
		<!-- <image:src="item.img" class="img"></image> -->
		<!-- <swiper indicator-dots>
			<swiper-item v-for="order in swiper">
				<image :src="order.img"></image>
			</swiper-item>
		</swiper> -->
		<!-- <u-row class="price1">
			<u--text  align="center" mode="price1" size=30 color="red">订单总价:{{order.tatalAmount}}</u--text>
		</u-row> -->
		<view class="goodsname">
			商品ID：{{order.goodsId}}
			
		</view>
		<view class="goodsname">
			
			商品名：{{order.goodsName}}
		</view>
		<view class="line"> </view>
		<view class="box2">
			
			<view>客户ID：{{order.clientId}}</view>
			<view>客户名：{{order.clientName}}</view>
			<view>商品数量：{{order.quantity}}</view>
			<view >订单总价：￥{{order.totalAmount}}</view>
			<view >订单状态：{{order.state}}</view>
			<view>订单ID：{{order.consumptionListId}}</view>
			<view>下单时间：{{(order.createTime || '').split('T')[0]}}</view>
		</view>
		<view v-if="order.state == '待付款'" style="padding-top: 2%;padding-left: 15%;padding-right: 15%;padding-bottom: 5%;">
			<u-button text="支付" @click="pay()"></u-button>
		</view>
		<view class="line">
		</view>
		
		
		
		

		<!-- <view class="album">
			<view class="album__content">
				<u-album :urls="urls1" keyName="src2" singleSize="300px" </u-album>
			</view>
		</view>
		<view class="but">
			<input class="but-1" placeholder="输入数量" align="center"></input>
			<text class="but-2" @tap.stop="gm">生成订单</text>
			<text class="but-3" @tap.stop="addCart">立即出库</text>
		</view> -->

	</view>
</template>

<script>
	export default {
		data() {
			return {
				albumWidth: 0,
				swiper:[],
				order:{},
				goodsName: "",
				urls1: [{
					
				}]
			}
		},
		onLoad(e) {
		 console.log(e.param, "----------------------------------");
			this.order = JSON.parse(decodeURIComponent(e.param));
			 console.log(this.order, "lllllllllllllllllll");

		},
		
		methods: {
			pay(){
				this.order.state = '待配送'
				uni.request({
					url:'http://admin.dkhaohao.shop/prod-api/api/orders/orders',
					method:"PUT",
					data:{
						consumptionListId: this.order.consumptionListId ,
						state: this.order.state,
					},
					success(res) {
						console.log("111");
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.swiper{
		height: 700rpx;
		image{
			height:100%;
			width: 100%;
		}
	}
	
	.album {
		@include flex;
		align-items: flex-start;
		width: calc(100vw);

		&__content {
			margin-left: 10px;
			flex: 1;
		}
	}

	.price1 {
		padding: 10rpx;
		color: #b50e03;
		line-height: 80rpx;

		text:nth-child(2) {
			font-size: 30rpx;
			color: #eee;
			text-decoration: line-through;
			margin-left: 30rpx;
		}
	}

	.goodsname {
		padding: 0 20px;
		font-size: 35rpx;
		line-height: 70rpx;
	}

	.line {
		width: 750rpx;
		height: 15rpx;
		background-color: #eee;
	}

	.box2 {
		padding: 0 20px;
		font-size: 35rpx;
		line-height: 80rpx;
	}

	.box3 {
		padding: 0 20rpx;
		padding-bottom: 50px;

		.title {
			font-size: 35rpx;
			padding-left: 10px;
			border-bottom: 1px solid #eee;
			line-height: 70rpx;
		}

		.contonent {
			padding: 10px;
			font-size: 28rpx;
			color: #333;
			line-height: 50rpx;
		}
	}

	.but {
		position: fixed;
		bottom: 0;
		width: 750rpx;
		height: 90rpx;
		border-top: 1rpx solid #e3e1e4;
		display: flex;
		align-items: center;
		justify-content: space-around;

		.but-1 {
			height: 60rpx;
			width: 200rpx;
			display: flex;
			justify-content: center;
			align-items: center;
			border-radius: 50rpx;
			background-color: #FFFFFF;
			color: #ff0006;
			border: 1rpx solid #ff0006;
		}

		.but-2 {
			@extend .but-1;
			color: #3f5c6e;
			border: 1rpx solid #3f5c6e;
		}

		.but-3 {
			@extend .but-1;
			border: none;
			color: #fff;
			background-color: #3f5c6e;
			border: 1rpx solid #3f5c6e;
		}
	}
</style>
