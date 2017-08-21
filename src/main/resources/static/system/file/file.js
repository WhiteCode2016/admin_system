$(document).ready(function () {
    // 初始化dataTables，并加载数据
    var table = $("#dataTable").DataTable({
        "paging": true,         //开启表格分页
        "lengthChange": false, //是否允许用户改变表格每页显示的记录数
        "searching": false,    //禁用搜索
        "ordering": false,     //全局禁用排序
        "deferRender": true,   //延迟渲染
        "autoWidth": false,    //开启自适应宽度
        "processing": true,
        "serverSide": true,
        "language": {
            "url": "/static/AdminLTE-2.3.11/plugins/datatables/i18n/Chinese.json"
        },
        "ajax": {
            "url": "/api/file/listByPage",
            "type": "POST",
            "data": function (d) {
                d.suffix=$("#suffix").val().trim();
            }
        },
        "columns": [
            { "data": "id" },
            { "data": "originalFileName" },
            { "data": "suffix" },
            { "data": "size" },
            { "data": "contentType"},
            {
                "data" : null,
                "render":function(data, type, row, meta){
                    return	data='<button class="btn btn-primary btn-xs" id="deleteOne" data-id='+ row.id +'><i class="glyphicon glyphicon-trash"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="editOne"  data-id='+ row.id +'><i class="glyphicon glyphicon-edit"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="detailOne" data-id='+ row.id +'><i class="glyphicon glyphicon-edit"></i></button> ';
                }
            }
        ],
    });

    //单行删除按钮点击事件响应
    $(document).delegate('#deleteOne','click',function() {
        var id = $(this).data("id");
        $("#delSubmit").val(id);
        $("#deleteOneModal").modal('show');
    });
    //点击确认删除按钮
    $(document).delegate('#delSubmit','click',function(){
        var id=$(this).val();
        $('#deleteOneModal').modal('hide');
        $.ajax({
            url: "/api/file/"+id,
            async:true,
            type:"DELETE",
            dataType:"json",
            cache:false,    //不允许缓存
            success: function(data){
                var obj = eval(data);
                if(obj.code==1) {
                    window.location.reload();
                } else {
                    alert("删除失败");
                }
            },
            error:function(data){
                alert("请求异常");
            }
        });
    });
    // 打开编辑页面
    $(document).delegate('#editOne','click',function() {
        var id=$(this).data("id");
        window.open("/api/menu/" + id,"_self");
    });
    // 打开详情页面
    $(document).delegate('#detailOne','click',function() {
        var id=$(this).data("id");
        $("#detailOneModal").modal("show");
        $("#detailOneModal").on('show.bs.modal',function () {
            $.ajax({
                url: "/api/file/"+id,
                async:true,
                type:"GET",
                dataType:"json",
                cache:false,    //不允许缓存
                success: function(data){
                    alert(data.suffix);
                    $(".id").html(data.id);
                    $(".suffix").val(data.suffix);
                }
            });
        });
    });
    // 重置查询条件
    $(document).delegate('#reset','click',function() {
        $("#suffix").val("");
    });
    // 查询操作
    $(document).delegate('#search','click',function() {
        table.ajax.reload();
    });


});