<template>
    <div>
        <!-- 页面：Setting -->
        <page-main>
            <el-tabs tab-position="left" style="height: 600px;">
                <el-tab-pane label="profile" class="basic">
                    <h2>Profile</h2>
                    <el-row :gutter="20">
                        <el-col :span="16">
                            <el-form ref="form" :model="form" label-width="120px" label-suffix="：">
                                <el-form-item label="NAME">
                                    <el-input v-model="form.name" placeholder="Please input your name" />
                                </el-form-item>
                                <el-form-item label="TEL">
                                    <el-input v-model="form.mobile" placeholder="Please input your TEL" />
                                </el-form-item>
                                <el-form-item label="E-MAIL">
                                    <el-input v-model="form.qq" placeholder="Please input your e-mail" />
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary">save</el-button>
                                </el-form-item>
                            </el-form>
                        </el-col>
                        <el-col :span="8">
                            <image-upload v-model:url="form.headimg" action="http://scrm.1daas.com/api/upload/upload" name="image" :data="{'token':'TKD628431923530324'}" notip class="headimg-upload" @on-success="handleSuccess" />
                        </el-col>
                    </el-row>
                </el-tab-pane>
            </el-tabs>
        </page-main>
    </div>
</template>

<script setup name="PersonalSetting">
const router = useRouter()
const { proxy } = getCurrentInstance()

const form = ref({
    headimg: '',
    mobile: '',
    name: '',
    qq: '',
    wechat: ''
})

function handleSuccess(res) {
    if (res.error == '') {
        form.value.headimg = res.data.path
    } else {
        proxy.$message.warning(res.error)
    }
}
function editPassword() {
    router.push({
        name: 'personalEditPassword'
    })
}
</script>

<style lang="scss" scoped>
:deep(.el-tabs) {
    .el-tabs__header .el-tabs__nav {
        .el-tabs__active-bar {
            z-index: 0;
            width: 100%;
            background-color: #e1f0ff;
            border-right: 2px solid #409eff;
        }
        .el-tabs__item {
            text-align: left;
            padding-right: 100px;
        }
    }
    .el-tab-pane {
        padding: 0 20px 0 30px;
    }
}
h2 {
    margin: 0;
    margin-bottom: 30px;
    font-weight: normal;
}
.basic {
    :deep(.headimg-upload) {
        > div {
            text-align: center;
        }
        .el-upload-dragger {
            border-radius: 50%;
        }
    }
}
.security {
    .setting-list {
        .item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px solid #e8e8e8;
            .content {
                .title {
                    margin-bottom: 5px;
                    color: #666;
                }
                .desc {
                    font-size: 14px;
                    color: #999;
                }
            }
            &:last-child {
                border-bottom: 0;
            }
        }
    }
}
</style>
