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
        // "dom": "<l<\'#topPlugin\'>f>rt<ip><'clear'>",
        "language": {
            "url": "/static/AdminLTE-2.3.11/plugins/datatables/i18n/Chinese.json"
        },
        "ajax": {
            "url": "/api/menu/listByPage",
            "type": "POST",
            "data": function (d) {
                d.show=$("#show").val();
                d.menuName=$("#menuName").val().trim();
            }
        },
        "columns": [
            { "data": "id" },
            { "data": "parentId" },
            { "data": "menuName" },
            { "data": "href" },
            {
                "data": "icon"/*,
                "render" : function(data, type, row, meta) {
                    return data='<i class='+data+'></i>';
                }*/
            },
            { "data": "sort" },
            {
                "data": "show" ,
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
                    return	data='<button class="btn btn-primary btn-xs" id="deleteOne" data-id='+ row.id +'><i class="glyphicon glyphicon-trash"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="editOne"  data-id='+ row.id +'><i class="glyphicon glyphicon-edit"></i></button> ' +
                        '<button class="btn btn-primary btn-xs" id="detailOne" title="详情" data-id='+ row.id +'><i class="glyphicon glyphicon-th"></i></button> ';
                }
            }
        ],
        // initComplete:initComplete
    });

    //表格加载渲染完毕，加载功能按钮
    function initComplete(){
        //功能按钮
        var topPlugin = '<button class="btn btn-primary btn-sm" id="addOne" >新 增</button> ' +
            '<button class="btn btn-warning btn-sm" id="reset">重置搜索条件</button>' ;
        //在表格上方topPlugin DIV中追加HTML
        $("#topPlugin").append(topPlugin);
    }


    // 打开添加页面
    $(document).delegate('#addOne','click',function() {
        // $('#addOneModal').modal('show');
        window.open("/menu/add","_self");
    });
    // 打开编辑页面
    $(document).delegate('#editOne','click',function() {
        var id=$(this).data("id");
        window.open("/api/menu/" + id,"_self");
    });
    // 打开详情页面
    $(document).delegate('#detailOne','click',function() {
        var id = $(this).data("id");
        var index = layer.open({
            type: 2,
            title: '菜单详情',
            shadeClose: false,
            shade: 0.8,
            area: ['830px', '450px'],
            content: '/api/menu/detail/' + id
        });
    });
    // 重置查询条件
    $(document).delegate('#reset','click',function() {
        $("#menuName").val("");
        $("#enabled").val("");
    });
    // 查询操作
    $(document).delegate('#search','click',function() {
        table.ajax.reload();
    });
    //单行删除操作
    $(document).delegate('#deleteOne','click',function() {
        var id = $(this).data("id");
        layer.confirm('您确定要删除当前信息吗？?', {icon: 3, title:'提示信息'}, function(index){
            $.ajax({
                url: "/api/user/"+id,
                async:true,
                type:"DELETE",
                dataType:"json",
                cache:false,    //不允许缓存
                success: function(data) {
                    layer.msg(data.message, {time: 2000},function(){
                        table.ajax.reload();
                        layer.close(index);
                    });
                },
                error: function () {
                    layer.msg("数据异常", {time: 2000},function(){
                        layer.close(index);
                    });
                }
            });
        });
    });
});