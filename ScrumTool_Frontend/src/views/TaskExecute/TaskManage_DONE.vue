<template>
    <div id="cu">
        <el-form>
            <el-form-item label="" style="width: 50%;">

            </el-form-item>
        </el-form>
        <el-row align="middle" justify="center">
            <el-button type="danger" @click="to_TODO_TASK">TO-DO TASK</el-button>
            <el-button type="primary" @click="to_DOING_TASK">DOING TASK</el-button>
            <el-button type="success" disabled>DONE TASK</el-button>
        </el-row>
        <el-form>
        </el-form>

        <div id="cc">
            <el-table
                :data="tableData"
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
                    label="Complete Date"
                    prop="complete_date">
                </el-table-column>
                <el-table-column
                    label="Title"
                    prop="title">
                </el-table-column>
                <el-table-column
                    align="right">
                    <template slot="header" >
                        <el-button
                            size="mini"
                            type="success"
                            @click="handleEdit(scope.$index, scope.row)">DONE</el-button>
                    </template>
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">Upd</el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">Del</el-button>
                </el-table-column>
            </el-table>
        </div>
    </div>
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
        axios.get('http://localhost:8080/api/tasks/enquiry/done').then(function(resp) {
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
        to_DOING_TASK(row) {
            this.$router.push({
                path: '/Sprint/TaskManage-DOING',
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
.el-row {
    text-align: center;
}
</style>
