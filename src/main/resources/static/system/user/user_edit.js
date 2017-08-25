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
                    layer.msg("编辑成功", {icon: 1, time: 2000});
                }else {
                    layer.msg("编辑失败", {icon: 2, time: 2000});
                }
                window.open("/api/user/list", "_self");
            },
            error:function(data){
                layer.msg("请求异常", {icon: 5, time: 1500});
            }
        });
    });
});