var app = new Vue({
    el:'#app',
    data:{
        username:'',
        password:''
    },
    methods:{
        handleRetrieveClick(){
            location.href="Retrieve.html";
        },
        handleLoginClick(){
            this.login();
        },
        login(){
            axios.get('http://localhost:8080/user/login',{
                params:{
                    username:this.username,
                    password:this.password
                }
            }).then(function(response){
                console.log('resoinse');
                localStorage['token']='TCat '+response.data;
                location.href='index.html';
                alert("登陆成功")
            })
            .catch(function (error) {
                alert("登陆失败")
                console.log(error);
            });
        }
    }
})