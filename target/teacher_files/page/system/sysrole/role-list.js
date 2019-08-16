layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/role_findBySysAdmin.action',//数据接口
        height : "full-125",//容器高度
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            {field: 'roleId', title: '编号', minWidth:200, align:'center'},
            {field: 'roleName', title: '角色名称', minWidth:200, align:"center"},
            {field:'canLook', title:'是否允许其他用户查看', width:200, templet: '#checkboxTpl', unresize: true},
            {title: '操作', minWidth:175, templet:'#infoListBar',fixed:"right",align:"center"}
        ]]
    });

    //监听锁定操作
    form.on('checkbox(lockDemo)', function(obj){
        var thisData = obj.value.split(',');
        $.post("/teacher_files_war/role_update.action",{
            roleId : thisData[0],  //参数
            roleName : thisData[1],
            canLook : obj.elem.checked ? 1 : 0
        })
    });

    $(".addBtn").click(function(){
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("/teacher_files_war/role_delete.action",{
                    roleId : data.roleId  //参数
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        } else if (layEvent === 'role_menu') {//角色菜单控制
            editMenu(data);
        }
    });

    //批量删除
    $(".delBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].matchId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher_files_war/role_deleteBatch.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "role-add.html",
            maxmin: true,
            area: ['800px', '300px'],
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.roleId);
                    body.find(".roleName").val(edit.roleName);
                    body.find(".canLook input[value=" + edit.canLook + "]").prop("checked", "checked");  //单选框选中
                    body.find(".updateFlag").val(1);//更新
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //角色菜单控制
    function editMenu(edit){
        var index = layui.layer.open({
            title : "角色菜单控制",
            type : 2,
            content : "role-menu.html",
            maxmin: true,
            area: ['800px', '500px'],
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.roleId);
                    body.find(".roleName").val(edit.roleName);
                    body.find(".canLook input[value=" + edit.canLook + "]").prop("checked", "checked");  //单选框选中

                    var menuIds = [];
                    $.each(edit.menuSet, function (index, item) {
                        menuIds.push(item.menuId);//将选中的值添加到数组chk_value中
                    });
                    body.find(".menuHide").val(menuIds.join(','));//菜单集合
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    };
});