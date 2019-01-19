var app = new Vue({
    el:'#app',
    data:{
       goodsrid:'',
       goodsname:''
    },
    methods:{
        handleAddClick(){
            this.register();
        },
        register(){
            axios.post('/goods/addGoods',{
                goodname:this.goodsid,
                price:this.goodsname
            }).then(function (response){
                console.log(response);
                alert('上架成功');
                location.href= 'goods.html';
            }).catch(function(error){
                console.log(error);
                alert('上架失败')
            }); 
        }
    }
})