<template>
    <el-table
        :data="tableData"
        border
        style="width: 100%;"
    >
        <el-table-column
            prop="userStatus"
            label="用户身份"
            width="200"
        />
        <el-table-column
            prop="userName"
            label="用户名称"
            width="120"
        />
        <el-table-column
            prop="account"
            label="账号"
            width="250"
        />
        <el-table-column
            prop="passwd"
            label="密码"
            width="120"
        />
        <el-table-column
            fixed="right"
            label="操作"
            width="295"
        >
            <template slot-scope="scope">
                <el-button type="text" size="small" @click="edit(scope.row)">修改</el-button>
                <el-button type="text" size="small" @click="remove(scope.row)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            tableData: null
        }
    },
    created() {
        const _this = this
        axios.get('http://127.0.0.1:1234/enquiry/allusers').then(function(resp) {
            _this.tableData = resp.data
            console.log(resp.data)
        })
    },
    methods: {
        edit(row) {
            this.$router.push({
                path: '/Users/UsersUpdate',
                query: {
                    UserName: row.UserName,
                    account: row.account
                }
            })
        },
        remove(row) {
            this.$router.push({
                path: '/Users/UserDelete',
                query: {
                    UserName: row.UserName,
                    account: row.account
                }
            })
        }
    }
}
</script>