var emp = new Vue({
    el:'#app',
    data:{
        pageInfo:'',
        pageNum:1,
        selectUserIds:[]
    },
    methods:{
       
        handleEMPClick(){
            location.href="emp.html"
        },
        handleGOODClick(){
            location.href="goods.html"
        }
    }
})