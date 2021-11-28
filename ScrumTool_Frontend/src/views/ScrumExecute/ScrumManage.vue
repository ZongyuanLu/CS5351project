<template>
    <el-table
        :data="tableData"
        style="width: 100%;">
        <el-table-column
            label="Scrum ID"
            prop="id">
        </el-table-column>
        <el-table-column
            label="Scrum title"
            prop="title">
        </el-table-column>
        <el-table-column
            label="Duration"
            prop="duration">
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
                @click="goto_detail()">Detail</el-button>
            <el-button
                size="mini"
                type="primary"
                @click="goto_join()">Join</el-button>
            <el-button
                size="mini"
                type="primary"
                @click="goto_update()">Upd</el-button>
        </el-table-column>
    </el-table>

</template>

<script>
import axios from 'axios'
import store from '../../store'
export default {
    data() {
        return {
            tableData: null
        }
    },
    created() {
        const _this = this
        axios.get('http://localhost:8080/api/scrums/').then(function(resp) {
            _this.tableData = resp.data
            console.log(resp.data)
            console.log(store.state.user.account)
        })
        axios.get('http://localhost:8080/api/scrums/').then(function(resp) {
            _this.tableData = resp.data
            console.log(resp.data)
            console.log(store.state.user.account)
        })
    },
    methods: {
        goto_detail(){
            this.$router.push({
                path: '/Scrum/RelatedTask',
                query: {
                }
            })
        },
        goto_join(){
            this.$router.push({
                path: '/Scrum/JoinScrum',
                query: {
                }
            })
        },
        goto_update(){
            this.$router.push({
                path: '/Scrum/ScrumUpdate',
                query: {
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
