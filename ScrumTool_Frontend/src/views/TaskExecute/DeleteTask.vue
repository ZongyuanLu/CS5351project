<template>

    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="" style="width: 50%;">

        </el-form-item>

        <el-form-item label="Task ID">
            <el-input v-model="form.id" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Title">
            <el-input v-model="form.title" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Description">
            <el-input v-model="form.description" style="width: 50%;"/>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('form')">Add</el-button>
            <el-button type="danger">Cancel</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            form: {
                id: '',
                title: '',
                description: ''
            }
        }
    },
    created() {
        const _this = this
        axios.get('http://localhost:8080/api/tasks/' + this.$route.query.id).then(function(resp) {
            _this.ruleForm = resp.data
        })
    },
    methods: {
        submitForm(formName) {
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    // eslint-disable-next-line no-unused-vars
                    axios.post('http://127.0.0.1:1234/expert/deletereport', this.ruleForm).then(function(resp) {
                        _this.$alert('删除成功', '消息', {
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
