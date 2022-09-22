<template name="goods-order">
	<view>
		<view class="list"  v-for="item in list" :key='item.consumptionListId' @click="navigoodetail(item)">
			<view class="left">
				<image :src="item.img" class="img"></image>
			</view>
			<view class="center flex-1 ml-3 ">
				<view class="name">
					<view class="text-truncate">客户：{{item.clientName}}</view>
					<view class="size">商品：{{item.goodsName}}</view>
				</view>
				<view class="price">
					总价：￥{{item.totalAmount}}
				</view>
			</view>
			<view class="right mr-2">
				<view
					v-if="types==0"
					:class="['types d-flex align-items-center justify-content-center',item.types==1||item.types==2||item.types==3?'types_dfk':item.types==4?'types_ywc':item.types==9?'types_yqx':'']">
					{{item.types==1?"待付款":item.types==2?"待发货":item.types==3?"配送中":item.types==4?"已完成":item.types==9?"已取消":"无"}}
				</view>
				<view v-if="types==1" class="types types_dfk d-flex align-items-center justify-content-center">
					待付款
				</view>
				<view v-if="types==2" class="types types_dfk d-flex align-items-center justify-content-center">
					待发货
				</view>
				<view v-if="types==3" class="types types_dfk d-flex align-items-center justify-content-center">
					配送中
				</view>
				<view v-if="types==4" class="types types_ywc d-flex align-items-center justify-content-center">
					已完成
				</view>
				<view v-if="types==9" class="types types_yqx d-flex align-items-center justify-content-center">
					已取消
				</view>
				<view class="count">
					商品总数：x{{item.quantity}}
				</view>
			</view>
		</view>
	</view>
</template>
 
<script>
	export default {
		name: "goods-order",
		data() {
			return {
 
			};
		},
		// 此处定义传入的数据,属性
		props: {
			list: {
				type: Array,
				value: null
			},
			types: {
				type: Number,
				value: null
			}
		},
		//组件生命周期
		created: function(e) {
			
		},
		methods:{
			navigoodetail(e){
				
				uni.navigateTo({
					url:"/pages/orderdetail/orderdetail?param="+encodeURIComponent(JSON.stringify(e)) 
				})
			},
		}
 
	}
</script>
 
<style lang="scss">
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
 
		.left {
			display: flex;
			.img {
				width: 150rpx;
				height: 136rpx;
				margin-left: 10rpx;
				border-radius: 8%;
			}
		}
 
		.center {
			width: 170rpx;
			display: flex;
			height: 136rpx;
			flex-direction: column;
			justify-content: space-between;
 
			.name {
				font-size: 30rpx;
				color: #3E3E3E;
				margin-top: 10rpx;
				white-space: nowrap;
				text-overflow: ellipsis;
				overflow: hidden;
			}
 
			.size {
				margin-top: 4rpx;
				font-size: 26rpx;
				color: #8D8D8D;
			}
 
			.price {
				color: #DD6C26;
				font-weight: bold;
			}
		}
 
		.right {
			display: flex;
			height: 136rpx;
			flex-direction: column;
			justify-content: space-between;
			align-items: center;
 
			.count {
				font-size: 26rpx;
				color: #8D8D8D;
			}
 
			.types {
				width: 120rpx;
				height: 50rpx;
				color: #FFFFFF;
			}
 
			.types_dfk,
			.types_dfh,
			.types_dsh,
				{
				background-color: #2994FA;
				border-radius: 12%;
				
			}
 
			.types_ywc {
				background-color: #0BC261;
				border-radius: 12%;
			}
 
			.types_yqx {
				background-color: #FF976A;
				border-radius: 12%;
			}
 
		}
	}
</style>