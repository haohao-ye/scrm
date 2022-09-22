<template>
	<view style="padding-top: 15%;">
		<view >
			<!-- 2.0.19支持autoBack，默认为false -->
			<u-navbar
			    title="修改密码"
			    :autoBack='true'
				:bgColor="bgColor"
			>
			</u-navbar>
		</view>
		<view style="padding-top: 25%;padding-left: 13%;padding-right: 6%;">
			<u-form labelWidth="100">
				<u-form-item
						label="您的工号为: "
						prop="userInfo.id"
						ref="item1"
				>
					<u--input disabled='true' v-model="userInfo.id"></u--input>
				</u-form-item>
				<u-form-item
						label="请输入旧密码: "
						prop="userInfo.password"
						ref="userInfo.password"
						style="padding-top: 8%;"
						
				>
					<u--input password='true' v-model='userInfo.password' clearable></u--input>
				</u-form-item>
				<u-form-item
						label="请输入新密码: "
						prop="userInfo.newPassword"
						ref="item1"
						style="padding-top: 8%;"
				>
					<u--input password='true' v-model='userInfo.newPassword' clearable></u--input>
				</u-form-item>
				<u-form-item
						label="请确认新密码: "
						prop="userInfo.newPassword"
						ref="item1"
						style="padding-top: 8%;"
						
				>
					<u--input password='true' clearable v-model='userInfo.confirmNewPassword' @blur="checkPassword()"></u--input>
				</u-form-item>
			</u-form>
			<view style="padding-top: 25%;" v-model="userInfo.check">
				<u-button text="确认修改密码" size="large" @click="changePassword()" :disabled="check" ></u-button>
			</view>
			<view style="padding-top: 6%;">
				<text  class="title" style="font-size: 13px;color: #acacac;" @click="GoToResetPassword(e)">忘记密码?</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				bgColor: '#eeeeee',
				userInfo:{
					id:'11111111',
					password: '',
					newPassword: '',
					confirmNewPassword:'',
					check:true,
				},
				
			}
		},
		
		onBackPress() {
			uni.redirectTo({
				url: '/pages/person/personalCenter?id='+this.userInfo.id
			})
		},
		
		onLoad:function(options){
			if(JSON.stringify(options)!='{}'){
				this.userInfo.id = JSON.parse(options.id);
				 // console.log(options.id);
			}
		},
		
		methods: {
			GoToResetPassword(){
				let id = this.userInfo.id;
				uni.navigateTo({
					url:'/pages/person/password/resetPassword?id='+JSON.stringify(id)
				})
			},
			changePassword(){
			     let that = this;
				 console.log(JSON.stringify(that.userInfo));
			     uni.request({
			      url: 'http://admin.dkhaohao.shop/prod-api/api/employee/employee/changePsd',
				  method:"GET",
			      data: {
					  id: that.userInfo.id,
					  password: that.userInfo.password,
					  newPassword: that.userInfo.newPassword,
				  },
			      success: (res) => {
			       if (res.data.code == 20000) {
			        console.log("修改密码成功！");
					uni.redirectTo({
						url:'/pages/person/personalCenter?id='+this.userInfo.id
					})
			       } else {
					   if(res.data.code == 50010){
						    return uni.$u.toast("密码错误！");
					   }
			       return uni.$u.toast("连接失败！");
			       }
			      }
			     })
			    },
				checkPassword(){
					if(this.userInfo.newPassword != this.userInfo.confirmNewPassword){
						 return uni.$u.toast("两次输入的新密码不一致！");
					}
				},
				
		}
	}
</script>

<style>
 .title{
            text-align: center;
            display: block;
        }
</style>
