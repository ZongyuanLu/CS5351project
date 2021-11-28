<template>

    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="Scrum ID">
            <el-input v-model="form.id" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Title">
            <el-input v-model="form.name" style="width: 50%;"/>
        </el-form-item>

        <el-form-item label="Author">
            <el-input v-model="form.author" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="Duration">
            <el-input v-model="form.author" style="width: 50%;"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">Delete</el-button>
            <el-button type="danger">Cancel</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
export default {
    data() {
        return {
            form: {
                name: '',
                region: '',
                date1: '',
                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
            }
        }
    },
    methods: {
        onSubmit() {
            console.log('submit!')
        }
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
