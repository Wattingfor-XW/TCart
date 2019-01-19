var app = new Vue({
    el:'#app',
    data:{
       username:'',
       firstname:'',
       lastname:'',
       email:'',
       password:'',
       selectedRoles:[]
    },
    methods:{
        handleAddClick(){
            this.register();
        },
        register(){
            axios.post('/user/addUser',{
                username:this.username,
                firstName:this.firstName,
                lastName:this.lastName,
                email:this.email,
                password:this.password,
                roles:this.selectedRoles
            }).then(function (response){
                console.log(response);
                alert('注册成功');
                location.href= 'emp.html';
            }).catch(function(error){
                console.log(error);
                alert('注册失败')
            }); 
        }
    }
})