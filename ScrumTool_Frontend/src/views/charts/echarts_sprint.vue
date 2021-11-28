<template>
    <div>
        <page-header title="Sprint Backlog" />
        <el-row style="margin: -10px 5px;">
            <el-col :md="24">
                <page-main style="margin: 2px;">
                    <a-table :columns="columns" :data-source="data">
                        <template #tags="{ text: tags }">
                            <a-tag
                                :color="tags == 'todo' ? 'volcano' : tag == 'inprogress' ? 'geekblue' : 'green'"
                            >
                                {{ tags.toUpperCase() }}
                            </a-tag>
                        </template>
                    </a-table>
                </page-main>
            </el-col>
        </el-row>
    </div>
</template>

<script>

const columns = [
    {
        title: 'User_id',
        dataIndex: 'name',
        key: 'name',
        slots: { customRender: 'name' }
    },
    {
        title: 'Taskid',
        dataIndex: 'taskid',
        key: 'taskid'
    },
    {
        title: 'Task Content',
        dataIndex: 'taskcontent',
        key: 'taskcontent'
    },
    {
        title: 'Task Status',
        key: 'tags',
        dataIndex: 'tags',
        slots: { customRender: 'tags' }
    }
]

import taskDatatest from './task_test.json'
import axios from 'axios'

export default {
    data() {
        const data = []
        return {
            data,
            columns
        }
    },
    created() {
        let that = this
        axios.get('http://localhost:8080/api/tasks/').then(function(resp) {
            let taskData = resp.data
            console.log(taskData)
            that.setData(taskData)
        })
        // let taskData = taskDatatest.data
        // this.setData(taskData)
    },
    mounted() {
    },
    methods: {
        setData(taskData) {
            let paras = this.$route.path.split('/')
            let scrumid = paras[2]
            let sprintnum = paras[3]
            console.log(scrumid)
            console.log(sprintnum)
            var datatmp = []
            for (let i = 0;i < taskData.length; i++) {
                if (taskData[i].sprint_num == sprintnum && taskData[i].scrum_id == scrumid) {
                    var taskitem = new Object()
                    taskitem.name = taskData[i].user_id
                    taskitem.taskid = taskData[i].id
                    taskitem.taskcontent = taskData[i].description
                    taskitem.tags = taskData[i].status
                    datatmp.push(taskitem)
                }
            }
            this.data = datatmp
        },
        open(url) {
            window.open(url, 'top')
        }
    }
}

</script>
