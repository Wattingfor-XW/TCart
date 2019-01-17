var emp = new Vue({
    el:'#emp',
    data:{
        pageInfo:'',
        pageNum:1,
        selectUserIds:[]
    },
    mounted:function(){
        alert('进入用户管理');
        this.getUser();
    },
    methods:{
        getUser(){
            axios.get('/user/getUsersWithPage',{
                params:{
                    pageNum:this.pageNum
                }
            }).then(function(response){
                console.log(response)
                emp.pageInfo=response.data;
            }).catch(function (error) {
                console.log(error);
            });
        },
        handleSelectionChange(users){
            console.log(users);
            this.selectedUserIds = users.map(u => u.userId);
        },
        handleRegisterClick(){
            location.href="register.html"
        },
        handleBatchdelecClick(){
            console.log('batch delete click');
            this.batchDelete();
        },
        batchDelete(){
            axios.post('/user/batchDelete', this.selectedUserIds)
              .then(function (response) {
                console.log(response);
                alert('删除成功');
                location.reload();
              })
              .catch(function (error) {
                console.log(error);
                alert(error.response.data.message);
              });
        }
    }
})