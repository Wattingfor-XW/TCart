var app = new Vue({
    el:'#app',
    data:{
       goodsname:'',
       price:''
    },
    methods:{
        handleAddClick(){
            this.register();
        },
        register(){
            axios.post('/goods/addGoods',{
                goodname:this.goodname,
                price:this.price
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