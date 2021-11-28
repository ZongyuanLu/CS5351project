const Layout = () => import('@/layout/index.vue')

export default [
    {
        path: '/Documentations',
        component: Layout,
        meta: {
            title: 'ReadMe'
        },
        children: [
            {
                path: '',
                name: 'videosExampleVideoComingSoon',
                component: () => import('@/views/videos_example/coming.soon.vue'),
                meta: {
                    title: '即将推出',
                    sidebar: false,
                    breadcrumb: false,
                    activeMenu: '/videos_example/coming/soon'
                }
            }
        ]
    }
]
