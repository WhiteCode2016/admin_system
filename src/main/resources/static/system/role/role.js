$(document).ready(function () {
    var table = $("#dataTable").DataTable({
        "paging": true,         //开启表格分页
        "lengthChange": false, //是否允许用户改变表格每页显示的记录数
        "searching": false,    //禁用搜索
        "ordering": false,     //全局禁用排序
        "deferRender": true,   //延迟渲染
        "autoWidth": false,    //开启自适应宽度
        "processing": true,
        "serverSide": true,
        "dom": "<l<\'#topPlugin\'>f>rt<ip><'clear'>",
        "language": {
            "url": "/static/AdminLTE-2.3.11/plugins/datatables/i18n/Chinese.json"
        },
        "ajax": {
            // "url": "/static/objects.txt",
            "url": "/api/role/listByPage",
            "type": "POST",
            "data": function (d) {
                d.enabled=$("#enabled").val();
                d.roleName=$("#roleName").val().trim();
            }
        },
        "columns": [
            { "data": "id" },
            { "data": "roleName" },
            {
                "data": "enabled" ,
                "render" : function(data, type, row, meta) {
                    if(data == 1){
                        data ="<a href='#' class='upOrderStatus' data-id="+row.id+">显示</a>";
                    }else{
                        data ="<a href='#' class='upOrderStatus' data-id="+row.id+"><font color='red'>不显示</font></a>";
                    }
                    return	 data;
                }
            },
            { "data": "remarks" },
            {
                "data" : null,
                "render":function(data, type, row, meta){
                    return	data='<button id="deleteOne" class="btn btn-danger btn-xs" data-id='+ row.id +'>删 除</button> ' +
                        '<button id="editOne" class="btn btn-success btn-xs" data-id='+ row.id +'>编 辑</button>';
                }
            }
        ],
        initComplete:initComplete
    });

    //表格加载渲染完毕，加载功能按钮
    function initComplete(){
        //功能按钮
        var topPlugin = '<button class="btn btn-primary btn-sm" id="addOne" >新 增</button> ' +
            '<button class="btn btn-warning btn-sm" id="reset">重置搜索条件</button>' ;
        //在表格上方topPlugin DIV中追加HTML
        $("#topPlugin").append(topPlugin);
    }

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
            url: "/api/user/"+id,
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

    $(document).delegate('#reset','click',function() {
        $("#state").val("");
        $("#deptname").val("");
        $("#startTime").val("");
        $("#endTime").val("");
    });
    $(document).delegate('#search','click',function() {
        table.ajax.reload();
    });




});