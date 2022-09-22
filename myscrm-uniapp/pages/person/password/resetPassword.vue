<template>
	<view >
		<u-navbar
		    title="重置密码"
		    :autoBack="true"
			:bgColor="bgColor"
		>
		</u-navbar>
		
		<view style="padding-top: 25%;padding-left: 13%;padding-right: 6%;">
			<u-form labelWidth="100">
				<u-form-item
						label="您的工号为: "
						prop="userInfo.id"
						ref="item1"
				>
					<u--input disabled="true" v-model="userInfo.id"></u--input>
				</u-form-item>
				<u-form-item
						label="请输入手机号: "
						prop="userInfo.phoneNumber"
						ref="item1"
						style="padding-top: 8%;"
						
				>
					<u--input v-model="userInfo.phoneNumber" type="number"></u--input>
				</u-form-item>
				<u-form-item
						label="请确认身份证: "
						prop="userInfo.inNum"
						ref="item1"
						style="padding-top: 8%;"
				>
					<u--input v-model="userInfo.idNum" type="idcard"></u--input>
				</u-form-item>
			</u-form>
			<view style="padding-top: 9%;padding-right: 3%;">
				<u-text text="注意:\n重置密码功能需要验证身份,因此需要验证用户的手机号与身份证号" size="13" color="#a1a1a1"></u-text>
			</view>
			<view style="padding-top: 16%;padding-right: 7%;">
				<u-button text="确认重置密码" size="large" @click="show = true"></u-button>
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
				<view style="padding-top: 10%;padding-left: 36%;padding-right: 36%;">
					<u-text text='确定重置密码？'></u-text>
				</view>
				<view style="padding-top: 3%;padding-left: 20%;padding-right: 20%;">
					<u-text text='密码将被重置为您身份证上的生日信息(如: 19990101), 请及时修改密码, 以防泄露' size="10" color="#7e7e7e"></u-text>
				</view>
				<view style="padding-top: 5%;padding-left: 20%;padding-right: 20%;">
					<u-row gutter="20">
						<u-col span = 6>
							<u-button type="success" text="取消" shape="circle" color="#dcdcdc" size="normal" @click="show = false" ></u-button>
						</u-col>
						<u-col span = 6>
							<u-button type="success" text="确定" shape="circle" color="#e53b2e" size="normal" @click="resetPassword()" ></u-button>
						</u-col>
					</u-row>
					
				</view>
					
				</view>
		</u-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false,
				bgColor: '#eeeeee',
				popupData: {
					overlay: true,
					mode: 'bottom',
					closeable: true,
					closeOnClickOverlay: true
				},
				userInfo:{
					id:'11111111',
					phoneNumber: '',
					idNum: '',
				},
			}
		},
		
		onBackPress() {
			uni.redirectTo({
				url: '/pages/person/password/password'
			})
		},
		
		onLoad:function(options){
			if(JSON.stringify(options)!='{}'){
				this.userInfo.id = JSON.parse(options.id);
				 console.log(options.id);
			}
		},
		
		methods: {
			open() {
			        // console.log('open');
			      },
			      close() {
			        this.show = false
			        // console.log('close');
			},
			resetPassword(){
				let that = this;
				console.log(JSON.stringify(that.userInfo));
				uni.request({
				 url: 'http://admin.dkhaohao.shop/prod-api/api/employee/employee/resetPassword',
				 method:"GET",
				 data: {
									  id: that.userInfo.id,
									  phoneNumber: that.userInfo.phoneNumber,
									  idNum: that.userInfo.idNum,
				 },
				 success: (res) => {
				  if (res.data.code == 20000) {
				   return uni.$u.toast("重置成功！请您尽快修改密码！");
				   this.close();
				  
				  } else {
									   if(res.data.code == 50011){
										    return uni.$u.toast("手机号或身份证号错误！");
									   }
				  return uni.$u.toast("连接失败！");
				  }
				 }
				})
				this.close();
				uni.redirectTo({
					url: '/pages/person/password/password'
				});
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
</style>
