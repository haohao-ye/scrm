<template>
	<view>
		<view >
			<!-- 2.0.19支持autoBack，默认为false -->
			<u-navbar
			    title="工作通知"
			    :autoBack="true"
				:bgColor="bgColor"
			>
			</u-navbar>
		</view>
		<view>
			<view style="padding-top: 4%;" v-for="item in info">
				<u-divider text=""></u-divider>
				<view>
					<text class="title" style="color: darkgray;font-size: 8px;" >{{item.updateTime}}</text>
				</view>
				<view style="padding-top: 1%;">
					<text class="title">{{item.infoTitle}}</text>
				</view>
				<view style="padding-top: 1%;padding-left: 13%;padding-right: 13%;" >
					<text style="padding-left: 33px;">{{item.infoBody}}</text>
				</view>
			</view>
			<view style="padding-top: 2%;">
				<u-divider text="我也是有底线的"></u-divider>
			</view>
			
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				bgColor: '#eeeeee',
				info:[],
			}
		},
		
		onBackPress() {
			uni.redirectTo({
				url: '/pages/person/personalCenter'
			})
		},
		
		created() {
			this.getMessageList();
		},
		
		methods: {
			getMessageList(){
				let that = this;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/inform/inform/lists',
					method: 'get',
					data: [],
					success: (res) => {
						if (res.data.code == 20000) {
							// console.log(res.data.data instanceof Array);
							that.info = res.data.data;
							// console.log(JSON.stringify(that.info)+"kkkkkkkkkkkkkkk");
							// return uni.$u.toast("读取成功");
						} else {
							return uni.$u.toast("连接失败");
						}
					}
				})
				// console.log(JSON.stringify(this.info));
			}
		}
	}
</script>

<style>

 .title{
            text-align: center;
            display: block;
        }
</style>
