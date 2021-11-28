<template>

</template>

<script setup>
const { proxy } = getCurrentInstance()

const locationOrigin = ref(location.href)

const isActived = ref(true)
setTimeout(() => {
    isActived.value = false
}, 5000)

const discoveryInfo = ref({
    dialogVisible: true,
    imageVisible: false,
    imagePreview: [],
    data: [
        { img: `https://hooray.${location.origin.includes('gitee') ? 'gitee' : 'github'}.io/fantastic-admin-discovery/intro-1.gif`, text: '摆脱传统后台框架路由跳转的交互，将每个模块以窗口的形式展示在同一个界面中，让使用人员可以做到跨模块的多线操作。' },
        { img: `https://hooray.${location.origin.includes('gitee') ? 'gitee' : 'github'}.io/fantastic-admin-discovery/intro-2.gif`, text: '窗口默认宽度为 800px ，可以在项目中进行调整，也可以通过最大化按钮将窗口最大化展示，以便展示更多信息。' },
        { img: `https://hooray.${location.origin.includes('gitee') ? 'gitee' : 'github'}.io/fantastic-admin-discovery/intro-3.gif`, text: '提供的预览界面可以帮助使用人员快速定位到需要操作的窗口。' },
        { img: `https://hooray.${location.origin.includes('gitee') ? 'gitee' : 'github'}.io/fantastic-admin-discovery/intro-4.gif`, text: '可勾选 2 到 4 个窗口平均分布在页面中，专注于所勾选的窗口进行操作。' },
        { img: `https://hooray.${location.origin.includes('gitee') ? 'gitee' : 'github'}.io/fantastic-admin-discovery/intro-5.gif`, text: '快速调整窗口的展示顺序。' }
    ]
})

onMounted(() => {
    proxy.$notify({
        type: 'success',
        title: 'Welcome',
        dangerouslyUseHTMLString: true,
        message: ``,
        position: 'bottom-right',
        duration: 5000
    })
    setTimeout(() => {
        proxy.$notify({
            type: 'warning',
            title: 'Welcome to Scrum Tool',
            dangerouslyUseHTMLString: true,
            message: `This tool is developed by Team 3`,
            position: 'bottom-right',
            duration: 0
        })
    }, 0)
})

function open(url) {
    window.open(url, 'top')
}
</script>

<style lang="scss" scoped>
.buy-it {
    position: fixed;
    z-index: 10;
    right: -58px;
    top: 50%;
    margin-top: -170px;
    width: 70px;
    display: flex;
    flex-direction: column;
    transition: right 0.3s;
    &.actived,
    &:hover {
        right: 0;
    }
    .item {
        display: flex;
        flex-direction: column;
        justify-content: center;
        height: 70px;
        text-align: center;
        color: #fff;
        background-color: #409eff;
        border-bottom: 1px solid #fff;
        transition: 0.3s;
        opacity: 0.7;
        cursor: pointer;
        &:hover {
            opacity: 1;
        }
        &:first-child {
            border-top-left-radius: 5px;
        }
        &:last-child {
            border-bottom-left-radius: 5px;
            border-bottom: 0;
        }
        &.chat {
            background-color: #0fcc1a;
        }
        &.buy {
            background-color: #ff4200;
        }
        &.doc {
            background-color: #727272;
        }
        &.code {
            background-color: #343b42;
        }
        .svg-icon {
            display: block;
            margin: 0 auto;
            font-size: 20px;
        }
        .title {
            display: inline-block;
            font-size: 12px;
        }
    }
}
</style>
