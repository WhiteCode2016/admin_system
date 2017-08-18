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
                   /* return	data='<button id="deleteOne" class="btn btn-danger btn-xs" data-id='+ row.id +'>删 除</button> ' +
                        '<button id="editOne" class="btn btn-success btn-xs" data-id='+ row.id +'>编 辑</button>';*/
                    return	data='<button class="btn btn-primary btn-xs" id="deleteOne" data-id='+ row.id +'><i class="glyphicon glyphicon-trash"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="editOne"  data-id='+ row.id +'><i class="glyphicon glyphicon-edit"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="editMneuTree"  data-id='+ row.id +'><i class="fa fa-th-list"></i></button> ';
                }
            }
        ],
        initComplete:initComplete
    });

    //表格加载渲染完毕，加载功能按钮
    function initComplete(){
        //功能按钮
      /*  var topPlugin = '<button class="btn btn-primary btn-sm" id="addOne" >新 增</button> ' +
            '<button class="btn btn-primary btn-sm" id="reset">重置搜索条件</button>' ;*/
      var topPlugin = '<div class="btn-group"> ' +
          '<button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bars"></i> Export Table Data</button> ' +
          '<ul class="dropdown-menu " role="menu"> ' +
          '<li><a href="#" id="excel"> <img src="icons/xls.png" width="24px"> XLS</a></li> ' +
        '<li><a href="#"> <img src="icons/pdf.png" width="24px"> PDF</a></li> ' +
        '</ul> ' +
        '</div>';
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
            url: "/api/role/" + id,
            async:true,
            type:"DELETE",
            dataType:"json",
            cache:false,    //不允许缓存
            success: function(data){
                if(data.code == 0) {
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
    // 打开添加页面
    $(document).delegate('#addOne','click',function() {
        window.open("/role/add","_self");
    });
    // 打开编辑页面
    $(document).delegate('#editOne','click',function() {
        var id=$(this).data("id");
        window.open("/api/role/" + id,"_self");
    });
    // 打开编辑菜单树页面
    $(document).delegate('#editMneuTree','click',function() {
        var id=$(this).data("id");
        window.open("/api/menu/getMenuTree/" + id,"_self");
    });
    // 重置查询条件
    $(document).delegate('#reset','click',function() {
        $("#roleName").val("");
        $("#enabled").val("");
    });
    // 查询操作
    $(document).delegate('#search','click',function() {
        table.ajax.reload();
    });

    $(document).delegate('#excel','click',function() {
        $('#dataTable').tableExport({
            fileName: '角色信息表',
            ignoreColumn:[4],
            type:'excel',
            escape:'false'
        });
    });


});