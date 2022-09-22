<template>
	<view >
		<view >
			<!-- 2.0.19支持autoBack，默认为false -->
			<u-navbar
			    title="设置"
			    :autoBack="true"
				:bgColor="bgColor"
			>
			</u-navbar>
		</view>
		<view style="padding-top: 24%;">
			<u-empty
			        mode="data"
			        icon="https://cdn.uviewui.com/uview/demo/empty/data.png"
					text="这家伙懒得很,此处空空如也"
			>
			</u-empty>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			
		},
		onBackPress() {
			uni.reLaunch({
				url: '/pages/person/personalCenter'
			})
		},
	}
</script>

<style>

</style>
