
// 要安装echart和antd（table）
// npm install ant-design-vue@next --save
// npm install echarts --save
// 在main.js中引入antd
// import Antd from 'ant-design-vue'
// import 'ant-design-vue/dist/antd.css'
// const app = createApp(App).use(Antd)

<template>
    <div>
        <page-header title="Charts" />
        <el-row style="margin: -10px 5px;">
            <el-col :md="12">
                <page-main style="margin: 2px;">
                    <div ref="chart1" style="width: 100%; height: 400px;" />
                </page-main>
            </el-col>
            <el-col :md="12">
                <page-main style="margin: 2px;">
                    <div ref="chart2" style="width: 100%; height: 400px;" />
                </page-main>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import * as Echarts from 'echarts'
import taskDatatest from './task_test.json'
import axios from 'axios'

export default {
    data() {
        return {
            chartsData: null,
            chart1: null,
            chart2: null
        }
    },
    created() {
        let scrum = this.$route.path.split('/')[2]
        console.log(scrum)
        this.scrumid = scrum

        this.duration = 10

    },
    mounted() {
        this.initialize()
        // this.getChartData()
        // this.initChart1()
        // this.initChart2()
        window.addEventListener('resize', () => {
            this.chart1.resize()
            this.chart2.resize()
        })

    },
    methods: {
        initialize() {
            let that = this
            axios.get('http://localhost:8080/api/tasks/').then(function(resp) {
                let taskData = resp.data
                console.log(taskData)
                let chart = that.getChartData(taskData)
                console.log('res')
                console.log(chart)
                that.initChart1(chart)
                that.initChart2(chart)
            })

            // let taskData = taskDatatest
            // console.log(taskData)
            // let chart = this.getChartData(taskData)
            // this.initChart1(chart)
            // this.initChart2(chart)
        },
        getChartData(taskData) {
            var res = new Object()
            let Allwkload = 0
            for (let i = 0;i < taskData.length; i++) {
                if (taskData[i].scrum_id == this.scrumid) {
                    Allwkload += taskData[i].workload
                }
            }
            console.log('wkload:')
            console.log(Allwkload)
            let round = Math.ceil(Allwkload / this.duration)
            let sprints = []
            for (let i = 0;i < round; i++) {
                let k = i + 1
                let item = 'Sprint'
                item += k.toString()
                sprints.push(item)
            }
            res.sprints = sprints

            let expect_remains = []
            for (let i = 0;i < round; i++) {
                let item = Math.max(Allwkload - (i + 1) * this.duration)
                expect_remains.push(Math.max(item, 0))
            }
            res.expect_remains = expect_remains

            let expect_wkload = []
            for (let i = 0;i < round - 1; i++) {
                expect_wkload.push(this.duration)
            }
            expect_wkload.push(Allwkload % this.duration)
            res.expect_wkload = expect_wkload

            let actual_wkload = []
            for (let i = 0;i < round; i++) {
                let sprint_num = i + 1
                let sprint_wkload = 0.0
                for (let j = 0;j < taskData.length; j++) {
                    if (taskData[j].scrum_id == this.scrumid && taskData[j].sprint_num == sprint_num && taskData[j].status == 'DONE') {
                        sprint_wkload += taskData[j].workload
                    }
                }
                actual_wkload.push(sprint_wkload)
            }
            res.actual_wkload = actual_wkload

            let actual_remains = []
            let Allwkload_copy = Allwkload
            for (let i = 0;i < round;i++) {
                actual_remains.push(Allwkload_copy)
                Allwkload_copy -= actual_wkload[i]
            }
            res.actual_remains = actual_remains

            this.chartsData = res
            // console.log(res)
            return res

        },
        initChart1(chartsData) {
            this.chart1 = Echarts.init(this.$refs.chart1)
            let option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['Expected wkload', 'Actual wkload']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: chartsData.sprints
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: 'wkload(hours)'
                    }
                ],
                series: [
                    {
                        name: 'Expected wkload',
                        type: 'bar',
                        data: chartsData.expect_wkload
                    },
                    {
                        name: 'Actual wkload',
                        type: 'bar',
                        data: chartsData.actual_wkload
                    }
                ]
            }
            this.chart1.setOption(option)
        },
        initChart2(chartsData) {
            this.chart2 = Echarts.init(this.$refs.chart2)
            let option = {
                title: {
                    text: 'Burn Down Chart'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                legend: {
                    data: ['Actual', 'Expected']
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        boundaryGap: false,
                        data: chartsData.sprints
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: 'remain(hours)'
                    }
                ],
                series: [
                    {
                        name: 'Actual',
                        type: 'line',
                        areaStyle: {
                            opacity: 0
                        },
                        data: chartsData.actual_remains
                    },
                    {
                        name: 'Expected',
                        type: 'line',
                        areaStyle: {
                            opacity: 0
                        },
                        data: chartsData.expect_remains
                    }
                ]
            }
            this.chart2.setOption(option)
        },

        open(url) {
            window.open(url, 'top')
        }
    }
}
</script>
