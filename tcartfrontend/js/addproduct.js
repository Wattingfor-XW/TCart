var app = new Vue({
    el:'#app',
    data:{
        productCode:'',
        name:'',
        price:'',
        brand:''
    },
    methods:{
        handleAddClick(){
            this.register();
        },
        register(){
            axios.post('/product/addProduct',{
                productCode:this.productCode,
                name:this.name,
                price:this.price,
                brand:this.brand
            }).then(function (response){
                console.log(response);
                alert('上架成功');
                location.href= 'product.html';
            }).catch(function(error){
                console.log(error);
                alert('上架失败')
            }); 
        }
    }
})