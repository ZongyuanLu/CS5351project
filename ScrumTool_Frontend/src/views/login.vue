<template>
    <div>
        <div class="bg-banner" />
        <div id="login-box">
            <div class="login-banner" />
            <el-form v-show="formType == 'login'" ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">
                <div class="title-container">
                    <h3 class="title">Sign in to {{ title }}</h3>
                </div>
                <div>
                    <el-form-item prop="account">
                        <el-input ref="name" v-model="loginForm.account" placeholder="phone" type="text" tabindex="1" autocomplete="on">
                            <template #prefix>
                                <el-icon><el-icon-phone-filled /></el-icon>
                                <!-- <svg-icon name="user" /> -->
                            </template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input ref="password" v-model="loginForm.password" :type="passwordType" placeholder="password" tabindex="2" autocomplete="on" @keyup.enter="handleLogin">
                            <template #prefix>
                                <svg-icon name="password" />
                            </template>
                            <template #suffix>
                                <svg-icon :name="passwordType === 'password' ? 'eye' : 'eye-open'" @click="showPassword" />
                            </template>
                        </el-input>
                    </el-form-item>
                </div>

                <div class="flex-bar">
                    <el-checkbox v-model="loginForm.remember">remember me</el-checkbox>
                    <el-button type="text" @click="formType = 'register'">Sign up</el-button>
                </div>
                <el-button :loading="loading" type="primary" style="width: 100%;" @click.prevent="handleLogin">Sign in</el-button>
                <div style="margin-top: 20px; margin-bottom: -10px; color: #666; font-size: 14px; text-align: center; font-weight: bold;">
                        <span style="margin-right: 5px;">test account login：</span>
                        <el-button type="danger" size="mini" @click="testAccount('admin')">admin</el-button>
                        <el-button type="danger" size="mini" plain @click="testAccount('test')">test</el-button>
                    </div>

            </el-form>
            <el-form v-show="formType == 'register'" ref="registerFormRef" :model="registerForm" :rules="registerRules" class="login-form" auto-complete="on" label-position="left">
                <div class="title-container">
                    <h3 class="title">Sign up to {{ title }} </h3>
                </div>
                <div>
                    <el-form-item prop="username">
                        <el-input ref="username" v-model="registerForm.userName" placeholder="username" type="text" tabindex="1" autocomplete="on">
                            <template #prefix>
                                <svg-icon name="user" />
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item prop="phone">
                        <el-input ref="phone" v-model="registerForm.phone" placeholder="phone" type="text" tabindex="1" autocomplete="on">
                            <template #prefix>
                                <el-icon><el-icon-phone-filled /></el-icon>
                                <!-- <svg-icon name="user" /> -->
                            </template>
                        </el-input>
                    </el-form-item>

                    <el-form-item prop="newPassword">
                        <el-input ref="newPassword" v-model="registerForm.userPassword" :type="passwordType" placeholder="password" tabindex="3" autocomplete="on">
                            <template #prefix>
                                <svg-icon name="password" />
                            </template>
                            <template #suffix>
                                <svg-icon :name="passwordType === 'password' ? 'eye' : 'eye-open'" @click="showPassword" />
                            </template>
                        </el-input>
                    </el-form-item>
                </div>
                <el-row :gutter="15" style="padding-top: 20px;">
                    <el-col :md="6">
                        <el-button style="width: 100%;" @click="formType = 'login'">back</el-button>
                    </el-col>
                    <el-col :md="18">
                        <el-button ref="submit" :loading="loading" type="primary" style="width: 100%;" @click.prevent="doRegister">submit</el-button>
                    </el-col>
                </el-row>
            </el-form>
        </div>
        <Copyright v-if="$store.state.settings.showCopyright" />
    </div>
</template>

<script setup name="Login">
import axios from "axios";

const { proxy } = getCurrentInstance()
const store = useStore()
const route = useRoute(), router = useRouter()
const title = import.meta.env.VITE_APP_TITLE

// 表单类型，login 登录，reset 重置密码
// const formType = ref('login')
var formType = ref('login')

const loginForm = ref({
    account: localStorage.login_account || '',
    password: '',
    remember: !!localStorage.login_account
})

