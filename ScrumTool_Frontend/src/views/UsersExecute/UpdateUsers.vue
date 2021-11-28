<template>
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户身份" prop="userStatus">
            <el-input v-model="ruleForm.UserStatus" />
        </el-form-item>

        <el-form-item label="用户名称" prop="userName">
            <el-input v-model="ruleForm.UserName" />
        </el-form-item>

        <el-form-item label="账号" prop="account">
            <el-input v-model="ruleForm.account" />
        </el-form-item>

        <el-form-item label="密码" prop="passwd">
            <el-input v-model="ruleForm.passwd" />
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import axios from 'axios' 
export default {
    data() {
        return {
            ruleForm: {
                UserName: '',
                UserStatus: '',
                account: '',
                passwd: ''
            },
            rules: {
                UserStatus: [
                    { required: true, message: '请输入企业编号', trigger: 'blur' }
                ],
                UserName: [
                    { required: true, message: '请输入缴税年度', trigger: 'blur' }
                ],
                account: [
                    { required: true, message: '请输入该年营收', trigger: 'blur' }
                ],
                passwd: [
                    { required: true, message: '请输入增值税', trigger: 'blur' }
                ]
            }
        }
    },
    created() {
        const _this = this
        axios.get('http://127.0.0.1:1234/enquiry/' + this.$route.query.account).then(function(resp) {
            _this.ruleForm = resp.data
        })
    },
    methods: {
        submitForm(formName) {
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    // eslint-disable-next-line no-unused-vars
                    axios.post('http://127.0.0.1:1234/update', this.ruleForm).then(function(resp) {

                        _this.$alert('修改成功', '消息', {
                            confirmButtonText: '确定'
                        })

                    })
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