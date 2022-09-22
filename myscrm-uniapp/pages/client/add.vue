<template>
	<view>
		<u-navbar title="添加客户" @rightClick="rightClick" :autoBack="true" :border="true">
		</u-navbar>
		<view style="padding-left: 5%;padding-top: 30%;">
		<u--form class="form" labelPosition="left" :model="model1" :rules="rules" ref="form1">

			<u-form-item labelWidth="80px" label="姓名" prop="userInfo.name" borderBottom ref="item1">
				<u--input @input="nameinput" v-model="model1.userInfo.name" border="none" placeholder="请填写姓名">
				</u--input>
			</u-form-item>

			<u-form-item labelWidth="80px" label="联系电话" prop="userInfo.phoneNumber" borderBottom ref="item1">
				<u--input @input="phoneinput" v-model="model1.userInfo.phoneNumber" border="none" placeholder="请填写联系电话">
				</u--input>
			</u-form-item>

			<u-form-item labelWidth="80px" label="地址" prop="userInfo.address" borderBottom ref="item1">
				<u--input @input="addrinput" v-model="model1.userInfo.address" border="none" placeholder="请填写地址">
				</u--input>
			</u-form-item>

			<u-form-item labelWidth="80px" label="客户级别" prop="userInfo.clientLevel"
				borderBottom ref="item1">
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

			<u-form-item labelWidth="80px" label="负责人" prop="userInfo.clientGroup"
				borderBottom ref="item1">
				<view>
					<view class="uni-title uni-common-pl"></view>
					<view class="uni-list">
						<view class="uni-list-cell">
							<view class="uni-list-cell-left">

							</view>
							<view class="uni-list-cell-db">
								<picker @change="groupPickerChange" :value="groupIndex" :range="groupArray"
									range-key="name">
									<view class="uni-input">{{selectGroup}}</view>
								</picker>
							</view>
						</view>
					</view>
				</view>
			</u-form-item>

			
		</u--form>
		</view>
		<view style="padding-top: 5%;padding-left: 15%;padding-right: 20%;">
			<u-button type="primary" class="yes-btn" text="确定" @click="yesbtnclick"></u-button>
		</view>
		<view style="padding-top: 5%;padding-left: 15%;padding-right: 20%;">
			<u-button class="no-btn" text="取消" @click="nobtnclick"></u-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				showSex: false,
				model1: {
					userInfo: {
						clientName: '',
						phoneNumber: '',
						address: '',
						clientLevel: '',
						clientGroup: '',
					},
				},
				rules: {
					'userInfo.name': {
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
				},
				radio: '',
				switchVal: false,
				groupIndex: 0,
				levelIndex: 0,
				groupArray: [],
				selectGroup: '',
				selectLevel: '',
				levelArray: [
					"A", "B","C"
				],
			};
		},
		onLoad() {
			this.getGroupArray();
		},
		methods: {
			yesbtnclick() {

				//更新到数据库
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/client/add',
					method: 'post',
					data: this.model1.userInfo,
					header: {
						"X-Token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYW9iYW8tdXNlciIsImlhdCI6MTY2MzYzODAyMiwiZXhwIjoxNjYzNzI0NDIyLCJ1c2VybmFtZSI6ImFkbWluIn0.bnkemNGfSbjPuSmMPP0LIGQpodB2xUhq9-qgJQfdRCE"
						//"X-Token": uni.getStorageSync("token");

					},
					success(res) {
						if(res.data.code == 20000){
							return uni.$u.toast("添加成功")
						}else{
							return uni.$u.toast("添加失败")
						}
					}
				});
				//返回到上一页
				uni.navigateBack({
					delta: 1,
					// success: function() {
					// 	beforePage.$vm.refreshRequest(); // 执行前一个页面或者前几个页面并且刷新该页面
					// }
					// success: () => {
					//     let page = getCurrentPages().pop();
					//     if (page) {
					//        page.onLoad(page.options);//执行上个页面的方法
					//     };
					//   }
				});

				//重新查询数据库
			},
			nobtnclick() {
				uni.navigateBack({
					delta: 1
				});
			},
			nameinput(text) {
				this.model1.userInfo.clientName = text;
			},
			phoneinput(text) {
				this.model1.userInfo.phoneNumber = text;
			},
			addrinput(text) {
				this.model1.userInfo.address = text;
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
			tclick() {
			//	console.log("dianle");
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
//						console.log(res, '--------------------------------------')
						if (res.data.code == 20000) {
//							console.log(res.data.data, '00000000000000000000000000000000000');
							if (res.data.data && res.data.data.length > 0) {
								this.groupArray = res.data.data;
								this.selectGroup = this.groupArray[0].name;
								

//								console.log(this.groupArray.length, this.groupArray[0].name);

								// 	this.levelArray = res.data.data;
								// 	this.selectLevel = this.levelArray[0].name;
							}
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
