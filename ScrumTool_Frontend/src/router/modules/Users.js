const Layout = () => import('@/layout/index.vue')
// import EmptyLayout from '@/layout/empty'

export default {
    path: '/Users',
    component: Layout,
    name: 'Users',
    meta: {
        title: 'Users Info Mgt',
        icon: 'sidebar-menu',
        auth: ['permission.create']
    },
    children: [
        {
            path: 'UsersManage',
            name: 'enquiryUsers',
            component: () => import(/* webpackChunkName: 'Users' */ '@/views/UsersExecute/UsersManage.vue'),
            meta: {
                title: 'Enquiry Users'
            }
        },

        {
            path: 'AddUsers',
            name: 'addUsers',
            component: () => import(/* webpackChunkName: 'Users' */ '@/views/UsersExecute/AddUsers.vue'),
            meta: {
                title: 'Add Users'
            }
        },
        {
            path: 'UsersDelete',
            name: 'deleteUsers',
            component: () => import(/* webpackChunkName: 'Users' */ '@/views/UsersExecute/DeleteUsers.vue'),
            meta: {
                title: 'Delete Users'
            },
            show: false
        },
        {
            path: 'UsersUpdate',
            name: 'updateUsers',
            component: () => import(/* webpackChunkName: 'Users' */ '@/views/UsersExecute/UpdateUsers.vue'),
            meta: {
                title: 'Update Users'
            },
            show: false
        }
    ]
}
