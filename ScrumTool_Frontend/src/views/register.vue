<template>
    <div>
        <div class="bg-banner" />
        <div id="login-box">
            <div class="login-banner" />
            <el-form ref="ruleForm" :model="ruleForm" :rules="rules" class="login-form" auto-complete="on" label-position="left">
                <div class="title-container">
                    <h3 class="title">Scrum Mgt Tool</h3>
                </div>
                <div>
                    <el-form-item label="身份" prop="userStatus">
                        <el-select v-model="ruleForm.userStatus">
                            <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            />
                        </el-select>
                    </el-form-item>
                    <el-form-item label="姓名" prop="userName">
                        <el-input v-model="ruleForm.userName" />
                    </el-form-item>
                    <el-form-item label="账号" prop="account">
                        <el-input v-model="ruleForm.account" />
                    </el-form-item>
                    <el-form-item label="密码" prop="passwd">
                        <el-input v-model="ruleForm.passwd" type="password" autocomplete="off" />
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input v-model="ruleForm.checkPass" type="password" autocomplete="off" />
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </div>
            </el-form>
        </div>
        <Copyright v-if="$store.state.settings.showCopyright" />
    </div>
</template>

<script>
export default {
    name: 'Register',
    data() {

        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'))
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass')
                }
                callback()
            }
        }
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'))
            } else if (value !== this.ruleForm.passwd) {
                callback(new Error('两次输入密码不一致!'))
            } else {
                callback()
            }
        }
        return {
            ruleForm: {
                userStatus: '',
                userName: '',
                account: '',
                passwd: '',
                checkPass: ''
            },
            rules: {
                passwd: [
                    { required: true, validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { required: true, validator: validatePass2, trigger: 'blur' }
                ],
                userName: [
                    {required: true, message: '请输入您的名称', trigger: 'blur'}
                ],
                account: [
                    {required: true, message: '请输入账号', trigger: 'blur'}
                ]
            },
            options: [{ // eslint-disable-next-line indent
                          label: 'user', // eslint-disable-next-line indent
                          value: 'user'// eslint-disable-next-line indent
                      }, // eslint-disable-next-line indent
                      {// eslint-disable-next-line indent
                          label: 'expert', // eslint-disable-next-line indent
                          value: 'expert'// eslint-disable-next-line indent
                      }]
        }
    },
    methods: {
        submitForm(formName) {
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.$api.post('http://127.0.0.1:1234/register', this.ruleForm).then(function(resp) {
                        console.log(resp)
                    })
                    alert('成功注册')
                    _this.$router.push('/login')
                } else {
                    console.log('error submit!!')
                    return false
                }
            })
        },
        resetForm(formName) {
            this.$refs[formName].resetFields()
        }
    }
}
</script>

<style lang="scss" scoped>
[data-mode=mobile] {
    #login-box {
        max-width: 80%;
        .login-banner {
            display: none;
        }
    }
}
::v-deep input[type=password]::-ms-reveal {
    display: none;
}
.bg-banner {
    position: absolute;
    z-index: 0;
    width: 100%;
    height: 100%;
    background-image: url(../assets/images/login-bg.jpg);
    background-size: cover;
    background-position: center center;
}
#login-box {
    display: flex;
    justify-content: space-between;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
    background: rgba(255, 255, 255, 0.8);
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 0 5px #999;
    .login-banner {
        width: 300px;
        background-image: url(../assets/images/login-banner.jpg);
        background-size: cover;
        background-position: center center;
    }
    .login-form {
        width: 450px;
        padding: 50px 35px 30px;
        overflow: hidden;
        .svg-icon {
            vertical-align: -0.35em;
        }
        .title-container {
            position: relative;
            .title {
                font-size: 22px;
                color: #666;
                margin: 0 auto 30px;
                text-align: center;
                font-weight: bold;
                @include text-overflow;
            }
        }
    }
    ::v-deep .el-input {
        display: inline-block;
        height: 48px;
        width: 100%;
        input {
            height: 48px;
        }
        .el-input__prefix {
            left: 10px;
        }
        .el-input__suffix {
            right: 10px;
        }
    }
    .el-checkbox {
        margin: 20px 0;
    }
}
.copyright {
    position: absolute;
    bottom: 30px;
    width: 100%;
    margin: 0;
    mix-blend-mode: difference;
}
</style>
