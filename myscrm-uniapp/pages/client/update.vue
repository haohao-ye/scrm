<template>
	<view>
		<u-navbar title="修改客户" @rightClick="rightClick" :autoBack="true" :border="true">
		</u-navbar>
		<view style="padding-left: 5%;padding-top: 30%;">
			<u--form class="form" labelPosition="left" :model="model1" :rules="rules" ref="form1">

				<u-form-item labelWidth="80px" label="姓名" prop="userInfo.clientName" borderBottom ref="item1">
					<u--input @input="nameinput" v-model="model1.userInfo.clientName" border="none" placeholder="请填写姓名">
					</u--input>
				</u-form-item>

				<u-form-item labelWidth="80px" label="联系电话" prop="userInfo.phoneNumber" borderBottom ref="item1">
					<u--input @input="phoneinput" v-model="model1.userInfo.phoneNumber" border="none"
						placeholder="请填写联系电话">
					</u--input>
				</u-form-item>

				<u-form-item labelWidth="80px" label="地址" prop="userInfo.address" borderBottom ref="item1">
					<u--input @input="addrinput" v-model="model1.userInfo.address" border="none" placeholder="请填写地址">
					</u--input>
				</u-form-item>

				<u-form-item labelWidth="80px" label="客户级别" prop="userInfo.clientLevel" borderBottom ref="item1">
					<view>
						<view class="uni-title uni-common-pl"></view>
						<view class="uni-list">
							<view class="uni-list-cell">
								<view class="uni-list-cell-left">

								</view>
								<view class="uni-list-cell-db">
									<picker @change="levelPickerChange" :value="levelIndex" :range="levelArray">
										<view class="uni-input">{{levelArray[levelIndex]}}</view>
									</picker>
								</view>
							</view>
						</view>
					</view>
				</u-form-item>


				<view style="padding-top: 5%;padding-left: 15%;padding-right: 20%;">
					<u-button type="primary" class="yes-btn" text="确定" @click="yesbtnclick"></u-button>
				</view>
				<view style="padding-top: 5%;padding-left: 15%;padding-right: 20%;">
					<u-button class="no-btn" text="取消" @click="nobtnclick"></u-button>
				</view>


			</u--form>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				client: null,
				showSex: false,
				model1: {
					userInfo: {
						id: '1',
						clientName: '张三',
						phoneNumber: '15485236421',
						address: '广东省广州市天河区五山街道483号',
						clientLevel: '呜啦啦啦啦',
						clientGroup: '1',
					},
				},
				rules: {
					'userInfo.clientName': {
						type: 'string',
						required: true,
						message: '请填写姓名',
						trigger: ['blur', 'change']
					},
					'userInfo.phoneNumber': {
						type: 'string',
						required: true,
						message: '请填写联系电话',
						trigger: ['blur', 'change']
					},
					'userInfo.address': {
						type: 'string',
						required: true,
						message: '请填写地址',
						trigger: ['blur', 'change']
					},
					'userInfo.label': {
						type: 'string',
						required: true,
						message: '请填写客户标签',
						trigger: ['blur', 'change']
					},
					'userInfo.clientGroup': {
						type: 'string',
						required: true,
						message: '请填写客户组别',
						trigger: ['blur', 'change']
					},
				},
				radio: '',
				switchVal: false,
				groupIndex: 0,
				levelIndex: 0,
				groupArray: [],
				selectGroup: '',
				selectLevel: '',
				levelArray: [
					"A类客户", "B类客户", "C类客户"
				],
				employee: '',
			};
		},
		onLoad(param) {
			this.getGroupArray();
			this.client = JSON.parse( decodeURIComponent(param.item));
			this.model1.userInfo = JSON.parse( decodeURIComponent(param.item));
			console.log(this.model1.userInfo);
			this.selectGroup = this.model1.userInfo.clientGroup;
			this.getEmployee();
		},
		methods: {
			yesbtnclick() {
				//uni.setStorageSync("token", res.data.data.token);

				//弹出模态窗口

				//更新到数据库
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/client',
					method: 'put',
					data: this.model1.userInfo,
					header: {
						"X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzY2MzcyOCwiZXhwIjoxNjYzNzUwMTI4LCJ1c2VybmFtZSI6ImFkbWluIn0.fHzecAPFY2NvSOaEikbKHAmTEn4DlckXkkyAcoXWp4s"
						//"X-Token": uni.getStorageSync("token");
					},
					success(res) {
						if (res.data.code == 20000) {
							return uni.$u.toast("修改成功");
							uni.redirectTo({
								url: '/pages/client/client'
							})
						} else {
							return uni.$u.toast("修改失败")
						}
					}
				})
				//返回到上一页
				// uni.navigateBack({
				// 	delta:1
				// });

				//重新查询数据库
			},
			nobtnclick() {
				uni.redirectTo({
					url: '/pages/client/client'
				});
			},
			nameinput(text) {
				this.model1.userInfo.clientName = text;
				console.log(this.model1.userInfo.clientName);
			},
			phoneinput(text) {
				this.model1.userInfo.phoneNumber = text;
			},
			addrinput(text) {
				this.model1.userInfo.address = text;
			},
			labelinput(text) {
				this.model1.userInfo.clientLevel = text;
			},
			groupinput(text) {
				this.model1.userInfo.clientGroup = text;
			},
			groupPickerCancel() {
				this.groupShow = false;
			},
			groupPickerChange: function(e) {
				this.groupIndex = e.detail.value;
				this.selectGroup = this.groupArray[this.groupIndex].name;
				this.model1.userInfo.clientGroup = this.groupArray[this.groupIndex].id;
				console.log(this.groupArray[this.groupIndex].id, '--------------------------');
			},
			levelPickerCancel() {
				this.levelShow = false;
			},
			levelPickerChange: function(e) {
				console.log(e.detail, '---------------')
				this.levelIndex = e.detail.value;
				this.selectLevel = this.levelArray[this.levelIndex].name;
				this.model1.userInfo.clientLevel = this.levelArray[this.levelIndex];
				// // 	console.log(this.model1.userInfo.clientLevel);

				console.log(this.levelArray[this.levelIndex].deptId, '--------------------------')
			},
			getGroupArray() {
				let that = this;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/employee/employee/lists',
					method: 'get',
					success: (res) => {
						if (res.data.code == 20000) {
							if (res.data.data && res.data.data.length > 0) {
								this.groupArray = res.data.data;
							}
						} else {
							return uni.$u.toast("连接失败")
						}
					}
				})
			},
			getEmployee() {
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/employee/employee/' + this.client.clientGroup,
					method: "GET",
					data: this.client,
					header: {
						"X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzY2MzcyOCwiZXhwIjoxNjYzNzUwMTI4LCJ1c2VybmFtZSI6ImFkbWluIn0.fHzecAPFY2NvSOaEikbKHAmTEn4DlckXkkyAcoXWp4s"
						//"X-Token": uni.getStorageSync("token");

					},
					success: (res) => {

						if (res.data.code == 20000) {
							this.employee = res.data.data;
							this.selectGroup = this.employee.name;
						} else {
							return uni.$u.toast("连接失败")
						}
					}
				})

			}

		},
	}
</script>

<style lang="scss" scoped>
	.form {
		padding-top: 10%;
	}

	.yes-btn {
		color: #ffffff;
		width: 150rpx;
		iconColor: #1296db;
		plain: true;
		margin-top: 100px;
	}

	.no-btn {
		width: 150rpx;
		iconColor: #bfbfbf;
		plain: true;
		margin-top: 100px;
	}
</style>
