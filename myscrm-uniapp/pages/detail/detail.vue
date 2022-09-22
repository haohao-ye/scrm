<template>
    <view >
				<image 
				:src="src" mode="scaleToFill" style="width: 100%;"></image>
				<view class="price">
					<text>￥{{goods.price}}</text>
					<!-- <text>￥{{goodstop.sellPrice}}</text> -->
				</view>
				
				<view class="goodsname">
					商品名:{{goods.name}}
				</view>
				<view class="line"> </view>
				<view class="box2">
					<view>货号:{{goods.id}}</view>
					<view>库存:{{goods.inventory}}</view>
				</view>
				<view class="line">		
				</view>
				<view class="box3">
					<view class="title">
						详情介绍
					</view>
					<view class="contonent">
						<!-- <rich-text :nodes="goods.remark"></rich-text> -->
						{{goods.remark}}
					</view>
				</view>
					
                <view class="album" >
                    <view class="album__content">
                        <u-album :urls="urls1" keyName="src2" singleSize="300px"</u-album>
                    </view>
                </view>
				<view class="but">
					<input class="but-1" v-model="quantity" placeholder="输入数量" align="center" style="font-size: 15px;padding-left: 20px;" ></input>
					<text class="but-2" @tap.stop="genOrder" style="font-size: 15px;" v-if="goods.inventory > 0">生成订单</text>
					<text class="but-2" @tap.stop="showError" style="font-size: 15px;" v-if="goods.inventory <= 0">生成订单</text>
					<!-- <text class="but-3" @tap.stop="out">立即出库</text> -->
				</view>
				
    </view>
</template>

<script>
    export default {
        data() {
            return {
				salemanId:'',
                albumWidth: 0,
				goodsName:"",
				src:"",
				goods:{
					quantity:"",
				},
				price:0,
				quantity:"",
                urls1: [{
                    src2: 'https://cdn.uviewui.com/uview/album/1.jpg',
                }]
            }
        },
		onLoad(e) {
			// console.log(e.param,"dddddddddddddd");
			this.goods=JSON.parse(decodeURIComponent( e.param));
			this.salemanId = JSON.parse(e.salemanId);
			this.src="/static/"+this.goods.id+".png";
			console.log(JSON.stringify(this.goods));
		},
		methods:{
			//生成订单
			genOrder(){
				if(this.quantity==''){
					return uni.$u.toast("请输入数量");
				}
				this.goods.quantity=this.quantity;
				console.log(this.goods,"rrrrrrrrrrrrrrrr");
				uni.navigateTo({
					url:"/pages/order/order?param="+encodeURIComponent(JSON.stringify(this.goods))+"&salemanId="+this.salemanId
				})
				
			},
			showError(){
				return uni.$u.toast("库存为0，无法生成订单！");
			},
			//出库
			// out(){
			// 	this.goods.quantity=this.quantity;
			// 	console.log(this.goods,"rrrrrrrrrrrrrrrr");
			// 	uni.navigateTo({
			// 		url:"/pages/index/order/out?param="+encodeURIComponent(JSON.stringify(this.goods))
			// 	})
			// }
		}
    }
</script>

<style lang="scss">
    .album {
        @include flex;
        align-items: flex-start;
		width: calc(100vw);
    
        &__content {
             margin-left: 10px;
             flex: 1;
         }
    }
	.price{
		padding: 10rpx;
		color: #b50e03;
		line-height: 80rpx;
		text:nth-child(2){
			font-size: 30rpx;
			color: #eee;
			text-decoration: line-through;
			margin-left: 30rpx;
		}
	}
	.goodsname{
		padding: 10rpx;
		line-height: 60rpx;
	}
	.line{
		width: 750rpx;
		height: 15rpx;
		background-color:#eee;
	}
	.box2{
		padding: 0 10px;
		font-size: 32rpx;
		line-height: 70rpx;
	}
	.box3{
		padding: 10rpx;
		padding-bottom: 50px;
		.title{
			font-size: 32rpx;
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
	.but{
		position: fixed;
		bottom: 0;
		width: 750rpx;
		height: 90rpx;
		border-top: 1rpx solid #e3e1e4;
		display: flex;
		align-items: center;
		justify-content: space-around;
		.but-1{
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
		.but-2{
			@extend .but-1;
			color: #3f5c6e;
			border: 1rpx solid #3f5c6e;
		}
		.but-3{
			@extend .but-1;
			border: none;
			color: #fff;
			background-color: #3f5c6e;
			border: 1rpx solid #3f5c6e;
		}
	}
</style>