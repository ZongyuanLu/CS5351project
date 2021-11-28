const Layout = () => import('@/layout/index.vue')
// import EmptyLayout from '@/layout/empty'

export default {
    path: '/Sprint',
    component: Layout,
    name: 'Sprint',
    meta: {
        title: 'Task Mgt',
        icon: 'sidebar-permission'
    },
    children: [
        {
            path: 'SprintManage',
            name: 'enquirySprint',
            component: () => import(/* webpackChunkName: 'Report' */ '@/views/TaskExecute/TaskManage.vue'),
            meta: {
                title: 'Enquiry Tasks'
            }
        },
        {
            path: 'AddTask',
            name: 'addTask',
            component: () => import('@/views/TaskExecute/AddTask.vue'),
            meta: {
                title: 'Add Task'
            }
        },
        {
            path: 'UpdateTask',
            name: 'updateTask',
            component: () => import('@/views/TaskExecute/UpdateTask.vue'),
            meta: {
                title: 'Update Task'
            }
        },
        {
            path: 'TaskManage-TODO',
            name: 'taskEnquiry-TODO',
            component: () => import(/* webpackChunkName: 'Report' */ '@/views/TaskExecute/TaskManage_TODO.vue'),
            meta: {
                title: 'Tasks-TODO'
            }
        },
        {
            path: 'TaskManage-DOING',
            name: 'taskEnquiry-DOING',
            component: () => import(/* webpackChunkName: 'Report' */ '@/views/TaskExecute/TaskManage_DOING.vue'),
            meta: {
                title: 'Tasks-DOING'
            }
        },
        {
            path: 'TaskManage-DONE',
            name: 'taskEnquiry-DONE',
            component: () => import(/* webpackChunkName: 'Report' */ '@/views/TaskExecute/TaskManage_DONE.vue'),
            meta: {
                title: 'Tasks-DONE'
            }
        }
    ]
}
