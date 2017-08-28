$(document).on('ready', function() {
    // 单文件上传配置
    $("#icon").fileinput({
        language : 'zh',
        uploadUrl : "/api/user/icon/" + $("#id").val(),
        autoReplace : true,
        maxFileCount : 1,
        allowedFileExtensions : [ "jpg", "png", "gif" ]
    }).on("fileuploaded", function(e, data) {
        console.log(data);
        if (data.response.code == 0) {
            layer.msg("上传成功", {icon: 1, time: 2000});
        }else {
            layer.msg("上传失败", {icon: 2, time: 2000});
        }
    })
});