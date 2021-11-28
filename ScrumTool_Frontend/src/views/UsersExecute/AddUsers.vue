<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="" style="width: 50%;">

        </el-form-item>
        <el-form-item label="活动名称">
            <el-input v-model="form.name" style="width: 50%;"/>
        </el-form-item>
        <el-form-item label="活动区域">
            <el-select v-model="form.region" placeholder="请选择活动区域">
                <el-option label="区域一" value="shanghai" />
                <el-option label="区域二" value="beijing" />
            </el-select>
        </el-form-item>
        <el-form-item label="活动时间">
            <el-col :span="11">
                <el-date-picker v-model="form.date1" type="date" placeholder="选择日期" style="width: 50%;" />
            </el-col>
            <el-col class="line" :span="2"></el-col>

        </el-form-item>
        <el-form-item label="即时配送">
            <el-switch v-model="form.delivery" />
        </el-form-item>
        <el-form-item label="活动性质">
            <el-checkbox-group v-model="form.type">
                <el-checkbox label="美食/餐厅线上活动" name="type" />
                <el-checkbox label="地推活动" name="type" />
                <el-checkbox label="线下主题活动" name="type" />
                <el-checkbox label="单纯品牌曝光" name="type" />
            </el-checkbox-group>
        </el-form-item>
        <el-form-item label="特殊资源">
            <el-radio-group v-model="form.resource">
                <el-radio label="线上品牌商赞助" />
                <el-radio label="线下场地免费" />
            </el-radio-group>
        </el-form-item>
        <el-form-item label="活动形式">
            <el-input v-model="form.desc" type="textarea" style="width: 50%;"/>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">立即创建</el-button>
            <el-button>取消</el-button>
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
<!--<script>-->
export default {
    data() {
        return {
            ruleForm: {
                UserName: '',
                UserStatus: '',
                account: '',
                passwd: '',
                otherTax: '',
                delivery: false,
                type: [],
                resource: '',
                desc: ''
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
    methods: {
        submitForm(formName) {
            // eslint-disable-next-line no-unused-vars
            const _this = this
            this.$refs[formName].validate(valid => {
                if (valid) {
                    this.$api.post('/register', this.ruleForm).then(function(resp) {
                        console.log(resp)
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
