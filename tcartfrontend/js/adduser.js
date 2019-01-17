var app = new Vue({
    el:'#app',
    data:{
       userid:'',
       username:''
    },
    methods:{
        handleAddClick(){
            this.register();
        },
        register(){
            axios.post('/user/addUser',{
                goodname:this.userid,
                price:this.username
            }).then(function (response){
                console.log(response);
                alert('上架成功');
                location.href= 'emp.html';
            }).catch(function(error){
                console.log(error);
                alert('上架失败')
            }); 
        }
    }
})