$(function () {
    // 初始化datepicker
    $(".datepicker").datepicker({
        startDate: '-d',// 设置开始时间为当前日期
        format:'yyyy/mm/dd',
        language: 'zh-CN',
        todayHighlight:true,
        autoclose: true
    });
    // 设置当前日期为datepicker的默认日期
    $(".datepicker").datepicker("setDate", new Date());

    // 添加操作
    $(document).delegate("#btn_add", 'click',function () {
        $.ajax({
            url: "/api/menu/",
            async:true,
            type:"POST",
            dataType:"json",
            cache:false,    //不允许缓存
            data:$("#addForm").serialize(),
            success: function(data){
                if (data.code == 0) {
                    layer.msg("添加成功", {icon: 1, time: 2000});
                } else {
                    layer.msg("操作失败", {icon: 2, time: 2000});
                }
                window.open("/api/menu/list", "_self");
            },
            error:function(data){
                layer.msg("请求异常", {icon: 5, time: 1500});
            }
        });
    });
});