<template>
    <div>
        <page-header title="Welcome to Scrum Manage Tool">
            <template #content>
                <div>
                    <div style="margin-bottom: 5px;">This tool is developed by <b class="text-emphasis">Team 3, </b>CS5351 Software Engineering</div>
                </div>
            </template>
            <el-button-group style="margin-right: 10px;">
                <el-button type="success" plain @click="open(`https://hooray.${locationOrigin.includes('gitee') ? 'gitee' : 'github'}.io/fantastic-admin`)">Documentation</el-button>
                <el-button type="primary" @click="open(`https://github.com/SinoShaoxian/scrum_tool`)">Git repository</el-button>
            </el-button-group>
        </page-header>
    </div>
</template>

<script>
export default {
    data() {
        return {
            diff: [{
                basic: [
                    '3 套页面布局',
                    '1 款主题风格',
                    '6 个侧边栏导航配置项',
                    '支持外链导航',
                    '支持全方位权限验证',
                    '10+ 个基础组件',
                    '提供少量扩展组件',
                    '支持 Mock 数据',
                    '团队代码规范',
                    '需保留作者版权信息'
                ],
                pro: [
                    '20 套页面布局',
                    '6 款主题风格',
                    '10 个侧边栏导航配置项',
                    '支持外链导航',
                    '支持内嵌导航',
                    '支持 Tab 标签栏',
                    '支持全方位权限验证',
                    '10+ 个基础组件',
                    '提供完整扩展组件',
                    '支持国际化',
                    '支持 Mock 数据',
                    '支持错误日志上报',
                    '支持文件自动生成',
                    '提供业务应用静态页面（长期扩充）',
                    '团队代码规范',
                    '可自定义版权信息',
                    '提供一对一技术支持'
                ]
            }],
            type: 1
        }
    },
    computed: {
        locationOrigin: () => location.origin,
        titlePrefix() {
            let arr = []
            switch (this.type) {
                case 1: arr = ['今天', '昨天']; break
                case 2: arr = ['本周', '上周']; break
                case 3: arr = ['本月', '上月']; break
                case 4: arr = ['今年', '去年']; break
            }
            return arr
        }
    },
    methods: {
        open(url) {
            window.open(url, 'top')
        },
        onIconClick(val) {
            this.$message({
                message: `你点击了：${val}`,
                type: 'info'
            })
        }
    }
}
</script>

<style lang="scss" scoped>
.text-emphasis {
    /* stylelint-disable-next-line property-no-vendor-prefix */
    -webkit-text-emphasis-style: "❤";
}
.fa-info {
    padding: 10px 0 0;
    text-align: center;
    h1 {
        margin: 10px auto 20px;
    }
}
.question {
    .answer {
        margin: 20px 0 0;
        padding-left: 20px;
        font-size: 14px;
        color: #aaa;
        li {
            margin-bottom: 10px;
            line-height: 1.5;
            &:last-child {
                margin-bottom: 0;
            }
        }
        span {
            color: #666;
            font-weight: bold;
        }
    }
}
.icon-box {
    padding: 20px;
    text-align: center;
    background-color: #fff;
    cursor: pointer;
    transition: 0.2s;
    &:hover {
        color: #fff;
    }
    &-1 {
        color: #ffc069;
        &:hover {
            background-color: #ffc069;
        }
    }
    &-2 {
        color: #b37feb;
        &:hover {
            background-color: #b37feb;
        }
    }
    &-3 {
        color: #95de64;
        &:hover {
            background-color: #95de64;
        }
    }
    &-4 {
        color: #ff85c0;
        &:hover {
            background-color: #ff85c0;
        }
    }
    &-5 {
        color: #ff9c6e;
        &:hover {
            background-color: #ff9c6e;
        }
    }
    &-6 {
        color: #282c34;
        &:hover {
            background-color: #282c34;
        }
    }
    i {
        font-size: 24px;
    }
    .title {
        margin-top: 5px;
        font-size: 14px;
    }
}
.cube-card {
    :deep(.el-col) {
        margin-top: 10px;
        margin-bottom: 20px;
    }
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        .el-select {
            width: 120px;
        }
    }
    .today,
    .yesterday,
    .contrast {
        font-size: 24px;
        line-height: 30px;
    }
    .today {
        font-weight: bold;
        color: #9d8254;
    }
    .yesterday {
        color: #666;
    }
    .contrast::after {
        content: "%";
    }
    .sub-title {
        font-size: 12px;
        line-height: 18px;
        color: #98a4b0;
    }
}
</style>
