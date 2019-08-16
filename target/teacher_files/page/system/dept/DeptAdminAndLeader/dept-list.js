layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //非系统管理员，不能查看全部部门
    var deptId = window.sessionStorage.getItem("deptId");
    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/dept_findByDept.action?deptId='+deptId,//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            // {field: 'deptId', title: 'Id', align: 'center', width: 10},
            {field: 'deptName', title: '部门名称', minWidth: 100, align: 'center'},
            {field: 'parent', title: '上一级部门', minWidth: 100, align: "center", templet: function (d) {
                    return d.parent == null ? '无' : d.parent.deptName;
                }
            },
            {field: 'deptPerson', title: '部门负责人', minWidth:100, align:'center'},
            {field: 'deptPhone', title: '部门电话', minWidth:100, align:'center'},
            // {field: 'children', title: '从属部门', minWidth: 100, align: "center", templet: function (d) {
            //         if (d.children.length == 0) return '无';
            //         else {
            //             var childrenName = [];
            //             for (var childrens of d.children) {
            //                 childrenName.push(childrens.deptName);
            //             }
            //             return childrenName.join(',');}}},
            {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    $(".addBtn").click(function () {
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此部门？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/teacher_files_war/biz/dept_delete.action", {
                    deptId: data.deptId  //将需要删除的deptId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

    //添加用户
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "dept-add.html",
            maxmin: true,
            area: ['800px', '350px'],
            btn: ['确定', '取消'],
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.deptId);
                    body.find(".deptName").val(edit.deptName).prop("disabled", true);//名称
                    body.find(".deptPhone").val(edit.deptPhone);//部门电话
                    body.find(".deptPerson").val(edit.deptPerson);
                    body.find(".parentHide").val(edit.parent==null ? '':edit.parent.deptId);//使用中间变量保存上级部门id
                    body.find(".updateFlag").val(1);//更新
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        })
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
});