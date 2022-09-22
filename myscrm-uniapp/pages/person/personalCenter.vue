<template>
	<view>
		<view style="padding-top: 7%;padding-left: 10%;">
			<u-row gutter=80>
				<u-col span=4>
					<u--image src="/static/default_avatar.jpg" shape="circle" width=120 height=120 mode="aspectFill"></u--image>
				</u-col>
				<u-col span=8>
					<view style="padding-top: 5%;padding-bottom: 5%;">
						<text style="font-size: 22px;">{{form.name}}</text>
					</view>
					<view style="padding-top: 5%;">
						<text style="font-size: 11;color: #969696;">{{greeting}}</text>
					</view>
				</u-col>
			</u-row>
		</view>
		<view style="padding-top: 3%;">
			<u-divider text="分割线" :dot="true"></u-divider>
		</view>
		<view>
			<view style="padding-left: 25%;padding-right: 25%;" class="title">
						<u-button 
						type="primary" 
						text="个人信息" 
						shape="square" 
						size="large"
						:plain='true'
						@click="goToInformation()"></u-button>
			</view>
		</view>
		<view style="padding-top: 9%;">
			<view style="padding-left: 25%;padding-right: 25%;" class="title">
				<u-button type="primary" text="通知" shape="square" size="large"  :plain='true' @click="goToMessage()"></u-button>
			</view>
		</view>
		<view style="padding-top: 9%;">
			<view style="padding-left: 25%;padding-right: 25%;" class="title">
				<u-button type="primary" text="修改密码" shape="square" size="large" :plain='true' @click="goToPassword()"></u-button>
			</view>
		</view>
		<!-- <view style="padding-top: 7%;">
			<view style="padding-left: 25%;padding-right: 25%;" class="title">
				<u-button type="primary" text="设置" shape="square" size="large" :plain='true' @click="goToConfig()"></u-button>
			</view>
		</view> -->
		
		<view style="padding-top: 13%;padding-bottom: 15%;">
			<view style="padding-left: 30%;padding-right: 30%;" class="title">
				<u-button  type="primary" text="退出登录" shape="circle" size="large" color="#e53b2e" @click="show = true"></u-button>
			</view>
		</view>
		
		<u-popup
				:safeAreaInsetBottom='true'
				:safeAreaInsetTop='true'
				:mode="popupData.mode"
				:show="show"
				:round="popupData.round"
				:overlay="popupData.overlay"
				:borderRadius="popupData.borderRadius"
				:closeable="popupData.closeable"
				:closeOnClickOverlay="popupData.closeOnClickOverlay"
				@close="close"
				@open="open"
				>
				<view
					class="u-popup-slot"
					:style="{
						width: ['bottom', 'top'].includes(popupData.mode) ? '750rpx' : '200px',
						marginTop: ['left', 'right'].includes(popupData.mode) ? '480rpx' : '0',
					}"
					style="padding-bottom: 10%;"
				>
				<view style="padding-top: 10%;padding-left: 32%;padding-right: 32%;">
					<u-text text='确定退出当前账号？'></u-text>
				</view>
				<view style="padding-top: 3%;padding-left: 20%;padding-right: 20%;">
					<u-text text='退出后不会清理历史数据,下次登录此账号仍可正常使用' size="10" color="#7e7e7e"></u-text>
				</view>
				<view style="padding-top: 5%;padding-left: 20%;padding-right: 20%;">
					<u-row gutter="20">
						<u-col span = 6>
							<u-button type="success" text="取消" shape="circle" color="#dcdcdc" size="normal" @click="show = false" ></u-button>
						</u-col>
						<u-col span = 6>
							<u-button type="success" text="确定" shape="circle" color="#e53b2e" size="normal" @click="logOut()" ></u-button>
						</u-col>
					</u-row>
					
				</view>
					
				</view>
		</u-popup>
		
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
			<u-tabbar-item text="订单" icon="order" name="orders" @click="goToOrders()"></u-tabbar-item>
			<u-tabbar-item text="我的" icon="account" name="account"></u-tabbar-item>
		</u-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
				popupData: {
					overlay: true,
					mode: 'bottom',
					closeable: true,
					closeOnClickOverlay: true
				},
				greeting:'每一天都是新的开始',
				form:{
					id:'11111111',
				},
				value6: '3'
			}
		},
		
		onLoad:function(options){
			if(JSON.stringify(options)!='{}'){
				this.form.id = JSON.parse(options.id);
				 console.log(options.id);
				 console.log(JSON.stringify(this.form.id) );
			}
			this.getEmployeeById();
		},
		methods: {
			goToInformation(){
				let id = this.form.id;
				uni.navigateTo({
					url:'/pages/person/information/information?id='+JSON.stringify(this.form.id)
				});
			},
			goToMessage(){
				uni.navigateTo({
					url:'/pages/person/message/message?id='+JSON.stringify(this.form.id)
				});
			},
			goToConfig(){
				uni.navigateTo({
					url:'/pages/person/config/config?id='+JSON.stringify(this.form.id)
				});
			},
			goToPassword(){
				uni.navigateTo({
					url:'/pages/person/password/password?id='+JSON.stringify(this.form.id)
				});
			},
			goToHome(){
				uni.redirectTo({
					url:'/pages/home/home?id='+JSON.stringify(this.form.id)
				});
			},
			goToOrders(){
				uni.redirectTo({
					url:'/pages/orders/orders?id='+JSON.stringify(this.form.id)
				});
			},
			goToClient(){
				uni.redirectTo({
					url:'/pages/client/client?id='+JSON.stringify(this.form.id)
				});
			},
			logOut(){
				this.show = false;
				uni.reLaunch({
					url:'/pages/index/index'
				});
			},
			open() {
			        // console.log('open');
			      },
			      close() {
			        this.show = false
			        // console.log('close');
			},
			getEmployeeById(){
			     let that = this;
				 console.log(this.form.id);
			     uni.request({
			      url: 'http://localhost:8080/api/employee/employee/'+this.form.id,
			      data: {},
			      success: (res) => {
			       if (res.data.code == 20000) {
			        // console.log(res.data.data);
			        // console.log(res.data,'--------------------')
			        that.form = res.data.data;
					console.log(JSON.stringify(that.form));
			        // console.log(that.orderlist+"获取数据成功");
			       } else {
			        return uni.$u.toast("连接失败")
			       }
			      }
			     })
			    },
		}
	}
</script>

<style>
	.u-popup-slot {
		width: 200px;
		height: 150px;
		justify-content: center;
		align-items: center;
	}
	.title{
		text-align: center;
		display: block;
	}
</style>
