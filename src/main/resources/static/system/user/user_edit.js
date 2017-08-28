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
   /* $(document).delegate("#btn_edit", 'click',function () {
        var id = $("#id").val();
        $.ajax({
            url: "/api/user/"+id,
            async:false,
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
    });*/

    $(document).delegate("#btn_edit", 'click',function () {
        var formData = new FormData($("#editForm")[0]);
        console.log(formData.get("username"));
        $.ajax({
            url: "/api/user/"+id,
            type:"POST",
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            data:formData,
            success: function(data){
                if (data.code == 0) {
                    layer.msg("编辑成功", {icon: 1, time: 2000});
                }else {
                    layer.msg("编辑失败", {icon: 2, time: 2000});
                }
            },
            error:function(data){
                layer.msg("请求异常", {icon: 5, time: 1500});
            }
        });
    });

    // bootstrap-fileinput图片上传配置
    $("#icon").fileinput({
        language: 'zh',     //设置语言
        showUpload: false, //是否显示上传按钮
        showRemove:true,   //是否显示删除按钮
        showCaption: true, //是否显示标题
        allowedPreviewTypes: ['image'],
        allowedFileTypes: ['image'],
        allowedFileExtensions:  ['jpg', 'png', 'gif'],
        maxFileSize : 2000, //最大文件大小
        maxFileCount: 1,    //上传允许的最大文件数
        uploadAsync: false //同步上传
    });
});