   function login(){
        var $btn=$(".btn");
        $btn.on(
            "click",function(){
               var username = $("#username").val();  
               var password = $("#password").val();
                 $.ajax({
                     url:"http://localhost:8081/auth",
                     data:{'username':username,'password':password},
                     beforeSend: function(request) {
                       request.setRequestHeader("X-AUTH-TOKEN", "test");
                     },
                     dataType:"json",
                    type:"post",
                    success:function(data){
                       if(data.code==200){
                           alert("登陆成功")
                            window.location.href = "index.html";
                       }else{
                           alert("登陆失败！");
                       }
                    }
                  })
        })
    }

    function data(){
      $.ajax({
        url: 'http://localhost:8081',
        type: 'get',
        // dataType: 'default: Intelligent Guess (Other values: xml, json, script, or html)',
        // data: {param1: 'value1'},
      })
      .done(function(data) {
        console.log("success");
        $("#city").text(data);
      })
      .fail(function() {
        console.log("error");
      })
      .always(function() {
        console.log("complete");
      });
      
    }