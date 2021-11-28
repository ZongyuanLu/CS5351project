const Layout = () => import('@/layout/index.vue')
// import EmptyLayout from '@/layout/empty'

export default {
    path: '/Scrum',
    component: Layout,
    name: 'Scrum',
    meta: {
        title: 'Scrum Mgt',
        icon: 'sidebar-breadcrumb'
    },
    children: [
        {
            path: 'ScrumManage',
            name: 'enquiryScrum',
            component: () => import(/* webpackChunkName: 'Taxinfo' */ '@/views/ScrumExecute/ScrumManage.vue'),
            meta: {
                title: 'Enquiry Scrum'
            }
        },

        {
            path: 'AddScrum',
            name: 'addScrum',
            component: () => import(/* webpackChunkName: 'Taxinfo' */ '@/views/ScrumExecute/AddScrum.vue'),
            meta: {
                title: 'Add Scrum'
            }
        },
        {
            path: 'JoinScrum',
            name: 'joinScrum',
            component: () => import('@/views/ScrumExecute/JoinScrum.vue'),
            meta: {
                title: 'Join Scrum'
            }
        },
        {
            path: 'ScrumUpdate',
            name: 'updateScrum',
            component: () => import(/* webpackChunkName: 'Taxinfo' */ '@/views/ScrumExecute/UpdateScrum.vue'),
            meta: {
                title: 'Update Scrum'
            },
            show: false
        },
        {
            path: 'RelatedTask',
            name: 'relatedTask',
            component: () => import(/* webpackChunkName: 'Taxinfo' */ '@/views/ScrumExecute/RelatedTask.vue'),
            meta: {
                title: 'Related Task'
            },
            show: false
        }
    ]
}
