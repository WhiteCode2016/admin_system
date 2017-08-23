$(function () {
    // 初始化datepicker
    $(".datepicker").datepicker({
        startDate: '-d',// 设置开始时间为当前日期
        format:'yyyy/mm/dd',
        language: 'zh-CN',
        todayHighlight:true,
        autoclose: true
    });

    // 编辑操作
    $(document).delegate("#btn_edit", 'click',function () {
        var id = $("#id").val();
        $.ajax({
            url: "/api/user/"+id,
            async:true,
            type:"POST",
            dataType:"json",
            cache:false,    //不允许缓存
            data:$("#editForm").serialize(),
            success: function(data){
                if (data.code == 0) {
                    alert("添加成功");
                }else {
                    alert("添加失败");
                }
            },
            error:function(data){
                alert("请求异常");
            }
        });
    });
});