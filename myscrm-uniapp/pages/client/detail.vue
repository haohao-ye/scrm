<template>
	<view>
		<u-navbar title="客户信息" @rightClick="rightClick" :autoBack="true" :border="true">
		</u-navbar>
		<view style="padding-left: 5%;padding-top: 30%;">
			<u--form class="form" labelPosition="left" :model="model1" :rules="rules" ref="form1">

				<u-form-item labelWidth="80px" label="ID" prop="userInfo.id" borderBottom ref="item1">
					<text>{{model1.userInfo.id }}</text>
				</u-form-item>

				<u-form-item labelWidth="80px" label="姓名" prop="userInfo.clientName" borderBottom ref="item1">
					<text>{{model1.userInfo.clientName }}</text>
				</u-form-item>

				<u-form-item labelWidth="80px" label="联系电话" prop="userInfo.phoneNumber" borderBottom ref="item1">
					<text>{{model1.userInfo.phoneNumber }}</text>
				</u-form-item>

				<u-form-item labelWidth="80px" label="地址" prop="userInfo.address" borderBottom ref="item1">
					<text>{{model1.userInfo.address }}</text>
				</u-form-item>

				<u-form-item labelWidth="80px" label="客户级别" prop="userInfo.clientLevel" borderBottom ref="item1">
					<text>{{model1.userInfo.clientLevel }}</text>
				</u-form-item>

				<u-form-item labelWidth="80px" label="负责人" prop="userInfo.clientGroup" borderBottom ref="item1">
					<text>{{employee.name}}</text>
				</u-form-item>

				<u-form-item labelWidth="80px" label="联系状态" prop="userInfo.contactLabel" borderBottom ref="item1">
					<!-- <view v-if="model1.userInfo.contactLabel==0"
						:class="['types d-flex align-items-center justify-content-center',model1.userInfo.contactLabel==0||model1.userInfo.contactLabel==1]">
						{{model1.userInfo.contactLabel==0?"本周待联系":model1.userInfo.contactLabel==1?"本周已联系":"无"}}
					</view> -->
					
							<text v-if="model1.userInfo.contactLabel==1">本周已联系</text>
							<view v-if="model1.userInfo.contactLabel==0">
								<u-row gutter="20%">
									<u-col span="4">
										<text>本周待联系</text>
									</u-col>
									<u-col span="4">
										<text space="emsp">    </text>
									</u-col>
									<u-col span="4">
										<u-button text='√已联系' @click="hasConnected()"></u-button>
									</u-col>
								</u-row>
								
								
							</view>
							
					

					<!-- 				<u--text v-text="model1.userInfo.contactLabel" border="none"></u--text>
 -->
				</u-form-item>

				

				<view style="padding-left: 15%;padding-right:20%;padding-top: 8%;">
						<u-button type="primary" class="update-btn" text="修改信息" @click="updatebtnclick"></u-button>
				</view>
				<view style="padding-left: 15%;padding-right: 20%;padding-top: 4%;">
						<u-button type="error" class="del-btn" text="删除客户" @click="delbtnclick"></u-button>
				</view>
				

				

			</u--form>
			
		</view>
		
		<view style="padding-top: 3%;">
			<u-divider text="沟通记录" ></u-divider>
		</view>
		
		<view style="padding-left: 20%;padding-right: 20%;padding-top: 4%;padding-bottom: 4%;">
			<u-button type="primary" class="update-btn" text="添加记录" @click="show = true"></u-button>
		</view>
		
		<view class="list" v-for="item in recordList" :key='recordList.id' style="padding-right: 6%;">
			<view class="center flex-1 ml-3 ">
				<view style="padding-top: 2%;padding-bottom: 2%;">
					<u-line></u-line>
				</view>
				<view style="padding-bottom: 1%;">
					<view class="text-truncate">{{item.updateTime}}    {{item.mode}}</view>
				</view>
				<view>
				<text space="nbsp">  {{item.remark}}</text>
				</view>
			</view>
		</view>
		
		<u-popup :show="show" :round="10" mode="bottom" @close="close" @open="open" closeable="true">
				<view style="padding: 5%;">
					<u-form>
						<u-form-item labelWidth="90px" label="沟通方式" prop="userInfo.phoneNumber" >
							<u--input v-model="newRecord.mode" border="none" placeholder="(电联,短信,微信)">
							</u--input>
						</u-form-item>
						<u-form-item labelWidth="90px" label="详情记录" prop="userInfo.phoneNumber">
							<u--textarea v-model="newRecord.remark" placeholder="请填写沟通详情"></u--textarea>
						</u-form-item>
					</u-form>
				</view>
				<view>
					<u-button text="提交" @click="addRecord()"></u-button>
				</view>
		</u-popup>
		
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				client: null,
				employee: {
					name: '',
				},
				showSex: false,
				model1: {
					userInfo: {},
				},
				rules: {
					// 'userInfo.name': {
					// 	type: 'string',
					// 	required: true,
					// 	message: '请填写姓名',
					// 	trigger: ['blur', 'change']
					// },
					// 'userInfo.phoneNumber': {
					// 	type: 'string',
					// 	required: true,
					// 	message: '请填写联系电话',
					// 	trigger: ['blur', 'change']
					// },
					// 'userInfo.address': {
					// 	type: 'string',
					// 	required: true,
					// 	message: '请填写地址',
					// 	trigger: ['blur', 'change']
					// },
					// 'userInfo.label': {
					// 	type: 'string',
					// 	required: true,
					// 	message: '请填写客户标签',
					// 	trigger: ['blur', 'change']
					// },
					// 'userInfo.clientGroup': {
					// 	type: 'string',
					// 	required: true,
					// 	message: '请填写客户组别',
					// 	trigger: ['blur', 'change']
					// },
				},
				radio: '',
				switchVal: false,
				recordList: {},
				nullList: [{
					id: '0',
					updateTime: '还没有这个客户的沟通记录哦 ˳⚆ɞ⚆˳ ',
					mode: '',
					remark: '   先开始联系客户吧'
				}],
				show: false,
				newRecord:{
					clientid:'',
					employeeid:'',
					mode:'',
					remark:'',
				}
			};
		},
		onLoad(e) {
			console.log(decodeURIComponent(e.param) + '-------------');
			this.model1.userInfo = JSON.parse(decodeURIComponent((e.param)));
			// console.log('finish!');
			console.log(JSON.stringify(this.model1.userInfo.clientGroup));
			// this.client = decodeURIComponent((e.param)) ;
			// console.log('1111111');
			this.getEmployee();
			// console.log('dinisha!222');
			console.log(this.model1.userInfo.address + '-------------');
			// console.log(this.client.clientGroup);
			this.getClientRecord();
		},
		methods: {
			updatebtnclick() {
				let client1 = this.client;
				// console.log(client1);
				//跳转到更新页面
				uni.navigateTo({
					url: '/pages/client/update?item=' + encodeURIComponent(JSON.stringify(client1)),
				});
			},
			getClientRecord() {
				let that = this;
				let id = this.model1.userInfo.id;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/record/record/lists',
					method: 'GET',
					data: {
						id
					},
					success: (res) => {
						if (res.data.code == 20000) {
							// console.log(JSON.stringify( res.data.data));
							that.recordList = res.data.data;
							// console.log(JSON.stringify(that.recordList));
							// console.log(JSON.stringify(that.nullList));
							if (that.recordList.length == 0) {
								that.recordList = that.nullList;
							}
						} else {
							return uni.$u.toast("读取记录错误！");
						}
					}
				})


			},
			delbtnclick() {
				//弹出模态窗口

				//更新到数据库
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/client/' + this.client.id,
					method: 'DELETE',
					data:{
						client :this.client,
					},
					header: {
						"X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzYzODAyMiwiZXhwIjoxNjYzNzI0NDIyLCJ1c2VybmFtZSI6ImFkbWluIn0.bnkemNGfSbjPuSmMPP0LIGQpodB2xUhq9-qgJQfdRCE"
						//"X-Token": uni.getStorageSync("token");

					}
				})
				//返回到上一页
				uni.navigateBack({
					delta: 1
				});
			},
			getEmployee() {
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/employee/employee/' + this.model1.userInfo.clientGroup,
					method: "GET",
					data: {},
					header: {
						"X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzY2MzcyOCwiZXhwIjoxNjYzNzUwMTI4LCJ1c2VybmFtZSI6ImFkbWluIn0.fHzecAPFY2NvSOaEikbKHAmTEn4DlckXkkyAcoXWp4s"
						//"X-Token": uni.getStorageSync("token");

					},
					success: (res) => {

						if (res.data.code == 20000) {
							this.employee = res.data.data;
						} else {
							return uni.$u.toast("连接失败")
						}
					}
				})

			},
			hasConnected(){
				this.model1.userInfo.contactLabel = 1;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/client/edit',
					method: "PUT",
					data: {
						id: this.model1.userInfo.id,
						contactLabel: this.model1.userInfo.contactLabel,
					},
					header: {
						// "X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzY2MzcyOCwiZXhwIjoxNjYzNzUwMTI4LCJ1c2VybmFtZSI6ImFkbWluIn0.fHzecAPFY2NvSOaEikbKHAmTEn4DlckXkkyAcoXWp4s"
						//"X-Token": uni.getStorageSync("token");
				
					},
					success: (res) => {
				
						if (res.data.code == 20000) {
							return uni.$u.toast("修改成功！")
						} else {
							return uni.$u.toast("连接失败！")
						}
					}
				})
			},
			addRecord(){
				// console.log(JSON.stringify(this.newRecord));
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/record/record',
					method: "POST",
					data: {
						mode : this.newRecord.mode,
						remark : this.newRecord.remark,
						clientid : this.model1.userInfo.id,
						employeeid : this.model1.userInfo.clientGroup,
						createBy: this.employee.name,
					},
					header: {
						"X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzY2MzcyOCwiZXhwIjoxNjYzNzUwMTI4LCJ1c2VybmFtZSI6ImFkbWluIn0.fHzecAPFY2NvSOaEikbKHAmTEn4DlckXkkyAcoXWp4s"
						//"X-Token": uni.getStorageSync("token");
				
					},
					success: (res) => {
				
						if (res.data.code == 20000) {
							return uni.$u.toast("修改成功！")
						} else {
							return uni.$u.toast("连接失败！")
						}
					}
				})
			},
			open() {
			        // console.log('open');
			},
			close() {
			        this.show = false
			        // console.log('close');
			},
		},
		
		
	}
</script>

<style lang="scss" scoped>
	.form {
		padding-top: 10%;
	}

	.update-btn {
		color: #ffffff;
		width: 150rpx;
		iconColor: #1296db;
		plain: true;
		margin-top: 100px;
	}

	.del-btn {
		width: 150rpx;
		iconColor: #bfbfbf;
		plain: true;
		margin-top: 100px;
	}
</style>
