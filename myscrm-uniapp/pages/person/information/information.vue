<template>
	<view >
		<view >
			<!-- 2.0.19支持autoBack，默认为false -->
			<u-navbar
			    title="个人信息"
			    :autoBack="true"
				:bgColor="bgColor"
			>
			</u-navbar>
		</view>
		<view class="u-demo-block" style="padding-top: 20%;padding-left: 12%;">
			<u--text text="工号:" size=14 color="#8b8a94" ></u--text>
			<view class="u-demo-block__content" style="padding-top: 3px;">
				<view class="u-page__text-item">
					<text style="font-size: 18px;">{{form.id}}</text>
				</view>
			</view>
		</view>
		<view class="u-demo-block" style="padding-top: 7%;padding-left: 12%;">
			<u--text text="姓名:" size=14 color="#8b8a94" ></u--text>
			<view class="u-demo-block__content" style="padding-top: 3px;">
				<view class="u-page__text-item">
					<text style="font-size: 18px;">{{form.name}}</text>
				</view>
			</view>
		</view>
		<view class="u-demo-block" style="padding-top: 7%;padding-left: 12%;">
			<u--text text="所属部门:" size=14 color="#8b8a94"></u--text>
			<view class="u-demo-block__content"  style="padding-top: 3px;">
				<view class="u-page__text-item" >
					<text style="font-size: 18px;">{{form.deptName}}</text>
				</view>
			</view>
		</view>
		<view class="u-demo-block" style="padding-top: 7%;padding-left: 12%;">
			<u--text text="绑定手机号:" size=14 color="#8b8a94"></u--text>
			<view class="u-demo-block__content"  style="padding-top: 3px;">
				<view class="u-page__text-item" >
					<text style="font-size: 18px;">{{form.phoneNumber}}</text>
				</view>
			</view>
		</view>
		<view class="u-demo-block" style="padding-top: 7%;padding-left: 12%;">
			<u--text text="绑定的身份证:" size=14 color="#8b8a94"></u--text>
			<view class="u-demo-block__content"  style="padding-top: 3px;">
				<view class="u-page__text-item" >
					<text style="font-size: 18px;">{{form.idNum}}</text>
				</view>
			</view>
		</view>
		<view class="u-demo-block" style="padding-top: 15%;padding-left: 12%;padding-right: 12%;padding-bottom: 12%;">
			<u--text text="注意:" size=13 color="#8b8a94"></u--text>
			<u--text text="员工个人信息由公司统一录入, 如需修改请联系管理员" size=10 color="#8b8a94" style="padding-top: 2%;"></u--text>
		</view>
	</view>
	</view>
	
</template>

<script>
	export default {
			data() {
				return {
					bgColor: '#eeeeee',
					form:{
						id: '11111111',
					},
				}
			},
			onBackPress() {
				uni.redirectTo({
					url: '/pages/person/personalCenter'
				})
			},
			onLoad:function(options){
				if(JSON.stringify(options)!='{}'){
					this.form.id = JSON.parse(options.id);
					 // console.log(options.id);
				}
				this.getEmployeeById();
			},
			methods:{
				getEmployeeById(){
				     let that = this;
					 // console.log(this.form.id);
				     uni.request({
				      url: 'http://localhost:8080/api/employee/employee/getCodedInfo/'+this.form.id,
				      data: {},
				      success: (res) => {
				       if (res.data.code == 20000) {
				        // console.log(res.data.data);
				        // console.log(res.data,'--------------------')
				        that.form = res.data.data;
						// console.log(JSON.stringify(that.form));
				        // console.log(that.orderlist+"获取数据成功");
				       } else {
				        return uni.$u.toast("连接失败")
				       }
				      }
				     })
				    },
			},
			
			
		}
</script>

<style lang="scss">
	.u-demo-block__content {
		@include flex;
		flex-direction: row;
		flex-wrap: wrap;
		align-items: center;
	}
</style>