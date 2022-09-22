<template>
	<view class="r_detail">
		<view class="mess">
			<view class="mess_01">订单信息</view>
			<view class="mess_02">商品编号：{{goods.id}}</view>
			<view class="mess_02">商品名：{{goods.name}}</view>
			<view class="mess_02">
				<picker @change="pickerChange" :value="clientIndex" :range="clientArray">
					<view class="uni-input">客户：{{clientArray[clientIndex]}}</view>
				</picker>
			</view>
			<view class="mess_02">单价：{{goods.price}}</view>
			<view class="mess_02">数量：{{goods.quantity}}</view>
			<view class="mess_02">总金额：{{goods.price*goods.quantity}}</view>
			<view class="mess_02">订单状态：待付款</view>
			<view class="but">
				<button class="but-1" @click="no">取消</button>
				<button class="but-2" @click="yes"> 确认</button>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				clientIndex: 0,
				clientArray: [],
				clientId: "",
				clientName: "",
				params: {
					/** 商品id */
					goodsId: "",
					/** 商品量 */
					quantity: "",
					/** 订单状态 */
					state: "",
					/** 总额 */
					totalAmount: "",
				},
				goods:{},
				salemanId:'',

			}
		},
		onLoad(e) {
			// console.log(e.param,"uuuuuuuuuuuuuuu")
			this.goods = JSON.parse(decodeURIComponent(e.param));
			console.log(JSON.stringify(this.goods));
			this.salemanId = JSON.parse(e.salemanId)
			console.log(this.salemanId)
			this.getClientList()
		},
		methods: {
			getClientList() {
				let that = this;
				let item = "";
				let i = 0;
				uni.request({
					url: 'http://localhost:8080/api/client/lists',
					method: 'get',
					data: {
						clientGroup: this.salemanId,
					},
					success: (res) => {
						if (res.data.code == 20000) {
							console.log(res.data, '--------------------')
							this.clientList = res.data.data.list;
							item = that.clientList[i].id + ":" + this.clientList[i].clientName;
							console.log(item)
							this.clientArray = [];
							for (i; i < this.clientList.length; i++) {
								item = this.clientList[i].id + ":" + this.clientList[i].clientName;
								this.clientArray.push(item);
							}
						} else {
							return uni.$u.toast("连接失败")
						}
					}
				})

			},


			yes() {
				let that = this;
				let tmp = this.clientArray[this.clientIndex].split(':');
				uni.request({

					url: 'http://localhost:8080/api/orders/orders',
					method: "POST",
					data: {
						goodsId: this.goods.id,
						goodsName: this.goods.goodsName,
						quantity: this.goods.quantity,
						clientName: tmp[1],
						img: "/static/" + this.goods.id + ".png",
						state: "待出库",
						totalAmount: this.goods.price * this.goods.quantity,
						clientId: tmp[0],
					},
					success: (res) => {
						if (res.data.code == 20000) {
							console.log(this.totalAmount,
								"----------------------------------------------------------------------------------"
							);
							uni.$u.toast("订单已生成");
							setTimeout(function() {
								uni.navigateBack({
									delta: 1
								})
							}, 500)


						} else {
							return uni.$u.toast("失败")
						}
					}
				})

			},
			no() {
				uni.navigateBack({
					delta: 1
				})
			},
			pickerChange: function(e) {
				this.clientIndex = e.detail.value
			}


		}
	}
</script>

<style lang="scss">
	.price {
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
		padding: 10rpx;
		line-height: 60rpx;
	}

	.line {
		width: 750rpx;
		height: 15rpx;
		background-color: #eee;
	}

	.box2 {
		padding: 0 10px;
		font-size: 32rpx;
		line-height: 70rpx;
	}

	.box3 {
		padding: 10rpx;
		padding-bottom: 50px;

		.title {
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

	.r_detail {
		background-color: #F5F5F5;
		height: 100vh;
		padding: 10px;

		.head {
			display: flex;
			justify-content: space-between;
			background-color: #F85043;
			padding: 10px 20px 0;

			.head_l {
				padding: 20px;
				color: #FDF1F0;

				.head_l_01 {
					font-size: 16px;
					padding-bottom: 6px;
				}

				.head_l_02 {
					font-size: 13px;
				}
			}

			.head_r img {
				height: 100px;
				width: 120px;
			}
		}

		.address {
			display: flex;
			padding: 20px 15px;
			;
			background-color: #fff;
			margin-bottom: 10px;

			.address_l {
				padding-right: 20px;
				display: flex;
				flex-direction: column;
				justify-content: center;

				img {
					width: 20px;
					height: 20px;
				}
			}

			.address_r {
				flex-grow: 1;

				.address_r_01 {
					display: flex;
					justify-content: space-between;
					font-size: 15px;
					font-weight: 600;
					padding-bottom: 8px;
				}

				.address_r_02 {
					line-height: 20px;
				}
			}
		}

		.tag-e {
			background-color: #fff;
			margin-bottom: 10px;

			.goods {
				display: flex;
				font-size: 14px;
				width: 100%;
				background-color: #ffffff;
				padding: 10px;
				box-sizing: border-box;
				border-bottom: 1px solid #EEF0EF;

				image {
					width: 160rpx;
					height: 160rpx;
				}

				.goods_02 {
					box-sizing: border-box;
					display: flex;
					flex-direction: column;
					height: 160rpx;
					flex-grow: 1;
					justify-content: space-between;
					padding-top: 10rpx;
					padding-left: 25px;
				}

				.goods_title {
					max-height: 40px;
					overflow: hidden;
					line-height: 20px;
					font-weight: 600;
					font-size: 15px;
				}

				.goods_des {
					color: #979797;
				}

				.good_p {
					display: flex;
					justify-content: space-between;

					.good_price {
						color: #F04E42;
						font-weight: 600;
					}
				}

			}
		}

		.total {
			background-color: #fff;
			padding: 12px 15px;
			text-align: right;
			margin-bottom: 10px;
			font-size: 15px;
			padding: 10px
		}

		.mess {
			background-color: #fff;
			padding: 10px;
			margin-bottom: 10px;

			.mess_01 {
				border-left: 2px solid #FD5153;
				padding-left: 10px;
				margin-bottom: 10px;
				font-size: 15px;
				font-weight: 600;
			}

			.mess_02 {
				border-top: 1px solid #F8F8F8;
				padding: 10px;

				.mess_02_1 {
					display: flex;
					line-height: 25px;

					.mess_02_l {
						width: 100px;
					}
				}
			}
		}

		.foot {
			position: fixed;
			bottom: 0px;
			left: 0px;
			z-index: 99;
			border-top: 1px solid #E4E4E4;
			width: 100%;
			background-color: #fff;
			justify-content: flex-end;
			padding: 10px 15px;
			display: flex;
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
	}
</style>
