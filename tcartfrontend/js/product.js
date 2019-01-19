var app = new Vue({
    el:'#app',
    data:{
        pageInfo:'',
        pageNum:1,
        selectUserIds:[]
    },
    mounted:function(){
        alert('进入商品管理');
        this.getUser();
    },
    methods:{
        getUser(){
            axios.get('/product/getProductWithPage',{
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
        handleAddgoodsClick(){
            location.href="addproduct.html"
        },
        handleDeletegoodsClick(){
            console.log('batch delete click');
            this.batchDelete();
        },
        batchDelete(){
            axios.post('/product/batchDelete', this.selectedUserIds)
              .then(function (response) {
                console.log(response);
                alert('下架成功');
                location.reload();
              })
              .catch(function (error) {
                console.log(error);
                alert(error.response.data.message);
              });
        }
    }
})