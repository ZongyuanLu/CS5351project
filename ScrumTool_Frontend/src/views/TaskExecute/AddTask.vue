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
        <el-form-item label="Title">
            <el-input v-model="form.title" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Description">
            <el-input v-model="form.description" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Status">
            <el-select v-model="form.status" placeholder="Please Select Status">
                <el-option label="TODO" value="TODO" />
                <el-option label="DOING" value="INPROGRESS" />
                <el-option label="DONE" value="DONE" />
            </el-select>
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
                user_id: this.$store.state.user.account,
                id:'',
                title: '',
                description: '',
                status: ''
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
                    axios.post('http://localhost:8080/api/scrums/enquiry/' + this.form.id +'/'+ this.form.user_id + '/createTasks/', this.form).then(function(resp) {
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
