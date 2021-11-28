<template>
    <el-table
        :data="tableData"
        style="width: 100%;">
        <el-table-column
            label="Scrum ID"
            prop="id">
        </el-table-column>
        <el-table-column
            label="Title"
            prop="title">
        </el-table-column>
        <el-table-column
            label="Sprint Num"
            prop="sprint_num">
        </el-table-column>
        <el-table-column
            label="Complete Date"
            prop="complete_date">
        </el-table-column>
        <el-table-column
            align="right">
            <template slot="header" >
                <el-button
                    size="mini"
                    type="warning"
                    @click="handleEdit(scope.$index, scope.row)">Add New Scrum</el-button>
            </template>
            <template slot-scope="scope">
                <el-button
                    size="mini"
                    @click="detail(scope.row)">Detail</el-button>
                <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">Join</el-button>
                <el-button
                    size="mini"
                    @click="edit(scope.row)">Upd</el-button>
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
        detail(row) {
            this.$router.push({
                path: '/Scrum/RelatedTask',
                query: {
                    id: row.id
                }
            })
        },
        edit(row) {
            this.$router.push({
                path: '/Scrum/ScrumUpdate',
                query: {
                    id: row.id
                }
            })
        },
        remove(row) {
            this.$router.push({
                path: '/Scrum/ScrumDelete',
                query: {
                    id: row.id
                }
            })
        }
    }
}
</script>
<style>
.el-table {
    text-align: center;
}
</style>
