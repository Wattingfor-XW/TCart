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
            axios.get('/user/getUserWithPage',{
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
        handleCurrentChange(){
          
        },
        handleSelectionChange(users){
            console.log(users);
            this.selectedUserIds = users.map(u => u.userId);
        },
        handleRegisterClick(){
            location.href="adduser.html"
        },
        handleBatchdelecClick(){
            this.batchDelete();
        },
        batchDelete(){
            axios.post('/user/batchDelete', this.selectedUserIds)
              .then(function (response) {
                console.log(response);
                alert('注销成功');
                location.reload();
              })
              .catch(function (error) {
                console.log(error);
                alert(error);
              });
        },
        selectedUserIds(){
            selectedUserIds:this.selectedUserIds;
        }
    }
})