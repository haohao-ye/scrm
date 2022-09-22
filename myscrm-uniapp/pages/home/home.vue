<template>
	<view class="u-page" >
		<view class="u-demo-block">
			<view class="u-demo-block__content">
				<view class="u-page__tag-item">
					<u-search v-model="value" shape="square" :show-action="false" @change="searchGoods" height="50"></u-search>
				</view>
			</view>
		</view>
		<view class="u-page__item">
			<u-list @scrolltolower="scrolltolower">
				<u-collapse :border="false">
					<u-collapse-item name="1" title="游戏本">
						<u-cell-group>
							<u-list-item v-if="goodsList[0]" v-for="item in goodsList">
								<u-cell v-if="item.type=='游戏本'" :title="`${item.name}`" @click="detail(item)">
									<u-avatar slot="icon" shape="square" size="35" :src="urls[item.id]"
										customStyle="margin: -3px 5px -3px 0"></u-avatar>
								</u-cell>
							</u-list-item>
						</u-cell-group>
					</u-collapse-item>
					<u-collapse-item name="2" title="轻薄本" >
						<u-cell-group>
							<u-list-item v-if="goodsList[0]" v-for="item in goodsList" >
								<u-cell v-if="item.type=='轻薄本'" :title="`${item.name}`" @click="detail(item)" >
									<u-avatar slot="icon" shape="square" size="35" :src="urls[item.id]"
										customStyle="margin: -3px 5px -3px 0"></u-avatar>
								</u-cell>
							</u-list-item>
						</u-cell-group>
					</u-collapse-item>
					<u-collapse-item name="3" title="商务本">
						<u-cell-group>
							<u-list-item v-if="goodsList[0]" v-for="item in goodsList">
								<u-cell v-if="item.type=='商务本'" :title="`${item.name}`" @click="detail(item)">
									<u-avatar slot="icon" shape="square" size="35" :src="urls[item.id]"
										customStyle="margin: -3px 5px -3px 0"></u-avatar>
								</u-cell>
							</u-list-item>
						</u-cell-group>
					</u-collapse-item>
					<u-collapse-item name="4" title="一体式" :border="false" @change="flush">
						<u-cell-group>
							<u-list-item v-if="goodsList[0]" v-for="item in goodsList">
								<u-cell v-if="item.type=='一体式'" :title="`${item.name}`" @click="detail(item)">
									<u-avatar slot="icon" shape="square" size="35" :src="urls[item.id]"
										customStyle="margin: -3px 5px -3px 0"></u-avatar>
								</u-cell>
							</u-list-item>
						</u-cell-group>
					</u-collapse-item>
				</u-collapse>
			</u-list>
		</view>
		<!-- 通用的底部导航栏 -->
		<u-tabbar :value="value6" :fixed="true" :placeholder="true" :safeAreaInsetBottom="true"
			@change="name => value6 = name">
			<u-tabbar-item text="首页" icon="home" name="home"></u-tabbar-item>
			<u-tabbar-item text="客户" icon="chat" name="client" @click="goToClient()"></u-tabbar-item>
			<u-tabbar-item text="订单" icon="order" name="orders" @click="goToOders()"></u-tabbar-item>
			<u-tabbar-item text="我的" icon="account" name="account" @click="goToPersonCenter()"></u-tabbar-item>
		</u-tabbar>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				id:'',
				value: "",
				keyword: 'hello',
				indexList: [],
				open:[],
				goodsList: {},
				param: {},
				urls: {
					'1':'/static/1.png',
					'2':'/static/2.png',
					'3':'/static/3.png',
					'7':'/static/7.png',
				},
				requestParam: {},
				value6: '0',
				queryParams: {
					pageNum: 1,
					pageSize: 10,
					name: null,
					type: null,
					price: null,
					lable: null,
					brand: null,
					inventory: null,
					color: null,
					activity_id: null,
					creatTime: null,
					creatBy: null,
				},
				
			}
		},
		onLoad(options) {
			this.getGoods();
			if(JSON.stringify(options)!='{}'){
				this.id = JSON.parse(options.id);
				 console.log(options.id);
			}
		},
		methods: {
			flush(){
				this.getGoods();
			},
			scrolltolower() {
				this.getGoods();
			},

		
			getGoods() {
				let that = this;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/goods/goods/lists',
					data: {},
					success: (res) => {
						if (res.data.code == 20000) {
							// console.log(res.data);
							console.log(res.data, '--------------------')
							that.goodsList = res.data.data;
						} else {
							return uni.$u.toast("shibai")
						}
					}
				})
			},
			detail(e) {
				console.log('------------------------')
				uni.navigateTo({
					url: "/pages/detail/detail?param=" + encodeURIComponent(JSON.stringify(e))+"&salemanId="+this.id
				})
			},
			change(e) {
				if (e === 1) {
					console.log("首页")
					// return uni.$u.toast('请您先登录')
				}
			},
			goToPersonCenter() {
				uni.navigateTo({
					url: '/pages/person/personalCenter?id='+this.id
				});
			},
			goToOders() {
				uni.navigateTo({
					url: '/pages/orders/orders?id='+this.id
				});
			},
			goToClient() {
				uni.navigateTo({
					url: '/pages/client/client?id='+this.id
				});
			},
			searchGoods() {
				let searchParam = this.queryParams;
				searchParam.name = this.value;
				let that = this;
				uni.request({
					url: 'http://admin.dkhaohao.shop/prod-api/api/goods/goods/list',
					method: "GET",
					data: {
						name: this.queryParams.name,
						pageNum: this.queryParams.pageNum,
						pageSize: this.queryParams.pageSize
					},
					success: (res) => {
						if (res.data.code == 20000) {
							// console.log(res.data);
							console.log(res.data, '--------------------')
							that.goodsList = res.data.data.list;
							this.open=[];
							if(that.goodsList['游戏本'])
								this.open.push('1')
							if(that.goodsList['轻薄本'])
								this.open.push('2')
							if(that.goodsList['商务本'])
								this.open.push('3')
							if(that.goodsList['一体式'])
								this.open.push('4')
						} else {
							return uni.$u.toast("shibai")
						}
					}
				})
			}
		},
	}
</script>

<style lang="scss">
	.u-page {
		padding: 0;

		&__item {

			&__title {
				color: $u-tips-color;
				background-color: $u-bg-color;
				padding: 15px;
				font-size: 15px;

				&__slot-title {
					color: $u-primary;
					font-size: 14px;
				}
			}

			&__slot-icon {
				width: 17px;
				height: 17px;
			}
		}
	}
</style>