const loginRules = ref({
    account: [
        { required: true, trigger: 'blur', message: 'please input your username!' }
    ],
    password: [
        { required: true, trigger: 'blur', message: 'please input your password!' },
        { min: 6, max: 18, trigger: 'blur', message: 'password length should between 6 to 18 characters!' }
    ]
})

const registerForm = ref({
    userName: '',
    phone: '',
    userPassword: ''
})

const registerRules = ref({
    userName: [
        { required: true, trigger: 'blur', message: 'please input a username!' }
    ],
    phone: [
        { required: true, trigger: 'blur', message: 'please input your phone number!' }
    ],
    userPassword: [
        { required: true, trigger: 'blur', message: 'please input a password!' },
        { min: 6, max: 18, trigger: 'blur', message: 'password length should between 6 to 18 characters!' }
    ]
})

const loading = ref(false)
const passwordType = ref('password')
const redirect = ref(null)

onMounted(() => {
    redirect.value = route.query.redirect ?? '/'
})

function showPassword() {
    passwordType.value = passwordType.value === 'password' ? '' : 'password'
    nextTick(() => {
        proxy.$refs.password.focus()
    })
}

function doRegister(){
    proxy.$refs.registerFormRef.validate(valid => {
        console.log(registerForm._rawValue)
        if (valid) {
            // eslint-disable-next-line no-unused-vars
            axios.post('http://localhost:8080/api/user/register', registerForm._rawValue).then(function(resp) {
            })
        } else {
            console.log('error submit!!')
            return false
        }
    })
}

// 注册函数
function handleRegister() {
    // console.log('handleRegister')
    // console.log(registerForm.value)
    proxy.$refs.registerFormRef.validate(valid => {
        if (valid) {
            loading.value = true
            store.dispatch('http://localhost:8080/api/user/register', registerForm.value).then(() => {
                location.reload()
                proxy.$message({
                    message: 'successful',
                    type: 'info'
                })
                formType.value = 'login'
                loading.value = false

                loginForm.value.account = registerForm.value.phone
                loginForm.value.password = registerForm.value.userPassword

            }).catch(error => {
                proxy.$message({
                    message: error.message,
                    type: 'error'
                })
                loading.value = false
            })
        }
    })
}

// 登录函数
function handleLogin() {
    proxy.$refs.loginFormRef.validate(valid => {
        if (valid) {
            loading.value = true
            store.dispatch('user/login', loginForm.value).then(() => {
                loading.value = false
                if (loginForm.value.remember) {
                    localStorage.setItem('login_account', loginForm.value.account)
                    console.log(loginForm.value.account)
                } else {
                    localStorage.removeItem('login_account')
                }
                router.push(redirect.value)
            }).catch(() => {
                loading.value = false
            })

        }
    })
}

function handleFind() {
    proxy.$message({
        message: '重置密码仅提供界面演示，无实际功能，需开发者自行扩展',
        type: 'info'
    })
    proxy.$refs.resetFormRef.validate(valid => {
        if (valid) {
            // 这里编写业务代码
        }
    })
}

function testAccount(account) {
    loginForm.value.account = account
    loginForm.value.password = '123456'
    handleLogin()
}
</script>

<style lang="scss" scoped>
[data-mode="mobile"] {
    #login-box {
        max-width: 80%;
        .login-banner {
            display: none;
        }
    }
}
:deep(input[type="password"]::-ms-reveal) {
    display: none;
}
.bg-banner {
    position: absolute;
    z-index: 0;
    width: 100%;
    height: 100%;
    background-image: url("../assets/images/login-bg.jpg");
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
    background: rgb(255 255 255 / 80%);
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 0 5px #999;
    .login-banner {
        width: 300px;
        background-image: url("../assets/images/login-banner.jpg");
        background-size: cover;
        background-position: center center;
    }
    .login-form {
        width: 450px;
        padding: 50px 35px 30px;
        overflow: hidden;
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
    :deep(.el-input) {
        height: 48px;
        line-height: inherit;
        width: 100%;
        input {
            height: 48px;
        }
        .el-input__prefix,
        .el-input__suffix {
            display: flex;
            align-items: center;
        }
        .el-input__prefix {
            left: 10px;
        }
        .el-input__suffix {
            right: 10px;
        }
    }
    .flex-bar {
        display: flex;
        justify-content: space-between;
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
