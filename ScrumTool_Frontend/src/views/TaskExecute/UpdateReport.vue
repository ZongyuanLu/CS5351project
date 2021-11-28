<template>
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="报告编号" prop="reportId">
            <el-input v-model="ruleForm.reportId" />
        </el-form-item>

        <el-form-item label="企业编号" prop="eId">
            <el-input v-model="ruleForm.eId" />
        </el-form-item>

        <el-form-item label="专家编号" prop="userName">
            <el-input v-model="ruleForm.userName" />
        </el-form-item>

        <el-form-item label="报告状态" prop="rStatus">
            <el-select v-model="ruleForm.rStatus">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                />
            </el-select>
        </el-form-item>

        <el-form-item label="日期" prop="time">
            <el-input v-model="ruleForm.time" />
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">确定</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            ruleForm: {
                reportId: '',
                eId: '',
                userName: '',
                rStatus: '',
                time: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            },
            rules: {
                reportId: [
                    { required: true, message: '请输入报告编号', trigger: 'blur' }
                ],
                eId: [
                    { required: true, message: '请输入企业编号', trigger: 'blur' }
                ],
                userName: [
                    { required: true, message: '请输入您的名称', trigger: 'blur' }
                ],
                time: [
                    { required: true, message: '请输入日期', trigger: 'blur' }
                ]
            },

            options: [{
                value: '0',
                label: '不可疑'
            }, {
                value: '1',
                label: '可疑'
            }]
        }
    },
    created() {
        const _this = this
        axios.get('http://127.0.0.1:1234/expert/report/' + this.$route.query.reportId).then(function(resp) {
            _this.ruleForm = resp.data
        })
    },
    methods: {
        submitForm(formName) {
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    // eslint-disable-next-line no-unused-vars
                    axios.post('http://127.0.0.1:1234/expert/updatereport', this.ruleForm).then(function(resp) {
                        _this.$alert('修改成功', '消息', {
                            confirmButtonText: '确定',
                            // eslint-disable-next-line no-unused-vars
                            callback: action => {
                                _this.$router.push('/reportmanage')
                            }
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