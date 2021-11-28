const Layout = () => import('@/layout/index.vue')
const Scrumchart = () => import('@/views/charts/echarts.vue')
const Sprintchart = () => import('@/views/charts/echarts_sprint.vue')

import routeDatatest from './testRouteData.json'
import axios from 'axios'
function getRoute() {
    console.log('getroutetest')
    // const _this = this
    // axios.get('http://localhost:8080/api/tasks/').then(function(resp) {
    //     let routeData = resp.data
    //     return generateRoute(routeData)

    // })
    let routeData = routeDatatest.data
    return generateRoute(routeData)

}

function generateRoute(routeData) {
    var res = []
    for (let i = 0;i < routeData.length; i++) {
        var routeitem = new Object()
        routeitem.path = routeData[i].project
        routeitem.name = routeData[i].project
        routeitem.redirect = '/charts/' + routeData[i].project

        var metaitem = new Object()
        metaitem.title = 'Scrum' + routeData[i].project
        routeitem.meta = metaitem

        var childrens = new Array()
        var firstview = new Object()
        firstview.path = 'page'
        firstview.name = routeData[i].project
        firstview.component = Scrumchart
        var firstchildrenmeta = new Object()
        firstchildrenmeta.title = 'Overview'
        firstview.meta = firstchildrenmeta
        childrens.push(firstview)

        let sprint_num = 1
        for (let j = 0;j < routeData[i].sprints; j++) {
            var childrenitem = new Object()
            childrenitem.path = sprint_num.toString()
            childrenitem.name = 'Scrum' + routeData[i].project + sprint_num
            childrenitem.component = Sprintchart
            var childrenmeta = new Object()
            childrenmeta.title = sprint_num.toString()
            childrenitem.meta = childrenmeta
            childrens.push(childrenitem)

            sprint_num += 1
        }

        routeitem.children = childrens

        res.push(routeitem)
    }
    return res
}

export default {
    path: '/charts',
    component: Layout,
    redirect: '/charts/page',
    name: 'chartExample',
    meta: {
        title: 'Charts',
        icon: 'sidebar-breadcrumb'
    },
    children: getRoute()
}
