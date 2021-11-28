<template>

    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="User ID">
            <el-input v-model="$store.state.user.account" style="width: 50%;"/>
        </el-form-item>

        <el-form-item label="Scrum ID">
            <el-input v-model="form.id" style="width: 50%;"/>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('form')">Join</el-button>
            <el-button type="danger">Cancel</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
import axios from "axios";

export default {
    data() {
        return {
            form: {
                phone: '',
                id: ''
            }
        }
    },
    methods: {
        submitForm(formName) {
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    // eslint-disable-next-line no-unused-vars
                    console.log(this.form)
                    axios.post('http://localhost:8080/api/scrumUser/join/' + this.form.id + '/' + this.$store.state.user.account).then(function(resp) {
                        _this.$alert('success', 'message', {
                            confirmButtonText: 'confirm',
                            // eslint-disable-next-line no-unused-vars
                        })
                    })
                } else {
                    console.log('error submit!!')
                    return false
                }
            })
        },
    }
}
</script>

<style>
.el-form {
    align-items: center;
    align-content: center;
}
.el-input {
    text-align: center;
}
</style>
