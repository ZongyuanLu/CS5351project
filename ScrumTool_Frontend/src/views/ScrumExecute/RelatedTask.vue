<template>
    <el-table
        :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
        style="width: 100%;">
        <el-table-column
            label="Sprint Num"
            prop="sprint_num">
        </el-table-column>
        <el-table-column
            label="Scrum ID"
            prop="scrum_id">
        </el-table-column>
        <el-table-column
            label="Created Date"
            prop="complete_date">
        </el-table-column>
        <el-table-column
            label="Status"
            prop="status">
        </el-table-column>
        <el-table-column
            align="right">
            <template slot="header" >
            </template>
            <template slot-scope="scope">
                <el-button
                    size="mini"
                    type="primary"
                    @click="handleEdit(scope.$index, scope.row)">Tasks Detail</el-button>
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
        axios.get('http://localhost:8080/api/tasks/').then(function(resp) {
            _this.tableData = resp.data
            console.log(resp.data)
        })
    },
    methods: {
        to_TODO_TASK(row) {
            this.$router.push({
                path: '/Sprint/TaskManage-TODO',
                query: {
                    id: row.id
                }
            })
        },
        to_DONE_TASK(row) {
            this.$router.push({
                path: '/Sprint/TaskManage-DONE',
                query: {
                    id: row.id
                }
            })
        },
        handleEdit(index, row) {
            console.log(index, row)
        },
        handleDelete(index, row) {
            console.log(index, row)
        }
    }
}
</script>
<style>
.el-table {
    text-align: center;
}
</style>
