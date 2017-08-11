$(document).ready(function () {
    var table = $("#dataTable").DataTable({
        "paging": true,        //开启表格分页
        "lengthChange" : false,//是否允许用户改变表格每页显示的记录数
        "searching": false,   //禁用搜索
        "ordering": false,    //全局禁用排序
        "deferRender":true,   //延迟渲染
        "dom": "<l<\'#topPlugin\'>f>rt<ip><'clear'>",
        "buttons" : [
            'copy', 'excel', 'pdf'
        ],
        "language": {
            "url": "/static/AdminLTE-2.3.11/plugins/datatables/i18n/Chinese.json"
        },
        "ajax": "/static/objects.txt",
        "columns": [
            {
                "data" : "id",
                "render" : function(data, type, full, meta) {
                    return '<input type="checkbox" value="'+ data + '" name="id"/>';
                }
            },
            { "data": "id" },
            { "data": "username" },
            { "data": "userNameCn" },
            { "data": "userNameEn" },
            {
                "data": "enabled" ,
                "render" : function(data, type, full, meta) {
                    if(data == 1){
                        data ="<a href='#' class='upOrderStatus' data-id="+full.id+">可用</a>";
                    }else{
                        data ="<a href='#' class='upOrderStatus' data-id="+full.id+"><font color='red'>不可用</font></a>";
                    }
                    return	 data;
                }
            },
            { "data": "remarks" },
            {
                "data" : "id",
                "render":function(data, type, full, meta){
                    return	data='<button id="deleteOne" class="btn btn-danger btn-xs" data-id='+ data +'>删 除</button>';

                }
            }
        ],
        initComplete:initComplete,
        drawCallback: function( settings ) {
            $('input[name=allChecked]')[0].checked = false;//取消全选状态
        }
    });

    //表格加载渲染完毕，加载功能按钮
    function initComplete(){
        //功能按钮
        var topPlugin='<button   class="btn btn-danger btn-sm" id="deleteAll">批量删除</button> ' +
            '<button   class="btn btn-primary btn-sm addBtn" >新 增</button> ' +
            '<iframe id="exp" style="display:none;"></iframe> ' +
            '<button  class="btn btn-info btn-sm" id="expCsv">导出全部</button> ' +
            '<button  class="btn btn-warning btn-sm" id="reset">重置搜索条件</button>' ;
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
            type:"GET",
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

    /**
     * 多选选中和取消选中,同时选中第一个单元格单选框,并联动全选单选框
     */
    $('#dataTable tbody').on('click', 'tr', function(event) {
        var allChecked=$('input[name=allChecked]')[0];//关联全选单选框
        $($(this).children()[0]).children().each(function(){
            if(this.type=="checkbox" && (!$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click"))){
                if(!this.checked){
                    this.checked = true;
                    addValue(this);
                    var selected=table.rows('.selected').data().length;//被选中的行数
                    //全选单选框的状态处理
                    var recordsDisplay=table.page.info().recordsDisplay;//搜索条件过滤后的总行数
                    var iDisplayStart=table.page.info().start;// 起始行数
                    if(selected === table.page.len()||selected === recordsDisplay||selected === (recordsDisplay - iDisplayStart)){
                        allChecked.checked = true;
                    }
                }else{
                    this.checked = false;
                    cancelValue(this);
                    allChecked.checked = false;
                }
            }
        });
        $(this).toggleClass('selected');//放在最后处理，以便给checkbox做检测
    });
    /**
     * 全选按钮被点击事件
     */
    $('input[name=allChecked]').click(function(){
        if(this.checked){
            $('#dataTable tbody tr').each(function(){
                if(!$(this).hasClass('selected')){
                    $(this).click();
                }
            });
        }else{
            $('#dataTable tbody tr').click();
        }
    });
    /**
     * 单选框被选中时将它的value放入隐藏域
     */
    function addValue(para) {
        var userIds = $("input[name=userIds]");
        if(userIds.val() === ""){
            userIds.val($(para).val());
        }else{
            userIds.val(userIds.val()+","+$(para).val());
        }
    }

    /**
     * 单选框取消选中时将它的value移除隐藏域
     */
    function cancelValue(para){
        //取消选中checkbox要做的操作
        var userIds = $("input[name=allChecked]");
        var array = userIds.val().split(",");
        userIds.val("");
        for (var i = 0; i < array.length; i++) {
            if (array[i] === $(para).val()) {
                continue;
            }
            if (userIds.val() === "") {
                userIds.val(array[i]);
            } else {
                userIds.val(userIds.val() + "," + array[i]);
            }
        }
    }

    $(document).delegate('#expCsv','click',function() {

        $("#exp").attr("src",contextPath+"/department/export.do?t=" + new Date().getTime());
    });

    $(document).delegate('.addBtn','click',function() {

        $('#myModal-add-info').modal('show');
    });
    $(document).delegate('#deleteAll','click',function() {
        var theArray=[];
        $("input[name=id]:checked").each(function() {
            theArray.push($(this).val());
        });
        if(theArray.length<1){
            alert("请至少选择一个");
        }else{
            alert(theArray);
        }

    });
    $(document).delegate('.upOrderStatus','click',function() {
        var id=$(this).data("id");
        //alert(id);
        $("#titleId").html(id);
        $('#editOrderStatus').modal("show");
    });
    $(document).delegate('#reset','click',function() {
        $("#state").val("");
        $("#deptname").val("");
        $("#startTime").val("");
        $("#endTime").val("");
    });
    $(document).delegate('.search','click',function() {
        table.ajax.reload();
    });
});