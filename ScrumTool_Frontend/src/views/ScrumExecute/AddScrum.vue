<template>

    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="" style="width: 50%;">

        </el-form-item>

        <el-form-item label="Account">
            <el-input v-model="$store.state.user.account" style="width: 50%;"/>
        </el-form-item>

        <el-form-item label="Title">
            <el-input v-model="form.title" style="width: 50%;"/>
        </el-form-item>

        <el-form-item label="Author">
            <el-input v-model="form.author" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Duration">
            <el-input v-model="form.duration" style="width: 50%;"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="submitForm('form')">Add</el-button>
            <el-button type="danger">Cancel</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
import axios from "axios";
import store from '../../store'

export default {
    data() {
        return {
            form: {
                phone: this.$store.state.user.account,
                title: '',
                author: '',
                duration: ''
            }
        }
    },

    methods: {
        onSubmit() {
            console.log('submit!')
        },
        submitForm(formName) {
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    // eslint-disable-next-line no-unused-vars
                    console.log(this.form)
                    axios.post('http://localhost:8080/api/scrums/create', this.form).then(function(resp) {
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
