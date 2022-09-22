<!-- 已弃用修改个人信息功能 -->
<template>
	<view>
		<view >
			<!-- 2.0.19支持autoBack，默认为false -->
			<u-navbar
			    title="编辑个人信息"
			    :autoBack="true"
				:bgColor="bgColor"
			>
			</u-navbar>
		</view>
		<view class="u-demo-block" style="padding-top: 20%;padding-left: 12%;">
			<u--text text="昵称:" size=13 color="#8b8a94" ></u--text>
			<view style="padding-top: 5px;width:  80%;">
				<u--input
				    placeholder="请输入新昵称"
				    border="surround"
				    v-model=form[0].name
				  ></u--input>
			</view>
			
		</view>
		<view class="u-demo-block" style="padding-top: 8%;padding-left: 12%;">
			<u--text text="性别:" size=13 color="#8b8a94"></u--text>
			<view style="padding-top: 5px;width: 80%;">
				<u--input
				    placeholder="请输入新性别"
				    border="surround"
				    v-model=form[0].gender
				  ></u--input>
			</view>
		</view>
		<view class="u-demo-block" style="padding-top: 8%;padding-left: 12%;">
			<u--text text="生日:" size=13 color="#8b8a94"></u--text>
			<view style="padding-top: 5px;width:  80%;">
				<u--input
				    placeholder="请输入新生日"
				    border="surround"
				    v-model=form[0].birth
				  ></u--input>
			</view>
		</view>
		
		<view class="u-page__button-item"  style="padding-top: 15%;width: 40%;padding-left: 30%;">
			<u-button
			    text="保存"
				size="normal"				    
				type="primary"
			></u-button>
		</view>
		<view class="u-page__button-item"  style="padding-top: 5%;width: 40%;padding-left: 30%;">
			<u-button
			    text="取消"
				size="normal"				    
				type="primary"
				@click="comeBack()"
			></u-button>
		</view>
		<view class="u-demo-block" style="padding-top: 6%;padding-left: 8%;width: 90%;">
			<u--text text="注意:" size=15 color="#ff0000"></u--text>
			<u--text text="手机号以及身份证号关系到登录信息,请联系管理员修改!" size=13 color="#8b8a94"></u--text>
		</view>
		
	</view>
	
</template>

<script>
	export default {
			data() {
				return {
					bgColor: '#eeeeee',
					form:[{
						name:'测试用户',
						gender:'null',
						birth:'9999-13-32'
					}],
				
				}
			},
			
			onLoad: function (options){
				if(JSON.stringify(options)!='{}'){
					this.form = JSON.parse(options.form);
				 // console.log(this.form);
				 // console.log(JSON.stringify(this.form));
				}
				 
			},

			
			onBackPress() {
				uni.reLaunch({
					url: '/pages/person/information/information'
				})
			},
			
			methods:{
				comeBack(){
					uni.redirectTo({
						url:'/pages/person/information/information'
					});
				},
			}
			
		}
</script>

<style lang="scss">
	.u-demo-block__content {
		@include flex;
		flex-direction: row;
		flex-wrap: wrap;
		align-items: center;
	}
	.box{
			width: 100px; 
			height: 100px;
			background-color: #909193;
			border-radius: 15px;
		}
</style>